package com.example.sprinbootweb.controller;

import com.example.sprinbootweb.model.User;
import com.example.sprinbootweb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String mainPage(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "main";
    }

    @GetMapping("/addNewUser")
    public String addUser(@ModelAttribute User user) {
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-info";
        }
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
