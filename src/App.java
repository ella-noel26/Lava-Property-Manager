import javax.swing.JFrame;

import Controller.Controller;
import Model.Model;
import View.View;
public class App {
    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        Model model = new Model(controller);
        View view = new View(controller);
        

        System.out.println(controller.getWindowWidth());
        System.out.println("Hello, World!");
    }
}
