package cz.muni.pa165.sem.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 *
 *
 * @author Kamil Triscik
 */
@Entity
@Table(name = "sport")
public class Sport {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    /**
     * Needed for testing purposes.
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 53;
        int hashCode = 7;
        hashCode = prime * hashCode + ((this.name == null) ? 0 : this.name.hashCode());
        hashCode = prime * hashCode + ((this.description == null) ? 0 : this.description.hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object object){
        if ((object == null) || (!(object instanceof Sport))) {
            return false;
        }

        if (this == object) {
            return true;
        }

        final Sport sport = (Sport) object;
        if (!Objects.equals(this.getName(), sport.getName())) return false;
        return Objects.equals(this.getDescription(), sport.getDescription());

    }

    @Override
    public String toString() {
        return "Sport:" +
                    "{" +
                        "id: " + this.id + ", " +
                        "name: " + this.name + ", " +
                        "description: " + this.description +
                    "}";
    }
}
