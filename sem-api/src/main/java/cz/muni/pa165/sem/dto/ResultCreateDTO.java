package cz.muni.pa165.sem.dto;

import cz.muni.pa165.sem.utils.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 *
 * @author Veronika Aksamitova
 */
public class ResultCreateDTO {
    
    @NotNull
    private Double performance;
    
    @NotNull
    private PerformanceUnits performanceUnit;
    
    @NotNull
    private Integer position;
    
    @NotNull
    private SportsmanDTO sportsman;
    
    private String note;
    
    @NotNull
    private EventDTO event;
    
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
        if(unit == null ){//||ak to nie je platny enum ...spavit 
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
    
    public SportsmanDTO getSportsman(){
        return sportsman;
    }
    
    public void setSportsman(SportsmanDTO sportsman){
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
            throw new NullPointerException("note value is not valid");
        }
        
        this.note = note;
    }
    
    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }
    
    @Override 
    public boolean equals(Object o){
        if(o == null) return false;
        if(this == o) return true;
        if(!(o instanceof ResultDTO)) return false;
        
        ResultDTO result = (ResultDTO) o;
        
        if(!getPerformance().equals(result.getPerformance())) return false;
        if(!getPerformanceUnit().equals(result.getPerformanceUnit())) return false;
        if(!getPosition().equals(result.getPosition()))return false;
        if(!getSportsman().equals(result.getSportsman())) return false;
        if(!getNote().equals(result.getNote())) return false;
        return getEvent().equals(result.getEvent());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.performance);
        hash = 47 * hash + Objects.hashCode(this.performanceUnit);
        hash = 47 * hash + Objects.hashCode(this.position);
        hash = 47 * hash + Objects.hashCode(this.sportsman);
        hash = 47 * hash + Objects.hashCode(this.note);
        hash = 47 * hash + Objects.hashCode(this.event);
        return hash;
    }

}
