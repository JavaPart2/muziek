package be.vdab.muziek.domain;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
public class Track {
    private String naam;
    private LocalTime tijd;

    protected Track() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;
        Track track = (Track) o;
        return Objects.equals(naam, track.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }

    public String getNaam() {
        return naam;
    }

    public LocalTime getTijd() {
        return tijd;
    }
}
