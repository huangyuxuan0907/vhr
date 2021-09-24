package org.javaboy.vhr.controller.system.basic;

import org.javaboy.vhr.bean.Menu;
import org.javaboy.vhr.bean.RespBean;
import org.javaboy.vhr.bean.Role;
import org.javaboy.vhr.service.MenuService;
import org.javaboy.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    //获取数据库中role表的所有数据
    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    //获取数据库中menu表的带子菜单(一共三层)的菜单数据
    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    //根据role的id从menu_role表获取属于这个role的菜单项
    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }

//先根据role的id从role_menu表中删除有关这个role的一切数据，再将mids这个数组传入role_menu表
    @PutMapping("/")
    public RespBean updateRoleMenu(Integer rid,Integer[] mids){
        if(menuService.updateRoleMenu(rid,mids)){
            return RespBean.ok("修改menu_role表成功");
        }
        return RespBean.error("修改menu_role表失败");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        if(roleService.addRole(role)==1){
            return RespBean.ok("添加role成功");
        }
        return RespBean.error("添加role失败");
    }

    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleById(@PathVariable Integer rid){
        if(roleService.deleteRoleById(rid)==1){
            return RespBean.ok("删除role成功");
        }
        return RespBean.error("删除role失败");
    }
}
