package guru.jdbctransaction.mapper;


import guru.jdbctransaction.model.EmployeeSalaryInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeSalaryMapper  implements RowMapper<EmployeeSalaryInfo> {

   public static final String BASE_SQL = "Select es.Id, es.Full_Name, es.Salary from EmployeeSalary es";

    @Override
    public EmployeeSalaryInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("Id");
        String fullName = rs.getString("Full_Name");
        double salary = rs.getDouble("Salary");
        return new EmployeeSalaryInfo(id, fullName, salary);
    }
}
