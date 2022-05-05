package com.mercadolibre.grupo1.projetointegrador.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.grupo1.projetointegrador.dtos.SectionSearchDTO;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testes de integração
 * Descrição em cada Displayname
 * @author Nayara Coca
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class SectionTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL = "http://localhost:8080/api/v1/fresh-products";

    @Test
    @WithMockUser(username = "agent1", roles = {"AGENT"})
    @DisplayName("Testa se retorna o espaço disponível no armazém, a capacidade original e a ID da sessão")
    public void itShouldReturnTheTotalAvailableSpace() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/section?warehouseCode=3"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(3)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].sectionCode",Matchers.is(7)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].originalCapacity",Matchers.is(500.0)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].currentCapacity",Matchers.is(225.0)));

    }
    @Test
    @WithMockUser(username = "agent1", roles = {"AGENT"})
    @DisplayName("Testa se retorna mensagem de erro quando o produto não existe em nenhum armazém")
    public void itShouldReturnAnException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/section?warehouseCode=2").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("ARMAZÉM NÃO ENCONTRADO"));
    }
}
