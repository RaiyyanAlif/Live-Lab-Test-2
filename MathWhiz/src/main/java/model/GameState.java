package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Everything about the game in progress for one player (kept in the HTTP session). */
public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String playerName;
    private final List<Attempt> attempts = new ArrayList<>();
    private int score;

    // Prime question waiting for the player's answer
    private Integer primeBase;
    private Integer primeExtra;
    private Integer primeNumber;

    private boolean finished;
    private boolean saved;
    private String saveError;

    public GameState(String playerName) {
        this.playerName = playerName;
    }

    public void record(Attempt attempt) {
        attempts.add(attempt);
        score += attempt.getMarks();
    }

    private Attempt find(MathModule module) {
        for (Attempt a : attempts) {
            if (a.getModule() == module) {
                return a;
            }
        }
        return null;
    }

    public void startPrime(int base, int extra) {
        this.primeBase = base;
        this.primeExtra = extra;
        this.primeNumber = base + extra;
    }

    public void clearPrime() {
        primeBase = null;
        primeExtra = null;
        primeNumber = null;
    }

    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }
    public int getMaxScore() { return MathModule.totalMarks(); }
    public List<Attempt> getAttempts() { return attempts; }
    public int getAttemptCount() { return attempts.size(); }

    public Integer getPrimeBase() { return primeBase; }
    public Integer getPrimeExtra() { return primeExtra; }
    public Integer getPrimeNumber() { return primeNumber; }

    public Attempt getPrimeAttempt() { return find(MathModule.PRIME); }
    public Attempt getModAttempt() { return find(MathModule.MOD); }
    public Attempt getTrigAttempt() { return find(MathModule.TRIG); }

    public boolean isPrimeDone() { return getPrimeAttempt() != null; }
    public boolean isModDone() { return getModAttempt() != null; }
    public boolean isTrigDone() { return getTrigAttempt() != null; }
    public boolean isAllDone() { return isPrimeDone() && isModDone() && isTrigDone(); }

    public boolean isFinished() { return finished; }
    public void setFinished(boolean finished) { this.finished = finished; }

    public boolean isSaved() { return saved; }
    public void setSaved(boolean saved) { this.saved = saved; }

    public String getSaveError() { return saveError; }
    public void setSaveError(String saveError) { this.saveError = saveError; }
}
