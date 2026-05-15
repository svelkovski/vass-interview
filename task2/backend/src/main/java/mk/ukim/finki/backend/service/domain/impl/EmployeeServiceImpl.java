package mk.ukim.finki.backend.service.domain.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.Employee;
import mk.ukim.finki.backend.repository.EmployeeRepository;
import mk.ukim.finki.backend.service.domain.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee create(Employee e) {
        return employeeRepository.save(e);
    }

    @Override
    public Employee update(Long id, Employee e) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id %d does not exist.".formatted(id)));

        existing.setName(e.getName());
        existing.setRole(e.getRole());
        existing.setEmail(e.getEmail());
        existing.setDepartment(e.getDepartment());
        existing.setPhoneNumber(e.getPhoneNumber());

        return employeeRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
