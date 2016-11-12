package cz.muni.pa165.sem.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kamil Triscik
 */
public class BeanMappingServiceImpl implements BeanMappingService {

    @Autowired
    private Mapper dozer;

    @Override
    public <T> List<T> mapTo(List<?> objects, Class<T> mapToClass) {
        List<T> mappedList = new ArrayList<>();
        for (Object object : objects) {
            mappedList.add(dozer.map(object, mapToClass));
        }
        return mappedList;
    }

    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return dozer.map(u, mapToClass);
    }

}
