package sky.pro.employeebooktest.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.employeebooktest.exception.DepartmentNotFindException;
import sky.pro.employeebooktest.models.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentService departmentServiceMock;

    public static Stream<Arguments> maxSalaryDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 15_000),
                Arguments.of(2, 17_000),
                Arguments.of(3, 20_000)
        );
    }

    public static Stream<Arguments> minSalaryDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 10_000),
                Arguments.of(2, 15_000),
                Arguments.of(3, 20_000)
        );
    }

    public static Stream<Arguments> sumSalaryDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 25_000),
                Arguments.of(2, 32_000),
                Arguments.of(3, 20_000),
                Arguments.of(4, 0)
        );
    }

    public static Stream<Arguments> empDepartmentTestParams() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of(
                                new Employee("Иван", "Иванов", 1, 10_000),
                                new Employee("Василий", "Васильев", 1, 15_000)
                        )),
                Arguments.of(2,
                        List.of(
                                new Employee("Сергей", "Сергеевич", 2, 15_000),
                                new Employee("Мария", "Васильева", 2, 17_000)
                        )),
                Arguments.of(3,
                        List.of(
                                new Employee("Глеб", "Самойлов", 3, 20_000)
                        )),
                Arguments.of(4,
                        Collections.emptyList()
                )
        );
    }

    @BeforeEach
    public void beforEach() {
        Mockito.when(employeeServiceMock.getAll()).thenReturn(
                List.of(new Employee("Иван", "Иванов", 1, 10_000),
                        new Employee("Сергей", "Сергеевич", 2, 15_000),
                        new Employee("Василий", "Васильев", 1, 15_000),
                        new Employee("Мария", "Васильева", 2, 17_000),
                        new Employee("Глеб", "Самойлов", 3, 20_000))
        );
    }

    @ParameterizedTest
    @MethodSource("maxSalaryDepartmentTestParams")
    public void maxSalaryDepartmentTestParams(int departmentId, int expected) {
        Assertions.assertThat(departmentServiceMock.findMaxSalaryFromDepartment(departmentId)).isEqualTo(expected);
    }

    @Test
    public void maxNegativeSalaryDepartmentTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFindException.class)
                .isThrownBy(() -> departmentServiceMock.findMaxSalaryFromDepartment(4));
    }

    @ParameterizedTest
    @MethodSource("minSalaryDepartmentTestParams")
    public void minSalaryDepartmentTestParams(int departmentId, int expected) {
        Assertions.assertThat(departmentServiceMock.findMinSalaryFromDepartment(departmentId)).isEqualTo(expected);
    }

    @Test
    public void minNegativeSalaryDepartmentTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFindException.class)
                .isThrownBy(() -> departmentServiceMock.findMinSalaryFromDepartment(4));
    }

    @ParameterizedTest
    @MethodSource("sumSalaryDepartmentTestParams")
    public void sumSalaryDepartmentTestParams(int departmentId, int expected) {
        Assertions.assertThat(departmentServiceMock.totalSalaryByDepartment(departmentId)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("empDepartmentTestParams")
    public void empDepartmentTestParams(int departmentId, List<Employee> expected) {
        Assertions.assertThat(departmentServiceMock.findAllEmployeesFromDepartment(departmentId)).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void findEmployeeFromDepartmentTest() {
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(new Employee("Иван", "Иванов", 1, 10_000),
                        new Employee("Василий", "Васильев", 1, 15_000)),
                2,
                List.of(new Employee("Сергей", "Сергеевич", 2, 15_000),
                        new Employee("Мария", "Васильева", 2, 17_000)),
                3,
                Collections.singletonList(new Employee("Глеб", "Самойлов", 3, 20_000))
        );
        Assertions.assertThat(departmentServiceMock.findEmployeeFromDepartment()).containsExactlyInAnyOrderEntriesOf(expected);
    }

}
