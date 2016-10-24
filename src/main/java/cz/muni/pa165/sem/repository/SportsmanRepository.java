package cz.muni.pa165.sem.repository;

import cz.muni.pa165.sem.entity.Sportsman;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Matej Majdis
 */
public interface SportsmanRepository extends CrudRepository<Sportsman, Long> {
}
