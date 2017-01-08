package cz.muni.pa165.sem.service.facade;

import cz.muni.pa165.sem.dto.*;
import cz.muni.pa165.sem.entity.*;
import cz.muni.pa165.sem.facade.ResultFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cz.muni.pa165.sem.service.BeanMappingService;
import cz.muni.pa165.sem.service.ResultService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Veronika Aksamitova
 */
@Service
@Transactional
public class ResultFacadeImpl implements ResultFacade{

    @Autowired
    private BeanMappingService beanMappingService;
    
    @Autowired
    private ResultService resultService;
    
    @Override
    public ResultDTO create(ResultCreateDTO result) {
        Result result1 = new Result();
        
        EventDTO eventDTO = result.getEvent();
        result1.setEvent(beanMappingService.mapTo(eventDTO, Event.class));
        
        SportsmanDTO sportsmanDTO = result.getSportsman();
        result1.setSportsman(beanMappingService.mapTo(sportsmanDTO, Sportsman.class));
        
        result1.setPosition(result.getPosition());
        result1.setPerformance(result.getPerformance());
        result1.setPerformanceUnit(result.getPerformanceUnit());
        result1.setNote(result.getNote());
        
        resultService.create(result1);
        return beanMappingService.mapTo(result1, ResultDTO.class);
    }

    @Override
    public ResultDTO findById(Long id) {
        Result result = resultService.findById(id);
        return beanMappingService.mapTo(result, ResultDTO.class);
    }

    @Override
    public List<ResultDTO> findBySportsman(SportsmanDTO sportsman) {
        List<Result> results = resultService.findBySportsman(beanMappingService.mapTo(sportsman, Sportsman.class));
        return beanMappingService.mapTo(results, ResultDTO.class);
    }

    @Override
    public List<ResultDTO> findByEvent(EventDTO event) {
        List<Result> results = resultService.findByEvent(beanMappingService.mapTo(event, Event.class));
        return beanMappingService.mapTo(results, ResultDTO.class);
    }

    @Override
    public ResultDTO findBySportsmanAndEvent(SportsmanDTO sportsman, EventDTO event) {
        Result result = resultService.findBySportsmanAndEvent(
                beanMappingService.mapTo(sportsman, Sportsman.class),
                beanMappingService.mapTo(event, Event.class));
        return beanMappingService.mapTo(result, ResultDTO.class);
    }

    @Override
    public List<ResultDTO> findBySport(SportDTO sport) {
        List<Result> results = resultService.findBySport(beanMappingService.mapTo(sport, Sport.class));
        return beanMappingService.mapTo(results, ResultDTO.class);
    }

    @Override
    public List<ResultDTO> findByPerformance(Double performance) {
        List<Result> results = resultService.findByPerformance(performance);
        return beanMappingService.mapTo(results, ResultDTO.class);
    }

    @Override
    public List<ResultDTO> findByPosition(Integer position) {
        List<Result> results = resultService.findByPosition(position);
        return beanMappingService.mapTo(results, ResultDTO.class);
    }

    @Override
    public List<ResultDTO> findByNote(String note) {
        List<Result> results = resultService.findByNote(note);
        return beanMappingService.mapTo(results, ResultDTO.class);
    }

    @Override
    public List<ResultDTO> findAll() {
        List<Result> results = resultService.findAll();
        return beanMappingService.mapTo(results, ResultDTO.class);
    }

    @Override
    public void update(ResultUpdateDTO resultUpdateDTO) {
        Result result = resultService.findById(resultUpdateDTO.getId());
        
        EventDTO eventDTO = resultUpdateDTO.getEvent();
        result.setEvent(beanMappingService.mapTo(eventDTO, Event.class));
        
        SportsmanDTO sportsmanDTO = resultUpdateDTO.getSportsman();
        result.setSportsman(beanMappingService.mapTo(sportsmanDTO, Sportsman.class));
        
        result.setPosition(resultUpdateDTO.getPosition());
        result.setPerformance(resultUpdateDTO.getPerformance());
        result.setPerformanceUnit(resultUpdateDTO.getPerformanceUnit());
        result.setNote(resultUpdateDTO.getNote());
        resultService.update(result);
    }

    @Override
    public void delete(Long resultId) {
        resultService.delete(resultService.findById(resultId));
    }

    /**
     * Only for unit tests
     * @param beanMappingService
     */
    public void setBeanMappingService(BeanMappingService beanMappingService) {
        this.beanMappingService = beanMappingService;
    }

}
