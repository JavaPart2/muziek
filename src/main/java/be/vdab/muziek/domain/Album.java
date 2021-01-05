package be.vdab.muziek.domain;

import javax.persistence.*;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String naam;
    private int score;
    private int artiestid;

    public Album(String naam, int score, int artiestid) {
        this.naam = naam;
        this.score = score;
        this.artiestid = artiestid;
    }

    public Album() {
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

    public int getArtiestid() {
        return artiestid;
    }
}
