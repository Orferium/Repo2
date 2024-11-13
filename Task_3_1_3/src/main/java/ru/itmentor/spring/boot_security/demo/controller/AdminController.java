package ru.itmentor.spring.boot_security.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/admin/")
@Slf4j
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping()
//    public String homeAdmin() {
//        return "redirect:/admin/users";
//    }


//    @GetMapping("users")
//    public String printUsers(Model model) {
//        model.addAttribute("userSet", userService.listUsers());
//        return "all_users";
//    }

    @GetMapping("users")
    public ResponseEntity<List<User>> printUsers() {
        List<User> users = userService.listUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


//    @GetMapping(value = "users/add")
//    public String newUserForm(@ModelAttribute("user") User user, Model model) {
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "add_user";
//    }

    @PostMapping(value = "/users/add")
    public User createNewUser(HttpServletResponse response, @RequestBody User user) {
        log.warn("invoke");
        log.warn(user.getName());
        log.warn(user.toString());
        userService.addUser(user);
        response.setStatus(HttpServletResponse.SC_OK);
        return user;
    }



//    @GetMapping("users/{id}/edit")
//    public String editUserForm(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("roles", roleService.getAllRoles());
//        model.addAttribute("user", userService.getUserById(id));
//        return "edit_user";
//    }

    @PutMapping("users/{id}/edit")
    public void update(@RequestBody User user) {
        userService.updateUser(user);
        //return user;
    }


    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.removeUserById(id);

    }


    @GetMapping("users/{id}")
    public User show(@PathVariable("id") Long id) {
       User user = userService.getUserById(id);
        return user;
    }

}
