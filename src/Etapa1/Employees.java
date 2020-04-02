package Etapa1.Employees;

public class Employees {
    private static int numberEmployees=1;
    private int id;
    private String name;
    private String function;
    private float salary;

    Employees(String name, String function, float salary) {
        this.id = numberEmployees;
        this.name = name;
        this.function = function;
        this.salary = salary;
        numberEmployees+=1;
    }

    public void aboutEmployee(){
        System.out.println("This is an employee. This method will be overridden");
    }

    String getName() {
        return name;
    }

    String getFunction() {
        return function;
    }

}

