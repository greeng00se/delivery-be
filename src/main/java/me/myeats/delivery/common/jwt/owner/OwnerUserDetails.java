package me.myeats.delivery.common.jwt.owner;

import lombok.Getter;
import me.myeats.delivery.owner.domain.Owner;
import org.springframework.security.core.userdetails.User;

import java.util.List;

import static me.myeats.delivery.common.jwt.AuthRole.ROLE_CUSTOMER;

@Getter
public class OwnerUserDetails extends User {

    private Owner owner;

    public OwnerUserDetails(Owner owner) {
        super(owner.getName(), owner.getPassword(), List.of(ROLE_CUSTOMER.getAuthority()));
        this.owner = owner;
    }
}
