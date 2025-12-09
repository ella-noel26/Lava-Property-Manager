import javax.swing.JFrame;

import Controller.Controller;
import Model.Model;
import View.View;
public class App {
    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        Model model = new Model();
        View view = new View(controller);
        controller.setView(view);
        controller.setModel(model);
        controller.setGuestPageGui(view.getGuestPageGUI());
        controller.setIssuePageGUI(view.getIssuePageGUI());
        controller.setStayPageGUI(view.getStayPageGUI());
        controller.setCalendarPageGUI(view.getCalendarPageGUI());
        controller.setCreatePageGUI(view.getCreatePageGUI());
        controller.setHomePageGUI(view.getHomePageGUI());
        controller.setIssuesPageGUI(view.getIssuesPageGUI());
        controller.setRatingsPageGUI(view.getRatingsPageGUI());
        controller.setSearchPageGUI(view.getSearchPageGUI());

        controller.start();

        

        System.out.println("Hello, World!");
    }
}
