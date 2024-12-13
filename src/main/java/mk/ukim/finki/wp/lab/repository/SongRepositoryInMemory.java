package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class SongRepositoryInMemory {
/*    public List<Song> findAll(){
        return DataHolder.songList;
    }
    public Song findByTrackId(String trackId){
        return DataHolder.songList.stream().filter(b->b.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    public Song findById(long id){
        return DataHolder.songList.stream().filter(b->b.getId() == id).findFirst().orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song){
        song.addArtist(artist);
        return artist;
    }

    public Optional<Song> save(String trackId, String title, String genre, Integer releaseYear, long albumId){
            Album album = DataHolder.albumList.stream().filter(b->b.getId() == albumId).findFirst().orElse(null);
            Song song = new Song(trackId, title, genre, releaseYear, album);
            DataHolder.songList.add(song);

            return Optional.of(song);

    }

    public void deleteById(Long id) {
        DataHolder.songList.removeIf(b->b.getId()==id);
    }

    public Optional<Song> update(String trackId, String title, String genre, Integer releaseYear, long albumId, long id){
        Album album = DataHolder.albumList.stream().filter(b->b.getId()==albumId).findFirst().orElse(null);
        DataHolder.songList.removeIf(b->b.getId()==id);
        Song song = new Song(trackId, title, genre, releaseYear, album);
        DataHolder.songList.add(song);

        return Optional.of(song);
    }

    public List<Song> findAllByAlbumId(Long albumId) {
        return DataHolder.songList.stream()
                .filter(song -> song.getAlbum() != null && Objects.equals(song.getAlbum().getId(), albumId))
                .toList();
    }*/
}
