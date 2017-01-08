package cz.muni.pa165.sem.dto;

import cz.muni.pa165.sem.utils.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
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

    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }

    public PerformanceUnits getPerformanceUnit() {
        return performanceUnit;
    }

    public void setPerformanceUnit(PerformanceUnits performanceUnit) {
        this.performanceUnit = performanceUnit;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public SportsmanDTO getSportsman() {
        return sportsman;
    }

    public void setSportsman(SportsmanDTO sportsman) {
        this.sportsman = sportsman;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
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
