package com.penchev.perfume.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.penchev.perfume.models.binding.PerfumeBindingModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerfumeRestControllerIntegrationTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testSavePerfume() throws Exception {

        PerfumeBindingModel oneMillion = PerfumeBindingModel.builder()
                .name("Paco Rabanne 1 Million")
                .price("149.99")
                .description("Луксозан аромат затворен в невероятно скъп флакон, облечен в 18 каратово злато. Няма равнодушни към този мъжки парфюм, истински шедьовър в съвременната парфюмерия. Новаторски, непреходен различен, гениален. Олицетворение на власт, богатство, лукс и дълготрайност. В създаването му са си дали среща трима от най-известните носове Christophe Raynaud, Olivier Pescheux, Michel Girard. Представен на пазара 2008 г. Топ аромат от тогава до днес.")
                .videoUrl("https://youtu.be/4VPHVygoca4")
                .discount("20")
                .ean("3349668566372")
                .qty("40")
                .aromaCombination("Невероятен ароматен коктейл съставен от освежаващи елементи като секси червен портокал и грейпфрут, средната нотка идва с деликатна роза, канела и подправки. Пачули, кожа и дървесни нюанси формират базовите нотки на този уникален аромат - идеален за мъже, които обичат и въплъщават лукса, кожа, бяло дърво, пачули и кехлибар.")
                .hasWrap(true)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(oneMillion);


        MvcResult result = mvc.perform(post("/api/perfumes")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", hasLength(36)))
                .andExpect(jsonPath("$.name", is("Paco Rabanne 1 Million")))
                .andExpect(jsonPath("$.price", is(149.99)))
                .andExpect(jsonPath("$.description", is("Луксозан аромат затворен в невероятно скъп флакон, облечен в 18 каратово злато. Няма равнодушни към този мъжки парфюм, истински шедьовър в съвременната парфюмерия. Новаторски, непреходен различен, гениален. Олицетворение на власт, богатство, лукс и дълготрайност. В създаването му са си дали среща трима от най-известните носове Christophe Raynaud, Olivier Pescheux, Michel Girard. Представен на пазара 2008 г. Топ аромат от тогава до днес.")))
                .andExpect(jsonPath("$.videoUrl", is("https://youtu.be/4VPHVygoca4")))
                .andExpect(jsonPath("$.discount", is(20)))
                .andExpect(jsonPath("$.ean", is("3349668566372")))
                .andExpect(jsonPath("$.qty", is(40)))
                .andExpect(jsonPath("$.aromaCombination", is("Невероятен ароматен коктейл съставен от освежаващи елементи като секси червен портокал и грейпфрут, средната нотка идва с деликатна роза, канела и подправки. Пачули, кожа и дървесни нюанси формират базовите нотки на този уникален аромат - идеален за мъже, които обичат и въплъщават лукса, кожа, бяло дърво, пачули и кехлибар.")))
                .andExpect(jsonPath("$.hasWrap", is(true)))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(201, result.getResponse().getStatus());

    }

}
