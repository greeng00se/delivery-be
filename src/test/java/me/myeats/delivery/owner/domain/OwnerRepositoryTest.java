package me.myeats.delivery.owner.domain;

import me.myeats.delivery.test.fixture.OwnerFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("이름을 통한 단일 조회")
    void findOneByName() {
        // given
        Owner owner = OwnerFixtures.owner().build();
        ownerRepository.save(owner);

        // when
        Owner findOwner = ownerRepository.findOneByName("green").get();

        // then
        assertThat(findOwner).isEqualTo(owner);
    }

    @Test
    @DisplayName("이름이 없는 경우 True 반환")
    void existsByNameReturnTrue() {
        // given
        Owner owner = OwnerFixtures.owner()
                .name("HELLO")
                .build();
        ownerRepository.save(owner);

        // except
        assertThat(ownerRepository.existsByName("HELLO")).isTrue();
    }

    @Test
    @DisplayName("이름이 없는 경우 False 반환")
    void existsByNameReturnFalse() {
        // except
        assertThat(ownerRepository.existsByName("NOTHING")).isFalse();
    }
}
