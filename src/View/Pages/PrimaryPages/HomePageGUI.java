package View.Pages.PrimaryPages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.Controller;

public class HomePageGUI extends JPanel{
    Controller controller;
    public HomePageGUI(Controller controller){
        this.controller = controller;
    }

    public void start(){
        this.setBackground(new Color(255,255,255));
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(255,255,255));
        BoxLayout buttonLayout = new BoxLayout(buttons, BoxLayout.PAGE_AXIS);
        buttons.setLayout(buttonLayout);
        this.add(buttons, layout.LINE_START);

        JLabel homeText = new JLabel("Lava Property Manager");
        homeText.setForeground(new Color(100,100,100));
        homeText.setFont(new Font("SansSerif", Font.PLAIN, 60));
        JPanel homeTextPanel = new JPanel();
        homeTextPanel.setBackground(new Color(255,255,255));
        homeTextPanel.add(homeText);
        this.add(homeTextPanel, layout.CENTER);



        JButton objectCreator = new JButton();
        objectCreator.setPreferredSize(new Dimension(this.controller.getWindowWidth()/3,100));
        objectCreator.setMaximumSize(new Dimension(this.controller.getWindowWidth()/3,100));
        objectCreator.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        JLabel objectCreatorText = new JLabel("Object Creator");
        objectCreatorText.setForeground(new Color(255,255,255));
        objectCreatorText.setFont(new Font("SansSerif", Font.PLAIN, 30));
        JPanel objectCreatorTextPanel = new JPanel();
        objectCreatorTextPanel.setLayout(new BoxLayout(objectCreatorTextPanel, BoxLayout.Y_AXIS));
        objectCreatorTextPanel.setBackground(new Color(180,126,222));
        objectCreatorTextPanel.add(objectCreatorText);
        objectCreatorTextPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        objectCreator.add(objectCreatorTextPanel);
        objectCreator.setHorizontalAlignment(SwingConstants.CENTER);

        JButton search = new JButton();
        search.setPreferredSize(new Dimension(this.controller.getWindowWidth()/3,100));
        search.setMaximumSize(new Dimension(this.controller.getWindowWidth()/3,100));
        search.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        JLabel searchText = new JLabel("Search");
        searchText.setForeground(new Color(255,255,255));
        searchText.setFont(new Font("SansSerif", Font.PLAIN, 30));
        JPanel searchTextPanel = new JPanel();
        searchTextPanel.setLayout(new BoxLayout(searchTextPanel, BoxLayout.Y_AXIS));
        searchTextPanel.setBackground(new Color(180,126,222));
        searchTextPanel.add(searchText);
        searchTextPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        search.add(searchTextPanel);
        search.setHorizontalAlignment(SwingConstants.CENTER);

        JButton calendar = new JButton();
        calendar.setPreferredSize(new Dimension(this.controller.getWindowWidth()/3,100));
        calendar.setMaximumSize(new Dimension(this.controller.getWindowWidth()/3,100));
        calendar.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        JLabel calendarText = new JLabel("Calendar");
        calendarText.setForeground(new Color(255,255,255));
        calendarText.setFont(new Font("SansSerif", Font.PLAIN, 30));
        JPanel calendarTextPanel = new JPanel();
        calendarTextPanel.setLayout(new BoxLayout(calendarTextPanel, BoxLayout.Y_AXIS));
        calendarTextPanel.setBackground(new Color(180,126,222));
        calendarTextPanel.add(calendarText);
        calendarTextPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        calendar.add(calendarTextPanel);
        calendar.setHorizontalAlignment(SwingConstants.CENTER);

        JButton issues = new JButton();
        issues.setPreferredSize(new Dimension(this.controller.getWindowWidth()/3,100));
        issues.setMaximumSize(new Dimension(this.controller.getWindowWidth()/3,100));
        issues.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        JLabel issuesText = new JLabel("issues");
        issuesText.setForeground(new Color(255,255,255));
        issuesText.setFont(new Font("SansSerif", Font.PLAIN, 30));
        JPanel issuesTextPanel = new JPanel();
        issuesTextPanel.setLayout(new BoxLayout(issuesTextPanel, BoxLayout.Y_AXIS));
        issuesTextPanel.setBackground(new Color(180,126,222));
        issuesTextPanel.add(issuesText);
        issuesTextPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        issues.add(issuesTextPanel);
        issues.setHorizontalAlignment(SwingConstants.CENTER);

        JButton ratings = new JButton();
        ratings.setPreferredSize(new Dimension(this.controller.getWindowWidth()/3,100));
        ratings.setMaximumSize(new Dimension(this.controller.getWindowWidth()/3,100));
        ratings.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        JLabel ratingsText = new JLabel("Ratings");
        ratingsText.setForeground(new Color(255,255,255));
        ratingsText.setFont(new Font("SansSerif", Font.PLAIN, 30));
        JPanel ratingsTextPanel = new JPanel();
        ratingsTextPanel.setLayout(new BoxLayout(ratingsTextPanel, BoxLayout.Y_AXIS));
        ratingsTextPanel.setBackground(new Color(180,126,222));
        ratingsTextPanel.add(ratingsText);
        ratingsTextPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ratings.add(ratingsTextPanel);
        ratings.setHorizontalAlignment(SwingConstants.CENTER);



        objectCreator.setBackground(new Color(180,126,222));
        search.setBackground(new Color(180,126,222));
        calendar.setBackground(new Color(180,126,222));
        issues.setBackground(new Color(180,126,222));
        ratings.setBackground(new Color(180,126,222));


        buttons.add(Box.createRigidArea(new Dimension(this.controller.getWindowWidth()/24, this.controller.getWindowHeight()/6)));
        buttons.add(objectCreator);
        buttons.add(search);
        buttons.add(calendar);
        buttons.add(issues);
        buttons.add(ratings);
        buttons.add(Box.createRigidArea(new Dimension(0, this.controller.getWindowHeight()/6)));

        
        
        
        System.out.println("Through homepages start method");
        repaint();
    }
}
