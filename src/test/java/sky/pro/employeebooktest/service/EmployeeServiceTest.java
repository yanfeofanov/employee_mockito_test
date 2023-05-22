package sky.pro.employeebooktest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sky.pro.employeebooktest.exception.ExceptionNotFound;
import sky.pro.employeebooktest.exception.SuchAnEmployeeAlreadyExists;

import static sky.pro.employeebooktest.constants.EmployeeTestConstants.*;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService();

    @Test//добавление пользователя без ошибок
    public void addEmployeeTest() {
        Assertions.assertDoesNotThrow(() -> {
            employeeService.addEmployee(CORRECT_FIRST_NAME, CORRECT_LAST_NAME, 2, 10_000);
            employeeService.addEmployee("Sergey", "Sergeevich", 2, 10_000);
        });
    }

    @Test//проверка на повторное добавление сотрудника
    public void addEmployeeRepetitionTest() {
        Assertions.assertThrows(SuchAnEmployeeAlreadyExists.class, () -> {
            employeeService.addEmployee(CORRECT_FIRST_NAME, CORRECT_LAST_NAME, 2, 10_000);
            employeeService.addEmployee(CORRECT_FIRST_NAME, CORRECT_LAST_NAME, 2, 10_000);
        });
    }

    @Test//проверка на пустые строки firstName,lastName в методе добавления
    public void addEmployeeEmptyTest() {
        Assertions.assertThrows(ExceptionNotFound.class, () -> {
            employeeService.addEmployee(EMPTY_FIRST_NAME, "Ivanov", 2, 10_000);
            employeeService.addEmployee("Ivan", EMPTY_LAST_NAME, 2, 10_000);
            employeeService.addEmployee(EMPTY_FIRST_NAME, EMPTY_LAST_NAME, 2, 10_000);
        });
    }

    @Test//проверка на удаление сотрудника
    public void removeEmployeeTest() {
        Assertions.assertDoesNotThrow(() -> {
            employeeService.addEmployee(CORRECT_FIRST_NAME, CORRECT_LAST_NAME, 2, 10_000);
            employeeService.removeEmployee(CORRECT_FIRST_NAME, CORRECT_LAST_NAME, 2, 10_000);
        });
    }

    @Test//проверка на удаление сотрудника которого нет
    public void removeEmployeeRepetitionTest() {
        Assertions.assertThrows(ExceptionNotFound.class, () -> {
            employeeService.addEmployee(CORRECT_FIRST_NAME, CORRECT_LAST_NAME, 2, 10_000);
            employeeService.removeEmployee("Sergey", "Sergeevich", 2, 10_000);
        });
    }

    @Test//проверка на пустые строки firstName,lastName в методе удаления
    public void removeEmployeeEmptyTest() {
        Assertions.assertThrows(ExceptionNotFound.class, () -> {
            employeeService.removeEmployee(EMPTY_FIRST_NAME, "Ivanov", 2, 10_000);
            employeeService.removeEmployee("Ivan", EMPTY_LAST_NAME, 2, 10_000);
            employeeService.removeEmployee(EMPTY_FIRST_NAME, EMPTY_LAST_NAME, 2, 10_000);
        });
    }

    @Test//проверка на поиск сотрудника
    public void findEmployeeTest() {
        Assertions.assertDoesNotThrow(() -> {
            employeeService.addEmployee(CORRECT_FIRST_NAME, CORRECT_LAST_NAME, 2, 10_000);
            employeeService.findEmployee(CORRECT_FIRST_NAME, CORRECT_LAST_NAME, 2, 10_000);
        });
    }

    @Test//проверка на поиск сотрудника которого нет в коллекции
    public void findEmployeeNotFindTest() {
        Assertions.assertThrows(ExceptionNotFound.class, () -> {
            employeeService.addEmployee(CORRECT_FIRST_NAME, CORRECT_LAST_NAME, 2, 10_000);
            employeeService.findEmployee("Sergey", "Sergeevich", 2, 10_000);
        });
    }
    @Test//проверка на поиск сотрудника если не введены поля
    public void findEmployeeEmptyTest(){
        Assertions.assertThrows(ExceptionNotFound.class, () -> {
            employeeService.findEmployee(EMPTY_FIRST_NAME, "Ivanov", 2, 10_000);
            employeeService.findEmployee("Ivan", EMPTY_LAST_NAME, 2, 10_000);
            employeeService.findEmployee(EMPTY_FIRST_NAME, EMPTY_LAST_NAME, 2, 10_000);
        });
    }

}
