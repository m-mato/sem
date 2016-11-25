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
    @Column(name = "event_date")
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
        final int prime = 53;
        int hashCode = 7;

        hashCode = prime * hashCode + ((this.name == null) ? 0 : this.name.hashCode());
        hashCode = prime * hashCode + ((this.description == null) ? 0 : this.description.hashCode());
        hashCode = prime * hashCode + ((this.date == null) ? 0 : this.date.hashCode());
        hashCode = prime * hashCode + ((this.sport == null) ? 0 : this.sport.hashCode());
        hashCode = prime * hashCode + ((this.capacity == null) ? 0 : this.capacity.hashCode());
        hashCode = prime * hashCode + ((this.city == null) ? 0 : this.city.hashCode());
        hashCode = prime * hashCode + ((this.address == null) ? 0 : this.address.hashCode());
        hashCode = prime * hashCode + ((this.admin == null) ? 0 : this.admin.hashCode());

        return hashCode;
    }

    @Override
    public boolean equals(Object object){
        if ((object == null) || (!(object instanceof Event))) {
            return false;
        }

        if (this == object) {
            return true;
        }

        final Event other = (Event) object;
        if (!Objects.equals(this.name, other.name)) return false;
        if (!Objects.equals(this.description, other.description))return false;
        if (!Objects.equals(this.city, other.city)) return false;
        if (!Objects.equals(this.address, other.address)) return false;
        if (!Objects.equals(this.id, other.id)) return false;
        if (!Objects.equals(this.date, other.date)) return false;
        if (!Objects.equals(this.sport, other.sport)) return false;
        if (!Objects.equals(this.capacity, other.capacity)) return false;
        return Objects.equals(this.admin, other.admin);
    }

    @Override
    public String toString() {
        return "Event:" +
                    "{" +
                        "id:" + this.id + ", " +
                        "name:" + this.name + ", " +
                        "description:" + this.description + ", " +
                        "date:" + this.date + ", " +
                        "sport:" + this.sport + ", " +
                        "capacity:" + this.capacity + ", " +
                        "city:" + this.city + ", " +
                        "address:" + this.address + ", " +
                        "admin:" + this.admin +
                    "}";
    }
    
}
