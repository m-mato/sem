package cz.muni.pa165.sem.entity;

import cz.muni.pa165.sem.utils.PerformanceUnits;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 *
 * @author Veronika Aksamitova
 */
@Entity
@Table(name = "result")
public class Result {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "performance")
    private Double performance;
    
    @NotNull
    @Column(name = "performance_unit")
    private PerformanceUnits performanceUnit;
    
    @NotNull
    @Column(name = "position")
    private Integer position;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "sportsman")
    private Sportsman sportsman;
    
    @Column(name = "note")
    private String note;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;
    
    public Long getId(){
        return id;
    }

    /**
     * Needed for testing purposes.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    public Double getPerformance(){
        return performance;
    }
    
    public void setPerformance(Double perform){
        if(perform == null || perform < -1){
            throw new IllegalArgumentException("perform is not valid");
        }
        
        this.performance = perform;
    }
    
    public PerformanceUnits getPerformanceUnit(){
        return performanceUnit;
    }
    
    public void setPerformanceUnit(PerformanceUnits unit){
        if(unit == null ){
            throw new IllegalArgumentException("units value is not valid");
        }
        
        this.performanceUnit = unit;
    }
    
    public Integer getPosition(){
        return position;
    }
    
    public void setPosition(Integer pos){
        if(pos == null || pos < -1){
            throw new IllegalArgumentException("position value is not valid");
        }
        
        this.position = pos;
    }
    
    public Sportsman getSportsman(){
        return sportsman;
    }
    
    public void setSportsman(Sportsman sportsman){
        if(sportsman == null){
            throw new NullPointerException("Sportsman is null");
        }
        
        this.sportsman = sportsman;
    }
    
    public String getNote(){
        return note;
    }
    
    public void setNote(String note){
        if(note == null){
            this.note = "";
            return;
        }
        
        this.note = note;
    }
    
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
    @Override 
    public boolean equals(Object object){
        if ((object == null) || (!(object instanceof Result))) {
            return false;
        }

        if (this == object) {
            return true;
        }

        final Result result = (Result) object;
        if(!Objects.equals(this.getPerformance(), result.getPerformance())) return false;
        if(!Objects.equals(this.getPerformanceUnit(), result.getPerformanceUnit())) return false;
        if(!Objects.equals(this.getPosition(), result.getPosition())) return false;
        if(!Objects.equals(this.getSportsman(), result.getSportsman())) return false;
        if(!Objects.equals(this.getNote(), result.getNote())) return false;
        return Objects.equals(this.getEvent(), result.getEvent());
    }
    
    @Override
    public int hashCode(){
        final int prime = 53;
        int hashCode = 7;

        hashCode = prime * hashCode + ((this.performance == null) ? 0 : this.performance.hashCode());
        hashCode = prime * hashCode + ((this.position == null) ? 0 : this.position.hashCode());
        hashCode = prime * hashCode + ((this.event == null) ? 0 : this.event.hashCode());

        return hashCode;
    }
    
    @Override
    public String toString() {
        return "Result:" +
                "{" +
                    "id:" + this.id + ", " +
                    "event:" + this.event + ", " +
                    "sportsman:" + this.sportsman + ", " +
                    "position:" + this.position + ", " +
                    "performance:" + this.performance + " " + this.performanceUnit + ", " +
                    "note: " + this.note +
                "}";
    }
}
