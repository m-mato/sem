package cz.muni.pa165.sem.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 *
 * @author Kamil Triscik
 */
@Entity
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
    public boolean equals(Object obj) {
        if ((obj == null) || (!(obj instanceof Sport)))
            return false;

        if (this == obj)
            return true;

        Sport objSport = (Sport) obj;
        if (name == null) {
            if (objSport.getName() != null)
                return false;
        } else if (!name.equals(objSport.getName())) {
            return false;
        }

        if (description == null) {
            if (objSport.getDescription() != null)
                return false;
        } else if (!description.equals(objSport.getDescription()))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "{id: " + id + ", " +
                "name: " + name + ", " +
                "description: " + description + " }";
    }
}
