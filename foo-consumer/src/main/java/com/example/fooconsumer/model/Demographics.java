package com.example.fooconsumer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Demographics {

    private String firstName;
    private String lastName;
    private long dob;
    private boolean active;
}
