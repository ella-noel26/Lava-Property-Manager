package Controller;
import java.awt.BorderLayout;
import java.util.Stack;

import Model.Model;
import Model.Stay;
import View.View;
import View.Pages.Objects.GuestPageGUI;
import View.Pages.Objects.IssuePageGUI;
import View.Pages.Objects.StayPageGUI;
import View.Pages.PrimaryPages.CalendarPageGUI;
import View.Pages.PrimaryPages.CreatePageGUI;
import View.Pages.PrimaryPages.HomePageGUI;
import View.Pages.PrimaryPages.IssuesPageGUI;
import View.Pages.PrimaryPages.RatingsPageGUI;
import View.Pages.PrimaryPages.SearchPageGUI;

public class Controller {
    private View view;
    private GuestPageGUI guestPageGui;
    private IssuePageGUI issuePageGUI;
    private StayPageGUI stayPageGUI;
    private CalendarPageGUI calendarPageGUI;
    private CreatePageGUI createPageGUI;
    private HomePageGUI homePageGUI;
    private IssuesPageGUI issuesPageGUI;
    private RatingsPageGUI ratingsPageGUI;
    private SearchPageGUI searchPageGUI;
    private Model model;
    Stack<Integer> pageStack;

    public Controller(){
        this.pageStack = new Stack<>();
    }
    
    public void setView(View view){
        this.view = view;
    }

    public void setGuestPageGui(GuestPageGUI guestPageGUI){
        this.guestPageGui = guestPageGUI;
    }

    public void setIssuePageGUI(IssuePageGUI issuePageGUI){
        this.issuePageGUI = issuePageGUI;
    }

    public void setStayPageGUI(StayPageGUI stayPageGUI){
        this.stayPageGUI = stayPageGUI;
    }

    public void setCalendarPageGUI(CalendarPageGUI calendarPageGUI){
        this.calendarPageGUI = calendarPageGUI;
    }

    public void setCreatePageGUI(CreatePageGUI createPageGUI){
        this.createPageGUI = createPageGUI;
    }

    public void setHomePageGUI(HomePageGUI homePageGUI){
        this.homePageGUI = homePageGUI;
    }

    public void setIssuesPageGUI(IssuesPageGUI issuesPageGUI){
        this.issuesPageGUI = issuesPageGUI;
    }

    public void setRatingsPageGUI(RatingsPageGUI ratingsPageGUI){
        this.ratingsPageGUI = ratingsPageGUI;
    }

    public void setSearchPageGUI(SearchPageGUI searchPageGUI){
        this.searchPageGUI = searchPageGUI;
    }

    public void setModel(Model model){
        this.model = model;
    }

    public int getWindowWidth(){
        return this.view.getWidth();
    }

    public int getWindowHeight(){
        return this.view.getHeight();
    }

    public void start(){
        System.out.println(view.getLayout());
        //view.getContentPane().add(this.homePageGUI, BorderLayout.CENTER);
        //this.homePageGUI.start();
        view.getContentPane().add(this.createPageGUI, BorderLayout.CENTER);
        this.createPageGUI.start();
        view.revalidate();
        view.repaint();
    }

    public void pack(){
        this.view.pack();
    }


}
