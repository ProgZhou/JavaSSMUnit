package com.JavaSSMUnit.Controller;

import com.JavaSSMUnit.Bean.Department;
import com.JavaSSMUnit.Bean.Message;
import com.JavaSSMUnit.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//处理和部门有关的请求
@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    //返回所有的部门信息
    @RequestMapping("/depts")
    @ResponseBody
    public Message getDepartments(){
        List<Department> departments = departmentService.getDepts();
        return Message.success().add("depts", departments);
    }

}
