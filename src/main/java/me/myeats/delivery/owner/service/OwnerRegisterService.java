package me.myeats.delivery.owner.service;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.owner.dto.OwnerRegisterDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OwnerRegisterService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public OwnerRegisterDto.Response register(OwnerRegisterDto.Request registerDto) {
        if (ownerRepository.findOneByName(registerDto.getName()).orElse(null) != null) {
            throw new IllegalArgumentException("이미 가입되어 있는 유저입니다.");
        }

        Owner owner = Owner.builder()
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();

        Owner savedOwner = ownerRepository.save(owner);

        return new OwnerRegisterDto.Response(savedOwner.getName());
    }
}
