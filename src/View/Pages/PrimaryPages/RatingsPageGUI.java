package View.Pages.PrimaryPages;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Controller.Controller;
import Model.Guest;

public class RatingsPageGUI extends JPanel{
    private Controller controller;
    private ArrayList<Guest> topGuests;
    private ArrayList<Guest> lowestGuests;
    private JTable topRatedTable;
    private JTable lowestRatedTable;
    private DefaultTableModel topTableModel;
    private DefaultTableModel bottomTableModel;

    public RatingsPageGUI(Controller controller){
        this.controller = controller;
        this.topGuests = new ArrayList<>();
        this.lowestGuests = new ArrayList<>();
    }

    public void start(){
        this.removeAll();
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(new BorderLayout());

        // Top section: Highest rated guests
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder("Highest Rated Guests"));
        topPanel.setBackground(new Color(255, 255, 255));

        topTableModel = new DefaultTableModel();
        topRatedTable = new JTable(topTableModel);
        topRatedTable.setFillsViewportHeight(true);
        topRatedTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = topRatedTable.rowAtPoint(e.getPoint());
                if (row >= 0 && row < topGuests.size()) {
                    Guest guest = topGuests.get(row);
                    controller.openGuestPage(guest.getId());
                }
            }
        });

        JScrollPane topScroll = new JScrollPane(topRatedTable);
        topPanel.add(topScroll, BorderLayout.CENTER);

        // Bottom section: Lowest rated guests
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Lowest Rated Guests"));
        bottomPanel.setBackground(new Color(255, 255, 255));

        bottomTableModel = new DefaultTableModel();
        lowestRatedTable = new JTable(bottomTableModel);
        lowestRatedTable.setFillsViewportHeight(true);
        lowestRatedTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = lowestRatedTable.rowAtPoint(e.getPoint());
                if (row >= 0 && row < lowestGuests.size()) {
                    Guest guest = lowestGuests.get(row);
                    controller.openGuestPage(guest.getId());
                }
            }
        });

        JScrollPane bottomScroll = new JScrollPane(lowestRatedTable);
        bottomPanel.add(bottomScroll, BorderLayout.CENTER);

        // Split pane to divide space
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        splitPane.setResizeWeight(0.5);
        this.add(splitPane, BorderLayout.CENTER);

        // Refresh data
        refreshRatings();

        revalidate();
        repaint();
    }

    public void refreshRatings(){
        // Top rated
        topGuests = controller.getTopRatedGuests(10);
        topTableModel.setRowCount(0);
        topTableModel.setColumnCount(0);
        topTableModel.addColumn("Name");
        topTableModel.addColumn("Average Rating");

        for (Guest g : topGuests) {
            Double avg = g.getAverageRating(controller.getModel());
            String rating = (avg != null) ? String.format("%.2f", avg) : "N/A";
            Object[] row = {g.getFirstName() + " " + g.getLastName(), rating};
            topTableModel.addRow(row);
        }

        // Lowest rated
        lowestGuests = controller.getLowestRatedGuests(10);
        bottomTableModel.setRowCount(0);
        bottomTableModel.setColumnCount(0);
        bottomTableModel.addColumn("Name");
        bottomTableModel.addColumn("Average Rating");

        for (Guest g : lowestGuests) {
            Double avg = g.getAverageRating(controller.getModel());
            String rating = (avg != null) ? String.format("%.2f", avg) : "N/A";
            Object[] row = {g.getFirstName() + " " + g.getLastName(), rating};
            bottomTableModel.addRow(row);
        }
    }
}
