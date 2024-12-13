package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @PostMapping
    public String getArtistsPage(@RequestParam(required = false) String error,
                                 @RequestParam(required = false) Long songId,
                                 Model model){

//        Song song = songService.listSongs().stream().filter(b -> b.getId() == songId).findAny().orElse(null);
        Song song = songService.findById(songId).get();
        if(song!=null){
            model.addAttribute("artists", artistService.listArtists());
            model.addAttribute("song", song);
            return "artistList";
        }
        return "artistList";
    }

    @PostMapping("/add-artist")
    public String addArtistsToSong(@RequestParam Long songId,
                                   @RequestParam Long artistId){


//        Artist artist = artistService.listArtists().stream().filter(b -> b.getId() == artistId).findAny().orElse(null);
        songService.addArtistToSong(songId, artistId);

        return "redirect:/songs";
    }
}