package View.Pages.PrimaryPages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Controller.Controller;

public class HomePageGUI extends JPanel{
    Controller controller;
    public HomePageGUI(Controller controller){
        this.controller = controller;
    }

    public void start(){
        this.setBackground(new Color(225,157,171));
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        JPanel buttons = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttons, BoxLayout.PAGE_AXIS);
        buttons.setLayout(buttonLayout);
        this.add(buttons, layout.LINE_START);

        JButton objectCreator = new JButton("Object Creator");
        objectCreator.setPreferredSize(new Dimension(this.controller.getWindowWidth()/3,100));
        objectCreator.setMaximumSize(new Dimension(this.controller.getWindowWidth()/3,100));
        JButton search = new JButton("Search");
        JButton calendar = new JButton("Calendar");
        JButton issues = new JButton("Issues");
        buttons.add(objectCreator);
        buttons.add(search);
        buttons.add(calendar);
        buttons.add(issues);
        System.out.println("Through homepages start method");
        repaint();
    }
}
