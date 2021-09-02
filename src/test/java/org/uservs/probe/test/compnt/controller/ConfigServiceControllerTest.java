package org.uservs.probe.test.compnt.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.uservs.probe.Probe;
import org.uservs.probe.utils.ResourceFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Probe.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Log4j2
class ConfigServiceControllerTest {

    @BeforeEach
    void setUp() {
        if (mvc == null) {
            mvc = MockMvcBuilders
                    .webAppContextSetup(webApplicationContext)
                    .build();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void newTest() throws Exception {
        var mvcResult = newRequest();
        var response = mvcResult.getResponse().getContentAsString();
        log.debug("response: " + response);
        JSONAssert.assertEquals(
                new ResourceFile("config_new_result_01.json")
                        .readAsString(),
                response,
                JSONCompareMode.STRICT);
    }

    @Test
    void listTest() throws Exception {
        newRequest();
        newRequest();

        MvcResult mvcResult =
                mvc.perform(
                        MockMvcRequestBuilders.get("/config/list")
                                .header("Connection", "close")
                                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        var response = mvcResult.getResponse().getContentAsString();
        log.debug("response: " + response);
        JSONAssert.assertEquals(
                new ResourceFile("config_list_result_01.json")
                        .readAsString(),
                response,
                JSONCompareMode.STRICT);
    }

    @Test
    void getTest() throws Exception {
        newRequest();

        MvcResult mvcResult =
                mvc.perform(
                        MockMvcRequestBuilders.get("/config/get?id=1")
                                .header("Connection", "close")
                                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        var response = mvcResult.getResponse().getContentAsString();
        log.debug("response: " + response);
        JSONAssert.assertEquals(
                new ResourceFile("config_get_result_01.json")
                        .readAsString(),
                response,
                JSONCompareMode.STRICT);
    }

    MvcResult newRequest() throws Exception {
        var mvcResult =  mvc.perform(
                    MockMvcRequestBuilders.post("/config/new")
                            .content(new ResourceFile("config_new_01.json").readAsString())
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .header("Connection", "close")
                            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        return mvcResult;
    }

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mvc;

    @Autowired
    private RestTemplate restTemplate;
}