package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidAlbumIdException;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model){

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("songs", this.songService.listSongs());
        return "listSongs";
    }
//==============================================================//
//    @GetMapping("/songs/add-form")
//    public String getAddSongPage(Model model){
//        List<Artist> artists = this.artistService.listArtists();
//        List<Album> albums = this.albumService.findAll();
//        model.addAttribute("artists", artists);
//        model.addAttribute("albums", albums);
//        return "add-song";
//    }
@GetMapping("/songs/add-form")
@PreAuthorize("hasRole('ADMIN')")
public String getAddSongPage(Model model) {
    model.addAttribute("albums", albumService.findAll());
//    model.addAttribute("song", null); //za nova pesna nema ureduvanje
    return "add-song";
}

/*    @PostMapping("/songs/add")
    public String saveSong( @RequestParam(required = false) Long id,
                            @RequestParam(required = false) String trackId,
                            @RequestParam(required = false) String title,
                            @RequestParam(required = false) String genre,
                            @RequestParam(required = false) Integer releaseYear,
                            @RequestParam(required = false) Long albumId){
         //Album album = albumService.findById(albumId).orElseThrow(() -> new InvalidAlbumIdException(albumId));
          if(id == null){
              songService.save(trackId, title, genre, releaseYear, albumId);
              return "redirect:/songs";
          }
          //nema da ima scenario kade sto id!=null
          songService.update(trackId, title, genre, releaseYear, albumId, id);
          return "redirect:/songs";
    }*/
@PostMapping("/songs/add")
@PreAuthorize("hasRole('ADMIN')")
public String saveSong(@RequestParam String trackId,
                       @RequestParam String title,
                       @RequestParam String genre,
                       @RequestParam Integer releaseYear,
                       @RequestParam Long albumId,
                       @RequestParam (required = false) List<Long> artistIds) {
    if (artistIds == null || artistIds.isEmpty()) {
        songService.save(trackId, title, genre, releaseYear, albumId);
    } else {
        List<Artist> artists = artistService.listByIds(artistIds);
        songService.update(trackId, title, genre, releaseYear, albumId, artists);
    }
    return "redirect:/songs";
}


    @GetMapping("/songs/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);
        return "redirect:/songs";
    }

    //============================================================//
//    @GetMapping("/songs/edit-form/{id}")
//    public String getEditSongForm(@PathVariable Long id, Model model){
//        Song song = this.songService.findByTrackId(id);
//        List<Artist> artists = this.artistService.listArtists();
//        List<Album> albums = this.albumService.findAll();
//        model.addAttribute("song", song);
//        model.addAttribute("artists", artists);
//        model.addAttribute("albums", albums);
//        return "add-song";
//    }
@GetMapping("/songs/edit-form/{id}")
@PreAuthorize("hasRole('ADMIN')")
public String getEditSongForm(@PathVariable Long id, Model model) {
    Song song = songService.findById(id).get();
    model.addAttribute("song", song);
    model.addAttribute("albums", albumService.findAll());
    model.addAttribute("artists", artistService.listArtists());
    return "add-song";
}


//    @GetMapping("/songs/edit/{songId}")
//    public String editSong(@RequestParam(required = false) String trackId,
//                           @RequestParam(required = false) String title,
//                           @RequestParam(required = false) String genre,
//                           @RequestParam(required = false) Integer releaseYear,
//                           @RequestParam(required = false) Long albumId,
//                           @RequestParam (required = false) List<Artist> artistIds){
//          songService.update(trackId, title, genre, releaseYear, albumId, artistIds);
//        return "redirect:/songs";
//    }

    @GetMapping("/songs/by-album/{albumId}")
    public String getSongsByAlbum(@PathVariable Long albumId, Model model) {
        List<Song> songs = this.songService.findSongsByAlbumId(albumId);
        model.addAttribute("songs", songs);
        return "listSongs";
    }
}

