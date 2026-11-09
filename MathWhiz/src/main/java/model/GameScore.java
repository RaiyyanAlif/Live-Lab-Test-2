package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** One row of the game_scores table. */
public class GameScore {
    private static final DateTimeFormatter DISPLAY =
            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a", Locale.ENGLISH);

    private int id;
    private String playerName;
    private int score;
    private String difficulty;
    private LocalDateTime playedAt;

    public GameScore() {}

    public GameScore(String playerName, int score, String difficulty, LocalDateTime playedAt) {
        this.playerName = playerName;
        this.score = score;
        this.difficulty = difficulty;
        this.playedAt = playedAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public LocalDateTime getPlayedAt() { return playedAt; }
    public void setPlayedAt(LocalDateTime playedAt) { this.playedAt = playedAt; }

    /** Date/time ready for display in a JSP. */
    public String getPlayedAtFormatted() {
        return playedAt == null ? "" : playedAt.format(DISPLAY);
    }
}
