package com.alcorcode.demo;


import lombok.AllArgsConstructor;
import lombok.Data;

// burdada @Data kullanıyoruz constructors,getter setter oluşturmamıza gerek yok.
@Data
@AllArgsConstructor
public class Address {
    private String country;
    private String city;
    private String postCode;
}
