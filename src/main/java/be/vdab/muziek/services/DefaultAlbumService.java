package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultAlbumService implements AlbumService{
    private final AlbumRepository repository;

    public DefaultAlbumService(AlbumRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findAllAlbums() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Album> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void wijzigScore(int id, int score) {
        repository.findById(id).ifPresent(album -> album.setScore(score));
    }
}
