package de.wko.mdb.types;

public class ScoredTitel {
    int score;
    Titel titel;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Titel getTitel() {
        return titel;
    }

    public void setTitel(Titel titel) {
        this.titel = titel;
    }
}
