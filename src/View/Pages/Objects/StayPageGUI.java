package View.Pages.Objects;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import Controller.Controller;
import Model.Stay;

public class StayPageGUI extends JPanel{
    private Controller controller;
    private Stay current;
    private Stay originalCopy;

    private JLabel idLabel;
    private JTextField guestIdField;
    private JTextField checkInField;
    private JTextField checkOutField;
    private JTextField priceField;
    private JTextField locationField;
    private JComboBox<String> ratingCombo;
    private JLabel statusLabel;

    public StayPageGUI(Controller controller){
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
        gbc.weightx = 1.0;

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Stay ID:"), gbc);
        idLabel = new JLabel();
        gbc.gridx = 1; form.add(idLabel, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridx = 0; form.add(new JLabel("Guest ID:"), gbc);
        guestIdField = new JTextField(); gbc.gridx = 1; form.add(guestIdField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridx = 0; form.add(new JLabel("Check-in (M/D/YYYY):"), gbc);
        checkInField = new JTextField(); gbc.gridx = 1; form.add(checkInField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Check-out (M/D/YYYY):"), gbc);
        checkOutField = new JTextField(); gbc.gridx = 1; form.add(checkOutField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Price:"), gbc);
        priceField = new JTextField(); gbc.gridx = 1; form.add(priceField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Location (1 Lodge, 2 Bunkhouse):"), gbc);
        locationField = new JTextField(); gbc.gridx = 1; form.add(locationField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Rating (1-5):"), gbc);
        ratingCombo = new JComboBox<>(new String[]{"", "1","2","3","4","5"});
        gbc.gridx = 1; form.add(ratingCombo, gbc);

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

    public void showStay(Stay s){
        this.current = s;
        this.originalCopy = cloneStay(s);
        populateFieldsFromStay(s);
    }

    private void populateFieldsFromStay(Stay s){
        idLabel.setText(String.valueOf(s.getId()));
        guestIdField.setText(String.valueOf(s.getParentGuestId()));
        checkInField.setText(s.getStartDate()[0] + "/" + s.getStartDate()[1] + "/" + s.getStartDate()[2]);
        checkOutField.setText(s.getEndDate()[0] + "/" + s.getEndDate()[1] + "/" + s.getEndDate()[2]);
        priceField.setText(String.valueOf(s.getPrice()));
        locationField.setText(String.valueOf(s.getLocation()));
        Integer r = s.getRating();
        ratingCombo.setSelectedItem((r == null) ? "" : String.valueOf(r));
        statusLabel.setText(" ");
    }

    private Stay cloneStay(Stay s){
        Stay c = new Stay(s.getStartDate(), s.getEndDate(), s.getPrice(), s.getLocation());
        c.setId(s.getId());
        c.setParentGuestId(s.getParentGuestId());
        c.addChildIssueIds(s.getChildIssueIds());
        c.setRating(s.getRating());
        return c;
    }

    private int[] parseDate(String s){
        String[] parts = s.split("/");
        if (parts.length != 3) throw new IllegalArgumentException("Date must be M/D/YYYY");
        int m = Integer.parseInt(parts[0].trim());
        int d = Integer.parseInt(parts[1].trim());
        int y = Integer.parseInt(parts[2].trim());
        LocalDate.of(y,m,d);
        return new int[]{m,d,y};
    }

    private void resetToOriginal(){
        if (originalCopy != null) {
            populateFieldsFromStay(originalCopy);
            // reset current to model copy
            this.current = controller.getModel().getStayById(originalCopy.getId());
            statusLabel.setText("Reset");
        }
    }

    private void saveChanges(){
        try {
            int guestId = Integer.parseInt(guestIdField.getText().trim());
            int[] in = parseDate(checkInField.getText().trim());
            int[] out = parseDate(checkOutField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());
            int loc = Integer.parseInt(locationField.getText().trim());
            String ratingStr = (String) ratingCombo.getSelectedItem();
            Integer rating = (ratingStr == null || ratingStr.isEmpty()) ? null : Integer.parseInt(ratingStr);

            // update current stay instance
            current.setParentGuestId(guestId);
            current.setStartDate(in);
            current.setEndDate(out);
            current.setPrice(price);
            current.setLocation(loc);
            current.setRating(rating);

            controller.updateStay(current);
            controller.save();
            // refresh originalCopy
            this.originalCopy = cloneStay(current);
            statusLabel.setText("Saved");
        } catch (Exception ex){
            statusLabel.setText("Error: " + ex.getMessage());
        }
    }
}
