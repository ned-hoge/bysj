
package com.projectssm.controller;

import com.projectssm.model.LoginUser;
import com.projectssm.model.RegUser;
import com.projectssm.model.Staff;
import com.projectssm.model.User;
import com.projectssm.service.StaffService;
import com.projectssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * @Author:dingji
 * @Date: 2021/4/23 0027 11:14
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private StaffService staffService;



    //登录操作  0为账户或密码错误 1为管理员账户 密码和验证码都正确 2为验证码错误
    @PostMapping("/loginUser")
    public int login(@RequestBody LoginUser user, HttpServletRequest request){
        System.out.println(user.toString());
        if(((String)request.getSession().getAttribute("accountLoginCpacha")).equals(user.getVercode()))
        {
            String userName = user.getUsername();
        String passWord = user.getPassword();
        User u1 =userService.login(userName,passWord);
        if (u1==null){
            return 0;
        }else
            {
            request.getSession().setAttribute("session_user",user);//登录成功后将用户放入session中，用于拦截
            if(u1.getRealName().equals("1"))
            {
//                System.out.println("登陆用户信息:"+u1.getRealName());
                return 1;
            }else if((u1.getRealName().equals("3"))){
                return 3;
            }
            else{
                    return 2;
                }

            }

        }
        else{return 4;}   //比较验证码 否则返回3
    }

    //跳转注册页
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "redirect:../register.html";
    }

    //注册操作
    @PostMapping("/register")
    public String register(@RequestBody RegUser regUser){
        User user=new User(regUser.getUsername(),regUser.getPassword(),"2", regUser.getStaffid());//2代表权限为普通员工
        int su = userService.register(user);
        if(su==0){
            return "注册失败";
        }
        return "注册成功";
    }

    //测试未登陆拦截页面
    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("session_user")!=null)
        {return "允许进入主页面";}else {return "返回登录页";}
    }

    //退出登录
    @RequestMapping("/outUser")
    public void outUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("session_user");
        //response.sendRedirect("/user/toIndex");重新发送请求
    }

    //主页面判断是否已登录
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("session_user")!=null)
        {
            LoginUser user= (LoginUser) request.getSession().getAttribute("session_user");
            return user.getUsername();
        }
        else
            {
            return null;
            }
    }
    @GetMapping("/getstaffidbyuser/{id}")
    public String getstaffidbyuser(@PathVariable(value = "id") String id){
        User a=userService.getstaffidbyuser(id);
        return a.getStaffid();
    }

    @GetMapping("/getrealNamebyuser/{id}")
    public String getrealNamebyuser(@PathVariable(value = "id") String id){
        User a=userService.getstaffidbyuser(id);
        return a.getRealName();
    }

    @GetMapping("/getprojectidbystaffid/{id}")
    public String getprojectidbystaffid(@PathVariable(value = "id") String id){
        Staff a=staffService.getStaffById(id);
        return a.getProjectid();
    }
//通过staffid修改user权限(realName)
    @GetMapping("/updateuserbystaffid")
    public int updateuserbystaffid(@RequestParam String state, @RequestParam  String staffid){
        return userService.update(state,staffid);

    }

    @GetMapping("/updatepassword")
    public int updatepassword(@RequestParam String password, @RequestParam  String staffid){
        return userService.updatepassword(password,staffid);

    }
}