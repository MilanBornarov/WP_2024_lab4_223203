package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {


    public List<Album> findAll(){
        return DataHolder.albumList;
    }

    public Optional<Album> findAlbumById(long albumId) {
        return DataHolder.albumList.stream().filter(a -> a.getId()==albumId).findFirst();
    }
}
