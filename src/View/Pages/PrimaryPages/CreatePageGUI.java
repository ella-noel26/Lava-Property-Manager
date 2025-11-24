package View.Pages.PrimaryPages;
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
        objectCreator.setHorizontalAlignment(SwingConstants.CENTER);*/





    }
}