package hiberspring.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "branches")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    public Branch() {

    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
