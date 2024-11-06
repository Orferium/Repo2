package org.example.task_3_1_1.controller;

import org.example.task_3_1_1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.task_3_1_1.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/add")
    public String addUser() {
        return "add";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("edit/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute User updatedUser, String name, Model model) {
        userService.getUserById(id);
        updatedUser.setName(name);
        userService.editUser(id, updatedUser);
        model.addAttribute("user", userService.getUserById(id));
        return "redirect:/users";
    }

    @PostMapping(value = "/add")
    public String createUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("userinfo/{id}")
    public String userInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userinfo";
    }
}