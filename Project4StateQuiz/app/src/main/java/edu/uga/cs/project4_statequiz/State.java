package edu.uga.cs.project4_statequiz;
public class State {
    private String state;
    private String capital;
    private String city1;
    private String city2;
    private int id;

    // Constructor
    public State(String state, String capital, String city1, String city2, int id) {
        this.state = state;
        this.capital = capital;
        this.city1 = city1;
        this.city2 = city2;
        this.id = id;
    }

    // Getters and setters
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
