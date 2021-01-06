package be.vdab.muziek.domain;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

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
    private Set<Track> tracks;

    public Album(String naam, int score, Artiest artiest) {
        this.naam = naam;
        this.score = score;
        this.artiest = artiest;
        this.tracks = new LinkedHashSet<>();
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
        return Collections.unmodifiableSet(tracks);
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

    public void setScore(int score) {
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime calculateTotaleTrackTime(){
        LocalTime som = LocalTime.of(0, 0, 0);

        for(Track track : this.tracks){
            som = som.plusHours(track.getTijd().getHour())
                    .plusMinutes(track.getTijd().getMinute())
                    .plusSeconds(track.getTijd().getSecond());
        }
        return som;
    }
}
