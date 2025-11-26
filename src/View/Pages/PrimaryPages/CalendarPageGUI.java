package View.Pages.PrimaryPages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import Controller.Controller;
import Model.Stay;

public class CalendarPageGUI extends JPanel {
    private final Controller controller;
    private YearMonth currentMonth;
    private JPanel gridPanel;
    private JLabel monthLabel;

    private final Color LODGE_COLOR = new Color(180, 126, 222);
    private final Color BUNK_COLOR = new Color(118, 215, 89);
    private final Color BOTH_COLOR = new Color(100, 149, 237);

    public CalendarPageGUI(Controller controller){
        this.controller = controller;
        this.currentMonth = YearMonth.now();
    }

    public void start(){
        this.removeAll();
        this.setLayout(new BorderLayout());
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);

        JButton prev = new JButton("<");
        JButton next = new JButton(">");
        monthLabel = new JLabel("", SwingConstants.CENTER);
        monthLabel.setFont(monthLabel.getFont().deriveFont(Font.BOLD, 16f));

        prev.addActionListener(e -> { currentMonth = currentMonth.minusMonths(1); buildCalendar(); });
        next.addActionListener(e -> { currentMonth = currentMonth.plusMonths(1); buildCalendar(); });

        JPanel nav = new JPanel(new BorderLayout());
        nav.add(prev, BorderLayout.WEST);
        nav.add(monthLabel, BorderLayout.CENTER);
        nav.add(next, BorderLayout.EAST);
        top.add(nav, BorderLayout.CENTER);
        this.add(top, BorderLayout.NORTH);

        gridPanel = new JPanel();
        this.add(new JScrollPane(gridPanel), BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) { buildCalendar(); }
        });

        buildCalendar();
        revalidate();
        repaint();
    }

    private void buildCalendar(){
        gridPanel.removeAll();

        JPanel header = new JPanel(new GridLayout(1,7));
        String[] days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        for (String d : days){
            JLabel lbl = new JLabel(d, SwingConstants.CENTER);
            lbl.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
            header.add(lbl);
        }

        gridPanel.setLayout(new BorderLayout());
        JPanel monthGrid = new JPanel();
        monthGrid.setBackground(Color.WHITE);

        YearMonth ym = currentMonth;
        monthLabel.setText(ym.getMonth().toString() + " " + ym.getYear());

        int daysInMonth = ym.lengthOfMonth();
        LocalDate firstOfMonth = ym.atDay(1);
        int startDayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7;

        int totalCells = ((startDayOfWeek + daysInMonth) <= 28) ? 28 :
                         ((startDayOfWeek + daysInMonth) <= 35) ? 35 : 42;
        int rows = totalCells / 7;
        monthGrid.setLayout(new GridLayout(rows, 7, 4, 4));
        monthGrid.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        int dayCounter = 1 - startDayOfWeek;
        for (int i = 0; i < totalCells; i++, dayCounter++) {
            if (dayCounter <= 0 || dayCounter > daysInMonth) {
                JPanel empty = new JPanel();
                empty.setBackground(Color.WHITE);
                monthGrid.add(empty);
            } else {
                LocalDate date = ym.atDay(dayCounter);
                JButton dayBtn = new JButton(String.valueOf(dayCounter));
                dayBtn.setFocusPainted(false);
                dayBtn.setOpaque(true);
                dayBtn.setBackground(Color.WHITE);
                dayBtn.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));

                ArrayList<Stay> stays = controller.getStaysOnDate(date);
                boolean lodge = false, bunk = false;
                for (Stay s : stays) {
                    if (s.getLocation() == 1) lodge = true;
                    if (s.getLocation() == 2) bunk = true;
                }
                if (lodge && bunk) dayBtn.setBackground(BOTH_COLOR);
                else if (lodge) dayBtn.setBackground(LODGE_COLOR);
                else if (bunk) dayBtn.setBackground(BUNK_COLOR);

                LocalDate clicked = date;
                dayBtn.addActionListener(ev -> {
                    ArrayList<Stay> sList = controller.getStaysOnDate(clicked);
                    if (sList.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No bookings on " + clicked);
                        return;
                    }
                    if (sList.size() == 1) {
                        controller.openStayPage(sList.get(0).getId());
                        return;
                    }
                    String[] options = new String[sList.size()];
                    for (int idx = 0; idx < sList.size(); idx++) {
                        Stay s = sList.get(idx);
                        options[idx] = "Stay ID " + s.getId() + " (guest " + s.getParentGuestId() + ")";
                    }
                    int choice = JOptionPane.showOptionDialog(this,
                            "Multiple stays on " + clicked + ", choose one:",
                            "Choose stay",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null, options, options[0]);
                    if (choice >= 0) {
                        controller.openStayPage(sList.get(choice).getId());
                    }
                });

                monthGrid.add(dayBtn);
            }
        }

        gridPanel.add(header, BorderLayout.NORTH);
        gridPanel.add(monthGrid, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
