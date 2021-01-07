package be.vdab.muziek;

import be.vdab.muziek.services.DefaultAlbumService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(DefaultAlbumService.class)
@ComponentScan(value = "be.vdab.muziek.repository", resourcePattern = "JpaAlbumRepository.class")
@Sql({"/insertArtiest.sql", "/insertAlbum.sql"})
public class DefaultMuziekServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final DefaultAlbumService service;
    private final EntityManager manager;

    public DefaultMuziekServiceIntegrationTest(DefaultAlbumService service, EntityManager manager) {
        this.service = service;
        this.manager = manager;
    }

    private int idVanTestAlbum() {
        return super.jdbcTemplate.queryForObject( "select id from albums where naam='test'", Integer.class);
    }

    @Test
    void wijzigScore() {
        var id = idVanTestAlbum();
        service.wijzigScore(id, 10);
        manager.flush();
        assertThat(super.jdbcTemplate.queryForObject( "select score from albums where id=?", Integer.class, id))
                .isEqualTo(10);
    }
}
