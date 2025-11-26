package View.Pages.PrimaryPages;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Controller.Controller;
import Model.Issue;

public class IssuesPageGUI extends JPanel {
    private Controller controller;
    private ArrayList<Issue> openIssues;
    private ArrayList<Issue> resolvedIssues;

    private JTable openTable;
    private DefaultTableModel openTableModel;

    private JTable resolvedTable;
    private DefaultTableModel resolvedTableModel;

    public IssuesPageGUI(Controller controller) {
        this.controller = controller;
        this.openIssues = new ArrayList<>();
        this.resolvedIssues = new ArrayList<>();
        initLayout();
    }

    private void initLayout() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // Top label
        JLabel title = new JLabel("Issues");
        this.add(title, BorderLayout.NORTH);

        // Main two-column panel
        JPanel twoCol = new JPanel(new GridLayout(1, 2, 8, 0));

        // LEFT: Open / active issues
        JPanel left = new JPanel(new BorderLayout(4, 4));
        left.setBorder(BorderFactory.createTitledBorder("Open Issues"));
        openTableModel = new DefaultTableModel(new Object[] { "Reported", "Started", "Description" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        openTable = new JTable(openTableModel);
        openTable.setFillsViewportHeight(true);
        openTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = openTable.rowAtPoint(e.getPoint());
                if (row >= 0 && row < openIssues.size()) {
                    Issue issue = openIssues.get(row);
                    controller.openIssuePage(issue.getId());
                }
            }
        });
        JScrollPane openScroll = new JScrollPane(openTable);
        left.add(openScroll, BorderLayout.CENTER);

        // RIGHT: Resolved issues
        JPanel right = new JPanel(new BorderLayout(4, 4));
        right.setBorder(BorderFactory.createTitledBorder("Resolved Issues"));
        resolvedTableModel = new DefaultTableModel(new Object[] { "Reported", "Description" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        resolvedTable = new JTable(resolvedTableModel);
        resolvedTable.setFillsViewportHeight(true);
        resolvedTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = resolvedTable.rowAtPoint(e.getPoint());
                if (row >= 0 && row < resolvedIssues.size()) {
                    Issue issue = resolvedIssues.get(row);
                    controller.openIssuePage(issue.getId());
                }
            }
        });
        JScrollPane resolvedScroll = new JScrollPane(resolvedTable);
        right.add(resolvedScroll, BorderLayout.CENTER);

        twoCol.add(left);
        twoCol.add(right);

        this.add(twoCol, BorderLayout.CENTER);

        // Make tables reasonable minimum sizes so layout behaves on resize
        openTable.setPreferredScrollableViewportSize(new Dimension(400, 300));
        resolvedTable.setPreferredScrollableViewportSize(new Dimension(300, 300));
    }

    // Call to (re)load data from model via controller
    public void refresh() {
        // clear models and lists
        openTableModel.setRowCount(0);
        resolvedTableModel.setRowCount(0);
        openIssues.clear();
        resolvedIssues.clear();

        // Get all issues from controller
        ArrayList<Issue> allIssues = controller.getAllIssues();
        if (allIssues == null) return;

        // Partition into open vs resolved
        for (Issue iss : allIssues) {
            int[] resolvedDate = iss.getResolvedDate();
            boolean isResolved = !(resolvedDate.length == 3 && resolvedDate[0] == 0 && resolvedDate[1] == 0 && resolvedDate[2] == 0);
            if (isResolved) resolvedIssues.add(iss);
            else openIssues.add(iss);
        }

        // Sort newest reported first (Issue implements Comparable ascending by reported date)
        Comparator<Issue> reportedAsc = Comparator.naturalOrder();
        Collections.sort(openIssues, reportedAsc);
        Collections.sort(resolvedIssues, reportedAsc);
        Collections.reverse(openIssues);
        Collections.reverse(resolvedIssues);

        // Populate open table: Reported, Started, Description
        for (Issue iss : openIssues) {
            String reported = formatDate(iss.getReportedDate());
            String started = formatDate(iss.getStartedDate());
            String desc = safeString(iss.getDescription());
            openTableModel.addRow(new Object[] { reported, started, desc });
        }

        // Populate resolved table: Reported, Description
        for (Issue iss : resolvedIssues) {
            String reported = formatDate(iss.getReportedDate());
            String desc = safeString(iss.getDescription());
            resolvedTableModel.addRow(new Object[] { reported, desc });
        }
    }

    private String safeString(String s) {
        return s == null ? "" : s;
    }

    private String formatDate(int[] date) {
        if (date == null || date.length < 3) return "";
        // If model uses {0,0,0} for unresolved, we still show 0/0/0 as blank
        if (date[0] == 0 && date[1] == 0 && date[2] == 0) return "";
        return date[0] + "/" + date[1] + "/" + date[2];
    }

    // Called by controller when this page is displayed
    public void start() {
        refresh();
    }
}
