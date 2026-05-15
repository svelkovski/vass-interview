package mk.ukim.finki.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateEmployeeDto(
        @NotBlank String name,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank String department,
        @NotBlank String phoneNumber
) {
}
