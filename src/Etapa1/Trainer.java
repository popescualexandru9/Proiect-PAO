package Etapa1.Employees;

public class Trainer extends Employees {
    private String certificate;
    private int experience;

    public Trainer(String name, float salary, String certificate, int experience) {
        super(name, "Trainer", salary);
        this.certificate = certificate;
        this.experience = experience;
    }

    @Override
    public void aboutEmployee() {
        System.out.print("Name : " +  this.getName());
        System.out.print(" works as a " +  this.getFunction());
        System.out.println(". Experience : " +  this.experience + " months");
        //System.out.println(". His salary is : " +  this.getSalary());
    }

    public int getExperience() {
        return experience;
    }
}
