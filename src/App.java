import javax.swing.JFrame;

import Controller.Controller;
import Model.Model;
import View.View;
public class App {
    public static void main(String[] args) throws Exception {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(view, view.getGuestPageGUI(), view.getIssuePageGUI(), 
        view.getStayPageGUI(), view.getCalendarPageGUI(), view.getCreatePageGUI(), view.getHomePageGUI(),
        view.getIssuesPageGUI(), view.getRatingsPageGUI(), view.getSearchPageGUI(), model);

        System.out.println("Hello, World!");
    }
}
