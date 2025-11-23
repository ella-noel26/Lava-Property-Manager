import javax.swing.JFrame;

import Model.Model;
public class App {
    public static void main(String[] args) throws Exception {
        Model model = new Model();
        JFrame window = new JFrame("Lava Property Manager");
        window.setBounds(100, 100, 300, 300);
        window.setDefaultCloseOperation(1);
        window.setVisible(true);
        System.out.println("Hello, World!");
    }
}
