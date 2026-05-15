package mk.ukim.finki.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateEmployeeDto(
        @NotBlank String name,
        @NotBlank String role,
        @NotBlank String department,
        @NotBlank @Email String email
) {
}
