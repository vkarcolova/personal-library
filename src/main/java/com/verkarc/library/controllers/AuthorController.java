package com.verkarc.library.controllers;

import com.verkarc.library.services.AuthorService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    //list all, delete

    private AuthorService authorService;



    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

}
