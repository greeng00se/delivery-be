package me.myeats.delivery.customer.service;

import me.myeats.delivery.common.exception.authentication.UnauthorizedException;
import me.myeats.delivery.common.jwt.TokenProvider;
import me.myeats.delivery.customer.domain.Customer;
import me.myeats.delivery.customer.domain.CustomerRepository;
import me.myeats.delivery.customer.dto.request.CustomerLoginRequestDto;
import me.myeats.delivery.customer.dto.response.CustomerLoginResponseDto;
import me.myeats.delivery.test.fixture.CustomerFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class CustomerLoginServiceTest {

    @Autowired
    private CustomerLoginService customerLoginService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Test
    @DisplayName("로그인 성공")
    void loginSuccess() {
        // given
        Customer customer = CustomerFixtures.customer().build();
        customerRepository.save(customer);

        CustomerLoginRequestDto request = CustomerLoginRequestDto.builder()
                .name("green")
                .password("goose")
                .build();

        // when
        CustomerLoginResponseDto response = customerLoginService.login(request);

        // then
        assertThat(tokenProvider.validateToken(response.getToken())).isTrue();
    }

    @Test
    @DisplayName("로그인 실패")
    void loginFail() {
        // given
        CustomerLoginRequestDto request = CustomerLoginRequestDto.builder()
                .name("green")
                .password("goose")
                .build();

        // expect
        assertThatThrownBy(() -> customerLoginService.login(request))
                .isInstanceOf(UnauthorizedException.class);
    }
}
