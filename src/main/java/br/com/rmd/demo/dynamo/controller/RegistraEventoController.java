package br.com.rmd.demo.dynamo.controller;

import br.com.rmd.demo.dynamo.model.Analitico;
import br.com.rmd.demo.dynamo.service.RegistraEventoService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistraEventoController {

    @Autowired
    private RegistraEventoService service;

    @GetMapping( "/registra-evento" )
    public String registrar() {

        Analitico analitico = new Analitico(
                "devproj",
                RandomStringUtils.randomAlphanumeric( 4 ),
                "68916022",
                "livro",
                1,
                null
        );

        service.registrar( analitico );

        return analitico.toString();
    }
}
