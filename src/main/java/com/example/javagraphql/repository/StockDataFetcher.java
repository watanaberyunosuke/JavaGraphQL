package com.example.javagraphql.repository;

import com.example.javagraphql.model.StockDetails;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StockDataFetcher implements DataFetcher<List<StockDetails>> {

    @Override
    public List<StockDetails> get(DataFetchingEnvironment DataFetchingEnvironment){
        StockDetails stockDetails = new StockDetails();

        stockDetails.setTicker("AAPL");
        stockDetails.setStockValue("127.14");
        stockDetails.setVolume("111.60M");
        stockDetails.setPE_Ratio("30.76");
        stockDetails.setEPS("3.28");
        stockDetails.setDividend("0.82 (0.64%)");
        stockDetails.setMarketCap("2.14T");
        stockDetails.setPreviousOpen("128.78");
        stockDetails.setPreviousClose("128.91");

        List<StockDetails> stockDetailsList = new ArrayList<>();
        stockDetailsList.add(stockDetails);

        return stockDetailsList;
    }
}
