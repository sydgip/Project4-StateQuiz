package edu.uga.cs.project4;
/**
 * Represents a quiz question with information about a state, its capital, and additional cities.
 */
public class QuizQuestion {
    private String state;
    private String capital;
    private String additionalCity1;
    private String additionalCity2;

    /**Constructs a QuizQuestion with the specified state, capital, and additional cities. This is the Method that was accidentally commented out. we only added the comment notation*/
    public QuizQuestion(String state, String capital, String additionalCity1, String additionalCity2) {
        this.state = state;
        this.capital = capital;
        this.additionalCity1 = additionalCity1;
        this.additionalCity2 = additionalCity2;
    }
    /**
     * Gets the state associated with the quiz question.
     */
    public String getState() {
        return state;
    }
    /**
     * Gets the capital city associated with the quiz question.
     */
    public String getCapital() {
        return capital;
    }
    /**
     * Gets the first additional city associated with the quiz question.
     */
    public String getAdditionalCity1() {
        return additionalCity1;
    }
    /**
     * Gets the second additional city associated with the quiz question.
     */
    public String getAdditionalCity2() {
        return additionalCity2;
    }
}
