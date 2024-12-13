package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private String releaseYear;
    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Song> songs;

    public Album(String name, String genre, String releaseYear) {
//        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.songs = new ArrayList<>();
    }
    public Album(String name, String genre, String releaseYear, List<Song> songs) {
//        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.songs = songs;
    }

}
