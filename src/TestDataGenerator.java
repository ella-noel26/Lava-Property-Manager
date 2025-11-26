import Model.Model;
import Model.Guest;
import Model.Stay;
import Model.Issue;
import Controller.Controller;

public class TestDataGenerator {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Model model = new Model(controller);
        model.populateSampleData(); // see sample method below
        // populateSampleData calls saveData() at the end
        System.out.println("Test data written to data.json");
    }
}
