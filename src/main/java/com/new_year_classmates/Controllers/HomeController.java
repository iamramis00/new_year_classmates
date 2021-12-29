package com.new_year_classmates.Controllers;

import com.new_year_classmates.Models.User;
import com.new_year_classmates.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login_get(){
        return "login";
    }

    @PostMapping("/login")
    public String login_post(HttpServletResponse res,
                             @RequestParam String username,
                             @RequestParam String password,
                             Model model){
        User user = userService.getUserByUsername(username);
        if(user != null){
            if(user.getPassword().equals(password)){
                Cookie cookie = new Cookie("User",username);
                res.addCookie(cookie);
                return "redirect:/"+username+"/home";
            }
            else{
                model.addAttribute("res","Неправильный пароль!");
                return "login";
            }
        }
        else{
            model.addAttribute("res","Такого пользвателя не существует!");
            return "login";
        }
    }
    @PostMapping("/reg")
    public String reg(@RequestParam Integer id,
                      @RequestParam String name,
                      @RequestParam String username,
                      @RequestParam String password,
                      @RequestParam String photo){
        User user = new User(id,name,username,password,photo);
        userService.userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/{username}/home")
    public String home(Model model,
                       @PathVariable String username){
        User user = userService.getUserByUsername(username);
        model.addAttribute("user",user);
        return "home";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("User")){
                if(!cookie.getValue().equals("")){
                    cookie.setValue("");
                    return "redirect:/";
                }
            }
        }
        return "redirect:/error";
    }
}
