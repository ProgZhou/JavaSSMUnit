package com.JavaSSMUnit.Test;

import com.JavaSSMUnit.Bean.Department;
import com.JavaSSMUnit.Bean.Employee;
import com.JavaSSMUnit.DAO.DepartmentMapper;
import com.JavaSSMUnit.DAO.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

//使用Spring的单元测试，自动注入需要的组件
/*
* 1. 导入SpringTest模块
* 2. @ContextConfiguration指定Spring配置文件的位置
* 3.
* */
//com/JavaSSMUnit/Test/D:/Java_idea/JavaSSMUnit/src/main/resources/applicationContext.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class MybatisTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;
    //测试DepartmentMapper
    @Test
    public void testCRUD(){
        System.out.println(departmentMapper);
    }

    @Test
    public void testCRUD1(){
        //1. 插入几个部门
//        Department dept1 = new Department(null, "开发部");
//        Department dept2 = new Department(null, "测试部");
//        departmentMapper.insertSelective(dept1);
//        departmentMapper.insertSelective(dept2);

        //2. 测试员工插入
//        Employee employee1 = new Employee(null, "Tom", "M", "tom123@qq.com", 1);
//        Employee employee2 = new Employee(null, "Jerry", "F", "jerry147@168.com", 2);
//        employeeMapper.insertSelective(employee1);
//        employeeMapper.insertSelective(employee2);

        //3. 批量插入多个员工，使用可以执行批量操作的sqlSession
        //获取一个能够执行批量操作的mapper
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i = 0; i < 100; i++){
            String substring = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null, substring, "M", substring + "@qq.com",1));
        }
        System.out.println("批量操作");
    }
}
