package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.models.binding.RegisterBindingModel;
import com.penchev.perfume.models.view.UserViewModel;
import com.penchev.perfume.service.UserService;
import com.penchev.perfume.validator.ValidationGroups.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserViewModel> postRegistration(@RequestBody @Validated({Sequence.class}) RegisterBindingModel registerBindingModel) {
        return new ResponseEntity<>(userService.register(registerBindingModel), HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public UserViewModel postRegistration(@PathVariable String email) {
        return this.userService.getUserByEmail(email);
    }
}
