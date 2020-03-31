package Etapa1;

import Etapa1.Customers_and_Memberships.Customer;
import Etapa1.Customers_and_Memberships.Membership;
import Etapa1.Employees.Cashier;
import Etapa1.Employees.Trainer;
import Etapa1.Files.readFile;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ActionCenter {
    private static ActionCenter single_instance = null;
    private ArrayList<Membership> memberships;
    private Gym gym;

    public static ActionCenter getInstance() throws IOException{
        if(single_instance == null)
            single_instance = new ActionCenter();
        return single_instance;
    }

    private ActionCenter() throws IOException {
        this.gym = new Gym();
        this.memberships = new ArrayList<Membership>();
        readFile in = readFile.getInstance();


        ArrayList<String []> objMembership = in.readFromFile("C:\\Users\\Popi\\OneDrive\\Facultate\\2 year\\Sem 2\\PAO\\Proiect PAO\\src\\Etapa1\\Files\\membershipsFile.csv");
        for( String [] line : objMembership) {
            Membership membership = new Membership(line[0], Integer.valueOf(line[2]), Integer.valueOf(line[1]), Integer.valueOf(line[3]));
            memberships.add(membership);
        }


        ArrayList<String []> objGym = in.readFromFile("C:\\Users\\Popi\\OneDrive\\Facultate\\2 year\\Sem 2\\PAO\\Proiect PAO\\src\\Etapa1\\Files\\trainersFile.csv");
        for( String [] line : objGym) {
            Trainer trainer = new Trainer(line[0], Float.valueOf(line[1]), line[2], Integer.valueOf(line[3]));
            gym.addTrainer(trainer);
         }

        ArrayList<String []> objCashier = in.readFromFile( "C:\\Users\\Popi\\OneDrive\\Facultate\\2 year\\Sem 2\\PAO\\Proiect PAO\\src\\Etapa1\\Files\\cashiersFile.csv");
        for( String [] line : objCashier) {
            Cashier cashier = new Cashier(line[0], Float.valueOf(line[1]), line[2]);
            gym.addCashier(cashier);
        }


        ArrayList<String []> objCustomers = in.readFromFile("C:\\Users\\Popi\\OneDrive\\Facultate\\2 year\\Sem 2\\PAO\\Proiect PAO\\src\\Etapa1\\Files\\customersFile.csv");
        for( String [] line : objCustomers) {
            String phoneNumber = line[0];
            Membership membershipCustomer = getMembership(Integer.valueOf(line[2]));
            Customer customer = new Customer(line[1], membershipCustomer, Integer.valueOf(line[3]));
            gym.addCustomer(phoneNumber, customer);
        }

    }

    public void Menu() {
        System.out.println("\nWelcome!");
        Scanner in = new Scanner(System.in);
        boolean input = true;
        displayMenu();
        do {
            System.out.println("\n--------------------------------------------------------------|");
            System.out.println("Note that you can visualize the menu again by picking '?'. ");
            System.out.print("Please pick a number : ");
            String choice = in.nextLine();
            System.out.println("--------------------------------------------------------------|\n");
            switch (choice.trim()) {
                case "1":
                    displaySortedTrainersByExperience();
                    break;
                case "2":
                    displayCashiers();
                    break;
                case "3":
                    displayMemberships();
                    break;
                case "4":
                    displayCustomers();
                    break;
                case "5":
                    try {
                        boolean ok = newCustomer();
                        if (!ok)
                            System.out.println("Error! We couldn't add the new customer.");
                    } catch (IOException ex) {
                        System.out.println("IO Exception.");
                    }
                    break;
                case "6":
                    boolean ok = existingCustomer();
                    if (!ok)
                        System.out.println("Error! We couldn't renew your membership.");
                    break;
                case "7":
                    try {
                        updateCustomerFile();
                        System.out.println("The file has been updated successfully.");
                    }catch (IOException ex){
                        System.out.println("IO Exception. We couldnt't update the file.");
                    }
                    break;
                case "8":
                    displayHours();
                    break;
                case "9":
                    displayDetails();
                    break;
                case "@":
                    boolean customer = searchByPhoneNumber();
                    if (!customer)
                        System.out.println("We don't have this phone number registered in our data base!");
                    break;
                case "!":
                    displayContactInformation();
                    break;
                case "0":
                    System.out.println("\nHave a nice day!");
                    input = false;
                    break;
                case "?":
                    displayMenu();
                    break;
                default:
                    System.out.println("Error! Make sure you picked a number between 1 and 10.");
            }
        } while (input);
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("[1] Display all the personal trainers ordered by experience.  |");
        System.out.println("[2] Display all the cashiers.                                 |");
        System.out.println("[3] Display all the memberships available.                    |");
        System.out.println("[4] Display all the members                                   |");
        System.out.println("--------------------------------------------------------------|");
        System.out.println("[5]                    Become a member!                       |");
        System.out.println("[6]                Renew your subscription.                   |");
        System.out.println("[7]                  Update members file.                     |");
        System.out.println("--------------------------------------------------------------|");
        System.out.println("[8] Display functional hours.                                 |");
        System.out.println("[9] Display details about the gym.                            |");
        System.out.println("[@] Search customer by phone number.                          |");
        System.out.println("[!] Contact information.                                      |");
        System.out.println("[0] Quit                                                      |");
    }

    private void displayMemberships() {
        String temp = " ";
        for (Membership elem : memberships) {
            if (temp.compareTo(elem.getName()) != 0)
                System.out.println();
            elem.aboutMembership();
            temp = elem.getName();
        }
    }

    private void displayCashiers() {
        System.out.println("Employees - Cashiers \n");
        for (Cashier elem : gym.getCashiers()) {
            elem.aboutEmployee();
        }
    }

    private void displayCustomers() {
        System.out.println("Alphalete Members\n");
        Map<String, Customer> customers = gym.getCustomers();
        for (String phoneNumber : customers.keySet()) {
            Customer customer = customers.get(phoneNumber);
            customer.aboutCustomer();
            System.out.println("Phone number : 0" + phoneNumber);
        }
    }

    private boolean newCustomer() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nHello! I'm glad you want to be a member of our gym. Please introduce your -->");
        System.out.print("Full name: ");
        String name = in.nextLine();
        System.out.print("\nPhone number [+40 ] ");
        String phoneNumber = in.nextLine();
        boolean verifyNumber = verifyPhoneNumber(phoneNumber);
        if (!verifyNumber)
            return false;

        displayMemberships();
        System.out.print("\nChoose a membership : ");
        int choice = in.nextInt();
        if (choice < 0 || choice > 12) {
            System.out.println("Membership id incorrect.");
            return false;
        }
        Membership membership = getMembership(choice);

        Customer customer = new Customer(name, membership, membership.getLength());
        boolean allGood = gym.addCustomer(phoneNumber, customer);
        if (allGood) {
            System.out.println("Stay beastly!");
            return true;
        } else
            return false;
    }

    private boolean existingCustomer() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nOh,you are already a member! \nPlease introduce your phone number [+40 ] ");
        String phoneNumber = in.nextLine();
        boolean verifyNumber = verifyPhoneNumber(phoneNumber);
        if (!verifyNumber)
            return false;

        Map<String, Customer> customers = gym.getCustomers();
        if(!customers.containsKey(phoneNumber)) {
            System.out.println("We don't have this phone number registered in our data base!");
            return false;
        }

        Customer customer = customers.get(phoneNumber);

        displayMemberships();
        System.out.print("\nChoose a membership : ");
        int choice = in.nextInt();
        if (choice < 0 || choice > 12) {
            System.out.println("Membership id incorrect.");
            return false;
        }
        Membership oldMembership = getMembership(choice);
        String newName= oldMembership.getName();
        double newPrice = oldMembership.getPrice();
        int newLength = oldMembership.getLength();
        double price = newPrice;

        if (customer.getFidelity() >= 3 && customer.getFidelity() < 6) {
            System.out.println("Discount 10% - Member for over 3 months.");
             price = oldMembership.getPrice()- 0.1 * oldMembership.getPrice();
        } else if (customer.getFidelity() >= 6 && customer.getFidelity() < 12) {
            System.out.println("Discount 15% - Member for over 6 months.");
             price = oldMembership.getPrice() - 0.15 * oldMembership.getPrice();
        } else if (customer.getFidelity() >= 12) {
            System.out.println("Discount 20% - Member for over a year.");
             price = oldMembership.getPrice() - 0.2 * oldMembership.getPrice();
        }

        Membership newMembership = new Membership(newName,price,newLength,choice);
        customer.setMembership(newMembership);
        if (choice < 9)
            customer.setFidelity(customer.getFidelity() + newLength);
        gym.modifyCustomer(phoneNumber, customer);
        return true;
    }

    private void displaySortedTrainersByExperience() {
        System.out.println("Employees - Personal Trainers\n");
        ArrayList<Trainer> trainers = gym.getTrainers();
        Collections.sort(trainers, new Comparator<Trainer>() {
            public int compare(Trainer o1, Trainer o2) {
                return Float.compare(o2.getExperience(), o1.getExperience());
            }
        });
        for (Trainer elem : trainers)
            elem.aboutEmployee();
    }

    private Membership getMembership(int id) {
        for (Membership elem : memberships)
            if (elem.getId() == id)
                return elem;
        return null;
    }

    private boolean verifyPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^(?=(?:[7]))(?=[0-9]{9}).*"))
            return true;
        else {
            System.out.println("Invalid format");
            return false;
        }
    }

    private void updateCustomerFile() throws IOException {
        BufferedWriter myWriter = new BufferedWriter(new FileWriter("C:\\Users\\Popi\\OneDrive\\Facultate\\2 year\\Sem 2\\PAO\\Proiect PAO\\src\\Etapa1\\Files\\customersFile.csv"));
        Map<String, Customer> customers = gym.getCustomers();
        for (String phoneNumber : customers.keySet()) {
            Customer customer = customers.get(phoneNumber);
            String toWrite = phoneNumber + "," + customer.getName() + "," + customer.getMembershipId() + "," + customer.getFidelity();

            myWriter.write(toWrite);
            myWriter.newLine();
        }
        myWriter.close();
    }

    private void displayHours(){
        System.out.println("Hours : \n");
        System.out.println("Monday - Friday : 06:00 - 01:00");
        System.out.println("Weekend : Open 24h");
    }

    private void displayDetails(){
        System.out.println(gym.getName());
        System.out.println("\nOwner : " + gym.getOwner());
        System.out.println("Adress : " + gym.getAddress() );
    }

    private void displayContactInformation(){
        System.out.println("Contact information : \n");
        System.out.println("Email : " + gym.getMail());
        System.out.println("Phone number : " + gym.getPhoneNumber());
    }

    private boolean searchByPhoneNumber() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please introduce your phone number [+40 ] ");
        String phoneNumber = in.nextLine();
        boolean verifyNumber = verifyPhoneNumber(phoneNumber);
        if (!verifyNumber)
            return false;

        Map<String, Customer> customers = gym.getCustomers();
        if(customers.containsKey(phoneNumber))
            customers.get(phoneNumber).aboutCustomer();
        return true;
    }
}

