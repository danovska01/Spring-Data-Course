package hiberspring.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String position;
    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private EmployeeCard employeeCard;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;


    public Employee() {

    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public EmployeeCard getEmployeeCard() {
        return employeeCard;
    }

    public void setEmployeeCard(EmployeeCard employeeCard) {
        this.employeeCard = employeeCard;
    }

    @Override
    public String toString() {
        String fullName = this.getFirstName() + " " + this.getLastName();
        return String.format("Name: %s\n" +
                "Position: %s\n" +
                "Card Number: %s\n" +
                "-------------------------", fullName, this.getPosition(), this.getEmployeeCard().getNumber());
    }
}
