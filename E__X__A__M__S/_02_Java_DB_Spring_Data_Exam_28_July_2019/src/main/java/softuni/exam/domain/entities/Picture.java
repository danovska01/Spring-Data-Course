package softuni.exam.domain.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String url;

    public Picture() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Picture picture)) return false;
        return Objects.equals(url, picture.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
