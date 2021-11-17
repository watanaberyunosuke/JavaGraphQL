package com.example.javagraphql.service;

import com.example.javagraphql.repository.StockDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Standard GraphQL Layer
 */

@Service
public class GraphQLService {
    private graphql.GraphQL graphQL;

    // The main GraphQL object with holds all the data of the schema and runtime
    @Autowired
    StockDataFetcher stockDataFetcher;

    // The repository layer
    @Value("schema.graphql")
    private ClassPathResource classPathLoader;

    // This is required to load the schema file present under the classpath inside the resources
    // The below method is loaded as soon as the application starts to serve the data
    @PostConstruct
    private void loadSchema() throws IOException {

        InputStream inputStream = classPathLoader.getInputStream();
        Reader targetReader = new InputStreamReader(inputStream);

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(targetReader);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }


    // Build the runtime wiring object for GraphQL
    private RuntimeWiring buildRuntimeWiring(){
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                .dataFetcher("stocks", stockDataFetcher))
        .build();

    }

    public GraphQL initiateGraphQL(){
        return graphQL;
    }
}
