package edu.uga.cs.project4;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
/**
 * The QuizActivity class represents the main activity for the quiz application.
 * It initializes the database by reading data from a CSV file using CSVreader, and
 * then sets up and displays the QuizFragment to start the quiz.
 */
public class QuizActivity extends AppCompatActivity {
    /**
     * Called when the activity is first created. This is where you should do all of your normal
     * static set up: create views, bind data to lists, etc.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CSVreader.readCSV(this);
        setContentView(R.layout.activity_quiz); // Set the activity layout

        // Load and display the QuizFragment
        QuizFragment quizFragment = new QuizFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, quizFragment);
        transaction.commit();
    }

}