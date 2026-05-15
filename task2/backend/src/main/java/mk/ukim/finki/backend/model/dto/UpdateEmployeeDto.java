package mk.ukim.finki.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.backend.model.Employee;

public record UpdateEmployeeDto(
        @NotBlank String name,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank String department,
        @NotBlank String phoneNumber
) {
    public static Employee toEmployee(UpdateEmployeeDto dto) {
        return Employee.builder()
                .name(dto.name)
                .lastName(dto.lastName)
                .email(dto.email)
                .department(dto.department)
                .phoneNumber(dto.phoneNumber)
                .build();
    }
}
