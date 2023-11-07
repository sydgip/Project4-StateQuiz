package edu.uga.cs.project4;
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
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizFragment extends Fragment {

    private ViewPager viewPager;
    private QuestionPagerAdapter questionPagerAdapter;
    private List<String> quizQuestions = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        DBhelper dbHelper = new DBhelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBhelper.TABLE_QUESTIONS, null);
        int count = 0;
        while (cursor.moveToNext() && count < 6) {
            String state = cursor.getString(cursor.getColumnIndex(DBhelper.KEY_STATE));
            quizQuestions.add("Question: What is the capital of " + state + "?");
            Log.d("quizQeustion", "question: " + quizQuestions.get(count));
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int questionNumber = position + 1;
                questionCounterTextView.setText("Question " + questionNumber + " of " + quizQuestions.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return view;
    }


    private class QuestionPagerAdapter extends FragmentPagerAdapter {

        public QuestionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return QuizQuestionFragment.newInstance(quizQuestions.get(position));
        }

        @Override
        public int getCount() {
            return quizQuestions.size();
        }
    }
}