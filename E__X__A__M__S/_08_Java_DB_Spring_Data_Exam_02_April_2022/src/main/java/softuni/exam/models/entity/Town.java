package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String townName;
    @Column(nullable = false)
    private int population;

    //One Agent may have only one Town, but one Town may have many Agents.
    //One Apartment may have only one Town, but one Town may have many Apartments.

    @OneToMany(mappedBy = "town")
    private List<Agent> agents;
    @OneToMany(mappedBy = "town")
    private List<Apartment> apartments;

    public Town() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
