package me.myeats.delivery.test.security;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.jwt.owner.OwnerUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

@RequiredArgsConstructor
public class CustomSecurityContextFactory implements WithSecurityContextFactory<WithCustomOwner> {

    private final OwnerUserDetailsService ownerUserDetailsService;

    @Override
    public SecurityContext createSecurityContext(WithCustomOwner owner) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User user = ownerUserDetailsService.loadUserByUsername(owner.username());
        Authentication auth = new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
