package com.example.demo.controller;

import com.example.demo.controller.dto.PersonDto;
import com.example.demo.domain.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.hql.internal.ast.ParseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(value = "/api/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("{id}")
    public Person getPerson(@PathVariable Long id){
        return personService.getPerson(id);
    }

    @PostMapping
    public void postPerson(@RequestBody Person person){
        personService.put(person);
        log.info("person -> {} ",personRepository.findAll());
    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto){
        personService.modify(id,personDto);

        log.info("person -> {} ",personRepository.findAll());
    }

    @PatchMapping("/{id}") // 일부 리소스만 업데이트 한다.
    public void modifyPerson(@PathVariable Long id,String name){
        personService.modify(id,name);

        log.info("person -> {} ",personRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.delete(id);

        log.info("person -> {} ",personRepository.findAll());
        log.info("person -> {} ",personRepository.findPeopleDeleted());
    }
}
