package Etapa1;

import Etapa1.Customers_and_Memberships.Customer;
import Etapa1.Employees.Cashier;
import Etapa1.Employees.Trainer;

import java.util.*;

class Gym {
    private static final String owner = "Christian Guzman";
    private static final String name = "Alphalete";
    private static final String address = "814 SUMMER PARK DR. STAFFORD";
    private static final String mail = "support@alphaleteathletics.com";
    private static final String phoneNumber = "0731876657";
    private List<Cashier> cashiers;
    private List<Trainer> trainers ;
    private Map<String, Customer> customers = new HashMap<>();

    Gym() {
        this.cashiers = new ArrayList<>();
        this.trainers = new ArrayList<>();
    }

    static String getOwner() {
        return owner;
    }

    static String getName() {
        return name;
    }

    static String getAddress() {
        return address;
    }

    static String getMail() {
        return mail;
    }

    static String getPhoneNumber() {
        return phoneNumber;
    }

    List<Cashier> getCashiers() {
        return cashiers;
    }

    List<Trainer> getTrainers() {
        return trainers;
    }

    Map<String, Customer> getCustomers() {
        return customers;
    }

    void addCashier(Cashier cashier){
        cashiers.add(cashier);
    }

    void addTrainer(Trainer trainer){
        trainers.add(trainer);
    }

    boolean addCustomer(String phoneNumber,Customer customer) {
        if(customers.get(phoneNumber) != null){
            System.out.println("Customer is already in data base.");
            return false;}

        customers.put(phoneNumber,customer);
        return true;
    }

    void modifyCustomer(String phoneNumber,Customer customer){
        customers.replace(phoneNumber,customer);
    }

}
