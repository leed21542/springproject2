package com.example.demo.repository;

import com.example.demo.domain.Person;
import com.example.demo.domain.dto.Birthday;
import org.aspectj.weaver.patterns.PerObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@Transactional
@SpringBootTest
class PersonRepositoryTest {


    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud() {

        Person person = new Person();
        person.setAge(10);
        person.setName("john");
        person.setBloodType("A");
        personRepository.save(person);

        List<Person> people = personRepository.findByName("john");

//        System.out.println(personRepository.findAll());

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("john");
        assertThat(people.get(0).getAge()).isEqualTo(10);
        assertThat(people.get(0).getBloodType()).isEqualTo("A");

    }

    @Test
    void findByBloodType(){

        List<Person> result = personRepository.findByBloodType("A");

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("benny");
    }

    @Test
    void findByBirthdayBetween(){

        List<Person> result = personRepository.findByMonthOfBirthday(8);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("sophie");
    }
}