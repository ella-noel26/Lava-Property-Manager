/*package View.Pages.PrimaryPages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Controller.Controller;


public class CreatePageGUI extends JPanel{
    private Controller controller;
    private enum creating{
        GUEST,
        STAY,
        ISSUE
    }
    public CreatePageGUI(Controller controller){
        this.controller = controller;
    }

    public void start(){
        this.setBackground(new Color(255,255,255));
        //GridLayout layout = new GridLayout(1, 2);
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(200, 150, 20, 20));
        comboBoxPanel.setBackground(new Color(255,255,255));
        String[] creationOptions = {"Guest", "Stay", "Issue"};
        JComboBox creationList = new JComboBox(creationOptions);
        creationList.setFont(new Font("SansSerif", Font.PLAIN, 50));
        creationList.setPreferredSize(new Dimension(200,200));
        creationList.setMaximumSize(new Dimension(200,200));
        comboBoxPanel.add(creationList);
        this.add(comboBoxPanel, BorderLayout.LINE_START);

        JPanel dataSide = new JPanel();
        BorderLayout dataSideLayout = new BorderLayout();
        dataSide.setLayout(dataSideLayout);

        JPanel dataTitles = new JPanel();
        BoxLayout dataTitlesLayout = new BoxLayout(dataTitles, BoxLayout.PAGE_AXIS);
        dataTitles.setLayout(dataTitlesLayout);
        //dataTitles.setAlignmentX(Component.LEFT_ALIGNMENT);

        dataTitles.add(Box.createRigidArea(new Dimension(0, this.controller.getWindowHeight()/4)));
        
        JLabel guestFirstNameLabel = new JLabel("First Name:");
        guestFirstNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        JPanel guestFirstNameLabelPanel = new JPanel();
        guestFirstNameLabelPanel.add(guestFirstNameLabel);
        guestFirstNameLabelPanel.setBackground(new Color(255,255,255));
        //guestFirstNameLabelPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        dataTitles.add(guestFirstNameLabelPanel);

        JLabel guestLastNameLabel = new JLabel("Last Name:");
        guestLastNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        JPanel guestLastNameLabelPanel = new JPanel();
        guestLastNameLabelPanel.add(guestLastNameLabel);
        guestLastNameLabelPanel.setBackground(new Color(255,255,255));
        //guestLastNameLabelPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        dataTitles.add(guestLastNameLabelPanel);

        JLabel guestPhoneNumberLabel = new JLabel("Phone Number:");
        guestPhoneNumberLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        JPanel guestPhoneNumberLabelPanel = new JPanel();
        guestPhoneNumberLabelPanel.add(guestPhoneNumberLabel);
        guestPhoneNumberLabelPanel.setBackground(new Color(255,255,255));
        //guestPhoneNumberLabelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dataTitles.add(guestPhoneNumberLabelPanel);

        JLabel guestEmailLabel = new JLabel("Email:");
        guestEmailLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        JPanel guestEmailLabelPanel = new JPanel();
        guestEmailLabelPanel.add(guestEmailLabel);
        guestEmailLabelPanel.setBackground(new Color(255,255,255));
        guestEmailLabelPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
        //guestEmailLabelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dataTitles.add(guestEmailLabelPanel);
        dataTitles.add(Box.createRigidArea(new Dimension(0, (this.controller.getWindowHeight()/6)*2)));

        dataSide.add(dataTitles, BorderLayout.LINE_START);
        dataTitles.setBackground(new Color(255,255,255));

        JPanel dataEntries = new JPanel();
        dataEntries.setBackground(new Color(255,255,255));
        BoxLayout dataEntriesLayout = new BoxLayout(dataEntries, BoxLayout.PAGE_AXIS);
        dataEntries.setLayout(dataEntriesLayout);
        dataEntries.add(Box.createRigidArea(new Dimension(0, this.controller.getWindowHeight()/4)));        
        JTextField firstNameEntry = new JTextField(20);
        firstNameEntry.setFont(new Font("SansSerif", Font.PLAIN, 30));
        firstNameEntry.setBackground(new Color(240,240,240));
        dataEntries.add(firstNameEntry);
        JTextField lastNameEntry = new JTextField(20);
        lastNameEntry.setFont(new Font("SansSerif", Font.PLAIN, 30));
        lastNameEntry.setBackground(new Color(240,240,240));
        dataEntries.add(lastNameEntry);
        JTextField phoneNumberEntry = new JTextField(20);
        phoneNumberEntry.setFont(new Font("SansSerif", Font.PLAIN, 30));
        phoneNumberEntry.setBackground(new Color(240,240,240));
        dataEntries.add(phoneNumberEntry);
        JTextField emailEntry = new JTextField(20);
        emailEntry.setFont(new Font("SansSerif", Font.PLAIN, 30));
        emailEntry.setBackground(new Color(240,240,240));
        dataEntries.add(emailEntry);
        dataEntries.add(Box.createRigidArea(new Dimension(0, (this.controller.getWindowHeight()/6)*2)));

        dataSide.add(dataEntries, BorderLayout.CENTER);

        this.add(dataSide, BorderLayout.CENTER);

        //JButton objectCreator = new JButton();
        //objectCreator.setPreferredSize(new Dimension(this.controller.getWindowWidth()/3,100));
        //objectCreator.setMaximumSize(new Dimension(this.controller.getWindowWidth()/3,100));
        //objectCreator.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        /*JLabel objectCreatorText = new JLabel("Object Creator");
        objectCreatorText.setForeground(new Color(255,255,255));
        objectCreatorText.setFont(new Font("SansSerif", Font.PLAIN, 30));
        JPanel objectCreatorTextPanel = new JPanel();
        objectCreatorTextPanel.setLayout(new BoxLayout(objectCreatorTextPanel, BoxLayout.Y_AXIS));
        objectCreatorTextPanel.setBackground(new Color(180,126,222));
        objectCreatorTextPanel.add(objectCreatorText);
        objectCreatorTextPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        objectCreator.add(objectCreatorTextPanel);
        objectCreator.setHorizontalAlignment(SwingConstants.CENTER);





    }
}*/

