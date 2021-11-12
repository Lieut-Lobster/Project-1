package pets;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/*
    Josiah Skorseth
    11/6/2021
    Software Engineering
    Assignment 1
 */

public class Main {
     /*
       Setting an Array list of <Pet> to 'group'
       */
    static ArrayList<Pet> group;
    static ArrayList<Object> dataList;



    /*
      The main method... (not much more to say about it)
     */
    public static void main(String[] args) throws FileNotFoundException {


        File file = new File ("out.txt");
        Scanner scanFile = new Scanner(file);



        //So i did everything that I could to load my out.txt file to my current array but no matter which way I did it, it would not tie to my original array list / 'group'
        
        //System.out.println(scanFile.next());
        //System.out.println(scanFile.nextInt());
/*
        try {
            ArrayList<String> group = new ArrayList<>(Files.readAllLines(Paths.get("out.txt")));
        }
        catch (IOException e) {
            // Handle a potential exception
        }*/





        System.out.print("Pet Database Program\n\n");
        //I do not know why it was having an issue with spacing with original \n but two did the job to make it look cleaner

        Scanner scan = new Scanner(System.in);
        group = new ArrayList<>();
        int choice;
        do {
            menu();
            System.out.print("Your choice: ");
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) { //switch cases are used for easy functionality between the methods to execute with the users next input type (INT)
                case 1:
                    viewAllPets();
                    break;
                case 2:
                    addPets(scan);
                    break;
                case 3:
                    removePet(scan);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        } while (choice != 4);

        scan.close();
    }

    /*
     This creates the Menu using \n (next line) for format purposes and ease of use
    */
    public static void menu() {

        System.out.println("What would you like to do?\n" + "1) View all pets\n" + "2) Add more pets\n"
                 + "3) Remove an existing pet\n" + "4) Exit program");
    }

    /*
              View all pets which allows the user to see each pet that is inserted
             */
    private static void viewAllPets() {

        System.out.println("+----------------------------+");
        System.out.printf("|%4s%4s%7s%7s%4s%4s\n", "ID", "|", "NAME", "|", "AGE", "|");
        System.out.println("+----------------------------+");
        int count = 0;
        for (Pet pet : group) {

            System.out.printf("|%3d%3s", count, pet.toString());

            count++;
        }
        System.out.println("+----------------------------+");
        System.out.println((count) + " rows in set.");
    }

    /*
  Add pets from the user to be displayed later with view all pets or deleting a pet
 */
    private static void addPets(Scanner scan) {
        int count = 0;
        String petInfo;
        do {

            System.out.print("add pet (name, age) or done (to leave): ");
            petInfo = scan.nextLine();
            if (petInfo.equalsIgnoreCase("done")) {

                break;
            }
            String name = petInfo.split("\\s+")[0];
            int age = Integer.parseInt(petInfo.split("\\s+")[1]);


            //This if else statement aids requirements to be met if you want your pet to be added
            if (name.length() >= 11 || age > 20 || age <= 0){ //(less than 11 characters for name and less than the age between 0 - 20 for the number)
                System.out.println("Exceeded name (10 characters or less) or the age (in-between the ages of 0 - 20).");
            }
            else { //This whole 'if else' nests are for the handling of errors and for limiting the user to the specified amounts needed to fill the pet database
                count++;
                if (count > 5) {
                    System.out.println("You have added the maximum amount of pets in the pet database!");
                }
                else {
                    group.add(new Pet(name, age));

                    String fileName = "out.txt";
                    try {
                        PrintWriter outputStream = new PrintWriter(fileName);
                        outputStream.println(group); //saves to the RAM

                        outputStream.close(); //gives data to the file (closes allowance of more data)


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

        } while (!petInfo.equalsIgnoreCase("done"));
        System.out.println(count + " pets added.");
    }


    /*
          Removes the existing pet from your list
         */
    private static void removePet(Scanner scan) {


        viewAllPets();
        System.out.print("Enter the pet ID to remove: ");
        int id = scan.nextInt();
        scan.nextLine();
        try {
            String name = group.get(id).getName();
            int age = group.get(id).getAge();
            group.remove(id);
            System.out.println(name + " " + age + " is removed.");
            String fileName = "out.txt";
            try {
                PrintWriter outputStream = new PrintWriter(fileName);
                outputStream.println(group);
                outputStream.close(); //gives data to the file (closes allowance of more data)


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nThis was not a proper ID... try again!\n");
            removePet(scan);

        }
    }


}
