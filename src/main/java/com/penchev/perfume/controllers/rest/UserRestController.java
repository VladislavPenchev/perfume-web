package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.models.binding.RegisterBindingModel;
import com.penchev.perfume.models.view.UserViewModel;
import com.penchev.perfume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserViewModel postRegistration(@RequestBody @Valid @Validated RegisterBindingModel registerBindingModel) {
        return this.userService.register(registerBindingModel);
    }

    @GetMapping("/{email}")
    public UserViewModel postRegistration(@PathVariable String email) {
        return this.userService.getUserByEmail(email);
    }

}
