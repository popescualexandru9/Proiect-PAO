package Etapa1;

public class Customer {
    private String name;
    private Membership membership;
    private int fidelity;

    public Customer(String name , Membership membership, int fidelity) {
        this.name = name;
        this.membership = membership;
        this.fidelity = fidelity;
    }

    public void aboutCustomer(){
        System.out.print("\n"+name + " has been a client for " + fidelity + " months. ");
        membership.aboutMembership();
    }

    public int getFidelity() {
        return fidelity;
    }

    public String getName() {
        return name;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public void setFidelity(int fidelity) {
        this.fidelity = fidelity;
    }

    public int getMembershipId(){
        return this.membership.getId();
    }
}
