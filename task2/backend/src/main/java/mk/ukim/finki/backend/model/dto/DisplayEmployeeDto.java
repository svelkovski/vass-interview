package mk.ukim.finki.backend.model.dto;

import mk.ukim.finki.backend.model.Employee;

public record DisplayEmployeeDto(
        Long id,
        String name,
        String role,
        String email,
        String department,
        String phoneNumber
) {
    public static DisplayEmployeeDto from(Employee e) {
        return new DisplayEmployeeDto(e.getId(),
                e.getName(),
                e.getRole(),
                e.getEmail(),
                e.getDepartment(),
                e.getPhoneNumber());
    }
}
