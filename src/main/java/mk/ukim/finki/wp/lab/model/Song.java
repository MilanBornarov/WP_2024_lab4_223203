package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    @ManyToMany
    private List<Artist> performers;
    @ManyToOne
    @ToString.Exclude
    private Album album;

    public Song(String trackId, String title, String genre, int releaseYear, Album album) {
//        this.id = (long) (Math.random()*1000);
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        performers = new ArrayList<>();
        this.album = album;
    }
    public Song(String trackId, String title, String genre, int releaseYear, Album album, List<Artist> performers) {
        //   this.id = (long) (Math.random() * 1000);
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.album = album;
        this.performers = performers;
    }

    public void addArtist(Artist performer){
        performers.add(performer);
    }
}
