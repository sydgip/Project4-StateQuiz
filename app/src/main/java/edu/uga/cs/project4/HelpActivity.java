package edu.uga.cs.project4;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
/**
 * The HelpActivity class represents the activity that provides assistance and guidance to the user.
 * This activity displays helpful information or instructions to the user.
 */
public class HelpActivity extends AppCompatActivity {
    /**
     * Called when the activity is first created. This method initializes the activity and sets up the UI..
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Enable the Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }
    /**
     * Called when a menu item is selected. This method handles the selection of menu items in the activity's toolbar.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
