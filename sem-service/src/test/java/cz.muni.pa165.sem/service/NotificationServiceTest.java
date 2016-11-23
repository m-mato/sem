package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.SportDAO;
import cz.muni.pa165.sem.entity.Sport;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.argThat;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.times;

/**
 * @author Veronika Aksamitova
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService = new NotificationServiceImpl();


    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        }



}
