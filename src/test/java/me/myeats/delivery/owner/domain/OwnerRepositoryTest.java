package me.myeats.delivery.owner.domain;

import me.myeats.delivery.fixture.OwnerFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @BeforeEach
    void clean() {
        ownerRepository.deleteAll();
    }

    @Test
    @DisplayName("이름을 통한 단일 조회")
    void findOneByName() {
        // givens
        Owner owner = OwnerFixtures.owner().build();
        ownerRepository.save(owner);

        // when
        Owner findOwner = ownerRepository.findOneByName("green").get();

        // then
        assertThat(findOwner).isEqualTo(owner);
    }
}
