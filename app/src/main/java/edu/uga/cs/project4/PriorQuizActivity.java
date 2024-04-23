package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
/**
 * The PriorQuizActivity class represents the activity that displays information about previous quiz results.
 * It hosts the PriorQuizFragment to show a list of prior quiz results.
 */
public class PriorQuizActivity extends AppCompatActivity {
    /**
     * Called when the activity is first created. This method initializes the activity, sets up the UI,
     * and hosts the PriorQuizFragment to display prior quiz results.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prior_quiz);
        // Enable the Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new PriorQuizFragment()) // Assuming you have a container with the id 'container' in your layout
                    .commit();
        }
    }
}
