package com.ex.business.users;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name can't be empty")
    @Size(min = 3, max = 50)
    private String name;

    @Max(value = 127) @Min(value = 18)
    private Byte age;

    @Email(regexp = "anotherRegex")
    @Size(min = 10, max = 100)
    private String email;

//    @Pattern is the generic annotation
//    @Pattern(regexp = "regularEXdyalkom")
//    @Size(min = 6, max = 20)
//    private String numberPhone;

//    private LocalDateTime dateCreated;
//    @NotEmpty

    private String password;
}
