package View;
import javax.swing.JFrame;

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

public class View extends JFrame{
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
        this.setBounds(100, 100, 1500, 1000);
        this.setDefaultCloseOperation(1);



        this.setVisible(true);
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
