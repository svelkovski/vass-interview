package mk.ukim.finki.backend.service.domain;

import mk.ukim.finki.backend.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);

    Employee create(Employee e);

    Employee update(Long id, Employee e);

    void delete(Long id);
}
