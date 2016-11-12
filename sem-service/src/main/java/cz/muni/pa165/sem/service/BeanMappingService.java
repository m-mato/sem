package cz.muni.pa165.sem.service;

import java.util.List;

/**
 * @author Kamil Triscik
 */
public interface BeanMappingService {

    <T> List<T> mapTo(List<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

}
