package com.pb.deposits.ServerSideApp.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepositorRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepositorRepository depositorRepository;

    @After
    public void deleteAllBeforeTests() throws Exception {
        depositorRepository.deleteAll();
    }

    @Test
    public void shouldReturnRepositoryIndex() throws Exception {

        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
                jsonPath("$._links.customer").exists());
    }

    @Test
    public void shouldCreateEntity() throws Exception {

        mockMvc.perform(post("/customer").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\", \"phone\":\"0503337178\", \"subscribe\":\"true\"}")).andExpect(
                status().isCreated()).andExpect(
                header().string("Location", containsString("customer/")));
    }

    @Test(expected = NestedServletException.class)
    public void shouldThrowExceptionWhenAddIncorrectPhoneInCreateEntityTime() throws Exception {

        mockMvc.perform(post("/customer").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\", \"phone\":\"%$f0503337178\", \"subscribe\":\"true\"}")).andExpect(
                status().isCreated()).andExpect(
                header().string("Location", containsString("customer/")));
    }

    @Test(expected = NestedServletException.class)
    public void shouldThrowExceptionWhenAddIncorrectNameOfCustomer() throws Exception {

        mockMvc.perform(post("/customer").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yur8iy\", \"phone\":\"0503337178\", \"subscribe\":\"true\"}")).andExpect(
                status().isCreated()).andReturn();
    }

    @Test
    public void shouldRetrieveEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/customer").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\", \"phone\":\"0503337178\", \"subscribe\":\"true\"}")).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Yuriy"))
                .andExpect(jsonPath("$.phone").value("0503337178"))
                .andExpect(jsonPath("$.subscribe").value("true"));
    }

    @Test
    public void shouldQueryEntity() throws Exception {

        mockMvc.perform(post("/customer").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\", \"phone\":\"0503337178\", \"subscribe\":\"true\"}")).andExpect(
                status().isCreated());

        mockMvc.perform(
                get("/customer/search/findByName?name={name}", "Yuriy")).andExpect(
                status().isOk()).andExpect(
                jsonPath("$._embedded.customer[0].name").value(        //jsonPath("$._embedded.customer[0].name").value(
                        "Yuriy"));
    }

    @Test
    public void shouldUpdateEntity() throws Exception {

        MvcResult mvcResult =  mockMvc.perform(post("/customer").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\", \"phone\":\"0503337178\", \"subscribe\":\"true\"}")).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(put(location).content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Kolya\", \"phone\":\"0677449650\", \"subscribe\":\"true\"}")).andExpect(
                status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.name").value("Kolya")).andExpect(
                jsonPath("$.phone").value("0677449650"));
    }

    @Test
    public void shouldPartiallyUpdateEntity() throws Exception {

        MvcResult mvcResult =  mockMvc.perform(post("/customer").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\", \"phone\":\"0503337178\", \"subscribe\":\"true\"}")).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(
                patch(location).content("{\"name\": \"Nikolay\"}")).andExpect(
                status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
                jsonPath("$.name").value("Nikolay")).andExpect(
                jsonPath("$.phone").value("0503337178"));
    }

    @Test
    public void shouldDeleteEntity() throws Exception {

        MvcResult mvcResult =  mockMvc.perform(post("/customer").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\", \"phone\":\"0503337178\", \"subscribe\":\"true\"}")).andExpect(
                status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        System.out.println(location);
        mockMvc.perform(delete(location)).andExpect(status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }
}