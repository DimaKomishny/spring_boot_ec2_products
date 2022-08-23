package com.dmytro.komyshnyi.ec2.controller;

import com.dmytro.komyshnyi.ec2.config.ExceptionHandlerController;
import com.dmytro.komyshnyi.ec2.dto.ProducerDto;
import com.dmytro.komyshnyi.ec2.facade.ProducerFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


class ProducerControllerTest {

    private MockMvc mockMvc;
    private ProducerFacade producerFacade = mock(ProducerFacade.class);
    private ProducerController sut = new ProducerController(producerFacade);

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(sut)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();
    }

    @Test
    public void test() throws Exception {
        ProducerDto producer = new ProducerDto(UUID.randomUUID(), "producer_name", "Ukraine");
        Mockito.when(producerFacade.findById(producer.getId()))
                .thenReturn(producer);

        mockMvc.perform(get("/producers/" + producer.getId()))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(producer.getId().toString())))
                .andExpect(jsonPath("name", is(producer.getName())))
                .andExpect(jsonPath("country", is(producer.getCountry())));
    }
}