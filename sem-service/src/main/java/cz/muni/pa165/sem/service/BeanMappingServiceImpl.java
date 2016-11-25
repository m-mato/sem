package cz.muni.pa165.sem.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kamil Triscik
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {

    @Autowired
    private Mapper dozer;

    @Override
    public <T> List<T> mapTo(List<?> objects, Class<T> mapToClass) {
        if (objects == null) {
            return null;
        }
        List<T> mappedList = new ArrayList<>();
        for (Object object : objects) {
            mappedList.add(dozer.map(object, mapToClass));
        }
        return mappedList;
    }

    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        if (u == null) {
            return null;
        }
        return dozer.map(u, mapToClass);
    }

}
