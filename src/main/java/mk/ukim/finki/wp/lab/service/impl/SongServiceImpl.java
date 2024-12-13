package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepositoryInMemory;
import mk.ukim.finki.wp.lab.repository.ArtistRepositoryInMemory;
import mk.ukim.finki.wp.lab.repository.SongRepositoryInMemory;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
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
    public Song addArtistToSong(Long songId, Long artistId) {
        Song song = songRepository.findById(songId)
                .orElseThrow();
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow();

        song.getPerformers().add(artist);
        songRepository.save(song);
        return song;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }
    @Override
    public Optional<Song> findById(Long songId) {
        return songRepository.findById(songId);
    }

    public Optional<Song> save(String trackId, String title, String genre, Integer releaseYear, Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow();

        Song postoi = songRepository.findByTrackId(trackId);
        if (postoi != null) {
            songRepository.delete(postoi);
        }

        Song song = new Song(trackId, title, genre, releaseYear, album);
        Song savedSong = songRepository.save(song);

        return Optional.of(savedSong);
    }

    @Override
    public Optional<Song> update(String trackId, String title, String genre, Integer releaseYear, Long albumId, List<Artist> artists) {
        Album album = albumRepository.findById(albumId).orElseThrow();
        Song song = new Song(trackId, title, genre, releaseYear, album);
        song.setPerformers(artists);
        Song savedSong = songRepository.save(song);
        return Optional.of(savedSong);
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }

    @Override
    public List<Song> findSongsByAlbumId(Long albumId) {
        return songRepository.findAllByAlbum_Id(albumId);
    }
}

