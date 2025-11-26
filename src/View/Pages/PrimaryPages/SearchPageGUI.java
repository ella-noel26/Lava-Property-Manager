package View.Pages.PrimaryPages;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private ArrayList<Guest> currentGuestResults;
    private ArrayList<Stay> currentStayResults;
    private ArrayList<Issue> currentIssueResults;
    private String currentSearchType = "Guest";

    public SearchPageGUI(Controller controller){
        this.controller = controller;
        this.searchFields = new ArrayList<>();
        this.currentGuestResults = new ArrayList<>();
        this.currentStayResults = new ArrayList<>();
        this.currentIssueResults = new ArrayList<>();
    }

    public void start(){
        this.removeAll();
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
        resultsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = resultsTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    onResultClicked(row);
                }
            }
        });
        
        JScrollPane resultsScrollPane = new JScrollPane(resultsTable);
        resultsScrollPane.setPreferredSize(new Dimension(400, 300));
        
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        resultPanel.add(resultsScrollPane, BorderLayout.CENTER);
        
        this.add(resultPanel, BorderLayout.EAST);

        // Reset results every time this page is opened
        resetResultsState();
        updateSearchCriteria();

        revalidate();
        repaint();
    }

    private void resetResultsState(){
        currentGuestResults.clear();
        currentStayResults.clear();
        currentIssueResults.clear();
        clearResultsTableColumns();
    }

    private void clearResultsTableColumns(){
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        // Optional: show empty headers for the selected type
        if ("Guest".equals(currentSearchType)) {
            tableModel.addColumn("First Name");
            tableModel.addColumn("Last Name");
            tableModel.addColumn("Phone");
            tableModel.addColumn("Email");
        } else if ("Stay".equals(currentSearchType)) {
            tableModel.addColumn("Check-in");
            tableModel.addColumn("Check-out");
            tableModel.addColumn("Price");
            tableModel.addColumn("Location");
        } else if ("Issue".equals(currentSearchType)) {
            tableModel.addColumn("Title");
            tableModel.addColumn("Description");
            tableModel.addColumn("Reported Date");
            tableModel.addColumn("Started Date");
            tableModel.addColumn("Resolved Date");
        }
    }

    private void updateSearchCriteria(){
        searchCriteriaPanel.removeAll();
        searchFields.clear();
        
        currentSearchType = (String) searchTypeCombo.getSelectedItem();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        if ("Guest".equals(currentSearchType)) {
            addSearchField("First Name:", gbc, 0);
            addSearchField("Last Name:", gbc, 1);
            addSearchField("Phone:", gbc, 2);
            addSearchField("Email:", gbc, 3);
        } else if ("Stay".equals(currentSearchType)) {
            addSearchField("Guest ID:", gbc, 0);
            addSearchField("Check-in Date (M/D/YYYY):", gbc, 1);
            addSearchField("Check-out Date (M/D/YYYY):", gbc, 2);
            addSearchField("Price:", gbc, 3);
            addSearchField("Location (1 Lodge, 2 Bunkhouse):", gbc, 4);
        } else if ("Issue".equals(currentSearchType)) {
            addSearchField("Title:", gbc, 0);
            addSearchField("Stay ID:", gbc, 1);
            addSearchField("Description:", gbc, 2);
            addSearchField("Reported Date (M/D/YYYY):", gbc, 3);
            addSearchField("Started Date (M/D/YYYY):", gbc, 4);
            addSearchField("Resolved Date (M/D/YYYY):", gbc, 5);
        }

        // Clear previous results when switching types
        resetResultsState();

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

        if ("Guest".equals(searchType)) {
            controller.searchGuests(searchTerms);
        } else if ("Stay".equals(searchType)) {
            controller.searchStays(searchTerms);
        } else if ("Issue".equals(searchType)) {
            controller.searchIssues(searchTerms);
        }
    }

    private void onResultClicked(int row) {
        if ("Guest".equals(currentSearchType) && row < currentGuestResults.size()) {
            controller.openGuestPage(currentGuestResults.get(row).getId());
        } else if ("Stay".equals(currentSearchType) && row < currentStayResults.size()) {
            controller.openStayPage(currentStayResults.get(row).getId());
        } else if ("Issue".equals(currentSearchType) && row < currentIssueResults.size()) {
            controller.openIssuePage(currentIssueResults.get(row).getId());
        }
    }

    // Called by controller after a search completes
    public void displayGuestResults(ArrayList<Guest> guests){
        currentGuestResults = guests;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Email");

        for (Guest guest : guests) {
            Object[] row = {guest.getFirstName(), guest.getLastName(), guest.getPhoneNumber(), guest.getEmail()};
            tableModel.addRow(row);
        }
    }

    public void displayStayResults(ArrayList<Stay> stays){
        currentStayResults = stays;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Check-in");
        tableModel.addColumn("Check-out");
        tableModel.addColumn("Price");
        tableModel.addColumn("Location");

        for (Stay stay : stays) {
            Object[] row = {
                arrayToString(stay.getStartDate()),
                arrayToString(stay.getEndDate()),
                stay.getPrice(),
                stay.getLocation()
            };
            tableModel.addRow(row);
        }
    }

    public void displayIssueResults(ArrayList<Issue> issues){
        currentIssueResults = issues;
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Title");
        tableModel.addColumn("Description");
        tableModel.addColumn("Reported Date");
        tableModel.addColumn("Started Date");
        tableModel.addColumn("Resolved Date");

        for (Issue issue : issues) {
            Object[] row = {
                issue.getTitle(),
                issue.getDescription(),
                arrayToString(issue.getReportedDate()),
                arrayToString(issue.getStartedDate()),
                arrayToString(issue.getResolvedDate())
            };
            tableModel.addRow(row);
        }
    }

    private String arrayToString(int[] date){
        if (date == null || date.length < 3) return "";
        return date[0] + "/" + date[1] + "/" + date[2];
    }
}