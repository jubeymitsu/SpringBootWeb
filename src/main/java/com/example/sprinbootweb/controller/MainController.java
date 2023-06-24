package com.example.sprinbootweb.controller;

import com.example.sprinbootweb.model.User;
import com.example.sprinbootweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String mainPage(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "main";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/updateInfo/{id}")
    public String updateUser(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return "redirect:/";
    }
}
