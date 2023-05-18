package sky.pro.employeebooktest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.employeebooktest.models.Employee;
import sky.pro.employeebooktest.service.DepartmentService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")

public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/salary/sum")
    public int findEmployeeGetDepartment(@PathParam("/id") int department){
        return departmentService.totalSalaryByDepartment(department);
    }

    @GetMapping("/employees")
    public List<Employee> findAllEmployeeFromDepartment(@PathParam("/id") int department){
        return departmentService.findAllEmployeesFromDepartment(department);
    }

    @GetMapping("/employees/all")
    public Map<Integer, List<Employee>> findEmployee(){
        return departmentService.findEmployeeFromDepartment();
    }

    @GetMapping("/salary/min")
    public Employee findMinSalaryFromDepartment(@PathParam("/id") int department){
        return departmentService.findMinSalaryFromDepartment(department);
    }

    @GetMapping("/salary/max")
    public Employee findMaxSalaryFromDepartment(@PathParam("/id") int department){
        return departmentService.findMaxSalaryFromDepartment(department);
    }


}
