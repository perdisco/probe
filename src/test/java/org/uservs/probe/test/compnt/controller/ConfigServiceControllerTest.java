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
@Log4j2
class ConfigServiceControllerTest {

    @BeforeEach
    void setUp() {
        if (mvc == null) {
            mvc = MockMvcBuilders
                    .webAppContextSetup(webApplicationContext)
                    .build();
        }

        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listTest() throws Exception {
        mockRestServiceServer
                .expect(requestTo("http://db_service/config"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withSuccess(new ResourceFile("config_list_01.json")
                                        .readAsString(),
                        MediaType.APPLICATION_JSON));

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

        mockRestServiceServer.verify();
    }

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mvc;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;
}