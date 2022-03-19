package com.JavaSSMUnit.Service;

import com.JavaSSMUnit.Bean.Employee;
import com.JavaSSMUnit.Bean.EmployeeExample;
import com.JavaSSMUnit.DAO.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    //查询所有员工
    public List<Employee> getAll(){
        return employeeMapper.selectByExampleWithDepartment(null);
    }

    //员工保存方法
    public void saveEmployee(Employee employee){
        employeeMapper.insertSelective(employee);
    }

    //查询员工信息是否重复
    //返回true表示记录可用，返回false表示当前不可用
    public boolean checkEmp(String empName){
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }
    //按照员工id查询员工
    public Employee getEmp(Integer id){
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    //更新员工信息
    public void updateEmployee(Employee employee){
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    //删除单个员工信息
    public void deleteEmployee(Integer id){
        employeeMapper.deleteByPrimaryKey(id);
    }

    //批量删除
    public void deleteEmployees(List<Integer> ids){
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andIdIn(ids);
        employeeMapper.deleteByExample(employeeExample);
    }
}
