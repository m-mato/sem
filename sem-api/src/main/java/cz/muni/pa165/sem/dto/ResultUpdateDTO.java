package cz.muni.pa165.sem.dto;

import cz.muni.pa165.sem.utils.PerformanceUnits;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Veronika Aksamitova
 */
public class ResultUpdateDTO {
    private Long id;
    
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
        if(unit == null ){
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
    
    @Override
    public String toString() {
        return "Result:"
                + " id = " + id
                + ", event = " + this.event
                + ", sportsman = " + this.sportsman
                + ", position = " + this.position
                + ", performance = " + this.performance + " " + performanceUnit
                + ", note = " + this.note;
    }
}
