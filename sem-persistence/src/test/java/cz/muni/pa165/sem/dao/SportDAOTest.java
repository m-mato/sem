package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.validation.ConstraintViolationException;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Matej Majdis
 */
@Transactional
@ContextConfiguration(locations = "classpath:dao-context.xml")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class SportDAOTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SportDAO sportDAO;

	private final String existingSportName = "existingSportName";

	@BeforeMethod
	public void setUp() {

		sportDAO.create(getSport(existingSportName, "description"));
	}

	@AfterClass
	public void tearDown() {
		List<Sport> sports = sportDAO.findAll();
		sports.forEach(sportDAO::delete);
	}

	@Test
	public void testCreate() {
		Sport sportExpected = getSport("golf", "some description");
		sportDAO.create(sportExpected);
		Sport sportActual = sportDAO.findById(sportExpected.getId());

		assertNotNull(sportActual);
		assertEquals(sportExpected, sportActual);

	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCreateNull() {
		sportDAO.create(null);
	}

	@Test(expectedExceptions = ConstraintViolationException.class)
	public void testCreateUninitialized() {
		Sport sport = new Sport();
		sportDAO.create(sport);
	}

	@Test
	public void testFindById() {
		Sport sportByName = sportDAO.findByName(existingSportName);
		Sport sportById = sportDAO.findById(sportByName.getId());

		assertNotNull(sportById);
		assertEquals(sportById.getId(), sportByName.getId());
		assertEquals(sportById.getName(), sportByName.getName());
		assertEquals(sportById.getDescription(), sportByName.getDescription());
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFindByIdNull() {
		sportDAO.findById(null);
	}

	@Test
	public void testFindByIdNotFound(){
		Long id = Long.MAX_VALUE;
		Assert.assertNull(sportDAO.findById(id));
	}

	@Test
	public void testFindByName() {
		Sport sportByName = sportDAO.findByName(existingSportName);

		assertNotNull(sportByName);
		assertEquals(existingSportName, sportByName.getName());
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFindByNameNull() {
		sportDAO.findByName(null);
	}

	@Test
	public void testFindByNameNotFound() {
		String name = "nonExistingSport";
		Assert.assertNull(sportDAO.findByName(name));
	}

	@Test
	public void testFindAll() {
		List<Sport> sports = sportDAO.findAll();
		Assert.assertNotNull(sports);
	}

	@Test
	public void testUpdate() throws Exception {
		Sport sport = getSport("updateSport", "description");
		sportDAO.create(sport);

		final String updatedDescription = "updated description";
		sport.setDescription(updatedDescription);

		sportDAO.update(sport);

		Sport updatedSport = sportDAO.findById(sport.getId());
		assertNotNull(updatedSport);
		assertEquals(sport, updatedSport);
		assertEquals(sport.getDescription(), updatedSport.getDescription());
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testUpdateNull() {
		sportDAO.update(null);
	}

	@Test
	public void delete() {
		Sport sport = getSport("sport", "desc");
		sportDAO.create(sport);

		sportDAO.delete(sport);
		Assert.assertNull(sportDAO.findById(sport.getId()));
	}

	private Sport getSport(String name, String description) {
		Sport sport = new Sport();
		sport.setName(name);
		sport.setDescription(description);

		return sport;
	}
}
