package Google;
import java.util.*;

/**
 Round 4:
 Implement a class Employment with these 3 methods:
 assignManager(p1, p2): assign p1 as p2's manager.
 beColleague(p1, p2): make p1 and p2 peer colleagues.
 isManager((p1, p2): decide if p1 is a manager of p2.
 */

class Employee {

    int id;
    private String name;
    //...other personal information
    private Employee manager;
    private List<Employee> subordinates; //direct subordinates

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        subordinates = new ArrayList<>();
    }

    boolean isManager(Employee manager) {
        Employee upperLevel = this.manager;
        while(upperLevel != null && upperLevel != manager) {
            upperLevel = upperLevel.manager;
        }
        return upperLevel.manager == manager;
    }

    void beColleague(Employee p) {
        p.setManager(this.manager);
    }

    void setManager(Employee m) {
        if(manager != null) {   //remove from subordinate's list of current manager
            manager.deleteSubordinate(this);
        }
        manager = m;
        m.addSubordinate(this); //add to new manager's subordinate's list
    }

    private void deleteSubordinate(Employee m) {
        subordinates.remove(m);
    }

    private void addSubordinate(Employee m) {
        subordinates.add(m);
    }

}

public class Employment {

    private Employee admin;
    private Map<Integer, Employee> employees;               //id: employee

    public Employment() {
        admin = new Employee(0, "ADMINISTRATOR");  //root of the employment tree, the highest level supervisor
        employees = new HashMap<>();
        employees.put(0, admin);
    }

    public void assignManager(int p1, int p2) {
        employees.get(p2).setManager(employees.get(p1));
    }

    public void beColleague(int p1, int p2) {
        employees.get(p2).beColleague(employees.get(p1));
    }

    public boolean isManager(int p1, int p2) {
        return employees.get(p2).isManager(employees.get(p1));
    }

    //public void addEmployee(int p);
    //public void deleteEmployee(int p);

}
