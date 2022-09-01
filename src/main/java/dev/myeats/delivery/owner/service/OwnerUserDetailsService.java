package dev.myeats.delivery.owner.service;

import dev.myeats.delivery.owner.domain.Owner;
import dev.myeats.delivery.owner.domain.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component("userDetailsService")
public class OwnerUserDetailsService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return ownerRepository.findOneWithAuthoritiesByName(name)
                .map(user -> createUser(user))
                .orElseThrow(() -> new UsernameNotFoundException(name + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private User createUser(Owner owner) {
        List<GrantedAuthority> grantedAuthorities = owner.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new User(owner.getName(), owner.getPassword(), grantedAuthorities);
    }
}
