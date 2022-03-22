package com.Gestao.Pessoas.Controllers;

import com.Gestao.Pessoas.DTO.PersonDTO;
import com.Gestao.Pessoas.DTO.response.MessageResponseDTO;
import com.Gestao.Pessoas.Entity.Person;
import com.Gestao.Pessoas.Exeptons.PersonNotFoundExeption;
import com.Gestao.Pessoas.Services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/Person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person getPerson(@PathVariable Long id) throws PersonNotFoundExeption {
         Person person = personService.findPerson(id);
         person.add(linkTo(methodOn(PersonController.class).getAll()).withSelfRel());
         return person;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getAll() throws PersonNotFoundExeption {
       List<Person> PersonList = personService.findAllPerson();
        if(!PersonList.isEmpty()){
            for(Person person : personService.findAllPerson()){
                Long id = person.getId();
                person.add(linkTo(methodOn(PersonController.class).getPerson(id)).withSelfRel());
            }
        }
        return PersonList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String CreatePerson(@RequestBody @Valid PersonDTO personDTO){
            return personService.CreatePerson(personDTO);
    }
    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id,@RequestBody @Valid PersonDTO person) throws PersonNotFoundExeption {
        return personService.UpdatePerson(id,person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable Long id) throws PersonNotFoundExeption {
        personService.Delete(id);
    }
}
