package mk.ukim.finki.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.backend.model.Employee;

public record UpdateEmployeeDto(
        @NotBlank String name,
        @NotBlank String role,
        @NotBlank String department,
        @NotBlank @Email String email
) {
    public static Employee toEmployee(UpdateEmployeeDto dto) {
        return Employee.builder()
                .name(dto.name)
                .role(dto.role)
                .email(dto.email)
                .department(dto.department)
                .build();
    }
}
