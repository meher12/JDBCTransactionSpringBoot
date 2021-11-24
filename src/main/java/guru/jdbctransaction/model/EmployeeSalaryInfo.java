package guru.jdbctransaction.model;

public class EmployeeSalaryInfo {

    private Long id;
    private String fullName;
    private double salary;

    public EmployeeSalaryInfo(Long id, String fullName, double salary) {
        this.id = id;
        this.fullName = fullName;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
