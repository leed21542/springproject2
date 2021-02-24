package com.example.demo.repository;

import com.example.demo.domain.Person;
import com.example.demo.domain.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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

    @Test
    void findByBloodType(){
        givenPerson("Martin",10,"A");
        givenPerson("David",9,"B");
        givenPerson("Dennis",8,"O");
        givenPerson("Sophie",7,"AB");
        givenPerson("John",10,"A");

        List<Person> result = personRepository.findByBloodType("A");

        result.forEach(System.out::println);
    }

    @Test
    void findByBirthdayBetween(){
        givenPerson("Martin",10,"A",LocalDate.of(1991,8,30));
        givenPerson("David",9,"B",LocalDate.of(1992,7,10));
        givenPerson("Dennis",8,"O",LocalDate.of(1993,1,5));
        givenPerson("Sophie",7,"AB",LocalDate.of(1994,6,30));
        givenPerson("Benny",6,"A",LocalDate.of(1995,8,30));

        List<Person> result = personRepository.findByMonthOfBirthday(8);

        result.forEach(System.out::println);
    }

    private void givenPerson(String name,int age, String bloodType){
        givenPerson(name,age,bloodType,null);
    }
    private void givenPerson(String name, int age, String bloodType,LocalDate birthday){
        Person person = new Person(name,age,bloodType);
        person.setBirthday(new Birthday(birthday));
        personRepository.save(person);
    }
}