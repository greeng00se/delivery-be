package me.myeats.delivery.common.jwt.owner;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.owner.domain.OwnerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class OwnerUserDetailsService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    @Override
    @Transactional
    public User loadUserByUsername(String name) throws UsernameNotFoundException {
        return ownerRepository.findOneByName(name)
                .map(OwnerUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(name + " -> 데이터베이스에서 찾을 수 없습니다."));
    }
}
