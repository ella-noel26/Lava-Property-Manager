package View.Pages.Objects;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import Controller.Controller;
import Model.Issue;

public class IssuePageGUI extends JPanel{
    private Controller controller;
    private Issue current;
    private Issue originalCopy;

    private JLabel idLabel;
    private JTextField titleField;
    private JTextField stayIdField;
    private JTextArea descArea;
    private JTextField reportedField;
    private JTextField startedField;
    private JTextField resolvedField;
    private JLabel statusLabel;

    public IssuePageGUI(Controller controller){
        this.controller = controller;
    }

    public void start(){
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Issue ID:"), gbc);
        idLabel = new JLabel(); gbc.gridx = 1; form.add(idLabel, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Title:"), gbc);
        titleField = new JTextField(); gbc.gridx = 1; form.add(titleField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Stay ID:"), gbc);
        stayIdField = new JTextField(); gbc.gridx = 1; form.add(stayIdField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Description:"), gbc);
        descArea = new JTextArea(4,20); JScrollPane sc = new JScrollPane(descArea); gbc.gridx = 1; form.add(sc, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Reported (M/D/YYYY):"), gbc);
        reportedField = new JTextField(); gbc.gridx = 1; form.add(reportedField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Started (M/D/YYYY):"), gbc);
        startedField = new JTextField(); gbc.gridx = 1; form.add(startedField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Resolved (M/D/YYYY) - optional:"), gbc);
        resolvedField = new JTextField(); gbc.gridx = 1; form.add(resolvedField, gbc);

        this.add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton resetBtn = new JButton("Reset");
        JButton saveBtn = new JButton("Save");
        statusLabel = new JLabel(" ");
        buttons.add(statusLabel);
        buttons.add(resetBtn);
        buttons.add(saveBtn);
        this.add(buttons, BorderLayout.SOUTH);

        resetBtn.addActionListener(e -> resetToOriginal());
        saveBtn.addActionListener(e -> saveChanges());

        revalidate();
        repaint();
    }

    public void showIssue(Issue i){
        this.current = i;
        this.originalCopy = cloneIssue(i);
        populateFromIssue(i);
    }

    private void populateFromIssue(Issue i){
        idLabel.setText(String.valueOf(i.getId()));
        titleField.setText(i.getTitle());
        stayIdField.setText(String.valueOf(i.getParentStayId()));
        descArea.setText(i.getDescription());
        int[] r = i.getReportedDate(); reportedField.setText(r[0] + "/" + r[1] + "/" + r[2]);
        int[] s = i.getStartedDate(); startedField.setText(s[0] + "/" + s[1] + "/" + s[2]);
        int[] res = i.getResolvedDate(); resolvedField.setText((res[0]==0) ? "" : res[0] + "/" + res[1] + "/" + res[2]);
        statusLabel.setText(" ");
    }

    private Issue cloneIssue(Issue i){
        Issue c = new Issue(i.getTitle(), i.getReportedDate(), i.getStartedDate(), i.getDescription());
        if (i.getResolvedDate()[0] != 0) {
            c = new Issue(i.getTitle(), i.getReportedDate(), i.getStartedDate(), i.getResolvedDate(), i.getDescription());
        }
        c.setId(i.getId());
        c.setParentStayId(i.getParentStayId());
        return c;
    }

    private int[] parseDate(String s){
        if (s == null || s.trim().isEmpty()) return null;
        String[] parts = s.split("/");
        if (parts.length != 3) throw new IllegalArgumentException("Date must be M/D/YYYY");
        int m = Integer.parseInt(parts[0].trim());
        int d = Integer.parseInt(parts[1].trim());
        int y = Integer.parseInt(parts[2].trim());
        LocalDate.of(y,m,d);
        return new int[]{m,d,y};
    }

    private void resetToOriginal(){
        if (originalCopy != null){
            populateFromIssue(originalCopy);
            this.current = controller.getModel().getIssueById(originalCopy.getId());
            statusLabel.setText("Reset");
        }
    }

    private void saveChanges(){
        try {
            String title = titleField.getText().trim();
            int stayId = Integer.parseInt(stayIdField.getText().trim());
            String desc = descArea.getText();
            int[] rep = parseDate(reportedField.getText().trim());
            int[] start = parseDate(startedField.getText().trim());
            int[] res = parseDate(resolvedField.getText().trim());

            current.setTitle(title);
            current.setParentStayId(stayId);
            current.setDescription(desc);
            current.setReportedDate(rep);
            current.setStartedDate(start);
            if (res != null) current.setResolvedDate(res);

            controller.updateIssue(current);
            controller.save();
            originalCopy = cloneIssue(current);
            statusLabel.setText("Saved");
        } catch (Exception ex){
            statusLabel.setText("Error: " + ex.getMessage());
        }
    }
}

