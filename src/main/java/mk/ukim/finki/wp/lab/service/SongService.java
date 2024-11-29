package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, long songId);
    Song findByTrackId(Long trackId);

    public Optional<Song> save(String trackId, String title, String genre, Integer releaseYear, long albumId);
    public Optional<Song> update(String trackId, String title, String genre, Integer releaseYear, long albumId, long id);
    void deleteById(Long id);
}
