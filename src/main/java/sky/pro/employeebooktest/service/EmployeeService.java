package sky.pro.employeebooktest.service;


import org.springframework.stereotype.Service;
import sky.pro.employeebooktest.exception.ExceptionNotFound;
import sky.pro.employeebooktest.exception.SuchAnEmployeeAlreadyExists;
import sky.pro.employeebooktest.models.Employee;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {

    final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String firstName,String lastName,int department,int salary) {
        Employee employee = new Employee(firstName,lastName,department,salary);
        if(firstName.isEmpty() || lastName.isEmpty()){
            throw new ExceptionNotFound("Поля не должны быть пустыми");
        }
        if(employees.contains(employee)){
            throw new SuchAnEmployeeAlreadyExists("Такой сотрудник уже есть");
        }
        employees.add(employee);
        return employee;
    }

    public Employee removeEmployee(String firstName,String lastName,int department,int salary){
        Employee employee = new Employee(firstName,lastName,department,salary);
        if(firstName.isEmpty() || lastName.isEmpty()){
            throw new ExceptionNotFound("Поля не должны быть пустыми");
        }
        if(!employees.contains(employee)){
            throw new ExceptionNotFound("Сотрудник не найден");
        }
        employees.remove(employee);
        return employee;
    }

    public Employee findEmployee(String firstName,String lastName,int department,int salary){
        Employee employee = new Employee(firstName,lastName,department,salary);
        if(firstName.isEmpty() || lastName.isEmpty()){
            throw new ExceptionNotFound("Поля не должны быть пустыми");
        }
        if(!employees.contains(employee)){
            throw  new ExceptionNotFound("Сотрудник не найден");
        }
        return employee;
    }

    public List<Employee> getAll(){
        return new ArrayList<>(employees);
    }


}
