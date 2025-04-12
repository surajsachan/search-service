package com.example.search_service.config;

import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.RestClient;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.HttpHost;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.opensearch.client.RestClientBuilder;

public class OpenSearchConfig {
    private static final String OPENSEARCH_HOST = "search-m-cart-search-a6vuhc35kj26vtfqxsnryquudy.aos.us-east-1.on.aws";
    private static final int OPENSEARCH_PORT = 443;
    private static final String OPENSEARCH_SCHEME = "https";
    private static final String OPENSEARCH_USERNAME = "Suraj@98";
    private static final String OPENSEARCH_PASSWORD = "SurajSachan@98";

    public static RestHighLevelClient createClient() {
        final BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(OPENSEARCH_USERNAME, OPENSEARCH_PASSWORD));

        RestClientBuilder builder = RestClient.builder(new HttpHost(OPENSEARCH_HOST, OPENSEARCH_PORT, OPENSEARCH_SCHEME))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        return new RestHighLevelClient(builder);
    }
}
