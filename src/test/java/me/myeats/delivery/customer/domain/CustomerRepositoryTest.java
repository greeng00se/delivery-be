package me.myeats.delivery.customer.domain;

import me.myeats.delivery.test.fixture.CustomerFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("이름을 통한 단일 조회")
    void findOneByName() {
        // given
        Customer customer = CustomerFixtures.customer().build();
        customerRepository.save(customer);

        // when
        Customer findCustomer = customerRepository.findOneByName("green").get();

        // then
        assertThat(findCustomer).isEqualTo(customer);
    }

    @Test
    @DisplayName("이름이 없는 경우 True 반환")
    void existsByNameReturnTrue() {
        // given
        Customer customer = CustomerFixtures.customer()
                .name("HELLO")
                .build();
        customerRepository.save(customer);

        // except
        assertThat(customerRepository.existsByName("HELLO")).isTrue();
    }

    @Test
    @DisplayName("이름이 없는 경우 False 반환")
    void existsByNameReturnFalse() {
        // except
        assertThat(customerRepository.existsByName("NOTHING")).isFalse();
    }
}
