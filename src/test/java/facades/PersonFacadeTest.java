package facades;

import dtos.PersonDTO;
import entities.Person;
import entities.RenameMe;
import errorhandling.PersonNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class PersonFacadeTest
{
    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    Person p1,p2;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getPersonFacade(emf);
    }

    @BeforeEach
    void setUp()
    {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            p1 = new Person("mc","Pete","1231415");
            p2 = new Person("mc","Mo","956547456");
            em.persist(p1);
            em.persist(p2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void testGetPerson() throws PersonNotFoundException
    {
        String expected = p1.getFirstName();
        String actual = facade.getPerson(p1.getId()).getFirstName();
        assertEquals(expected, actual);

    }

    @Test
    void testGetAllPersons()
    {
        int expected = 2;
        int actual = facade.getAllPersons().getAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void testAddPerson()
    {
        facade.addPerson("test", "testy","12345");
        int expected = 3;
        int actual = facade.getAllPersons().getAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void testEditPerson()throws PersonNotFoundException
    {
        PersonDTO personDTO = new PersonDTO(new Person("MCBO", "Pete", "12345"));
        personDTO.setId(1);
        facade.editPerson(personDTO);
        String expected = personDTO.getFirstName();
        String actual = facade.getPerson(1).getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    void testDeletePerson() throws PersonNotFoundException
    {
        facade.deletePerson(2);
        int expected = 1;
        int actual = facade.getAllPersons().getAll().size();
        assertEquals(expected, actual);
    }
}