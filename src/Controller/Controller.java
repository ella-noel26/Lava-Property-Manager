package Controller;
import java.awt.BorderLayout;
import java.time.LocalDate;
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
    Stack<CurrentPage> pageStack;

    public Controller(){
        this.pageStack = new Stack<>();
        this.pageStack.push(new CurrentPage(6));
    }
    
    public void save(){
        this.model.saveData();
    }

    public void back(){
        if (pageStack.size() > 1){
            pageStack.pop(); // Remove current page
            CurrentPage prev = pageStack.peek(); // Get previous page without removing
            restorePage(prev);
        }
    }
    
    public void displayPage(int pageID){
        CurrentPage page = new CurrentPage(pageID);
        pageStack.push(page);
        restorePage(page);
    }

    private void restorePage(CurrentPage page){
        view.getContentPane().removeAll();
        int pageID = page.getPageId();
        switch (pageID){
            case 1:
                view.getContentPane().add(this.guestPageGUI, BorderLayout.CENTER);
                this.guestPageGUI.start();
                Object guestId = page.getContext("guestId");
                if (guestId != null) {
                    Guest g = model.getGuestById((Integer)guestId);
                    if (g != null) this.guestPageGUI.showGuest(g);
                }
                break;
            case 2:
                view.getContentPane().add(this.issuePageGUI, BorderLayout.CENTER);
                this.issuePageGUI.start();
                Object issueId = page.getContext("issueId");
                if (issueId != null) {
                    Issue i = model.getIssueById((Integer)issueId);
                    if (i != null) this.issuePageGUI.showIssue(i);
                }
                break;
            case 3:
                view.getContentPane().add(this.stayPageGUI, BorderLayout.CENTER);
                this.stayPageGUI.start();
                Object stayId = page.getContext("stayId");
                if (stayId != null) {
                    Stay s = model.getStayById((Integer)stayId);
                    if (s != null) this.stayPageGUI.showStay(s);
                }
                break;
            case 4:
                view.getContentPane().add(this.calendarPageGUI, BorderLayout.CENTER);
                this.calendarPageGUI.start();
                break;
            case 5:
                view.getContentPane().add(this.createPageGUI, BorderLayout.CENTER);
                this.createPageGUI.start();
                break;
            case 6:
                view.getContentPane().add(this.homePageGUI, BorderLayout.CENTER);
                this.homePageGUI.start();
                break;
            case 7:
                view.getContentPane().add(this.issuesPageGUI, BorderLayout.CENTER);
                this.issuesPageGUI.start();
                break;
            case 8:
                view.getContentPane().add(this.ratingsPageGUI, BorderLayout.CENTER);
                this.ratingsPageGUI.start();
                break;
            case 9:
                view.getContentPane().add(this.searchPageGUI, BorderLayout.CENTER);
                this.searchPageGUI.start();
                Object searchType = page.getContext("searchType");
                Object searchTerms = page.getContext("searchTerms");
                if (searchType != null && searchTerms != null) {
                    this.searchPageGUI.restoreSearch((String)searchType, (ArrayList<String>)searchTerms);
                }
                break;
        }
        view.revalidate();
        view.repaint();
    }

    public Model getModel(){
        return this.model;
    }

    public ArrayList<Stay> getStaysOnDate(LocalDate date){
        return model.getStaysOnDate(date);
    }

    public ArrayList<Guest> getTopRatedGuests(int limit){
        return model.getTopRatedGuests(limit);
    }

    public ArrayList<Guest> getLowestRatedGuests(int limit){
        return model.getLowestRatedGuests(limit);
    }

        public void updateGuest(Guest g){
        model.updateGuest(g);
    }

    public void updateStay(Stay s){
        model.updateStay(s);
    }

    public void updateIssue(Issue i){
        model.updateIssue(i);
    }

    public void openGuestPage(int guestId){
        Guest g = model.getGuestById(guestId);
        if (g == null) return;
        CurrentPage page = new CurrentPage(1);
        page.setContext("guestId", guestId);
        pageStack.push(page);
        restorePage(page);
    }

    public void openIssuePage(int issueId){
        Issue i = model.getIssueById(issueId);
        if (i == null) return;
        CurrentPage page = new CurrentPage(2);
        page.setContext("issueId", issueId);
        pageStack.push(page);
        restorePage(page);
    }


    public void openStayPage(int stayId){
        Stay stay = model.getStayById(stayId);
        if (stay == null) return;
        CurrentPage page = new CurrentPage(3);
        page.setContext("stayId", stayId);
        pageStack.push(page);
        restorePage(page);
    }

    public void searchGuests(ArrayList<String> searchTerms){
        ArrayList<Guest> results = model.searchGuests(searchTerms);
        searchPageGUI.displayGuestResults(results);
        saveSearchContext("Guest", searchTerms);
    }

    public void searchStays(ArrayList<String> searchTerms){
        ArrayList<Stay> results = model.searchStays(searchTerms);
        searchPageGUI.displayStayResults(results);
        saveSearchContext("Stay", searchTerms);
    }

    public void searchIssues(ArrayList<String> searchTerms){
        ArrayList<Issue> results = model.searchIssues(searchTerms);
        searchPageGUI.displayIssueResults(results);
        saveSearchContext("Issue", searchTerms);
    }

    private void saveSearchContext(String searchType, ArrayList<String> searchTerms){
        if (!pageStack.isEmpty()) {
            CurrentPage currentPage = pageStack.peek();
            if (currentPage.getPageId() == 9) {
                currentPage.setContext("searchType", searchType);
                currentPage.setContext("searchTerms", new ArrayList<>(searchTerms));
            }
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

    public ArrayList<Issue> getAllIssues() {
        return model.getAllIssues();
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
        this.displayPage(6);
    }

    public void pack(){
        this.view.pack();
    }
}
