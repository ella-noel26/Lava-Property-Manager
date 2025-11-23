package View.Pages.PrimaryPages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
        buttons.setBackground(new Color(255,255,255));
        BoxLayout buttonLayout = new BoxLayout(buttons, BoxLayout.PAGE_AXIS);
        buttons.setLayout(buttonLayout);
        this.add(buttons, layout.LINE_START);



        JButton objectCreator = new JButton();
        objectCreator.setPreferredSize(new Dimension(this.controller.getWindowWidth()/3,100));
        objectCreator.setMaximumSize(new Dimension(this.controller.getWindowWidth()/3,100));
        objectCreator.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        
        
        /*JLabel objectCreatorText = new JLabel("Object Creator");
        objectCreatorText.setFont(new Font("SansSerif", Font.PLAIN, 30));
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setBackground(new Color(180,126,222));
        labelPanel.add(objectCreatorText);
        labelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        objectCreator.add(labelPanel);
        objectCreator.setHorizontalAlignment(SwingConstants.CENTER);*/

        JButton search = new JButton("Search");
        search.setPreferredSize(new Dimension(this.controller.getWindowWidth()/3,100));
        search.setMaximumSize(new Dimension(this.controller.getWindowWidth()/3,100));
        search.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));


        JButton calendar = new JButton("Calendar");
        calendar.setPreferredSize(new Dimension(this.controller.getWindowWidth()/3,100));
        calendar.setMaximumSize(new Dimension(this.controller.getWindowWidth()/3,100));
        calendar.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));

        JButton issues = new JButton("Issues");
        issues.setPreferredSize(new Dimension(this.controller.getWindowWidth()/3,100));
        issues.setMaximumSize(new Dimension(this.controller.getWindowWidth()/3,100));
        issues.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));


        objectCreator.setBackground(new Color(180,126,222));
        search.setBackground(new Color(180,126,222));
        calendar.setBackground(new Color(180,126,222));
        issues.setBackground(new Color(180,126,222));


        buttons.add(Box.createRigidArea(new Dimension(this.controller.getWindowWidth()/24, this.controller.getWindowHeight()/6)));
        buttons.add(objectCreator);
        buttons.add(search);
        buttons.add(calendar);
        buttons.add(issues);
        buttons.add(Box.createRigidArea(new Dimension(0, this.controller.getWindowHeight()/6)));

        System.out.println("Through homepages start method");
        repaint();
    }
}
