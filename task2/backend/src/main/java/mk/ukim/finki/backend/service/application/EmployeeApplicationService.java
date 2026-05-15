package mk.ukim.finki.backend.service.application;

import mk.ukim.finki.backend.model.dto.CreateEmployeeDto;
import mk.ukim.finki.backend.model.dto.DisplayEmployeeDto;
import mk.ukim.finki.backend.model.dto.UpdateEmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeApplicationService {
    Page<DisplayEmployeeDto> findAll(Pageable pageable);

    DisplayEmployeeDto create(CreateEmployeeDto e);

    DisplayEmployeeDto update(Long id, UpdateEmployeeDto e);

    void delete(Long id);
}
