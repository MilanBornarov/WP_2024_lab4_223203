package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Artist> artistList = null;
    public static List<Song> songList = null;
    public static List<Album> albumList = null;

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    public DataHolder(SongRepository songRepository, ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    @PostConstruct //se povikuva metodot init() vednas po startuvanje na aplikacija i inicijalizacija na klasata
    public void init() {
        artistList = new ArrayList<>();
        songList = new ArrayList<>();
        albumList = new ArrayList<>();

        if (artistRepository.count() == 0) {
            artistList.add(new Artist("Freddie", "Mercury", "The legendary lead singer of Queen, known for his powerful vocals, dynamic performances, and hits like 'Bohemian Rhapsody' and 'We Will Rock You'."));
            artistList.add(new Artist("Beyonce", "Knowles", "An American singer, songwriter, and actress, famous for her powerful vocals, dynamic stage presence, and hits like 'Halo' and 'Single Ladies'."));
            artistList.add(new Artist("Elvis", "Presley", "An American rock and roll icon known as the 'King of Rock,' with hits like 'Jailhouse Rock' and 'Can't Help Falling in Love'."));
            artistList.add(new Artist("Taylor", "Swift", "An American singer-songwriter known for her narrative songwriting and crossover from country to pop with songs like 'Love Story' and 'Shake It Off'."));
            artistList.add(new Artist("John", "Lennon", "An English singer, songwriter, and peace activist, co-founder of The Beatles, known for songs like 'Imagine' and 'Give Peace a Chance'."));
            artistRepository.saveAll(artistList);
        }
        if (albumRepository.count() == 0) {
            albumList.add(new Album("A Night at the Opera", "Rock", "1975"));
            albumList.add(new Album("I Am... Sasha Fierce", "Pop", "2008"));
            albumList.add(new Album("Jailhouse Rock", "Rock and Roll", "1957"));
            albumList.add(new Album("Fearless", "Country/Pop", "2008"));
            albumList.add(new Album("Imagine", "Rock", "1971"));
            albumRepository.saveAll(albumList);
        }
        if (songRepository.count() == 0) {
            songList.add(new Song("S001", "Bohemian Rhapsody", "Rock", 1975, albumList.get(0)));
            songList.add(new Song("S002", "Halo", "Pop", 2008, albumList.get(1)));
            songList.add(new Song("S003", "Jailhouse Rock", "Rock and Roll", 1957, albumList.get(2)));
            songList.add(new Song("S004", "Love Story", "Country/Pop", 2008, albumList.get(3)));
            songList.add(new Song("S005", "Imagine", "Rock", 1971, albumList.get(4)));
            songRepository.saveAll(songList);
        }
    }

}
