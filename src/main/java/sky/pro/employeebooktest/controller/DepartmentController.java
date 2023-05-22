package sky.pro.employeebooktest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @GetMapping(value = "/{id}/salary/sum")
    public int findEmployeeGetDepartment(@PathVariable int id){
        return departmentService.totalSalaryByDepartment(id);
    }

    @GetMapping(value = "/{id}/employees")
    public List<Employee> findAllEmployeeFromDepartment(@PathVariable int id){
        return departmentService.findAllEmployeesFromDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> findEmployee(){
        return departmentService.findEmployeeFromDepartment();
    }

    @GetMapping(value = "/{id}/salary/min")
    public int findMinSalaryFromDepartment(@PathVariable int id){
        return departmentService.findMinSalaryFromDepartment(id);
    }

    @GetMapping(value = "/{id}/salary/max")
    public int findMaxSalaryFromDepartment(@PathVariable int id){
        return departmentService.findMaxSalaryFromDepartment(id);
    }


}
