package me.myeats.delivery.owner.service;

import lombok.RequiredArgsConstructor;
import me.myeats.delivery.common.exception.owner.OwnerNameDuplicateException;
import me.myeats.delivery.owner.domain.Owner;
import me.myeats.delivery.owner.domain.OwnerRepository;
import me.myeats.delivery.owner.dto.request.OwnerRegisterRequestDto;
import me.myeats.delivery.owner.dto.response.OwnerRegisterResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OwnerRegisterService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public OwnerRegisterResponseDto register(OwnerRegisterRequestDto registerDto) {

        if (!ownerRepository.existsByName(registerDto.getName())) {
            throw new OwnerNameDuplicateException();
        }

        Owner owner = Owner.builder()
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();

        Owner savedOwner = ownerRepository.save(owner);

        return new OwnerRegisterResponseDto(savedOwner.getName());
    }
}
