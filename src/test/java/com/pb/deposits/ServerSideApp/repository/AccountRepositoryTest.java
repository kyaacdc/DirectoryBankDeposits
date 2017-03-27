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
public class AccountRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DepositorRepository depositorRepository;
    
    @After
    public void deleteAllBeforeTests() throws Exception {
        accountRepository.deleteAll();
        depositorRepository.deleteAll();
    }

    @Test
    public void shouldReturnRepositoryIndex() throws Exception {

        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
                jsonPath("$._links.account").exists());
    }

    @Test
    public void shouldCreateEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/depositor").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\"}")).andExpect(
                status().isCreated()).andExpect(header().string("Location", containsString("depositor/")))
                .andReturn();
        String location = mvcResult.getResponse().getHeader("Location");
        String postRequest = "{\"id\": \"111111\",\"amount\": \"12345\",\"bankName\": \"privat\",\"country\": \"Ukraine\",\"profitability\": \"23\",\"timeConstraints\": \"1234\",\"typeDeposit\": \"savings\", \"depositor\": \"" + location + "\"}";

        //test
        mockMvc.perform(post("/account").content(postRequest)).andExpect(
                status().isCreated()).andExpect(header().string("Location", containsString("account/")));
    }

    @Test
    public void shouldRetrieveEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/depositor").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\"}")).andExpect(
                status().isCreated()).andExpect(header().string("Location", containsString("depositor/")))
                .andReturn();

        String locationOfCustomer = mvcResult.getResponse().getHeader("Location");
        String postRequest = "{\"id\": \"111111\",\"amount\": \"12345\",\"bankName\": \"privat\",\"country\": \"Ukraine\"" +
                ",\"profitability\": \"23\",\"timeConstraints\": \"1234\",\"typeDeposit\": \"savings\", \"depositor\": \""
                + locationOfCustomer + "\"}";

        mvcResult = mockMvc.perform(post("/account").content(postRequest)).andExpect(
                status().isCreated()).andReturn();

        //test
        String locationOfAccount = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(get(locationOfAccount)).andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value("12345"))
                .andExpect(jsonPath("$.bankName").value("privat"))
                .andExpect(jsonPath("$.country").value("Ukraine"))
                .andExpect(jsonPath("$.profitability").value("23"))
                .andExpect(jsonPath("$.timeConstraints").value("1234"))
                .andExpect(jsonPath("$.typeDeposit").value("savings"));
    }

    @Test
    public void shouldQueryEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/depositor").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\"}")).andExpect(
                status().isCreated()).andExpect(header().string("Location", containsString("depositor/")))
                .andReturn();

        String locationOfCustomer = mvcResult.getResponse().getHeader("Location");
        String postRequest = "{\"id\": \"111111\",\"amount\": \"12345\",\"bankName\": \"privat\",\"country\": \"Ukraine\"" +
                ",\"profitability\": \"23\",\"timeConstraints\": \"1234\",\"typeDeposit\": \"savings\", \"depositor\": \""
                + locationOfCustomer + "\"}";

        //test
        mockMvc.perform(post("/account").content(postRequest)).andExpect(
                status().isCreated()).andReturn();

        mockMvc.perform(
                get("/account/search/findByBankNameIgnoreCase?bankName={bankName}", "privat")).andExpect(
                status().isOk()).andExpect(
                jsonPath("$._embedded.account[0].typeDeposit").value("savings"));
    }

    @Test
    public void shouldUpdateEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/depositor").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\"}")).andExpect(
                status().isCreated()).andExpect(header().string("Location", containsString("depositor/")))
                .andReturn();

        String locationOfCustomer = mvcResult.getResponse().getHeader("Location");
        String postRequest = "{\"id\": \"111111\",\"amount\": \"12345\",\"bankName\": \"privat\",\"country\": \"Ukraine\"" +
                ",\"profitability\": \"23\",\"timeConstraints\": \"1234\",\"typeDeposit\": \"savings\", \"depositor\": \""
                + locationOfCustomer + "\"}";

        mvcResult = mockMvc.perform(post("/account").content(postRequest)).andExpect(
                status().isCreated()).andReturn();

        //test
        String locationOfAccount = mvcResult.getResponse().getHeader("Location");

        postRequest = "{\"id\": \"111111\",\"amount\": \"12345\",\"bankName\": \"privat\",\"country\": \"Poland\"" +
                ",\"profitability\": \"23\",\"timeConstraints\": \"1234\",\"typeDeposit\": \"savings\", \"depositor\": \""
                + locationOfCustomer + "\"}";

        mockMvc.perform(put(locationOfAccount).content(postRequest)).andExpect(
                status().isNoContent());

        mockMvc.perform(get(locationOfAccount)).andExpect(status().isOk()).andExpect(
                jsonPath("$.country").value("Poland"));
    }

    @Test
    public void shouldPartiallyUpdateEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/depositor").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\"}")).andExpect(
                status().isCreated()).andExpect(header().string("Location", containsString("depositor/")))
                .andReturn();

        String locationOfCustomer = mvcResult.getResponse().getHeader("Location");
        String postRequest = "{\"id\": \"111111\",\"amount\": \"12345\",\"bankName\": \"privat\",\"country\": \"Ukraine\"" +
                ",\"profitability\": \"23\",\"timeConstraints\": \"1234\",\"typeDeposit\": \"savings\", \"depositor\": \""
                + locationOfCustomer + "\"}";
        mvcResult = mockMvc.perform(post("/account").content(postRequest)).andExpect(
                status().isCreated()).andReturn();

        String locationOfAccount = mvcResult.getResponse().getHeader("Location");

        //test
        mockMvc.perform(
                patch(locationOfAccount).content("{\"country\": \"Poland\"}")).andExpect(
                status().isNoContent());

        mockMvc.perform(get(locationOfAccount)).andExpect(status().isOk()).andExpect(
                jsonPath("$.country").value("Poland"));
    }

    @Test
    public void shouldDeleteEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/depositor").content(
                "{\"email\": \"kya@bk.ru\", \"name\":\"Yuriy\"}")).andExpect(
                status().isCreated()).andExpect(header().string("Location", containsString("depositor/")))
                .andReturn();

        String locationOfCustomer = mvcResult.getResponse().getHeader("Location");
        String postRequest = "{\"id\": \"111111\",\"amount\": \"12345\",\"bankName\": \"privat\",\"country\": \"Ukraine\"" +
                ",\"profitability\": \"23\",\"timeConstraints\": \"1234\",\"typeDeposit\": \"savings\", \"depositor\": \""
                + locationOfCustomer + "\"}";

        mvcResult = mockMvc.perform(post("/account").content(postRequest)).andExpect(
                status().isCreated()).andReturn();

        String locationOfAccount = mvcResult.getResponse().getHeader("Location");

        //test
        mockMvc.perform(delete(locationOfAccount)).andExpect(status().isNoContent());

        mockMvc.perform(get(locationOfAccount)).andExpect(status().isNotFound());
    }
}
