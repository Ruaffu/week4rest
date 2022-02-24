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
