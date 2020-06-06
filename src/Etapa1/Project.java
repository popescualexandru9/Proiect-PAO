package Etapa1;


public class Project {
    public static void main(String[] args) {

        Runnable services = ActionCenter.getInstance();
        Thread thread = new Thread(services);
        thread.setName("menu");
        thread.start();
    }
}



/*
Prezentare

Clasa Employees din care se deriva clasele Cashiers si Trainers
Clasa Customers
Clasa Gym in care sunt colectiile de tip : - ArrayList <Clasa> pentru Cashiers si Trainers
                                           - Map  <String,Clasa> pentru Customers
Clasa ActionCenter de serviciu in care sunt toate metodele si cam tot ce se intampla.

Run si ai meniu "interactiv" cu tot ce poti face.
*/
