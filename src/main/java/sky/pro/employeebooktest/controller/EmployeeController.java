package sky.pro.employeebooktest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.employeebooktest.models.Employee;
import sky.pro.employeebooktest.service.EmployeeService;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("department") int department, @RequestParam("salary") int salary) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("department") int department, @RequestParam("salary") int salary) {
        return employeeService.removeEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("department") int department, @RequestParam("salary") int salary) {
        return employeeService.findEmployee(firstName, lastName, department, salary);
    }

    @GetMapping()
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

}
