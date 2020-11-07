package br.com.rmd.demo.dynamo.repository;

import br.com.rmd.demo.dynamo.model.Analitico;
import br.com.rmd.demo.dynamo.model.Consolidado;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegistraEventoRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void registrar(Analitico analitico) {

        dynamoDBMapper.save( analitico );

        Consolidado consolidado = new Consolidado(
                analitico.getUsuario(),
                analitico.getProduto(),
                analitico.getQuantidade(),
                null
        );

        dynamoDBMapper.save( consolidado );
    }
}
