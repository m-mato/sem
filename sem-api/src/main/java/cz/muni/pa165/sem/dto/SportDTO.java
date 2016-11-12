package cz.muni.pa165.sem.dto;

/**
 * @author Kamil Triscik
 */
public class SportDTO {

    private Long id;
    private String name;
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
        if ((obj == null) || (!(obj instanceof SportDTO)))
            return false;

        if (this == obj)
            return true;

        SportDTO objSportDTO = (SportDTO) obj;
        if (id == null) {
            if (objSportDTO.getId() != null)
                return false;
        } else if (!id.equals(objSportDTO.getId())) {
            return false;
        }
        if (name == null) {
            if (objSportDTO.getName() != null)
                return false;
        } else if (!name.equals(objSportDTO.getName())) {
            return false;
        }

        if (description == null) {
            if (objSportDTO.getDescription() != null)
                return false;
        } else if (!description.equals(objSportDTO.getDescription()))
            return false;

        return true;
    }

}