// ...existing code...
package View.Pages.PrimaryPages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.Guest;
import Model.Stay;
import Model.Issue;

public class CreatePageGUI extends JPanel {
    private final Controller controller;

    // Common UI
    private JComboBox<String> typeCombo;
    private CardLayout formLayout;
    private JPanel formCards;
    private JLabel statusLabel;

    // Guest fields
    private JTextField gFirstName;
    private JTextField gLastName;
    private JTextField gPhone;
    private JTextField gEmail;

    // Stay fields
    private JTextField sGuestId;
    private JTextField sCheckIn;
    private JTextField sCheckOut;
    private JTextField sPrice;
    private JTextField sLocation; // 1 or 2

    // Issue fields
    private JTextField iTitle;
    private JTextField iStayId;
    private JTextField iDescription;
    private JTextField iReportedDate;
    private JTextField iStartedDate;
    private JTextField iResolvedDate;

    public CreatePageGUI(Controller controller){
        this.controller = controller;
    }

    public void start(){
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        split.setResizeWeight(0.25);
        split.setBorder(null);

        // Left: type selector and help
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBackground(Color.WHITE);
        left.setBorder(new EmptyBorder(20,20,20,20));

        JLabel lbl = new JLabel("Create:");
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 18f));
        left.add(lbl);
        left.add(Box.createRigidArea(new Dimension(0,10)));

        String[] options = {"Guest","Stay","Issue"};
        typeCombo = new JComboBox<>(options);
        typeCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        left.add(typeCombo);
        left.add(Box.createRigidArea(new Dimension(0,20)));

        JLabel help = new JLabel("<html><small>Enter data on the right. Dates format: M/D/YYYY.</small></html>");
        help.setAlignmentX(Component.LEFT_ALIGNMENT);
        left.add(help);
        left.add(Box.createVerticalGlue());

        split.setLeftComponent(left);

        // Right: forms
        formLayout = new CardLayout();
        formCards = new JPanel(formLayout);
        formCards.setBackground(Color.WHITE);

        formCards.add(buildGuestForm(), "Guest");
        formCards.add(buildStayForm(), "Stay");
        formCards.add(buildIssueForm(), "Issue");

        split.setRightComponent(formCards);

        this.add(split, BorderLayout.CENTER);

        // Bottom: actions / status
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(Color.WHITE);
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> onSave());
        bottom.add(saveBtn, BorderLayout.EAST);

        statusLabel = new JLabel(" ");
        statusLabel.setBorder(new EmptyBorder(4,8,4,8));
        bottom.add(statusLabel, BorderLayout.CENTER);

        this.add(bottom, BorderLayout.SOUTH);

        typeCombo.addActionListener(e -> {
            String sel = (String) typeCombo.getSelectedItem();
            formLayout.show(formCards, sel);
            clearStatus();
        });

        formLayout.show(formCards, "Guest");
        revalidate();
        repaint();
    }

    private JPanel buildGuestForm(){
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        GridBagConstraints gbc = formGbc();

        int row = 0;
        gbc.gridy = row++;
        p.add(new JLabel("First Name:"), gbc);
        gFirstName = new JTextField();
        gbc.gridx = 1; p.add(gFirstName, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Last Name:"), gbc);
        gLastName = new JTextField();
        gbc.gridx = 1; p.add(gLastName, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Phone Number:"), gbc);
        gPhone = new JTextField();
        gbc.gridx = 1; p.add(gPhone, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Email:"), gbc);
        gEmail = new JTextField();
        gbc.gridx = 1; p.add(gEmail, gbc); gbc.gridx = 0;

        return p;
    }

    private JPanel buildStayForm(){
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        GridBagConstraints gbc = formGbc();

        int row = 0;
        gbc.gridy = row++;
        p.add(new JLabel("Guest ID:"), gbc);
        sGuestId = new JTextField();
        gbc.gridx = 1; p.add(sGuestId, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Check-in (M/D/YYYY):"), gbc);
        sCheckIn = new JTextField();
        gbc.gridx = 1; p.add(sCheckIn, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Check-out (M/D/YYYY):"), gbc);
        sCheckOut = new JTextField();
        gbc.gridx = 1; p.add(sCheckOut, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Price:"), gbc);
        sPrice = new JTextField();
        gbc.gridx = 1; p.add(sPrice, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Location (1 Lodge, 2 Bunkhouse):"), gbc);
        sLocation = new JTextField();
        gbc.gridx = 1; p.add(sLocation, gbc); gbc.gridx = 0;

        return p;
    }

    private JPanel buildIssueForm(){
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        GridBagConstraints gbc = formGbc();

        int row = 0;
        gbc.gridy = row++;
        p.add(new JLabel("Title:"), gbc);
        iTitle = new JTextField();
        gbc.gridx = 1; p.add(iTitle, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Stay ID:"), gbc);
        iStayId = new JTextField();
        gbc.gridx = 1; p.add(iStayId, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Description:"), gbc);
        iDescription = new JTextField();
        gbc.gridx = 1; p.add(iDescription, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Reported Date (M/D/YYYY):"), gbc);
        iReportedDate = new JTextField();
        gbc.gridx = 1; p.add(iReportedDate, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Started Date (M/D/YYYY):"), gbc);
        iStartedDate = new JTextField();
        gbc.gridx = 1; p.add(iStartedDate, gbc); gbc.gridx = 0;

        gbc.gridy = row++;
        p.add(new JLabel("Resolved Date (M/D/YYYY) - optional:"), gbc);
        iResolvedDate = new JTextField();
        gbc.gridx = 1; p.add(iResolvedDate, gbc); gbc.gridx = 0;

        return p;
    }

    private GridBagConstraints formGbc(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(6,6,6,6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.3;
        return gbc;
    }

    private void onSave(){
        clearStatus();
        String type = (String) typeCombo.getSelectedItem();
        try {
            if ("Guest".equals(type)) {
                createGuest();
            } else if ("Stay".equals(type)) {
                createStay();
            } else if ("Issue".equals(type)) {
                createIssue();
            }
        } catch (Exception ex) {
            setStatus("Error: " + ex.getMessage(), Color.RED);
            return;
        }
        setStatus("Saved "+type, new Color(20,120,20));
        clearFormFields(type);
        controller.save();
    }

    private void createGuest(){
        String fn = gFirstName.getText().trim();
        String ln = gLastName.getText().trim();
        String ph = gPhone.getText().trim();
        String em = gEmail.getText().trim();
        if (fn.isEmpty() || ln.isEmpty()) throw new IllegalArgumentException("Name required");
        long phone = 0;
        if (!ph.isEmpty()) phone = Long.parseLong(ph);
        Guest g = new Guest(fn, ln, phone, em);
        controller.getModel().addGuest(g); // uses model via controller getter
    }

    private void createStay(){
        String guestIdStr = sGuestId.getText().trim();
        String in = sCheckIn.getText().trim();
        String out = sCheckOut.getText().trim();
        String priceStr = sPrice.getText().trim();
        String locStr = sLocation.getText().trim();

        if (guestIdStr.isEmpty() || in.isEmpty() || out.isEmpty()) throw new IllegalArgumentException("Guest ID and dates required");
        int guestId = Integer.parseInt(guestIdStr);
        int[] inDate = parseDate(in);
        int[] outDate = parseDate(out);
        double price = priceStr.isEmpty() ? 0.0 : Double.parseDouble(priceStr);
        int loc = locStr.isEmpty() ? 1 : Integer.parseInt(locStr);

        Stay s = new Stay(inDate, outDate, price, loc);
        controller.getModel().addStay(s, guestId);
        // link stay to guest if guest exists
        Guest g = controller.getModel().getGuestById(guestId);
        if (g != null) g.addChildStayId(s.getId());
    }

    private void createIssue(){
        String title = iTitle.getText().trim();
        String stayIdStr = iStayId.getText().trim();
        String desc = iDescription.getText().trim();
        String rep = iReportedDate.getText().trim();
        String start = iStartedDate.getText().trim();
        String res = iResolvedDate.getText().trim();

        if (title.isEmpty() || stayIdStr.isEmpty() || rep.isEmpty() || start.isEmpty()) throw new IllegalArgumentException("Title, stay id, reported and started dates required");
        int stayId = Integer.parseInt(stayIdStr);
        int[] repDate = parseDate(rep);
        int[] startDate = parseDate(start);

        Issue issue;
        if (res.isEmpty()) {
            issue = new Issue(title, repDate, startDate, desc);
        } else {
            int[] resDate = parseDate(res);
            issue = new Issue(title, repDate, startDate, resDate, desc);
        }

        controller.getModel().addIssue(issue, stayId);
        // link issue to stay if exists
        Stay s = controller.getModel().getStayById(stayId);
        if (s != null) s.addChildIssueId(issue.getId());
    }

    private int[] parseDate(String s){
        String[] parts = s.split("/");
        if (parts.length != 3) throw new IllegalArgumentException("Date must be M/D/YYYY");
        int m = Integer.parseInt(parts[0].trim());
        int d = Integer.parseInt(parts[1].trim());
        int y = Integer.parseInt(parts[2].trim());
        // validate with LocalDate
        LocalDate.of(y,m,d);
        return new int[]{m,d,y};
    }

    private void clearFormFields(String type){
        if ("Guest".equals(type)){
            gFirstName.setText("");
            gLastName.setText("");
            gPhone.setText("");
            gEmail.setText("");
        } else if ("Stay".equals(type)){
            sGuestId.setText("");
            sCheckIn.setText("");
            sCheckOut.setText("");
            sPrice.setText("");
            sLocation.setText("");
        } else if ("Issue".equals(type)){
            iTitle.setText("");
            iStayId.setText("");
            iDescription.setText("");
            iReportedDate.setText("");
            iStartedDate.setText("");
            iResolvedDate.setText("");
        }
    }

    private void setStatus(String text, Color color){
        statusLabel.setText(text);
        statusLabel.setForeground(color);
    }

    private void clearStatus(){
        statusLabel.setText(" ");
    }
}