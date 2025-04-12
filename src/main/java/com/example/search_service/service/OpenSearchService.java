package com.example.search_service.service;

import com.example.search_service.config.OpenSearchConfig;
import com.example.search_service.model.Product;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.get.GetResponse;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.index.query.MultiMatchQueryBuilder;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.SearchHit;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OpenSearchService {

    private final RestHighLevelClient client;

    public OpenSearchService() {
        this.client = OpenSearchConfig.createClient();
    }

    public String getDocument(String index, String id) throws IOException {
        GetRequest getRequest = new GetRequest(index, id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        return getResponse.getSourceAsString();
    }

    public void closeClient() throws IOException {
        client.close();
    }

    public List<Product> searchDocuments(String queryText) throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");

        MultiMatchQueryBuilder query = QueryBuilders.multiMatchQuery(queryText, "title", "description", "category");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .query(query)
                .size(1000); // Limit to 10 results

        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        List<Product> productList = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String,Object> resultMap = hit.getSourceAsMap();
            Product product = new Product(
                    Integer.valueOf(resultMap.get("id").toString()),
                    resultMap.get("title").toString(),
                    resultMap.get("description").toString(),
                    Double.valueOf(resultMap.get("price").toString()),
                    resultMap.get("category").toString(),
                    resultMap.get("image").toString()
            );
            productList.add(product);
        }

        return productList;
    }
}

