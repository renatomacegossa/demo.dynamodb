package br.com.rmd.demo.dynamo.repository;

import br.com.rmd.demo.dynamo.model.Analitico;
import br.com.rmd.demo.dynamo.model.Consolidado;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.TransactionWriteRequest;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.TransactionCanceledException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class RegistraEventoRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

//    @Retryable( value = { ConditionalCheckFailedException.class, TransactionCanceledException.class }, maxAttempts = 2 )
    public void registrar(Analitico analitico) {

        TransactionWriteRequest transactionWriteRequest = new TransactionWriteRequest();

        Consolidado consolidado = dynamoDBMapper.load( Consolidado.class, analitico.getUsuario(), analitico.getProduto() );

        transactionWriteRequest.addPut( analitico );

        if ( consolidado != null ) {

            consolidado.setQuantidadeAcumulada(
                    consolidado.getQuantidadeAcumulada() + analitico.getQuantidade()
            );

            transactionWriteRequest.addUpdate( consolidado );
        }
        else {
            consolidado = new Consolidado(
                    analitico.getUsuario(),
                    analitico.getProduto(),
                    analitico.getQuantidade(),
                    null
            );

            transactionWriteRequest.addPut( consolidado );
        }

        dynamoDBMapper.transactionWrite( transactionWriteRequest );
    }

//    @Recover
//    void recover(ConditionalCheckFailedException e, Analitico analitico) {
//        log.error( String.format( "caiu no retry. %s", analitico ) );
//    }
//
//    @Recover
//    void recover(TransactionCanceledException e, Analitico analitico) {
//        log.error( String.format( "caiu no retry. %s", analitico ) );
//    }
}
