package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") Long id, Model model) {
        User  user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "employee-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);
        return "redirect:/employees/list";
    }
}
