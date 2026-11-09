package model;

import java.io.Serializable;

/** One answered question: what was asked, what the player said, and what Java calculated. */
public class Attempt implements Serializable {
    private static final long serialVersionUID = 1L;

    private final MathModule module;
    private final String question;
    private final String yourAnswer;
    private final String correctAnswer;
    private final boolean correct;
    private final int marks;

    public Attempt(MathModule module, String question, String yourAnswer,
                   String correctAnswer, boolean correct) {
        this.module = module;
        this.question = question;
        this.yourAnswer = yourAnswer;
        this.correctAnswer = correctAnswer;
        this.correct = correct;
        this.marks = correct ? module.getMarks() : 0;
    }

    public MathModule getModule() { return module; }
    public String getModuleLabel() { return module.getLabel(); }
    public String getQuestion() { return question; }
    public String getYourAnswer() { return yourAnswer; }
    public String getCorrectAnswer() { return correctAnswer; }
    public boolean isCorrect() { return correct; }
    public int getMarks() { return marks; }
    public int getMaxMarks() { return module.getMarks(); }
}
