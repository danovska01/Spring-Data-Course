package entities;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class BasicLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(mappedBy = "label")
    private BasicShampoo shampoo;

    public BasicLabel() {
    }

    public BasicLabel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BasicShampoo getShampoo() {
        return shampoo;
    }

    public void setShampoo(BasicShampoo shampoo) {
        this.shampoo = shampoo;
    }

    public String getName() {
        return name;
    }

    public void setName(String color) {
        this.name = color;
    }
}
