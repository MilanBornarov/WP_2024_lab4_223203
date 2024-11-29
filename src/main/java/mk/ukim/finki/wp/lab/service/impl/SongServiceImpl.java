package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, long songId) {

        Song song = songRepository.findById(songId);
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(Long trackId) {
        return songRepository.findById(trackId);
    }

    @Override
    public Optional<Song> save(String trackId, String title, String genre, Integer releaseYear, long albumId) {
        return songRepository.save(trackId, title, genre, releaseYear, albumId);
    }

    @Override
    public Optional<Song> update(String trackId, String title, String genre, Integer releaseYear, long albumId, long id) {
        return songRepository.update(trackId, title, genre, releaseYear, albumId, id);
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }
}
