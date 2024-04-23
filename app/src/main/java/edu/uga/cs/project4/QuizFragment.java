package edu.uga.cs.project4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * A Fragment representing the quiz functionality.
 * It displays a set of quiz questions using a ViewPager and allows users to answer them.
 */
public class QuizFragment extends Fragment {

    private ViewPager viewPager;
    private QuestionPagerAdapter questionPagerAdapter;
    private List<QuizQuestion> quizQuestions = new ArrayList<>();
    public int userScore = 0;
    private int selectedAnswer = -1;
    /**
     * Called to have the fragment instantiate its user interface view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        DBhelper dbHelper = new DBhelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBhelper.TABLE_QUESTIONS + " ORDER BY RANDOM() LIMIT 6", null);
        int count = 0;

        while (cursor.moveToNext() && count < 6) {
            String state = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_STATE));
            String capital = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_CAPITAL_CITY));
            String additionalCity = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_ADDITIONAL_CITY2));
            String additionalCityy = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_ADDITIONAL_CITY3));

            quizQuestions.add(new QuizQuestion(state, capital, additionalCity, additionalCityy));
            count++;
        }

        cursor.close();
        db.close();

        // Shuffle the quiz questions
        Collections.shuffle(quizQuestions);

        // Initialize ViewPager and adapter
        viewPager = view.findViewById(R.id.viewPager);
        questionPagerAdapter = new QuestionPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(questionPagerAdapter);

        // Set up the question counter
        final TextView questionCounterTextView = view.findViewById(R.id.questionCounterTextView);
        final TextView UserScoreText = view.findViewById(R.id.UserScoreText);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < quizQuestions.size()) {
                    int answer = ((QuizQuestionFragment) questionPagerAdapter.instantiateItem(viewPager, position)).getSelectedAnswer();
                    selectedAnswer = answer;
                }
            }

            @Override
            public void onPageSelected(int position) {
                int questionNumber = position + 1;
                questionCounterTextView.setText("Question " + questionNumber + " of " + quizQuestions.size());
                checkSelectedAnswer(selectedAnswer);
                UserScoreText.setText("User Score: " + userScore);

                selectedAnswer = -1;

                if (position == quizQuestions.size()) {
                    saveQuizResultToDatabase();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }
    /**
     * Checks the selected answer and updates the user's score accordingly.
     */
    private void checkSelectedAnswer(int selectedAnswer) {
        if (selectedAnswer == 1) {
            userScore++;
        }
    }

    /**
     * A custom FragmentStatePagerAdapter for managing quiz questions.
     */
    private class QuestionPagerAdapter extends FragmentStatePagerAdapter {
        /**
         * Constructor for the QuestionPagerAdapter.
         */
        public QuestionPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            if (position < quizQuestions.size()) {
                QuizQuestion quizQuestion = quizQuestions.get(position);
                String question = "Question: What is the capital of " + quizQuestion.getState() + "?";
                return QuizQuestionFragment.newInstance(question, quizQuestion.getCapital(), quizQuestion.getAdditionalCity1(), quizQuestion.getAdditionalCity2());
            } else if (position == quizQuestions.size()) {
                return QuizResultFragment.newInstance("Quiz Result: " + userScore + " / " + quizQuestions.size());
            } else {
                return null;
            }
        }

        @Override
        public int getCount() {
            return quizQuestions.size() + 1;
        }
    }
    /**
     * Saves the quiz result to the database.
     */
    private void saveQuizResultToDatabase() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        int result = userScore;
        int questionsAnswered = quizQuestions.size();

        SaveQuizResultTask saveQuizResultTask = new SaveQuizResultTask(getContext(), date, result, questionsAnswered);
        saveQuizResultTask.execute();
    }

    /**
     * A static AsyncTask class for saving quiz results to the database in the background.
     */
    private static class SaveQuizResultTask extends AsyncTask<Void, Void, Void> {
        private Context context;
        private String date;
        private int result;
        private int questionsAnswered;
        /**
         * Constructor for SaveQuizResultTask.
         */
        public SaveQuizResultTask(Context context, String date, int result, int questionsAnswered) {
            this.context = context;
            this.date = date;
            this.result = result;
            this.questionsAnswered = questionsAnswered;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DBhelper dbHelper = new DBhelper(context);
            dbHelper.insertQuizResult(date, result, questionsAnswered);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(context, "Quiz result saved!", Toast.LENGTH_SHORT).show();
        }
    }
}
