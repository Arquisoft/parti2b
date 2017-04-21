package uo.asw.participants.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ParticipantControllerTest {

    private MediaType contentType = new MediaType(
	    MediaType.APPLICATION_JSON.getType(),
	    MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
	this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void wrongPostParticipantInfo() throws Exception {
	mockMvc.perform(post("/user").content("{\"login\":\"berto@doe.com\"")
		.contentType(contentType)).andExpect(status().isBadRequest());
    }

    @Test
    public void participantNotFound() throws Exception {
	mockMvc.perform(post("/user")
		.content(
			"{\"login\":\"adrian@hotmail.com\", \"password\":\"adrian123\"}")
		.contentType(contentType)).andExpect(status().isNotFound());
    }

    @Test
    public void participantFound() throws Exception {
	mockMvc.perform(post("/user")
		.content(
			"{\"login\":\"paco@doe.com\", \"password\":\"paco123\"}")
		.contentType(contentType)).andExpect(status().isOk());
    }

    @Test
    public void wrongPassword() throws Exception {
	mockMvc.perform(post("/user")
		.content(
			"{\"login\":\"berto@doe.com\", \"password\":\"berto123456\"}")
		.contentType(contentType)).andExpect(status().isNotFound());
    }

    @Test
    public void okChangePassword() throws Exception {
	mockMvc.perform(post("/changePass")
		.content(
			"{\"login\":\"iker@doe.com\", \"password\":\"iker123\", \"newPassword\":\"iker123456\"}")
		.contentType(contentType)).andExpect(status().isOk());

	mockMvc.perform(post("/user")
		.content(
			"{\"login\":\"iker@doe.com\", \"password\":\"iker123456\"}")
		.contentType(contentType)).andExpect(status().isOk());
    }
    
    @Test
    public void wrongPostChangePassword() throws Exception {
	mockMvc.perform(post("/changePass").content("{\"login\":\"berto@doe.com\"")
		.contentType(contentType)).andExpect(status().isBadRequest());
    }

}
