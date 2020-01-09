package com.techedge.ias.service.impl;

import com.techedge.ias.service.MarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;

@Service
public class MarketDataServiceImpl implements MarketDataService {
    Logger logger = LoggerFactory.getLogger(MarketDataServiceImpl.class);
    @Value("${market.endpoint.quoteEndpoint}")
    private String quoteEndpoint;

    /**
     * Get the Stock price for a symbol
     *
     * @param symbol
     */
    public String getStockPrice(String symbol) {
        logger.info("Getting price for Symbol {}", symbol);
        String queryParam = "&symbol=" + symbol;
        String quoteURL = this.quoteEndpoint + queryParam;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> result = restTemplate
                .exchange(quoteURL, HttpMethod.GET, requestEntity, String.class);

//        String queryParam = "&symbol="+ symbol;
//        String quoteURL = this.quoteEndpoint + queryParam;
//        RestTemplate restTemplate = new RestTemplate();
//        logger.debug(quoteURL);
//        String result = restTemplate.getForObject(quoteURL, String.class);
        logger.info("Response from service {}", result);
        return result.toString();
    }

}
