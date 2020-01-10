package com.techedge.ias.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.*;
import com.techedge.ias.IASApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;


@Profile("local")
@Configuration
public class InMemoryDynamoDBConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(IASApplication.class);

    private DynamoDBProxyServer server;
    private AmazonDynamoDB amazonDynamoDB;

    @Value("${local.dynamodb.port:14014}")
    private String port;

    @Bean
    protected AmazonDynamoDB amazonDynamoDB(AWSCredentials amazonAWSCredentials) {
        System.setProperty("sqlite4java.library.path", "native-libs");

        try {
            this.server = ServerRunner.createServerFromCommandLineArgs(new String[]{"-inMemory", "-port", port});
            server.start();
            amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials);
            amazonDynamoDB.setEndpoint("http://localhost:" + port);
            initializeDatabase();
            return amazonDynamoDB;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void stopServer(){
        try {
            server.stop();
        } catch (Exception e) {
            LOGGER.error("unable to stop dynamo db",e);
        }
    }

    private void initializeDatabase() {
        createTable("InvestmentPortfolio","transactionID", ScalarAttributeType.S, 5L, 5L);
    }

    private void createTable(String tableName, String partitionIdName, ScalarAttributeType partitionIdType, long rcu, long wcu) {
        List<KeySchemaElement> keySchema = new ArrayList<>();
        keySchema.add(new KeySchemaElement()
                .withAttributeName(partitionIdName)
                .withKeyType(KeyType.HASH));

        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
        attributeDefinitions
                .add(new AttributeDefinition()
                        .withAttributeName(partitionIdName)
                        .withAttributeType(partitionIdType));


        CreateTableRequest request = new CreateTableRequest()
                .withTableName(tableName)
                .withKeySchema(keySchema)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(rcu)
                        .withWriteCapacityUnits(wcu));
        request.setAttributeDefinitions(attributeDefinitions);
        amazonDynamoDB.createTable(request);
    }


}