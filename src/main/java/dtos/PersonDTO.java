package dtos;

import entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonDTO
{
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String street;
    private String zip;
    private String city;

    public PersonDTO(String firstName, String lastName, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public PersonDTO(Person person)
    {
        if (person.getId() !=0)
            this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phone = person.getPhone();


    }

    public PersonDTO(String firstName, String lastName, String phone, String street, String zip, String city)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

    public static List<PersonDTO> getDTOs(List<Person> personList){
        List<PersonDTO> personsDTOS = new ArrayList<>();
        personList.forEach(person -> personsDTOS.add(new PersonDTO(person)));
        return personsDTOS;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return id == personDTO.id && Objects.equals(firstName, personDTO.firstName) && Objects.equals(lastName, personDTO.lastName) && Objects.equals(phone, personDTO.phone);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, firstName, lastName, phone);
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
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
}
