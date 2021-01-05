package be.vdab.muziek.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "albums")
@NamedEntityGraph(name = "Album.metArtiest", attributeNodes = @NamedAttributeNode("artiest"))
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String naam;
    private int score;
    @ManyToOne(optional = false)
    @JoinColumn(name = "artiestid")
    private Artiest artiest;
    @ElementCollection
    @CollectionTable(name = "tracks", joinColumns = @JoinColumn(name = "albumid"))
    @OrderBy("naam")
    private Set<Track> tracks;

    public Album(String naam, int score, Artiest artiest) {
        this.naam = naam;
        this.score = score;
        this.artiest = artiest;
    }

    public Album() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return Objects.equals(naam, album.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public int getScore() {
        return score;
    }
}
