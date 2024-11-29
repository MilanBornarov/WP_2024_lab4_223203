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
                                 @RequestParam Long songId,
                                 Model model){

        Song song = songService.listSongs().stream().filter(b -> b.getId() == songId).findAny().orElse(null);
        if(song!=null){
            model.addAttribute("artists", artistService.listArtists());
            model.addAttribute("song", song);
            return "artistList";
        }
        return "artistList";
    }

    @PostMapping("/add-artist")
    public String addArtistsToSong(@RequestParam Long artistId,
                                   @RequestParam Long songId){


        Artist artist = artistService.listArtists().stream().filter(b -> b.getId() == artistId).findAny().orElse(null);
        songService.addArtistToSong(artist, songId);

        return "redirect:/songs";
    }
}