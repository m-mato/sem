/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.sem.entity;

import cz.muni.pa165.sem.utils.PerformanceUnits;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author xaksamit
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
    
    public Double getPerformance(){
        return performance;
    }
    
    public void setPerformance(Double perform){
        if(perform == null || perform < 0){   
            throw new IllegalArgumentException("perform is not valid");
        }
        
        this.performance = perform;
    }
    
    public PerformanceUnits getPerformanceUnit(){
        return performanceUnit;
    }
    
    public void setPerformanceUnit(PerformanceUnits unit){
        if(unit == null ){//||ak to nie je platny enum ...spavit 
            throw new IllegalArgumentException("units value is not valid");
        }
        
        this.performanceUnit = unit;
    }
    
    public Integer getPosition(){
        return position;
    }
    
    public void setPosition(Integer pos){
        if(pos == null || pos < 1){
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
        
        // TODO skontrolovat ci dany hrac naozaj existuje v DB
        this.sportsman = sportsman;
    }
    
    public String getNote(){
        return note;
    }
    
    public void setNote(String note){
        if(note == null){
            throw new NullPointerException("note value is not valid");
        }
        
        this.note = note;
    }
    
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
    @Override //TREBA OTESTOVAT CI TO OZAJ FUNGUJE
    public boolean equals(Object o){
        if(o == null) return false;
        if(this == o) return true;
        if(!(o instanceof Result)) return false;
        
        Result result = (Result) o;
        
        if(getId() == null) return false ;
        if(!getId().equals(result.getId())) return false;
        if(!getPerformance().equals(result.getPerformance())) return false;
        if(!getPerformanceUnit().equals(result.getPerformanceUnit())) return false;
        if(!getPosition().equals(result.getPosition()))return false;
        if(!getSportsman().equals(result.getSportsman())) return false;
        if(!getNote().equals(result.getNote())) return false;
        return getEvent().equals(result.getEvent());
    }
    
    @Override
    public int hashCode(){
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getPerformance().hashCode();
        result = 31 * result + getPosition().hashCode();
        result = 31 * result + getNote().hashCode();
        result = 31 * result + getEvent().hashCode();
        return result;
    }
}
