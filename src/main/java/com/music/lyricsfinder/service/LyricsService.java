package com.music.lyricsfinder.service;

import com.music.lyricsfinder.dto.SongResult;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LyricsService {

    private final WebClient webClient;

    public LyricsService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.lyrics.ovh/v1").build();
        // à remplacer par une API adaptée (Genius, Musixmatch, etc.)
    }

    public SongResult findSongByLyrics(String lyricsSnippet) {
        // Ici, on simule car la plupart des APIs n’acceptent pas directement les lyrics comme query
        // Il faudra utiliser une API spécialisée (Musixmatch par ex.) et configurer une clé d’API
        if (lyricsSnippet.toLowerCase().contains("i just wanna tell you how i'm feeling")) {
            return new SongResult("Never Gonna Give You Up", "Rick Astley");
        }
        return new SongResult("Inconnu", "Inconnu");
    }
}
