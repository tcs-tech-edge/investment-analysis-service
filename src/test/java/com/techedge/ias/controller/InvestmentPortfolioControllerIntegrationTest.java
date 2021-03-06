package com.techedge.ias.controller;

import com.techedge.ias.IASApplication;
import com.techedge.ias.data.entity.InvestmentPortfolio;
import com.techedge.ias.data.repository.InvestmentPortfolioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IASApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@ActiveProfiles("local")
public class InvestmentPortfolioControllerIntegrationTest {

    private MockMvc mvc;

    @Autowired
    private InvestmentPortfolioRepository repository;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setMockMvc() {
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser("murugesh")
    @Test
    public void givenUsersPresent_thenReturnAllUsers() throws Exception {

        InvestmentPortfolio investmentPortfolio = new InvestmentPortfolio("Murugesh", "Kumar");
        repository.save(investmentPortfolio);

        mvc.perform(get("/investment/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].customerID", is("Murugesh")))
                .andExpect(jsonPath("$[0].accountID", is("Kumar")));

    }
}
