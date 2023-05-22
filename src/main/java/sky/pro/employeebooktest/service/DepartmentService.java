package sky.pro.employeebooktest.service;

import org.springframework.stereotype.Service;
import sky.pro.employeebooktest.exception.DepartmentNotFindException;
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


    public int totalSalaryByDepartment(int departmentId){
        return employeeService.getAll().stream()
                .filter(emp -> emp.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int findMaxSalaryFromDepartment(int departmentId){
        return employeeService.getAll().stream()
                .filter(emp -> emp.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(DepartmentNotFindException::new);
    }

    public int findMinSalaryFromDepartment(int departmentId){
        return employeeService.getAll().stream()
                .filter(emp -> emp.getDepartment() == departmentId)
                .mapToInt((Employee::getSalary))
                .min()
                .orElseThrow(DepartmentNotFindException::new);
    }

    public Map<Integer, List<Employee>> findEmployeeFromDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public List<Employee> findAllEmployeesFromDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(emp -> emp.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }
}
