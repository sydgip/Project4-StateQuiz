package edu.uga.cs.project4;
/**
 * Represents the result of a quiz, including quiz ID, date, result, and number of questions answered.
 */
public class QuizResult {
    private int quizId;
    private String quizDate;
    private int quizResult;
    private int questionsAnswered;
    /**
     * Constructs a QuizResult object with the specified quiz information.
     */
    public QuizResult(int quizId, String quizDate, int quizResult, int questionsAnswered) {
        this.quizId = quizId;
        this.quizDate = quizDate;
        this.quizResult = quizResult;
        this.questionsAnswered = questionsAnswered;
    }
    /**
     * Gets the ID of the quiz.
     */
    public int getQuizId() {
        return quizId;
    }
    /**
     * Sets the ID of the quiz.
     */
    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
    /**
     * Gets the date of the quiz.
     */
    public String getQuizDate() {
        return quizDate;
    }
    /**
     * Sets the date of the quiz.
     */
    public void setQuizDate(String quizDate) {
        this.quizDate = quizDate;
    }
    /**
     * Gets the result of the quiz.
     */
    public int getQuizResult() {
        return quizResult;
    }
    /**
     * Sets the result of the quiz.
     */
    public void setQuizResult(int quizResult) {
        this.quizResult = quizResult;
    }
    /**
     * Gets the number of questions answered in the quiz.
     */
    public int getQuestionsAnswered() {
        return questionsAnswered;
    }
    /**
     * Sets the number of questions answered in the quiz.
     */
    public void setQuestionsAnswered(int questionsAnswered) {
        this.questionsAnswered = questionsAnswered;
    }
}
