package com.techedge.ias.controller;

import com.techedge.ias.model.UserDetail;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {

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
        UserDetail user = new UserDetail();
        user.setFirstName("Murugesh");
        user.setLastName("Kumar");
        List<UserDetail> users = Collections.singletonList(user);

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/user/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(user.getLastName())))
                .andDo(document("user-list-example",
                        responseFields(
                                fieldWithPath("[].firstName").type(JsonFieldType.STRING).description("First name of the user"),
                                fieldWithPath("[].lastName").type(JsonFieldType.STRING).description("Last name of the user")
                        )
                ));

    }

    @WithMockUser("murugesh")
    @Test
    public void addUserTest() throws Exception {
        UserDetail user = new UserDetail();
        user.setFirstName("Murugesh");
        user.setLastName("Kumar");

        when(userService.addUser(Mockito.any())).thenReturn(user);

        mockMvc.perform(post("/user/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"Murugesh\",\"lastName\":\"Kumar\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", is(user.getFirstName())))
                .andExpect(jsonPath("lastName", is(user.getLastName())))
                .andDo(document("user-add-example",
                        requestFields(
                                fieldWithPath("firstName").type(JsonFieldType.STRING).description("First name of the user"),
                                fieldWithPath("lastName").type(JsonFieldType.STRING).description("Last name of the user")),
                        responseFields(
                                fieldWithPath("firstName").type(JsonFieldType.STRING).description("First name of the user"),
                                fieldWithPath("lastName").type(JsonFieldType.STRING).description("Last name of the user")
                        )
                ));

    }
}
