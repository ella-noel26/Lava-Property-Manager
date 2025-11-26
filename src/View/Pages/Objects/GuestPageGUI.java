package View.Pages.Objects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Controller.Controller;
import Model.Guest;

public class GuestPageGUI extends JPanel{
    private Controller controller;
    private Guest current;
    private Guest originalCopy;

    private JLabel idLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextArea descriptionArea;
    private JSpinner discountSpinner;
    private JLabel statusLabel;

    public GuestPageGUI(Controller controller){
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
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Guest ID:"), gbc);
        idLabel = new JLabel(); gbc.gridx = 1; form.add(idLabel, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("First Name:"), gbc);
        firstNameField = new JTextField(); gbc.gridx = 1; form.add(firstNameField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Last Name:"), gbc);
        lastNameField = new JTextField(); gbc.gridx = 1; form.add(lastNameField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Phone:"), gbc);
        phoneField = new JTextField(); gbc.gridx = 1; form.add(phoneField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(); gbc.gridx = 1; form.add(emailField, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Description:"), gbc);
        descriptionArea = new JTextArea(4, 20); JScrollPane sc = new JScrollPane(descriptionArea);
        gbc.gridx = 1; form.add(sc, gbc);

        row++;
        gbc.gridx = 0; gbc.gridy = row; form.add(new JLabel("Discount (0.0 - 3.0):"), gbc);
        discountSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 3.0, 0.1));
        gbc.gridx = 1; form.add(discountSpinner, gbc);

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

    public void showGuest(Guest g){
        this.current = g;
        this.originalCopy = cloneGuest(g);
        populateFromGuest(g);
    }

    private void populateFromGuest(Guest g){
        idLabel.setText(String.valueOf(g.getId()));
        firstNameField.setText(g.getFirstName());
        lastNameField.setText(g.getLastName());
        phoneField.setText(String.valueOf(g.getPhoneNumber()));
        emailField.setText(g.getEmail());
        descriptionArea.setText(g.getDescription());
        discountSpinner.setValue(g.getDiscountModifier());
        statusLabel.setText(" ");
    }

    private Guest cloneGuest(Guest g){
        Guest c = new Guest(g.getFirstName(), g.getLastName(), g.getPhoneNumber(), g.getEmail());
        c.setId(g.getId());
        c.addChildStays(g.getChildStays());
        c.setDescription(g.getDescription());
        c.setDiscountModifier(g.getDiscountModifier());
        return c;
    }

    private void resetToOriginal(){
        if (originalCopy != null){
            populateFromGuest(originalCopy);
            this.current = controller.getModel().getGuestById(originalCopy.getId());
            statusLabel.setText("Reset");
        }
    }

    private void saveChanges(){
        try {
            String fn = firstNameField.getText().trim();
            String ln = lastNameField.getText().trim();
            long phone = Long.parseLong(phoneField.getText().trim());
            String email = emailField.getText().trim();
            String desc = descriptionArea.getText();
            double discount = ((Number)discountSpinner.getValue()).doubleValue();

            current.setFirstName(fn);
            current.setLastName(ln);
            current.setPhoneNumber(phone);
            current.setEmail(email);
            current.setDescription(desc);
            current.setDiscountModifier(discount);

            controller.updateGuest(current);
            controller.save();
            originalCopy = cloneGuest(current);
            statusLabel.setText("Saved");
        } catch (Exception ex){
            statusLabel.setText("Error: " + ex.getMessage());
        }
    }
}
