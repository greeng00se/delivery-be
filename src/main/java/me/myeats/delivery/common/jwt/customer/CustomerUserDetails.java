package me.myeats.delivery.common.jwt.customer;

import lombok.Getter;
import me.myeats.delivery.customer.domain.Customer;
import org.springframework.security.core.userdetails.User;

import java.util.List;

import static me.myeats.delivery.common.jwt.AuthRole.ROLE_CUSTOMER;

@Getter
public class CustomerUserDetails extends User {

    private Customer customer;

    public CustomerUserDetails(Customer customer) {
        super(customer.getName(), customer.getPassword(), List.of(ROLE_CUSTOMER.getAuthority()));
        this.customer = customer;
    }
}
