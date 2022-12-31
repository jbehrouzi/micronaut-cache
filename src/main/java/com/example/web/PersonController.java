package com.example.web;

import com.example.repository.PersonRepository;
import com.example.domain.Person;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;


import java.util.Optional;

@Controller
public class PersonController {
    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @Get
    public Optional<Person> getIndex() {
        return repository.findById(1L);
    }

    @Post
    public Person create() {
        Person person=new Person();
        person.setName("test");
        person.setAuthenticated(true);
        person.setConfigId("231");
        person.setCustomerNo("234");
        return repository.save(person);
    }
}
