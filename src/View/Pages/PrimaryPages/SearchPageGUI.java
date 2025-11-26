/*package View.Pages.PrimaryPages;
import javax.swing.JPanel;

import Controller.Controller;

public class SearchPageGUI extends JPanel{
    private Controller controller;
    public SearchPageGUI (Controller controller){
        this.controller = controller;
    }
}*/
package View.Pages.PrimaryPages;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import Controller.Controller;
import Model.Guest;
import Model.Stay;
import Model.Issue;

public class SearchPageGUI extends JPanel{
    private Controller controller;
    private JComboBox<String> searchTypeCombo;
    private JPanel searchCriteriaPanel;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private ArrayList<JTextField> searchFields;

    public SearchPageGUI(Controller controller){
        this.controller = controller;
        this.searchFields = new ArrayList<>();
    }

    public void start(){
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(new BorderLayout());

        // Left panel: Search type selector
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(255, 255, 255));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel typeLabel = new JLabel("Search Type:");
        typeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] searchTypes = {"Guest", "Stay", "Issue"};
        searchTypeCombo = new JComboBox<>(searchTypes);
        searchTypeCombo.setMaximumSize(new Dimension(150, 40));
        searchTypeCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchTypeCombo.addActionListener(e -> updateSearchCriteria());
        
        leftPanel.add(typeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(searchTypeCombo);
        leftPanel.add(Box.createVerticalGlue());
        
        this.add(leftPanel, BorderLayout.WEST);

        // Middle panel: Search criteria
        searchCriteriaPanel = new JPanel();
        searchCriteriaPanel.setBackground(new Color(255, 255, 255));
        searchCriteriaPanel.setLayout(new GridBagLayout());
        searchCriteriaPanel.setBorder(BorderFactory.createTitledBorder("Search Criteria"));
        
        JScrollPane criteriaScrollPane = new JScrollPane(searchCriteriaPanel);
        criteriaScrollPane.setPreferredSize(new Dimension(400, 300));
        
        JPanel middleWrapper = new JPanel(new BorderLayout());
        middleWrapper.add(criteriaScrollPane, BorderLayout.CENTER);
        
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 40));
        searchButton.addActionListener(e -> performSearch());
        middleWrapper.add(searchButton, BorderLayout.SOUTH);
        
        this.add(middleWrapper, BorderLayout.CENTER);

        // Right panel: Results table
        tableModel = new DefaultTableModel();
        resultsTable = new JTable(tableModel);
        resultsTable.setFillsViewportHeight(true);
        
        JScrollPane resultsScrollPane = new JScrollPane(resultsTable);
        resultsScrollPane.setPreferredSize(new Dimension(400, 300));
        
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        resultPanel.add(resultsScrollPane, BorderLayout.CENTER);
        
        this.add(resultPanel, BorderLayout.EAST);

        // Initialize with Guest search criteria
        updateSearchCriteria();
    }

    private void updateSearchCriteria(){
        searchCriteriaPanel.removeAll();
        searchFields.clear();
        
        String searchType = (String) searchTypeCombo.getSelectedItem();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        if ("Guest".equals(searchType)) {
            addSearchField("First Name:", gbc, 0);
            addSearchField("Last Name:", gbc, 1);
            addSearchField("Phone Number:", gbc, 2);
            addSearchField("Email:", gbc, 3);
        } else if ("Stay".equals(searchType)) {
            addSearchField("Guest ID:", gbc, 0);
            addSearchField("Check-in Date (M/D/YYYY):", gbc, 1);
            addSearchField("Check-out Date (M/D/YYYY):", gbc, 2);
            addSearchField("Price:", gbc, 3);
            addSearchField("Location: (1 Lodge, 2 Bunkhouse)", gbc, 4);

        } else if ("Issue".equals(searchType)) {
            addSearchField("Title:", gbc, 0);
            addSearchField("Stay ID:", gbc, 1);
            addSearchField("Description:", gbc, 2);
            addSearchField("Reported Date (M/D/YYYY):", gbc, 3);
            addSearchField("Started Date (M/D/YYYY):", gbc, 4);
            addSearchField("Resolved Date (M/D/YYYY):", gbc, 5);

        }

        searchCriteriaPanel.revalidate();
        searchCriteriaPanel.repaint();
    }

    private void addSearchField(String label, GridBagConstraints gbc, int row){
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        JLabel jLabel = new JLabel(label);
        searchCriteriaPanel.add(jLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField textField = new JTextField();
        searchCriteriaPanel.add(textField, gbc);
        searchFields.add(textField);
    }

    private void performSearch(){
        String searchType = (String) searchTypeCombo.getSelectedItem();
        ArrayList<String> searchTerms = new ArrayList<>();
        
        for (JTextField field : searchFields) {
            searchTerms.add(field.getText());
        }

        // Call controller to search
        if ("Guest".equals(searchType)) {
            controller.searchGuests(searchTerms);
        } else if ("Stay".equals(searchType)) {
            controller.searchStays(searchTerms);
        } else if ("Issue".equals(searchType)) {
            controller.searchIssues(searchTerms);
        }
    }

    public void displayGuestResults(ArrayList<Guest> guests){
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        //tableModel.addColumn("ID");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Email");

        for (Guest guest : guests) {
            Object[] row = {/*guest.getId(), */guest.getFirstName(), guest.getLastName(), guest.getPhoneNumber(), guest.getEmail()};
            tableModel.addRow(row);
        }
    }

    public void displayStayResults(ArrayList<Stay> stays){
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        //tableModel.addColumn("ID");
        //tableModel.addColumn("Guest ID");
        tableModel.addColumn("Check-in");
        tableModel.addColumn("Check-out");
        tableModel.addColumn("Price");
        tableModel.addColumn("Location");

        for (Stay stay : stays) {
            Object[] row = {/*stay.getId(), stay.getParentGuestId(),*/ 
                           arrayToString(stay.getStartDate()), arrayToString(stay.getEndDate()), stay.getPrice(), stay.getLocation()};
            tableModel.addRow(row);
        }
    }

    //fix   
    public void displayIssueResults(ArrayList<Issue> issues){
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        //tableModel.addColumn("ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Description");
        tableModel.addColumn("Reported Date");
        tableModel.addColumn("Started Date");
        tableModel.addColumn("Resolved Date");
        //tableModel.addColumn("Stay ID");

        for (Issue issue : issues) {
            Object[] row = {/*issue.getId(),*/ issue.getTitle(), issue.getDescription(), arrayToString(issue.getReportedDate()), arrayToString(issue.getStartedDate()), 
                arrayToString(issue.getResolvedDate()), /*issue.getParentStayId()*/};
            tableModel.addRow(row);
        }
    }

    private String arrayToString(int[] date){
        return date[0] + "/" + date[1] + "/" + date[2];
    }
}
