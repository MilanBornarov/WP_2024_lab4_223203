package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Song addArtistToSong(Long songId, Long artistId);
    Song findByTrackId(String trackId);
    Optional<Song> findById(Long songId);
    public Optional<Song> save(String trackId, String title, String genre, Integer releaseYear, Long albumId);
    public Optional<Song> update(String trackId, String title, String genre, Integer releaseYear, Long albumId, List<Artist> artists);
    void deleteById(Long id);

    List<Song> findSongsByAlbumId(Long albumId);
}
