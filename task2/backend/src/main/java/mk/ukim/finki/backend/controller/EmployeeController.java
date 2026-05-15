package mk.ukim.finki.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.dto.CreateEmployeeDto;
import mk.ukim.finki.backend.model.dto.DisplayEmployeeDto;
import mk.ukim.finki.backend.model.dto.UpdateEmployeeDto;
import mk.ukim.finki.backend.service.application.EmployeeApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeApplicationService employeeApplicationService;

    @GetMapping
    public ResponseEntity<Page<DisplayEmployeeDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(employeeApplicationService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<DisplayEmployeeDto> create(@Valid @RequestBody CreateEmployeeDto dto) {
        DisplayEmployeeDto created = employeeApplicationService.create(dto);
        return ResponseEntity
                .created(URI.create("/api/employees/" + created.id()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisplayEmployeeDto> update(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeDto dto) {
        DisplayEmployeeDto updated = employeeApplicationService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
