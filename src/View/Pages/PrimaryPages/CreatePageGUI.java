package View.Pages.PrimaryPages;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Controller.Controller;


public class CreatePageGUI extends JPanel{
    private Controller controller;
    public CreatePageGUI(Controller controller){
        this.controller = controller;
    }

    public void start(){
        this.setBackground(new Color(200,200,200));
        GridLayout layout = new GridLayout(1, 2);
        
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(200, 20, 20, 20));
        String[] creationOptions = {"Guest", "Stay", "Issue"};
        JComboBox creationList = new JComboBox(creationOptions);
        comboBoxPanel.add(creationList);
        this.add(comboBoxPanel);
    }
}
