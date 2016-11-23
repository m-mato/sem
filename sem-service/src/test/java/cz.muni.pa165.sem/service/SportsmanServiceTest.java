package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.*;
import cz.muni.pa165.sem.entity.*;
import cz.muni.pa165.sem.utils.InvitationState;
import java.util.ArrayList;
import java.util.Calendar;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import static org.mockito.Matchers.argThat;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * @author Veronika Aksamitova
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
@RunWith(MockitoJUnitRunner.class)
public class SportsmanServiceTest {

    @InjectMocks
    private final SportsmanService sportsmanService = new SportsmanServiceImpl();

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Mock
    private SportsmanDAO sportsmanDAOMock;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        

    }

}
