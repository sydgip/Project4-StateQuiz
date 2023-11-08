package edu.uga.cs.project4_statequiz;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class QuizFragment extends Fragment {

    private ViewPager viewPager;
    private QuestionPagerAdapter questionPagerAdapter;
    private ArrayList<State> quizContent = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int userScore = 0;
    private int selectedAnswer = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        DBhelper dbHelper = new DBhelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBhelper.TABLE_QUESTIONS + " ORDER BY RANDOM() LIMIT 6", null);

        while (cursor.moveToNext()) {
            String state = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_STATE));
            String capital = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_CAPITAL_CITY));
            String city1 = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_ADDITIONAL_CITY2));
            String city2 = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_ADDITIONAL_CITY3));
            int id = cursor.getInt(cursor.getColumnIndex(DBhelper.KEY_QUESTION_ID));

            State newState = new State(state, capital, city1, city2, id);
            quizContent.add(newState);
            Log.d("QuizFragment", "What is the Capital of  " + state);
            Log.d("QuizFragment", "Capital: " + capital);
            Log.d("QuizFragment", "City 1: " + city1);
            Log.d("QuizFragment", "City 2: " + city2);
            Log.d("QuizFragment", "ID: " + id);
        }

        cursor.close();
        db.close();

        viewPager = view.findViewById(R.id.viewPager);
        questionPagerAdapter = new QuestionPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(questionPagerAdapter);

        final TextView questionCounterTextView = view.findViewById(R.id.questionCounterTextView);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Do nothing here
            }

            @Override
            public void onPageSelected(int position) {
                int questionNumber = position + 1;
                questionCounterTextView.setText("Question " + questionNumber + " of " + quizContent.size());

                Log.d("QuizFragment", "Question - Selected Answer: " + selectedAnswer);
                Log.d("QuizFragment", "User Score: " + userScore);
                checkSelectedAnswer(selectedAnswer);

                // Reset the selected answer to an invalid value for the next question

            }
            private void checkSelectedAnswer(int selectedAnswer) {
                String correctAnswer = quizContent.get(currentQuestionIndex).getCapital(); // Get the correct capital from the quizContent list

                if (selectedAnswer == 1 && correctAnswer.equals(capital)) {
                    userScore++; // Increment the score if the selected answer is 1 (indicating the first option) and it matches the correct capital
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                // Do nothing here
            }
        });

        return view;
    }




    }



    private class QuestionPagerAdapter extends FragmentStatePagerAdapter {

        public QuestionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            State currentState = quizContent.get(position);
            String questionText = "What is the capital of " + currentState.getState();

            return QuizQuestionFragment.newInstance(questionText, currentState.getCapital(), currentState.getCity1(), currentState.getCity2());
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
        @Override
        public int getCount() {
            return quizContent.size();
        }
    }
}

/*
public class QuizFragment extends Fragment {


    private ViewPager viewPager;
    //private QuestionPagerAdapter questionPagerAdapter;
    //Have an array list ArrayList<state>  , state would be a class with state , capital, city 1 , city 2 , and id, fill this array list . the class shoudl have a consructor , setter and getters
    private List<String> quizQuestions = new ArrayList<>();
    //private List<String> quizCapitals = new ArrayList<>();
    //private List<String> quizAdditionalCities = new ArrayList<>();
    //private List<String> quizAdditionalCities2 = new ArrayList<>();
    private ArrayList<State> quizContent = new ArrayList<>();



    private int currentQuestionIndex = 0;
    private int userScore = 0;
    private int selectedAnswer = -1; // Initialize to an invalid value
    private boolean answerChecked = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
// Assuming you have a DBhelper object named dbHelper
        DBhelper dbHelper = new DBhelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBhelper.TABLE_QUESTIONS + " ORDER BY RANDOM() LIMIT 6", null);
        int count = 0;
        while (cursor.moveToNext() && count < 6) {
            do {
                String state = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_STATE));
                String capital = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_CAPITAL_CITY));
                String city1 = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_ADDITIONAL_CITY2));
                String city2 = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_ADDITIONAL_CITY3));
                int id = cursor.getInt(cursor.getColumnIndex(DBhelper.KEY_QUESTION_ID));

                // Create a new State object and add it to the list
                State newState = new State(state, capital, city1, city2, id);
                quizContent.add(newState);
                quizQuestions.add("Question: What is the capital of " + state + "?");
                // Debug log statements to verify the data
                Log.d("QuizFragment", "What is the Capital of  " + state);
                Log.d("QuizFragment", "Capital: " + capital);
                Log.d("QuizFragment", "City 1: " + city1);
                Log.d("QuizFragment", "City 2: " + city2);
                Log.d("QuizFragment", "ID: " + id);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        // Initialize ViewPager and adapter
        viewPager = view.findViewById(R.id.viewPager);
        questionPagerAdapter = new QuestionPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(questionPagerAdapter);


        // Set up the question counter
        final TextView questionCounterTextView = view.findViewById(R.id.questionCounterTextView);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int answer = ((QuizQuestionFragment) questionPagerAdapter.instantiateItem(viewPager, position)).getSelectedAnswer();


                // Save the selected answer
                selectedAnswer = answer;
            }


            @Override
            public void onPageSelected(int position) {
                int questionNumber = position + 1;
                questionCounterTextView.setText("Question " + questionNumber + " of " + quizQuestions.size());


                // Check the selected answer and increment the score
                checkSelectedAnswer(selectedAnswer);
                Log.d("QuizFragment", "Question - Selected Answer: " + selectedAnswer);
                Log.d("QuizFragment", "User Score: " + userScore);


                // Reset the selected answer to an invalid value for the next question
                selectedAnswer = -1;
            }


            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });


        return view;
    }
    private void checkSelectedAnswer(int selectedAnswer) {
        if (selectedAnswer == 1) {
            userScore++; // Increment the score
        }
    }
    private class QuestionPagerAdapter extends FragmentPagerAdapter {


        public QuestionPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        public Fragment getItem(int position) {
            String question = quizQuestions.get(position);


            return QuizQuestionFragment.newInstance(question;
        }




        @Override
        public int getCount() {
             return quizQuestions.size();
        }
    }
    return view;
    }

        DBhelper dbHelper = new DBhelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBhelper.TABLE_QUESTIONS + " ORDER BY RANDOM() LIMIT 6", null);
        int count = 0;
        while (cursor.moveToNext() && count < 6) {
            String state = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_STATE));
            String capital = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_CAPITAL_CITY));
            String additionalCity = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_ADDITIONAL_CITY2));
            String additionalCityy = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_ADDITIONAL_CITY3));
            quizQuestions.add("Question: What is the capital of " + state + "?");
            quizCapitals.add(capital);
            quizAdditionalCities.add(additionalCity);
            quizAdditionalCities2.add(additionalCityy);
            Log.d("quizQuestion", "question: " + quizQuestions.get(count));
            count++;
        }


        cursor.close();
        db.close();


        // Shuffle the quiz questions
            Collections.shuffle(quizQuestion);





    }

        */
