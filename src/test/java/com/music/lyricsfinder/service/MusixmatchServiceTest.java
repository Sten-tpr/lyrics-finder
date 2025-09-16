package com.music.lyricsfinder.service;

import com.music.lyricsfinder.dto.SongResult;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MusixmatchServiceTest {

    private MockWebServer mockWebServer;
    private MusixmatchService service;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        String baseUrl = mockWebServer.url("/").toString();
        service = new MusixmatchService(baseUrl, "FAKE_KEY");
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void findSongByLyrics_returnsHit() {
        String jsonResponse = """
            {
              "message": {
                "body": {
                  "track_list": [
                    {
                      "track": {
                        "track_name": "Never Gonna Give You Up",
                        "artist_name": "Rick Astley"
                      }
                    }
                  ]
                }
              }
            }
            """;

        mockWebServer.enqueue(new MockResponse()
                .setBody(jsonResponse)
                .addHeader("Content-Type", "application/json"));

        SongResult result = service.findSongByLyrics("I just wanna tell you how I'm feeling");

        assertEquals("Never Gonna Give You Up", result.title());
        assertEquals("Rick Astley", result.artist());
    }

    @Test
    void findSongByLyrics_returnsUnknown_whenEmpty() {
        String emptyJson = """
            {
              "message": {
                "body": {
                  "track_list": []
                }
              }
            }
            """;

        mockWebServer.enqueue(new MockResponse()
                .setBody(emptyJson)
                .addHeader("Content-Type", "application/json"));

        SongResult result = service.findSongByLyrics("unknown snippet");

        assertEquals("Inconnu", result.title());
        assertEquals("Inconnu", result.artist());
    }
}
