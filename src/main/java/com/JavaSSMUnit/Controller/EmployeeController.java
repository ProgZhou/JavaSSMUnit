package com.JavaSSMUnit.Controller;

import com.JavaSSMUnit.Bean.Employee;
import com.JavaSSMUnit.Bean.Message;
import com.JavaSSMUnit.Service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/*
* 处理员工CRUD请求
* */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    //删除员工
    //改造方法，使得方法能够执行单个删除和批量删除
    //批量删除所携带的ids：1-2-3...
    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Message deleteEmployee(@PathVariable("ids") String ids){
        if(ids.contains("-")){
            String[] strings = ids.split("-");
            List<Integer> list = new ArrayList<>();
            //组装id的集合
            for (String id: strings) {
                list.add(Integer.valueOf(id));
            }
            employeeService.deleteEmployees(list);
        } else{
            employeeService.deleteEmployee(Integer.valueOf(ids));
        }
        return Message.success();
    }

    //保存员工信息
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Message updateEmployee(Employee employee){
//        System.out.println(employee);
        employeeService.updateEmployee(employee);
        return Message.success();
    }


    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Message getEmp(@PathVariable("id") Integer id){
        Employee emp = employeeService.getEmp(id);

        return Message.success().add("emp", emp);
    }

    //查询员工数据，分页查询
    //引入PageHelper分页插件
    @RequestMapping("/emps")
    public String getEmployees(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model){
        //在查询之前只需要调用如下方法，传入页码以及每页的大小
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟着的一个查询方法就是分页查询
        List<Employee> employees = employeeService.getAll();
        //使用PageInfo包装查询后的结果，只需要将PageInfo交给页面，封装了详细的信息，包括查询出来的信息
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "list";
    }

    //返回Json数据，方便Ajax进行处理
    @RequestMapping("/empsJson")
    @ResponseBody
    public Message getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        //在查询之前只需要调用如下方法，传入页码以及每页的大小
        PageHelper.startPage(pn, 5);
        //startPage后面紧跟着的一个查询方法就是分页查询
        List<Employee> employees = employeeService.getAll();
        //使用PageInfo包装查询后的结果，只需要将PageInfo交给页面，封装了详细的信息，包括查询出来的信息
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees, 5);
        return Message.success().add("pageInfo", pageInfo);
    }
    //新增员工信息
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Message saveEmployee(Employee employee){
        employeeService.saveEmployee(employee);
        return Message.success();
    }

    //查询员工是否重复
    @RequestMapping("/checkEmp")
    @ResponseBody
    public Message checkEmployee(@RequestParam("empName") String empName){
        boolean b = employeeService.checkEmp(empName);
        if(b){
            return Message.success();
        } else{
            return Message.failed();
        }
    }

}
