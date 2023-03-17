package com.alcorcode.demo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//Lombokdan @Data kullanacağız bize getter,setters,constructors verecek
@Data
@Document
public class Student {
    // we need id
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true) // aynı email ile veritabanı kaydı olmayacak.
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favouriteSubjects;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;

    // constructors ve getter setters eklememize gerek kalmadı @Data halletti.
    //CommandLineRunner için constructor without the id

    public Student(String firstName,
                   String lastName,
                   String email,
                   Gender gender,
                   Address address,
                   List<String> favouriteSubjects,
                   BigDecimal totalSpentInBooks,
                   LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favouriteSubjects = favouriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.created = created;
    }
}
