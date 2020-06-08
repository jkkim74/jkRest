package com.example.jkrest.demo.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createEvent() throws Exception {
        List<Person> attendantList = new ArrayList<>();
        attendantList.add(new Person("영철","서울"));
        attendantList.add(new Person("순희","성남"));
        Event event = Event.builder()
                .name("Spring")
                .description("Rest API")
                .beginEnrollmentDateTime(LocalDateTime.of(2020,06,9,00,53))
                .closeEnrollmentDateTime(LocalDateTime.of(2020,06,9,00,58))
                .beginEventDateTime(LocalDateTime.of(2020,06,9,00,53))
                .endEventDateTime(LocalDateTime.of(2020,06,9,00,53))
                .basePrice(100)
                .limitOfEnrollment(100)
                .location("강남역")
                .attendant(attendantList)
                .build();
        mockMvc.perform(post("/api/events")
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
                 .accept(MediaTypes.HAL_JSON)
                 .content(objectMapper.writeValueAsString(event))
                 )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists());
    }
}
