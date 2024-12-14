package com.Bakunov.OOPpractice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RootController {

    @Autowired

    @GetMapping({"/", "/home"})
    public String getHomePath(){
        return "home";
    }

    @GetMapping({"/auth"})
    public String getAuthPath(){
        return "authPage";
    }

    @GetMapping({"/create-day"})
    public String getCreatePath(){
        return "createPage";
    }

    @GetMapping({"/delete-day"})
    public String getDeletePath(){
        return "deletePage";
    }
    
    @GetMapping({"/update-day"})
    public String getUpdatePath(){
        return "updatePage";
    }
}
