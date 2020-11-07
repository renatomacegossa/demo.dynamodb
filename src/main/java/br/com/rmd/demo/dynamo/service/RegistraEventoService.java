package br.com.rmd.demo.dynamo.service;

import br.com.rmd.demo.dynamo.model.Analitico;
import br.com.rmd.demo.dynamo.repository.RegistraEventoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegistraEventoService {

    @Autowired
    private RegistraEventoRepository repository;

    public void registrar(Analitico analitico) {

        repository.registrar( analitico );
    }
}
