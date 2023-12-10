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
        return "listUsers";
    }

    @GetMapping("/addUser")
    public String createUserForm(User user){
        return "addUser";
    }
    @PostMapping("/addUser")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/list";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") Long id, Model model) {
        User  user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userUpdate";
    }

    @GetMapping("/user-update")
    public String updateUserForm(@RequestParam(name = "id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userUpdate";
    }


    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/list";
    }
}
