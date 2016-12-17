package cz.muni.pa165.sem.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author Kamil Triscik
 */
public class SportCreateDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

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
        if ((obj == null) || (!(obj instanceof SportCreateDTO)))
            return false;

        if (this == obj)
            return true;

        SportCreateDTO sportCreateDTO = (SportCreateDTO) obj;

        if (name == null) {
            if (sportCreateDTO.getName() != null)
                return false;
        } else if (!name.equals(sportCreateDTO.getName())) {
            return false;
        }

        if (description == null) {
            if (sportCreateDTO.getDescription() != null)
                return false;
        } else if (!description.equals(sportCreateDTO.getDescription()))
            return false;

        return true;
    }
}
