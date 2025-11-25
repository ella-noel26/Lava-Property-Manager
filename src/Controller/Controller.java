package Controller;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Stack;

import Model.Model;
import Model.Stay;
import Model.Guest;
import Model.Issue;
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
    private GuestPageGUI guestPageGUI;
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
        this.pageStack.push(7);
    }
    
    public void save(){
        this.model.saveData();
    }

    public void back(){
        if (!pageStack.isEmpty()){
            int pageID = pageStack.pop();
            this.displayPage(pageID);
        }
    }
    
    public void displayPage(int pageID){
        view.getContentPane().removeAll();
        switch (pageID){
            case 5:
                view.getContentPane().add(this.createPageGUI, BorderLayout.CENTER);
                this.createPageGUI.start();
                view.revalidate();
                view.repaint();
                break;
            case 6:
                view.getContentPane().add(this.homePageGUI, BorderLayout.CENTER);
                this.homePageGUI.start();
                view.revalidate();
                view.repaint();
                break;
            case 7:
                view.getContentPane().add(this.searchPageGUI, BorderLayout.CENTER);
                this.searchPageGUI.start();
                view.revalidate();
                view.repaint();
                break;
        }
    }

    public void searchGuests(ArrayList<String> searchTerms){
        ArrayList<Guest> results = model.searchGuests(searchTerms.get(0), searchTerms.get(1), 
                                                      searchTerms.get(2), searchTerms.get(3));
        searchPageGUI.displayGuestResults(results);
    }

    public void searchStays(ArrayList<String> searchTerms){
        ArrayList<Stay> results = model.searchStays(searchTerms);
        searchPageGUI.displayStayResults(results);
    }

    public void searchIssues(ArrayList<String> searchTerms){
        ArrayList<Issue> results = model.searchIssues(searchTerms);
        searchPageGUI.displayIssueResults(results);
    }

    public void setView(View view){
        this.view = view;
    }

    public void setGuestPageGui(GuestPageGUI guestPageGUI){
        this.guestPageGUI = guestPageGUI;
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
        this.displayPage(7);
    }

    public void pack(){
        this.view.pack();
    }
}
/*package Controller;
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
    private GuestPageGUI guestPageGUI;
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
        this.pageStack.push(6);
    }
    
    public void save(){
        this.model.saveData();
    }

    public void back(){
        if (!pageStack.isEmpty()){
            int pageID = pageStack.pop();
            this.displayPage(pageID);
        }
    }
     public void displayPage(int pageID){
            view.getContentPane().removeAll();
            switch (pageID){
                /*case 1:
                    view.getContentPane().add(this.guestPageGUI, BorderLayout.CENTER);
                    this.guestPageGUI.start();
                    view.revalidate();
                    view.repaint();
                    break;
                case 2:
                    view.getContentPane().add(this.issuePageGUI, BorderLayout.CENTER);
                    this.issuePageGUI.start();
                    view.revalidate();
                    view.repaint();
                    break;
                case 3:
                    view.getContentPane().add(this.stayPageGUI, BorderLayout.CENTER);
                    this.stayPageGUI.start();
                    view.revalidate();
                    view.repaint();
                    break;
                case 4:
                    view.getContentPane().add(this.calendarPageGUI, BorderLayout.CENTER);
                    this.calendarPageGUI.start();
                    view.revalidate();
                    view.repaint();
                    break;
                case 5:
                    view.getContentPane().add(this.createPageGUI, BorderLayout.CENTER);
                    this.createPageGUI.start();
                    view.revalidate();
                    view.repaint();
                    break;
                case 6:
                    view.getContentPane().add(this.homePageGUI, BorderLayout.CENTER);
                    this.homePageGUI.start();
                    view.revalidate();
                    view.repaint();
                    break;
                /*case 7:
                    view.getContentPane().add(this.issuesPageGUI, BorderLayout.CENTER);
                    this.issuesPageGUI.start();
                    view.revalidate();
                    view.repaint();
                    break;
                case 8:
                    view.getContentPane().add(this.ratingsPageGUI, BorderLayout.CENTER);
                    this.ratingsPageGUI.start();
                    view.revalidate();
                    view.repaint();
                    break;
                case 9:
                    view.getContentPane().add(this.searchPageGUI, BorderLayout.CENTER);
                    this.searchPageGUI.start();
                    view.revalidate();
                    view.repaint();
                    break;
            }
        }

    public void setView(View view){
        this.view = view;
    }

    public void setGuestPageGui(GuestPageGUI guestPageGUI){
        this.guestPageGUI = guestPageGUI;
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
        this.displayPage(5);
        //view.getContentPane().add(this.createPageGUI, BorderLayout.CENTER);
        //this.createPageGUI.start();
        //view.revalidate();
        //view.repaint();
    }

    public void pack(){
        this.view.pack();
    }

}*/

