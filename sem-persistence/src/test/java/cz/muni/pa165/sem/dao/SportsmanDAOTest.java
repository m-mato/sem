/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import static org.testng.Assert.*;
import org.testng.annotations.*;
/**
 *
 * @author Veronika Aksamitova
 */
@Transactional
@ContextConfiguration(locations = "classpath:dao-context.xml")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class SportsmanDAOTest extends AbstractTestNGSpringContextTests {    
    @Autowired
    private SportsmanDAO sportsmanDAO;  
    
    @BeforeMethod
    public void setUp(){
    }
    
    @AfterClass
    public void tearDown() {
            List<Sportsman> sportsmans = sportsmanDAO.findAll();
            sportsmans.forEach(sportsmanDAO::delete);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull() {
            sportsmanDAO.create(null);
    }
    
    @Test
    public void testCreate() {
        Sportsman expResult = generateSportsman("Jana");
        sportsmanDAO.create(expResult);

        Sportsman sportsman1 = sportsmanDAO.findById(expResult.getId());
        assertNotNull(sportsman1);
        assertEquals(expResult, sportsman1);
    }
    
    @Test
    public void testFindByIdNotFound() {
        Sportsman sportsman1 = sportsmanDAO.findById(new Long("123"));
        assertNull(sportsman1);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNull(){
        sportsmanDAO.findById(null);
    }
    
    @Test
    public void testFindById(){
        Sportsman expResult = generateSportsman("Adam");
        Long expId = expResult.getId();
        sportsmanDAO.create(expResult);
        
        Sportsman result = sportsmanDAO.findById(expId);
        assertNotNull(result);
        assertEquals(expResult, result);       
    }
    
    @Test
    public void testFindByNameNotFound() {
        List<Sportsman> sportsmans = sportsmanDAO.findByName("Jarka");
        assertNull(sportsmans);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNameNull(){
        sportsmanDAO.findByName(null);
    }
    
    @Test
    public void testFindByName(){
        Sportsman expResult = generateSportsman("Jana");
        sportsmanDAO.create(expResult);
        
        List<Sportsman> result = sportsmanDAO.findByName("Jana");
        assertNotNull(result);
        assertEquals(expResult, result.get(0));
    }
    
    @Test
    public void testFindBySurnameNotFound(){
        List<Sportsman> result = sportsmanDAO.findBySurname("Jano");
        assertNull(result);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindBySurnameNull(){
        sportsmanDAO.findBySurname(null);
    }
    
    @Test
    public void testFindBySurname(){
        String expectedSurname = "Nejaký";
        Sportsman expResult = generateSportsman("Jano");
        expResult.setSurname(expectedSurname);
        sportsmanDAO.create(expResult);
        
        List<Sportsman> result = sportsmanDAO.findBySurname(expectedSurname);
        assertNotNull(result);
        assertEquals(result.get(0), expResult);
    }

    @Test
    public void testFindByEmailNotFound(){
        Sportsman result = sportsmanDAO.findByEmail("nonexisitng@example.com");
        assertNull(result);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByEmailNull(){
        sportsmanDAO.findBySurname(null);
    }

    @Test
    public void testFindByEmail(){
        String expectedEmail = "test.jano@example.com";
        Sportsman expResult = generateSportsman("Jano");
        expResult.setEmail(expectedEmail);
        sportsmanDAO.create(expResult);

        Sportsman result = sportsmanDAO.findByEmail(expectedEmail);
        assertNotNull(result);
        assertEquals(result, expResult);
    }

    @Test
    public void testUpdate(){
        Sportsman sportsman1 = generateSportsman("Janči");
        sportsmanDAO.create(sportsman1);
        Long id = sportsman1.getId();
        
        sportsman1.setEmail("novyEmail@email.com");
        sportsman1.setSurname("222");
        sportsmanDAO.update(sportsman1);
        Sportsman changedSportsman = sportsmanDAO.findById(id);
        assertNotNull(changedSportsman);
        assertEquals(sportsman1, changedSportsman);
    }
    
    @Test
    public void testDelete(){
        Sportsman sportsman1 = generateSportsman("Janči");
        sportsmanDAO.create(sportsman1);
        Long id = sportsman1.getId();

        sportsmanDAO.delete(sportsman1);
        assertNull(sportsmanDAO.findById(id));
    }
    
    @Test
    public void testGetAll(){
        generateSportsmans(5);
        List<Sportsman> result = sportsmanDAO.findAll();
        assertTrue(result.size() == 5);
    }
    
    public Sportsman generateSportsman(String name){            
        Sportsman sportsman1 = new Sportsman();
        sportsman1.setName(name);
        sportsman1.setSurname("SportsmanSurname");
        sportsman1.setBirthDate(Calendar.getInstance());
        sportsman1.setEmail(name + "@email");
        sportsman1.setPassword("asdf");
        sportsman1.setIsManager(true);
        sportsmanDAO.create(sportsman1);
        return sportsman1;
    }

    public List<Sportsman> generateSportsmans(int count){
        List<Sportsman> sportsmans = new ArrayList<>();
        for(int i = 1; i <= count; i++)
        {
            Sportsman sportsmanEx = generateSportsman("Sportsman " + i);
            sportsmans.add(sportsmanEx);
        }
        return sportsmans;
    }   
}
