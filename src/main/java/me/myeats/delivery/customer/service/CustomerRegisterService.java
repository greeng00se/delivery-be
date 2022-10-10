package me.myeats.delivery.customer.service;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.exception.customer.CustomerNameDuplicateException;
import me.myeats.delivery.customer.domain.Customer;
import me.myeats.delivery.customer.domain.CustomerRepository;
import me.myeats.delivery.customer.dto.request.CustomerRegisterRequestDto;
import me.myeats.delivery.customer.dto.response.CustomerRegisterResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomerRegisterService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CustomerRegisterResponseDto register(CustomerRegisterRequestDto registerDto) {

        if (!customerRepository.existsByName(registerDto.getName())) {
            throw new CustomerNameDuplicateException();
        }

        Customer customer = Customer.builder()
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerRegisterResponseDto(savedCustomer.getName());
    }
}
