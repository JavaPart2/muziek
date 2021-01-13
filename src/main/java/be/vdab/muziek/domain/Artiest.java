package be.vdab.muziek.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "artiesten")
public class Artiest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String naam;

    public Artiest(String naam) {
        this.naam = naam;
    }

    protected Artiest() {
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
