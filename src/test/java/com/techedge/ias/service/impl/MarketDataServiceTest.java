package com.techedge.ias.service.impl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

public class MarketDataServiceTest {


    @InjectMocks
    private MarketDataServiceImpl marketDataService;

    @Before
    public void intialize() {
        ReflectionTestUtils.setField(marketDataService, "quoteEndpoint", "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&apikey=NYK6AAP8B7B2KITM");
    }

    @Test
    public void getStockPrice()
    {
        marketDataService.getStockPrice("AAPL");
    }

}




