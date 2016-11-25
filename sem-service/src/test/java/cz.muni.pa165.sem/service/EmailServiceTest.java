package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.configuration.EmailConfiguration;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.*;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import javax.mail.Session;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * @author Veronika Aksamitova
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @InjectMocks
    private final EmailService emailService = new EmailServiceImpl();

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Mock
    private EmailConfiguration emailConfigurationMock;
    private Session session;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Mockito.when(emailConfigurationMock.getAddress()).thenReturn("veronika@email.com");
        Mockito.when(emailConfigurationMock.getPersonal()).thenReturn("veronika");
    }

    @Test
    public void sendMessageSubjectNull(){
        boolean result = emailService.sendMessage(null, "body", "email@email.com");
        Assert.assertEquals(result, false);
    }

    @Test
    public void sendMessageSubjectEmpty(){
        boolean result = emailService.sendMessage("", "body", "email@email.com");
        Assert.assertEquals(result, false);
    }

    @Test
    public void sendMessageRecipientsNull(){
        boolean result = emailService.sendMessage("subject", "body", null);
        Assert.assertEquals(result, false);
    }

    @Test
    public void sendMessageRecipientsEmpty(){
        boolean result = emailService.sendMessage("subject", "","");
        Assert.assertEquals(result, false);
    }


    @Test
    public void sendWithoutMessage(){
        boolean result = emailService.sendMessage(null);
        Assert.assertEquals(result, false);
    }

}
