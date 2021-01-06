package be.vdab.muziek.repository;

import be.vdab.muziek.domain.Album;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaAlbumRepository implements AlbumRepository{
    private final EntityManager manager;

    public JpaAlbumRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Album> findAll() {
        return manager.createQuery("select a from Album a order by a.naam", Album.class)
                .getResultList();
/*
        return manager.createNamedQuery("Album.findAll", Album.class)
                .setHint("javax.persistence.loadgraph", manager.createEntityGraph("Album.metArtiest"))
                .getResultList();
*/
    }

    @Override
    public Optional<Album> findById(int id) {
        return Optional.ofNullable(manager.find(Album.class, id));
    }
}
