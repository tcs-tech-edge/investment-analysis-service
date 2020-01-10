package com.techedge.ias.controller;

import com.techedge.ias.model.InvestmentPortfolioDetail;
import com.techedge.ias.service.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvestmentPortfolioController.class)
public class InvestmentPortfolioControllerTest {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @Before
    public void setMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @WithMockUser("murugesh")
    @Test
    public void givenUsersPresent_thenReturnAllUsers() throws Exception {
        InvestmentPortfolioDetail investmentPortfolioDetail = new InvestmentPortfolioDetail();
        investmentPortfolioDetail.setCustomerID("cust-id");
        investmentPortfolioDetail.setAccountID("acc-id");
        investmentPortfolioDetail.setAccountName("acc-name");
        investmentPortfolioDetail.setTicker("APPL");
        investmentPortfolioDetail.setInvestment_type("InvstType");
        investmentPortfolioDetail.setPurchase_price(0.0);
        investmentPortfolioDetail.setNumber_of_shares(1);
        investmentPortfolioDetail.setPurchase_date("date");

        List<InvestmentPortfolioDetail> investmentPortfolioDetails = Collections.singletonList(investmentPortfolioDetail);

        when(userService.getAllInvestments()).thenReturn(investmentPortfolioDetails);

        mockMvc.perform(get("/investment/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].customerID", is(investmentPortfolioDetail.getCustomerID())))
                .andExpect(jsonPath("$[0].accountID", is(investmentPortfolioDetail.getAccountID())))
                .andDo(document("investment-list-example",
                        responseFields(
                                fieldWithPath("[].customerID").type(JsonFieldType.STRING).description("Id of the customer"),
                                fieldWithPath("[].accountID").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("[].accountName").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("[].ticker").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("[].investment_type").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("[].purchase_price").type(JsonFieldType.NUMBER).description("id of the account"),
                                fieldWithPath("[].number_of_shares").type(JsonFieldType.NUMBER).description("id of the account"),
                                fieldWithPath("[].purchase_date").type(JsonFieldType.STRING).description("id of the account")
                        )
                ));

    }

    @WithMockUser("murugesh")
    @Test
    public void addUserTest() throws Exception {
        InvestmentPortfolioDetail investmentPortfolioDetail = new InvestmentPortfolioDetail();
        investmentPortfolioDetail.setCustomerID("cust-id");
        investmentPortfolioDetail.setAccountID("acc-id");
        investmentPortfolioDetail.setAccountName("acc-name");
        investmentPortfolioDetail.setTicker("APPL");
        investmentPortfolioDetail.setInvestment_type("InvstType");
        investmentPortfolioDetail.setPurchase_price(0.0);
        investmentPortfolioDetail.setNumber_of_shares(1);
        investmentPortfolioDetail.setPurchase_date("date");

        when(userService.addInvestment(Mockito.any())).thenReturn(investmentPortfolioDetail);

        mockMvc.perform(post("/investment/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "    \"customerID\": \"customerID\",\n" +
                        "    \"accountID\": \"accountID\",\n" +
                        "    \"accountName\": \"accountName\",\n" +
                        "    \"ticker\": \"ticker\",\n" +
                        "    \"investment_type\": \"investment_type\",\n" +
                        "    \"purchase_price\": 0.0,\n" +
                        "    \"number_of_shares\": 0,\n" +
                        "    \"purchase_date\": \"purchase_date\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("customerID", is(investmentPortfolioDetail.getCustomerID())))
                .andExpect(jsonPath("accountID", is(investmentPortfolioDetail.getAccountID())))
                .andExpect(jsonPath("accountName", is(investmentPortfolioDetail.getAccountName())))
                .andDo(document("investment-add-example",
                        requestFields(
                                fieldWithPath("customerID").type(JsonFieldType.STRING).description("Id of the customer"),
                                fieldWithPath("accountID").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("accountName").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("ticker").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("investment_type").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("purchase_price").type(JsonFieldType.NUMBER).description("id of the account"),
                                fieldWithPath("number_of_shares").type(JsonFieldType.NUMBER).description("id of the account"),
                                fieldWithPath("purchase_date").type(JsonFieldType.STRING).description("id of the account")
                        ),
                        responseFields(
                                fieldWithPath("customerID").type(JsonFieldType.STRING).description("Id of the customer"),
                                fieldWithPath("accountID").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("accountName").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("ticker").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("investment_type").type(JsonFieldType.STRING).description("id of the account"),
                                fieldWithPath("purchase_price").type(JsonFieldType.NUMBER).description("id of the account"),
                                fieldWithPath("number_of_shares").type(JsonFieldType.NUMBER).description("id of the account"),
                                fieldWithPath("purchase_date").type(JsonFieldType.STRING).description("id of the account")
                        )
                ));

    }
}
