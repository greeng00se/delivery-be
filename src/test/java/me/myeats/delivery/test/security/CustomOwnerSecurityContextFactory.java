package me.myeats.delivery.test.security;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.jwt.owner.OwnerUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

import static me.myeats.delivery.common.jwt.AuthRole.ROLE_OWNER;

@RequiredArgsConstructor
public class CustomOwnerSecurityContextFactory implements WithSecurityContextFactory<WithCustomOwner> {

    private final OwnerUserDetailsService ownerUserDetailsService;

    @Override
    public SecurityContext createSecurityContext(WithCustomOwner owner) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User user = ownerUserDetailsService.loadUserByUsername(owner.username());
        Authentication auth = new UsernamePasswordAuthenticationToken(user, "", List.of(ROLE_OWNER.getAuthority()));
        context.setAuthentication(auth);
        return context;
    }
}
