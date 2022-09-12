package me.myeats.delivery.common.jwt.owner;

import lombok.Getter;
import me.myeats.delivery.owner.domain.Owner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class OwnerUserDetails extends User {

    private Owner owner;

    public OwnerUserDetails(Owner owner) {
        super(owner.getName(), owner.getPassword(), List.of(new SimpleGrantedAuthority(owner.getAuthority().name())));
        this.owner = owner;
    }
}
