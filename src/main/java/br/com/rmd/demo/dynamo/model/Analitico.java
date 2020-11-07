package br.com.rmd.demo.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@DynamoDBTable( tableName = "Analitico" )
public class Analitico {

    @DynamoDBHashKey( attributeName = "chaveParticao" )
    private String chaveParticao;

    @DynamoDBRangeKey( attributeName = "chaveOrdenacao" )
    private String chaveOrdenacao;

    @DynamoDBAttribute( attributeName = "usuario" )
    private String usuario;

    @DynamoDBAttribute( attributeName = "produto" )
    private String produto;

    @DynamoDBAttribute( attributeName = "quantidade" )
    private Integer quantidade;

    @DynamoDBVersionAttribute
    private Long version;
}
