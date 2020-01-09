package com.techedge.ias.service.impl;


import com.techedge.ias.controller.UserController;
import com.techedge.ias.service.MarketDataService;
import com.techedge.ias.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.internal.util.reflection.FieldSetter;
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




