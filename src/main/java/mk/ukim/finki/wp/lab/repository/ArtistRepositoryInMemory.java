package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepositoryInMemory {
/*    public List<Artist> findAll(){
        return DataHolder.artistList;
    }
    public Optional<Artist> findById(long id){
        return DataHolder.artistList.stream().filter(a -> a.getId()==id).findFirst();
    }*/
}
