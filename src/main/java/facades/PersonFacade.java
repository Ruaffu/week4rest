package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import errorhandling.PersonNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class PersonFacade implements IPersonFacade
{
    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public PersonDTO addPerson(String fName, String lName, String phone)
    {
        Person p = new Person(fName,lName,phone);
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        }finally
        {
            em.close();
        }
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO deletePerson(int id) throws PersonNotFoundException
    {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        if (p == null)
            throw  new PersonNotFoundException("Could not delete, provided id does not exist");
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO getPerson(int id) throws PersonNotFoundException
    {
        EntityManager em = getEntityManager();
        try
        {
            Person p = em.find(Person.class, id);
            if (p == null)
                throw new PersonNotFoundException("No person with provided id found");
            return new PersonDTO(p);

        }finally
        {
            em.close();
        }

    }

    @Override
    public PersonsDTO getAllPersons()
    {
        EntityManager em = getEntityManager();
        try
        {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            List<Person> persons = query.getResultList();
            return new PersonsDTO(persons);
        }finally
        {
            em.close();
        }
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException
    {
        EntityManager em = getEntityManager();
        try
        {
            Person person = em.find(Person.class,p.getId());
            if (p == null)
                throw new PersonNotFoundException("No person with provided id found");
            if (p.getFirstName() != null)
                person.setFirstName(p.getFirstName());
            if (p.getLastName() != null)
                person.setLastName(p.getLastName());
            if (p.getPhone() != null)
                person.setPhone(p.getPhone());
            person.setLastEdited(new Date());
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        }finally
        {
            em.close();
        }
        return p;
    }
}
