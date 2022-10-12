package me.myeats.delivery.customer.service;

import me.myeats.delivery.common.exception.customer.CustomerNameDuplicateException;
import me.myeats.delivery.customer.domain.Customer;
import me.myeats.delivery.customer.domain.CustomerRepository;
import me.myeats.delivery.customer.dto.request.CustomerRegisterRequestDto;
import me.myeats.delivery.customer.dto.response.CustomerRegisterResponseDto;
import me.myeats.delivery.test.fixture.CustomerFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class CustomerRegisterServiceTest {

    @Autowired
    private CustomerRegisterService customerRegisterService;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void clean() {
        customerRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공")
    void registerSuccess() {
        // given
        CustomerRegisterRequestDto request = CustomerRegisterRequestDto.builder()
                .name("green")
                .email("green@naver.com")
                .password("goose")
                .build();

        // when
        CustomerRegisterResponseDto response = customerRegisterService.register(request);
        Customer customer = customerRepository.findAll().get(0);

        // then
        assertThat(customerRepository.count()).isEqualTo(1);
        assertThat(response.getName()).isEqualTo("green");
        assertThat(customer.getName()).isEqualTo("green");
    }

    @Test
    @DisplayName("이름 중복으로 인한 회원가입 실패")
    void registerFail() {
        // given
        Customer customer = CustomerFixtures.customer().build();
        customerRepository.save(customer);

        CustomerRegisterRequestDto request = CustomerRegisterRequestDto.builder()
                .name("green")
                .email("green@naver.com")
                .password("goose")
                .build();

        // expect
        assertThatThrownBy(() -> customerRegisterService.register(request))
                .isInstanceOf(CustomerNameDuplicateException.class);
    }
}
