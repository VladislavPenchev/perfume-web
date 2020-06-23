package com.penchev.perfume.controllers.web;

import com.penchev.perfume.models.binding.RegisterBindingModel;
import com.penchev.perfume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.Set;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        RegisterBindingModel registerBindingModel = new RegisterBindingModel();
        modelAndView.addObject("registerBindingModel", registerBindingModel);

        return modelAndView;
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView confirmRegister(ModelAndView modelAndView, @ModelAttribute RegisterBindingModel registerBindingModel,
                                        Locale locale) {

        Set<String> errors = this.userService.validateForm(registerBindingModel);

        if (errors.isEmpty()) {
            this.userService.register(registerBindingModel);
            modelAndView.setViewName("redirect:".concat("/login"));
        } else {
            modelAndView.addObject("error", errors);
            modelAndView.addObject("registerBindingModel", registerBindingModel);
        }

        return modelAndView;
    }

}
