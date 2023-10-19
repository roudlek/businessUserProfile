package com.ex.business.users.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDTO {

    @NotBlank(message = "name can't be empty")
    @Size(min = 3, max = 50)
    private String name;

    @Max(value = 127) @Min(value = 18)
    private Byte age;

    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Size(min = 10, max = 100)
    private String email;

    private String password;
}
