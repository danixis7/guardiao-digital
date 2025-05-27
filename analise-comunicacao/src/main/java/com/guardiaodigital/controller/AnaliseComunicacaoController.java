package com.guardiaodigital.controller;

import com.guardiaodigital.model.Comunicacao;
import com.guardiaodigital.service.AnaliseComunicacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analise")
public class AnaliseComunicacaoController {
    private final AnaliseComunicacaoService service;

    public AnaliseComunicacaoController(AnaliseComunicacaoService service) {
        this.service = service;
    }

    @PostMapping("/verificar")
    public String verificar(@RequestBody Comunicacao comunicacao) {
        boolean suspeita = service.isSuspeita(comunicacao);
        return suspeita ? "Possível golpe detectado!" : "Comunicação segura.";
    }

    @GetMapping("/numeros-verificados")
    public List<String> numerosVerificados() {
        return service.getTelefonesVerificados();
    }

    @GetMapping("/emails-verificados")
    public List<String> emailsVerificados() {
        return service.getEmailsVerificados();
    }

    @GetMapping("/palavras-chave")
    public List<String> palavrasChave() {
        return service.getPalavrasChave();
    }
}
