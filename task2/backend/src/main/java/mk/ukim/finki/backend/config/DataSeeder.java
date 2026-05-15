package mk.ukim.finki.backend.config;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.Employee;
import mk.ukim.finki.backend.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) {
        if (employeeRepository.count() > 0) {
            return;
        }

        List<Employee> seed = List.of(
                emp("Sarah Jenkins",   "Senior Frontend Engineer", "Engineering",     "sarah.j@nexawork.com",  "+1 (555) 123-4567"),
                emp("Marcus Chen",     "Lead Product Designer",    "Design",          "marcus.c@nexawork.com", "+1 (555) 987-6543"),
                emp("Emily Rodriguez", "Marketing Director",       "Marketing",       "emily.r@nexawork.com",  "+1 (555) 456-7890"),
                emp("James Wilson",    "Senior Account Executive", "Sales",           "james.w@nexawork.com",  "+1 (555) 234-5678"),
                emp("Priya Patel",     "HR Manager",               "Human Resources", "priya.p@nexawork.com",  "+1 (555) 876-5432"),
                emp("David Kim",       "Backend Developer",        "Engineering",     "david.k@nexawork.com",  "+1 (555) 345-6789"),
                emp("Sofia Martinez",  "Finance Analyst",          "Finance",         "sofia.m@nexawork.com",  "+1 (555) 567-8901"),
                emp("Liam O'Brien",    "DevOps Engineer",          "Engineering",     "liam.o@nexawork.com",   "+1 (555) 678-9012"),
                emp("Nina Petrova",    "UX Designer",              "Design",          "nina.p@nexawork.com",   "+1 (555) 789-0123"),
                emp("Carlos Mendez",   "Sales Manager",            "Sales",           "carlos.m@nexawork.com", "+1 (555) 890-1234")
        );

        employeeRepository.saveAll(seed);
    }

    private static Employee emp(String name, String role, String department, String email, String phoneNumber) {
        return Employee.builder()
                .name(name)
                .role(role)
                .department(department)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}