package org.javaboy.vhr.controller.system.basic;

import org.javaboy.vhr.bean.Department;
import org.javaboy.vhr.bean.RespBean;
import org.javaboy.vhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    //使用递归的方式从department表获取带子节点的的记录
    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    /*通过在数据库创建存储过程并调用来完成对插入department的一系列操作，先插入dep的数据，再将dep
    的parent department的depPath拿来并加上dep的id形成新的depPath,最后将parent department的
    isParent赋值为true
    */
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep){
        departmentService.addDep(dep);
        if(dep.getResult()==1){
            return RespBean.ok("添加部门成功", dep);
        }
        return RespBean.error("添加失败");
    }

    //调用存储过程的话要传一个类进去，虽然我不知道是为什么
    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable Integer id){
        Department dep=new Department();
        dep.setId(id);
        departmentService.deleteDepById(dep);
        if(dep.getResult()==-2){
            return RespBean.error("该部门下有子部门，删除失败");
        }else if(dep.getResult()==-1){
            return RespBean.error("该部门下还有员工，删除失败");
        }else if(dep.getResult()==1){
            return RespBean.ok("删除部门成功");
        }
        return RespBean.error("因未知原因导致删除部门失败");
    }
}
