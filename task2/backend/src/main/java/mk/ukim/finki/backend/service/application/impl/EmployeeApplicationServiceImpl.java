package mk.ukim.finki.backend.service.application.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.Employee;
import mk.ukim.finki.backend.model.dto.CreateEmployeeDto;
import mk.ukim.finki.backend.model.dto.DisplayEmployeeDto;
import mk.ukim.finki.backend.model.dto.UpdateEmployeeDto;
import mk.ukim.finki.backend.service.application.EmployeeApplicationService;
import mk.ukim.finki.backend.service.domain.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeApplicationServiceImpl implements EmployeeApplicationService {

    private final EmployeeService employeeService;

    @Override
    public Page<DisplayEmployeeDto> findAll(Pageable pageable) {
        return employeeService.findAll(pageable)
                .map(DisplayEmployeeDto::from);
    }

    @Override
    public DisplayEmployeeDto create(CreateEmployeeDto e) {
        Employee emp = Employee.builder()
                .name(e.name())
                .role(e.role())
                .email(e.email())
                .department(e.department())
                .build();

        return DisplayEmployeeDto.from(employeeService.create(emp));
    }

    @Override
    public DisplayEmployeeDto update(Long id, UpdateEmployeeDto e) {
        Employee emp = UpdateEmployeeDto.toEmployee(e);
        return DisplayEmployeeDto.from(employeeService.update(id, emp));
    }

    @Override
    public void delete(Long id) {
        employeeService.delete(id);
    }
}
