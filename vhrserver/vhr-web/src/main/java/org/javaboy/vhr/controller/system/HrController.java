package org.javaboy.vhr.controller.system;

import org.javaboy.vhr.bean.Hr;
import org.javaboy.vhr.bean.RespBean;
import org.javaboy.vhr.bean.Role;
import org.javaboy.vhr.service.HrService;
import org.javaboy.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;
    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public List<Hr> getAllHrs(String keywords){
        return hrService.getAllHrs(keywords);
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr){
        if(hrService.updateHr(hr)==1){
            return RespBean.ok("更新hr成功");
        }
        return RespBean.error("更新hr失败");
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateHrRole(Integer hrid,Integer[] rids){
        if(hrService.updateHrRole(hrid,rids)){
            return RespBean.ok("更新Hr的Role成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable Integer id){
        if(hrService.deleteHrById(id)==1){
            return RespBean.ok("删除hr成功");
        }
        return RespBean.error("删除hr失败");
    }
}
