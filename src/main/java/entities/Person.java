package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE FROM Person")
public class Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private Date created;
    private Date lastEdited;

    public Person()
    {
    }

    public Person(String firstName, String lastName, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.created = new Date();
        this.lastEdited = new Date();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getLastEdited()
    {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited)
    {
        this.lastEdited = lastEdited;
    }
}
