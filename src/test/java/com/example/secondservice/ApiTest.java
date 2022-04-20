package com.example.secondservice;

import com.example.secondservice.controller.RestController;
import com.example.secondservice.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {RestController.class})
@WebMvcTest
public class ApiTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    RestController restController;

    @MockBean
    RestTemplate mockRestTemplate;

    @Test
    public void testListUser() throws Exception {

        User user = new User();
        user.setName("mail.ru");
        List<User> userList = new ArrayList<>();
        userList.add(user);

        ResponseEntity<List<User>> mockListResponseEntity = mock(ResponseEntity.class);

        when(mockRestTemplate.exchange(
                anyString()
                , Mockito.<HttpMethod>any()
                , Mockito.<HttpEntity<String>>any()
                , Mockito.<ParameterizedTypeReference<List<User>>>any()
        ))
                .thenReturn(mockListResponseEntity);

        when(mockListResponseEntity.getBody()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/listuser")
                        .with(jwt())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":0,\"name\":\"mail.ru\",\"role\":null}]"));
    }

}
