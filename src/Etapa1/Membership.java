package Etapa1.Customers_and_Memberships;

import java.text.DecimalFormat;

public class Membership {
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private String name;
    private double price;
    private int length;
    private String type;
    private int id;

    public Membership(String name, double price, int length,int id) {
        this.name = name;
        this.price = price;
        this.length = length;
        this.id = id;

        if (this.name.compareTo("Day-Pass") == 0)
            this.type = "days";
        else
            this.type = "months";
    }

    public void aboutMembership(){
        System.out.println("Membership : " + name + " (" + length + " " + type +") - " + df2.format(price) + " lei. [" + id +"]");
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

}
