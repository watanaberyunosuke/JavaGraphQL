package com.example.javagraphql.controller;

import com.example.javagraphql.service.GraphQLService;
import graphql.ExecutionResult;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class GraphQLController {

    @Autowired
    GraphQLService graphQLService;

    @RequestMapping(value ="/stockData")
    public String getPreAuthDecisionData(@RequestBody String query) throws JSONException, IOException {
        ExecutionResult execute = graphQLService.initiateGraphQL().execute(query);
        Map<String, String> obj = (Map<String, String>) execute.getData();
        JSONObject jsonObject = new JSONObject(obj);
        return jsonObject.toString();
    }
}
