package me.myeats.delivery.common.jwt.owner;

import me.myeats.delivery.owner.domain.Owner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class OwnerAccount extends User {

    private Owner owner;

    public OwnerAccount(Owner owner) {
        super(owner.getName(), owner.getPassword(), List.of(new SimpleGrantedAuthority(owner.getAuthority().name())));
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }
}
