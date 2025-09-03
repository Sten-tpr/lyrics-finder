package com.music.lyricsfinder.dto.musixmatch;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {

    @JsonProperty("track_name")
    private String trackName;

    @JsonProperty("artist_name")
    private String artistName;

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
