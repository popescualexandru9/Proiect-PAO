package Etapa1.Employees;

public class Cashier extends Employees {
    private String shift;

    public Cashier( String name, float salary, String shift) {
        super(name,"Cashier", salary);
        this.shift = shift;
    }

    @Override
    public void aboutEmployee() {
        System.out.print("Name : " +  this.getName());
        System.out.println(" works as a " + this.shift + "-time "  + this.getFunction());
        //System.out.println(". His salary is : " +  this.getSalary());

    }

}
