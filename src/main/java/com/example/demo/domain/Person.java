package com.example.demo.domain;

import com.example.demo.controller.dto.PersonDto;
import com.example.demo.domain.dto.Birthday;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor // @NonNull을 쓰기위한 어노테이션
@Where(clause = "deleted = false") // 모든 쿼리에 deleted를 포함해서 진행한다.
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String hobby;

    private String job;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String phoneNumber;

    @ColumnDefault("0")
    private boolean deleted; // 삭제여부

    public void set(PersonDto personDto){

        if(!StringUtils.isEmpty(personDto.getHobby())){
            this.setHobby(personDto.getHobby());
        }

        if(!StringUtils.isEmpty(personDto.getAddress())){
            this.setAddress(personDto.getAddress());
        }

        if(!StringUtils.isEmpty(personDto.getJob())){
            this.setJob(personDto.getJob());
        }

        if(!StringUtils.isEmpty(personDto.getPhoneNumber())){
            this.setPhoneNumber(personDto.getPhoneNumber());
        }

        if(personDto.getBirthday() != null){
            this.setBirthday(Birthday.of(personDto.getBirthday()));
        }

    }

    public Integer getAge(){
        if(this.birthday != null) {
            return LocalDate.now().getYear() - this.birthday.getYearOfBirthday() + 1;
        }
        else{
            return null;
        }
    }

    public boolean isBirthdayToday(){
        return LocalDate.now().equals(LocalDate.of(birthday.getYearOfBirthday(),birthday.getMonthOfBirthday(),birthday.getDayOfBirthday()));
    }
}
