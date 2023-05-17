package sky.pro.employeebooktest.service;

import org.springframework.stereotype.Service;
import sky.pro.employeebooktest.exception.ExceptionNotFound;
import sky.pro.employeebooktest.models.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String firstName,String lastName,int department,int salary) {
        Employee employee = new Employee(firstName,lastName,department,salary);
        employees.add(employee);
        return employee;
    }

    public Employee removeEmployee(String firstName,String lastName,int department,int salary){
        Employee employee = new Employee(firstName,lastName,department,salary);
        if(!employees.contains(employee)){
            throw new ExceptionNotFound("Сотрудник не найден");
        }
        employees.remove(employee);
        return employee;
    }

    public Employee findEmployee(String firstName,String lastName,int department,int salary){
        Employee employee = new Employee(firstName,lastName,department,salary);
        if(!employees.contains(employee)){
            throw  new ExceptionNotFound("Сотрудник не найден");
        }
        return employee;
    }

    public List<Employee> getAll(){
        return new ArrayList<>(employees);
    }


}
