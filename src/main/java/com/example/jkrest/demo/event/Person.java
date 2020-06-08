package com.example.jkrest.demo.event;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "name")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    String name;
    String address;
}
