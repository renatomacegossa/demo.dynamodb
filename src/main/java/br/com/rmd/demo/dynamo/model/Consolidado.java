package br.com.rmd.demo.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable( tableName = "Consolidado" )
public class Consolidado {

    @DynamoDBHashKey( attributeName = "chaveParticao" )
    private String chaveParticao;

    @DynamoDBRangeKey( attributeName = "chaveOrdenacao" )
    private String chaveOrdenacao;

    @DynamoDBAttribute( attributeName = "quantidadeAcumulada" )
    private Integer quantidadeAcumulada;

    @DynamoDBVersionAttribute
    private Long version;
}
