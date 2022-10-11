package me.myeats.delivery.common.jwt.owner;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.exception.authentication.UnauthorizedException;
import me.myeats.delivery.owner.domain.OwnerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class OwnerUserDetailsService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    @Override
    @Transactional
    public User loadUserByUsername(String name) {
        return ownerRepository.findOneByName(name)
                .map(OwnerUserDetails::new)
                .orElseThrow(UnauthorizedException::new);
    }
}
