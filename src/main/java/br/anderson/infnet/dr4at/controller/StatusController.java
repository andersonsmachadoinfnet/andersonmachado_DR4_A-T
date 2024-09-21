package br.anderson.infnet.dr4at.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StatusController {
    @GetMapping("/status")
    public String status() {
        return "Ativo";
    }
}
