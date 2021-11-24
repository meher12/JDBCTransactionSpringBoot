package guru.jdbctransaction.dao;

import guru.jdbctransaction.exception.EmployeeTransactionException;
import guru.jdbctransaction.mapper.EmployeeSalaryMapper;
import guru.jdbctransaction.model.EmployeeSalaryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class EmployeeSalaryDAO extends JdbcDaoSupport {

    @Autowired
    public EmployeeSalaryDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    public List<EmployeeSalaryInfo> getEmployeeSalary(){
        String sql = EmployeeSalaryMapper.BASE_SQL;
        Object[] params = new Object[] {};
        EmployeeSalaryMapper employeeSalaryMapper = new EmployeeSalaryMapper();
        List <EmployeeSalaryInfo> list = this.getJdbcTemplate().query(sql, params, employeeSalaryMapper);
        return list;
    }

    public EmployeeSalaryInfo findEmployeeSalary(Long id){
        String sql = EmployeeSalaryMapper.BASE_SQL + " where es.Id =? ";
        Object[] params = new Object[] {id};
        EmployeeSalaryMapper mapper = new EmployeeSalaryMapper();
        try {
            EmployeeSalaryInfo employeesalary = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return employeesalary;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    // MANDATORY: Transaction must be created before.
    @Transactional(propagation = Propagation.MANDATORY)
    public void addSalary(Long id, double amount) throws EmployeeTransactionException {
         EmployeeSalaryInfo employee = this.findEmployeeSalary(id);
         if(employee ==null){
             throw new EmployeeTransactionException("Employee Not found" + id);
         }
         double newSalary = employee.getSalary() + amount;
         if(employee.getSalary() + amount < 0) {
             throw new EmployeeTransactionException("The salary for this employee '" + id + "' is not enough (" + employee.getSalary() + ")");
         }

         employee.setSalary(newSalary);
        // Update to DB
        String updateSalary = "update EmployeeSalary set Salary = ? where Id = ? ";
        this.getJdbcTemplate().update(updateSalary, employee.getSalary(), employee.getId());
    }

    // Do not catch EmployeeTransactionException in this method.
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = EmployeeTransactionException.class)
    public void sendMoney(Long fromEmployeeId, Long toEmployeeId, double amount) throws EmployeeTransactionException {
       addSalary(toEmployeeId, amount);
       addSalary(fromEmployeeId, -amount);
    }
}
