package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> findAllAlbums();
    Optional<Album> findById(int id);
}
