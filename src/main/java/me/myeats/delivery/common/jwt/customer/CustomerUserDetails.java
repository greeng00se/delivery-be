package me.myeats.delivery.common.jwt.customer;

import lombok.Getter;
import me.myeats.delivery.customer.domain.Customer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class CustomerUserDetails extends User {

    private Customer customer;

    public CustomerUserDetails(Customer customer) {
        super(customer.getName(), customer.getPassword(), List.of(new SimpleGrantedAuthority(customer.getAuthority().name())));
        this.customer = customer;
    }
}
