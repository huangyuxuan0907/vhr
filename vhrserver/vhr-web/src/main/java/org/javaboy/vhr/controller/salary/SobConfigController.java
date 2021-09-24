package org.javaboy.vhr.controller.salary;

import org.javaboy.vhr.bean.RespBean;
import org.javaboy.vhr.bean.RespPageBean;
import org.javaboy.vhr.bean.Salary;
import org.javaboy.vhr.service.EmployeeService;
import org.javaboy.vhr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sobcfg")
public class SobConfigController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    SalaryService salaryService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPageWithSalary(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeByPageWithSalary(page, size);
    }

    @GetMapping("/salaries")
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }

    //使用replace的sql语句，它集合了update和insert的功能，不过要把empsalary的eid索引设为unique
    @PutMapping("/")
    public RespBean updateEmployeeSalaryById(Integer eid,Integer sid){
        Integer result = employeeService.updateEmployeeSalaryById(eid, sid);
        if(result==1||result==2) {
            return RespBean.ok("更新员工的工资账套成功");
        }
        return RespBean.error("更新员工的工资账套失败");
    }
}
