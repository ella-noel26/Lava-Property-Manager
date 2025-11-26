package View;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Controller.Controller;
import View.Pages.Objects.GuestPageGUI;
import View.Pages.Objects.IssuePageGUI;
import View.Pages.Objects.StayPageGUI;
import View.Pages.PrimaryPages.CalendarPageGUI;
import View.Pages.PrimaryPages.CreatePageGUI;
import View.Pages.PrimaryPages.HomePageGUI;
import View.Pages.PrimaryPages.IssuesPageGUI;
import View.Pages.PrimaryPages.RatingsPageGUI;
import View.Pages.PrimaryPages.SearchPageGUI;
//import javafx.event.ActionEvent;

public class View extends JFrame implements ActionListener{
    private GuestPageGUI guestPageGui;
    private IssuePageGUI issuePageGUI;
    private StayPageGUI stayPageGUI;
    private CalendarPageGUI calendarPageGUI;
    private CreatePageGUI createPageGUI;
    private HomePageGUI homePageGUI;
    private IssuesPageGUI issuesPageGUI;
    private RatingsPageGUI ratingsPageGUI;
    private SearchPageGUI searchPageGUI;
    private Controller controller;

    private JMenuBar menu;
    private JMenu options;
    private JMenuItem saveButton;
    private JMenuItem backButton;
    private JMenuItem homeButton;
    
    
    public View(Controller controller){
        this.controller = controller;
    
        this.guestPageGui = new GuestPageGUI(this.controller);
        this.issuePageGUI = new IssuePageGUI(this.controller);
        this.stayPageGUI = new StayPageGUI(this.controller);
        this.calendarPageGUI = new CalendarPageGUI(this.controller);
        this.createPageGUI = new CreatePageGUI(this.controller);
        this.homePageGUI = new HomePageGUI(this.controller);
        this.issuesPageGUI = new IssuesPageGUI(this.controller);
        this.ratingsPageGUI = new RatingsPageGUI(this.controller);
        this.searchPageGUI = new SearchPageGUI(this.controller);
        
        this.setTitle("Lava Property Manager");
        this.setBounds(50, 50, 1200, 800);
        this.setDefaultCloseOperation(1);
        
        this.menu = new JMenuBar();
        this.options = new JMenu("Options");
        this.saveButton = new JMenuItem("Save");
        this.backButton = new JMenuItem("Back");
        this.homeButton = new JMenuItem("Home");
        this.saveButton.addActionListener(this);
        this.backButton.addActionListener(this);
        this.homeButton.addActionListener(this);
        this.options.add(saveButton);
        this.options.add(backButton);
        this.options.add(homeButton);
        this.menu.add(options);
        this.setJMenuBar(menu);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Object source = e.getSource();
        if (source == this.saveButton){
            this.controller.save();
        }
        if (source == this.backButton){
            System.out.println("Back button pressed");
            this.controller.back();
        }
        if (source == this.homeButton){
            this.controller.displayPage(6);
        }
    }

    public GuestPageGUI getGuestPageGUI(){
        return this.guestPageGui;
    }
    public IssuePageGUI getIssuePageGUI(){
        return this.issuePageGUI;
    }
    public StayPageGUI getStayPageGUI(){
        return this.stayPageGUI;
    }
    public CalendarPageGUI getCalendarPageGUI(){
        return this.calendarPageGUI;
    }
    public CreatePageGUI getCreatePageGUI(){
        return this.createPageGUI;
    }
    public HomePageGUI getHomePageGUI(){
        return this.homePageGUI;
    }
    public IssuesPageGUI getIssuesPageGUI(){
        return this.issuesPageGUI;
    }
    public RatingsPageGUI getRatingsPageGUI(){
        return this.ratingsPageGUI;
    }
    public SearchPageGUI getSearchPageGUI(){
        return this.searchPageGUI;
    }




}
