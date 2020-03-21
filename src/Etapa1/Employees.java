package Etapa1;

public class Employees {
    private static int numberEmployees=1;
    private int id;
    private String name;
    private String function;
    private float salary;

    public Employees(String name, String function, float salary) {
        this.id = numberEmployees;
        this.name = name;
        this.function = function;
        this.salary = salary;
        numberEmployees+=1;
    }

    protected void aboutEmployee(){
        System.out.println("This is an employee. This method will be overridden");;
    }

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }

    public float getSalary() {
        return salary;
    }

}

