package pets;
import java.util.ArrayList;
import java.util.Scanner;

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



    /*
      The main method... (not much more to say about it)
     */
    public static void main(String[] args) {

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
                /*case 3:
                    updatePetInfo(scan);
                    break;
                case 4:
                    removePet(scan);
                    break;*/
                case 5:
                    searchName(scan);
                    break;
                case 6:
                    searchAge(scan);
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        } while (choice != 7);

        scan.close();
    }

    /*
     This creates the Menu using \n (next line) for format purposes and ease of use
    */
    public static void menu() {

        System.out.println("What would you like to do?\n" + "1) View all pets\n" + "2) Add more pets\n"
                + "3) Update an existing pet\n" + "4) Remove an existing pet\n" + "5) Search pets by name\n"
                + "6) Search pets by age\n" + "7) Exit program");
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
            if (name.length() >= 11 || age > 9999){ //(less than 11 characters for name and less than 5 numbers (or 10 thousand) for number)
                System.out.println("Exceeded name and/or age length limitations.");
            }
            else {
                group.add(new Pet(name, age));
                count++;
            }

        } while (!petInfo.equalsIgnoreCase("done"));
        System.out.println(count + " pets added.");
    }

    /*
         Allows for the functionality to single out pets with that name (not case-sensitive)
        */
    private static void searchName(Scanner scan) {

        System.out.print("Enter name to search: ");
        String name = scan.nextLine();

        System.out.println("+----------------------------+");
        System.out.printf("|%4s%4s%7s%7s%4s%4s\n", "ID", "|", "NAME", "|", "AGE", "|");
        System.out.println("+----------------------------+");
        int i = 0;
        for (Pet pet : group) {

            if (pet.getName().equalsIgnoreCase(name)) {

                System.out.printf("|%4d%4s", i, pet);
                i++;
            }
        }
        System.out.println("+----------------------------+");
        System.out.println((i) + "rows in set.");

    }

    /*
      This allows for the user to search for pets using their age as a parameter
     */
    private static void searchAge(Scanner scan) {

        System.out.print("Enter age to search: ");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.println("+----------------------------+");
        System.out.printf("|%4s%4s%7s%7s%4s%4s\n", "ID", "|", "NAME", "|", "AGE", "|");
        System.out.println("+----------------------------+");
        int i = 0;
        for (Pet pet : group) {

            if (pet.getAge() == age) {

                System.out.printf("|%4d%4s", i, pet);
                i++;
            }
        }
        System.out.println("+----------------------------+");
        System.out.println((i) + "rows in set.");

    }



}
