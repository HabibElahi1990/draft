package com.example.draft.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private Integer id;
    private String name;
    private String nationalCode;
    private Integer age;
}
