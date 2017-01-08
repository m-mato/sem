package cz.muni.pa165.sem.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Objects;

/**
 * @author Vit Hovezak
 */
public class EventUpdateDTO {

    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Calendar date;

    @NotNull
    private SportDTO sport;

    @NotNull
    @Min(1)
    private Integer capacity;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    private SportsmanDTO admin;

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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public SportDTO getSport() {
        return sport;
    }

    public void setSport(SportDTO sport) {
        this.sport = sport;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SportsmanDTO getAdmin() {
        return admin;
    }

    public void setAdmin(SportsmanDTO admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.date);
        hash = 47 * hash + Objects.hashCode(this.sport);
        hash = 47 * hash + Objects.hashCode(this.capacity);
        hash = 47 * hash + Objects.hashCode(this.city);
        hash = 47 * hash + Objects.hashCode(this.address);
        hash = 47 * hash + Objects.hashCode(this.admin);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof EventUpdateDTO)) {
            return false;
        }
        final EventUpdateDTO other = (EventUpdateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.sport, other.sport)) {
            return false;
        }
        if (!Objects.equals(this.capacity, other.capacity)) {
            return false;
        }
        if (!Objects.equals(this.admin, other.admin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EventUpdateDTO{"
                + "id=" + id
                + ", name=" + name
                + ", description=" + description
                + ", date=" + date
                + ", sport=" + sport
                + ", capacity=" + capacity
                + ", city=" + city
                + ", address=" + address
                + ", admin=" + admin
                + '}';
    }

}
