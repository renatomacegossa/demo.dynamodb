package br.com.rmd.demo.dynamo.config;

import br.com.rmd.demo.dynamo.model.Analitico;
import br.com.rmd.demo.dynamo.model.Consolidado;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DynamoDBConfiguration implements InitializingBean {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public void afterPropertiesSet() throws Exception {

        createTableAnalitico();
        createTableConsolidado();
    }

    @Bean( "amazonDynamoDB" )
    public AmazonDynamoDB amazonDynamoDB() {

        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration( "http://localhost:8000", "sa-east-1" );

        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials( "",  "" );

        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider( basicAWSCredentials );

        amazonDynamoDB = AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration( endpointConfiguration )
                .withCredentials( awsStaticCredentialsProvider )
                .build();

        return amazonDynamoDB;
    }

    @Bean( "dynamoDBMapper" )
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {

        dynamoDBMapper = new DynamoDBMapper( amazonDynamoDB );

        return dynamoDBMapper;
    }

    private void createTableAnalitico() {

        try {
            CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest( Analitico.class );

            createTableRequest.setProvisionedThroughput( new ProvisionedThroughput( 5l, 5l) );

            amazonDynamoDB.createTable( createTableRequest );

        } catch ( ResourceInUseException e ) {
            log.error( e.getLocalizedMessage() );
        }
    }

    private void createTableConsolidado() {

        try {
            CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest( Consolidado.class );

            createTableRequest.setProvisionedThroughput( new ProvisionedThroughput( 5l, 5l) );

            amazonDynamoDB.createTable( createTableRequest );

        } catch ( ResourceInUseException e ) {
            log.error( e.getLocalizedMessage() );
        }
    }
}
