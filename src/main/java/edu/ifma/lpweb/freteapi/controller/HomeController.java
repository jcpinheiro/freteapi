package edu.ifma.lpweb.freteapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String hello() {
        return "Bem Vindo a API de FRETE!!!";
    }

}
