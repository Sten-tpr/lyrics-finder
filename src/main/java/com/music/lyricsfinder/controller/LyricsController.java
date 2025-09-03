package com.music.lyricsfinder.controller;

import com.music.lyricsfinder.dto.SongResult;
import com.music.lyricsfinder.service.MusixmatchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lyrics")
@CrossOrigin(origins = "http://localhost:4200")
public class LyricsController {

    private final MusixmatchService musixmatchService;

    public LyricsController(MusixmatchService musixmatchService) {
        this.musixmatchService = musixmatchService;
    }

    @GetMapping("/search")
    public SongResult searchLyrics(@RequestParam String query) {
        return musixmatchService.findSongByLyrics(query);
    }
}