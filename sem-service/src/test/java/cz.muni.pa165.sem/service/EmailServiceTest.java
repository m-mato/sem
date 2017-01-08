package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.configuration.EmailConfiguration;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * @author Veronika Aksamitova
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
public class EmailServiceTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private final EmailService emailService = new EmailServiceImpl();

    @Mock
    private EmailConfiguration emailConfigurationMock;

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
