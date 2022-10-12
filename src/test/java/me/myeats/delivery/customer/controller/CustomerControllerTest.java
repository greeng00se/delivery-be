package me.myeats.delivery.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.myeats.delivery.customer.domain.Customer;
import me.myeats.delivery.customer.domain.CustomerRepository;
import me.myeats.delivery.customer.dto.request.CustomerLoginRequestDto;
import me.myeats.delivery.customer.dto.request.CustomerRegisterRequestDto;
import me.myeats.delivery.test.fixture.CustomerFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void register() throws Exception {
        // given
        CustomerRegisterRequestDto request = CustomerRegisterRequestDto.builder()
                .name("green")
                .email("green@naver.com")
                .password("goose")
                .build();

        String json = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(post("/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated())
                .andDo(print());

        // then
        assertThat(customerRepository.count()).isEqualTo(1L);

        Customer customer = customerRepository.findAll().get(0);
        assertThat(customer.getName()).isEqualTo("green");
        assertThat(customer.getEmail()).isEqualTo("green@naver.com");
    }

    @Test
    @DisplayName("로그인 성공")
    void login() throws Exception {
        // given
        Customer customer = CustomerFixtures.customer().build();
        customerRepository.save(customer);

        CustomerLoginRequestDto request = CustomerLoginRequestDto.builder()
                .name("green")
                .password("goose")
                .build();
        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/customer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andDo(print());
    }
}
