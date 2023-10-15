package com.ex.business.users.Entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name can't be empty")
    @Size(min = 3, max = 50)
    private String name;

    @Max(value = 127, message = "Access denied for age above 127.") @Min(value = 18,message = "Access denied for age under 18.")
    private Byte age;

    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Size(min = 10, max = 100)
    private String email;

//    @Pattern is the generic annotation
//    @Pattern(regexp = "regularEXdyalkom")
//    @Size(min = 6, max = 20)
//    private String numberPhone;

//    private LocalDateTime dateCreated;
//    @NotEmpty

//  at least one number, one Capital letter, one special character, 8 characters
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Password does not meet the right format.")
    private String password;
}
