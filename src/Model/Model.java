package Model;
import com.google.gson.Gson;

import Controller.Controller;

import java.io.FileWriter;
import java.io.IOException;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class Model {
    private Controller controller;
    private ArrayList<Guest> listOfGuests;
    public Model(Controller controller){
        this.controller = controller;
        Gson gson = new Gson();
        Guest guest = new Guest("Ella", "Frandsen", 3852991133l, "ella.n.frandsen@gmail.com");
        Issue issue = new Issue("Toilet Broken", new int[]{5,17,2015}, new int[]{5,15,2015}, "Jerry broke the toilet");
        Stay stay = new Stay(new int[]{2,20,2024}, new int[]{2,22,2024}, 500.50, 1);
        issue.addParentStay(stay);
        stay.addChildIssue(issue);
        stay.addParentGuest(guest);
        guest.addChildStay(stay);

        Guest guestTwo = new Guest("Jerry", "Zheng", 3852991100l, "jerry.zhend@gmail.com");
        listOfGuests = new ArrayList<>();
        listOfGuests.add(guest);
        listOfGuests.add(guestTwo);
        //String ellaJson = gson.toJson(guest);
        String ellaJson = gson.toJson(listOfGuests);
        System.out.println(ellaJson);
        System.out.println(stay.getParentGuests());
        
        /*Guest practiceGuest = gson.fromJson(ellaJson, Guest.class);
        practiceGuest.setEmail("yourmomsahoe@gmail.com");
        System.out.println(gson.toJson(practiceGuest));
        System.out.println(practiceGuest.getFirstName());
        System.out.println("break");*/



        try (FileWriter writer = new FileWriter("data.json")){
            writer.write(ellaJson);
            writer.close();
        }
        catch(IOException error){
            System.out.println(error);
        }

        try (Scanner reader = new Scanner(new File("data.json"))){
            while (reader.hasNextLine()){
                String dataFromFile = reader.nextLine();
                System.out.println(dataFromFile + "printing");
                Guest[] fromJsonExample = gson.fromJson(dataFromFile, Guest[].class);
                System.out.println(fromJsonExample[0].getEmail());
            }
        }
        catch (IOException error){

        }

        


    }
}
