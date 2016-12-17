package cz.muni.pa165.sem.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author Kamil Triscik
 */
public class SportUpdateDTO {
    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 53;
        int hashCode = id.hashCode();
        hashCode = prime * hashCode + ((this.name == null) ? 0 : this.name.hashCode());
        hashCode = prime * hashCode + ((this.description == null) ? 0 : this.description.hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (!(obj instanceof SportUpdateDTO)))
            return false;

        if (this == obj)
            return true;

        SportUpdateDTO sportUpdateDTO = (SportUpdateDTO) obj;
        if (id == null) {
            if (sportUpdateDTO.getId() != null)
                return false;
        } else if (!id.equals(sportUpdateDTO.getId())) {
            return false;
        }
        if (name == null) {
            if (sportUpdateDTO.getName() != null)
                return false;
        } else if (!name.equals(sportUpdateDTO.getName())) {
            return false;
        }

        if (description == null) {
            if (sportUpdateDTO.getDescription() != null)
                return false;
        } else if (!description.equals(sportUpdateDTO.getDescription()))
            return false;

        return true;
    }

}
