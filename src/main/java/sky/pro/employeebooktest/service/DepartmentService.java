package sky.pro.employeebooktest.service;

import org.springframework.stereotype.Service;
import sky.pro.employeebooktest.exception.ExceptionNotFound;
import sky.pro.employeebooktest.models.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public int totalSalaryByDepartment(int department){
        return employeeService.getAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee findMaxSalaryFromDepartment(int department) throws ExceptionNotFound {
        return employeeService.getAll().stream()
                .filter(emp -> emp.getDepartment() == department)       // Фильтр по отделам
                .max(Comparator.comparing(Employee::getSalary)) // Фильтр по максимальному значение getSalary
                .orElseThrow(() -> new ExceptionNotFound(" Сотрудник с максимальной зарплатой не найден ")); // Выброс исключения
    }

    public Employee findMinSalaryFromDepartment(int department) throws ExceptionNotFound {
        return employeeService.getAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new ExceptionNotFound(" Сотрудник с минимальной зарплатой не найден "));
    }

    public Map<Integer, List<Employee>> findEmployeeFromDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public List<Employee> findAllEmployeesFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .toList();
    }
}
