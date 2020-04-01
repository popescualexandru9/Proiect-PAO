package Etapa1;

import java.util.*;

public class Gym {
    private static final String owner = "Christian Guzman";
    private static final String name = "Alphalete";
    private static final String address = "814 SUMMER PARK DR. STAFFORD";
    private static final String mail = "support@alphaleteathletics.com";
    private static final String phoneNumber = "0731876657";
    private List<Cashier> cashiers;
    private List<Trainer> trainers ;
    private Map<String,Customer> customers = new HashMap<String,Customer>();;

    public Gym() {
        this.cashiers = new ArrayList<Cashier>();
        this.trainers = new ArrayList<Trainer>();

    }

    public static String getOwner() {
        return owner;
    }

    public static String getName() {
        return name;
    }

    public static String getAddress() {
        return address;
    }

    public static String getMail() {
        return mail;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }

    public void addCashier(Cashier cashier){
        cashiers.add(cashier);
    }

    public void addTrainer(Trainer trainer){
        trainers.add(trainer);
    }

    public boolean addCustomer(String phoneNumber,Customer customer) {
        if(customers.get(phoneNumber) != null){
            System.out.println("Customer is already in data base.");
            return false;}

        customers.put(phoneNumber,customer);
        return true;
    }

    public void modifyCustomer(String phoneNumber,Customer customer){
        customers.replace(phoneNumber,customer);
    }

}
