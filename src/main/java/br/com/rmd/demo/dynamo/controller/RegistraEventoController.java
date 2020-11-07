package br.com.rmd.demo.dynamo.controller;

import br.com.rmd.demo.dynamo.model.Analitico;
import br.com.rmd.demo.dynamo.service.RegistraEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistraEventoController {

    @Autowired
    private RegistraEventoService service;

    @GetMapping( "/registra-evento" )
    public String registrar() {

        service.registrar( new Analitico( "x", "y", 1 ) );

        return "evento registrado";
    }
}
