package View;
import javax.swing.JFrame;

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
    
    
    public View(){
        this.guestPageGui = new GuestPageGUI();
        this.issuePageGUI = new IssuePageGUI();
        this.stayPageGUI = new StayPageGUI();
        this.calendarPageGUI = new CalendarPageGUI();
        this.createPageGUI = new CreatePageGUI();
        this.homePageGUI = new HomePageGUI();
        this.issuesPageGUI = new IssuesPageGUI();
        this.ratingsPageGUI = new RatingsPageGUI();
        this.searchPageGUI = new SearchPageGUI();
        
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
