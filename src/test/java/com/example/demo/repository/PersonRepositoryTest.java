package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {


    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){

        Person person = new Person();
        person.setAge(10);
        person.setName("유상호");
        person.setBloodType("B");
        personRepository.save(person);

//        System.out.println(personRepository.findAll());

        List<Person> people = personRepository.findAll();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("유상호");
        assertThat(people.get(0).getAge()).isEqualTo(10);

        System.out.println(personRepository.findAll());
    }

    @Test
    void hashCodeAndEquals(){
        Person person1 = new Person("Martain",10,"A");
        Person person2 = new Person("Martain",10,"B");

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
    }
}