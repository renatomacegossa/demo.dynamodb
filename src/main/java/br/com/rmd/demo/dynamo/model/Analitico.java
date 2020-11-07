package br.com.rmd.demo.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
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

    @DynamoDBAttribute( attributeName = "quantidade" )
    private Integer quantidade;
}
