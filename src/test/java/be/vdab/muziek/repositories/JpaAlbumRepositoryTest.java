package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.repository.JpaAlbumRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaAlbumRepository.class)
@Sql({"/insertArtiest.sql", "/insertAlbum.sql"})
public class JpaAlbumRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaAlbumRepository repository;
    private final EntityManager manager;

    public JpaAlbumRepositoryTest(JpaAlbumRepository repository, EntityManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

    private int idTestAlbum(){
        return super.jdbcTemplate.queryForObject("select id from albums where naam='test'", Integer.class);
    }

    @Test
    void findAll(){
        assertThat(repository.findAll()).hasSize(super.countRowsInTable("albums"))
                .extracting(album -> album.getNaam().toLowerCase()).isSorted();
        assertThat(repository.findAll()).extracting(album -> album.getArtiest().getNaam());
    }

    @Test
    void findById(){
        Album album = repository.findById(idTestAlbum()).get();
        assertThat(album.getNaam()).isEqualTo("test");
        assertThat(album.getArtiest().getNaam()).isEqualTo("test");
        assertThat(album.getTracks().stream().findFirst().get().getTijd()).isEqualTo(LocalTime.of(01, 10));
        assertThat(album.calculateTotaleTrackTime()).isEqualTo(LocalTime.of(01, 10));
    }

    @Test
    void findByOnbestaandeId() {
        assertThat(repository.findById(-1)).isNotPresent();
    }
}
