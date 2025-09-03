package com.music.lyricsfinder.dto.musixmatch;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MusixmatchResponse {
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public static class Message {
        private Body body;

        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }
    }

    public static class Body {
        @JsonProperty("track_list")
        private List<TrackContainer> trackList;

        public List<TrackContainer> getTrackList() {
            return trackList;
        }

        public void setTrackList(List<TrackContainer> trackList) {
            this.trackList = trackList;
        }
    }
}
