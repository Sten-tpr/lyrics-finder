package com.music.lyricsfinder.service;

import com.music.lyricsfinder.dto.SongResult;
import com.music.lyricsfinder.dto.musixmatch.MusixmatchResponse;
import com.music.lyricsfinder.dto.musixmatch.TrackContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class MusixmatchService {

    private final WebClient webClient;
    private final String apiKey;

    public MusixmatchService(@Value("${musixmatch.base.url}") String baseUrl,
                             @Value("${musixmatch.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        this.apiKey = apiKey;
    }

    public SongResult findSongByLyrics(String snippet) {
        MusixmatchResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/track.search")
                        .queryParam("q_lyrics", snippet)
                        .queryParam("page_size", 1)
                        .queryParam("apikey", apiKey)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MusixmatchResponse.class)
                .block();

        if (response == null
                || response.getMessage() == null
                || response.getMessage().getBody() == null) {
            return new SongResult("Inconnu", "Inconnu");
        }

        List<TrackContainer> tracks = response.getMessage().getBody().getTrackList();
        if (tracks == null || tracks.isEmpty()) {
            return new SongResult("Inconnu", "Inconnu");
        }

        var track = tracks.get(0).getTrack();
        return new SongResult(track.getTrackName(), track.getArtistName());
    }
}
