package cz.muni.pa165.sem.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Vit Hovezak
 */
@Entity
@Table(name = "event")
public class Event implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(name = "name")
    private String name;
    
    @NotBlank
    @Column(name = "description")
    private String description;
    
    @NotNull
    @Column(name = "date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar date;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "sport")
    private Sport sport;
    
    @NotNull
    @Column(name = "capacity")
    private Integer capacity;
    
    @NotBlank
    @Column(name = "city")
    private String city;
    
    @NotBlank
    @Column(name = "address")
    private String address;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "admin")
    private Sportsman admin;
    
    @ManyToMany
    private Set<Sportsman> participants = new HashSet<>();

    public Long getId() {
        return id;
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
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

    public Sportsman getAdmin() {
        return admin;
    }

    public void setAdmin(Sportsman admin) {
        this.admin = admin;
    }

    public Set<Sportsman> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Sportsman> participants) {
        this.participants = participants;
    }
    
    public void addParticipant(Sportsman participant) {
        this.participants.add(participant);
    }
    
    public void removeParticipant(Sportsman participant) {
        this.participants.remove(participant);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + Objects.hashCode(this.date);
        hash = 41 * hash + Objects.hashCode(this.sport);
        hash = 41 * hash + Objects.hashCode(this.capacity);
        hash = 41 * hash + Objects.hashCode(this.city);
        hash = 41 * hash + Objects.hashCode(this.address);
        hash = 41 * hash + Objects.hashCode(this.admin);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
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
        return "Event{"
                + "id=" + id
                + ", name=" + name
                + ", description=" + description
                + ", date=" + date
                + ", sport=" + sport
                + ", capacity=" + capacity
                + ", city=" + city
                + ", address=" + address
                + ", admin=" + admin
                + ", participants=" + participants
                + '}';
    }
    
}
