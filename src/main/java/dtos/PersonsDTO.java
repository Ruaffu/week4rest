package dtos;

import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsDTO
{
    List<PersonDTO> personsDTOS = new ArrayList<>();

    public PersonsDTO(List<Person> personEntities) {

        personEntities.forEach((p) -> {
            personsDTOS.add(new PersonDTO(p));
        });

    }

    public List<PersonDTO> getAll(){
        return personsDTOS;
    }


}
