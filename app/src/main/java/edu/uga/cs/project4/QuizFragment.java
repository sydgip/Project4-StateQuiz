package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class QuizFragment extends Fragment {

    private ViewPager viewPager;
    private QuestionPagerAdapter questionPagerAdapter;

    private String[] quizQuestions = {
            "Question 1: What is the capital of State 1?",
            "Question 2: What is the capital of State 2?",
            "more questions"
            // Add more questions here...
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

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
                questionCounterTextView.setText("Question " + questionNumber + " of " + quizQuestions.length);

                // Check if the user has reached the last question
                if (position == quizQuestions.length - 1) {
                    // Set the current item to display QuizResultFragment
                    viewPager.setCurrentItem(position + 1);
                }
                else if (position == quizQuestions.length) {
                    replaceWithQuizResultFragment();
                }
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
            return QuizQuestionFragment.newInstance(quizQuestions[position]);
        }

        @Override
        public int getCount() {
            return quizQuestions.length;
        }
    }

    // Method to replace the current fragment with QuizResultFragment
    private void replaceWithQuizResultFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        String quizResult = calculateQuizResult(); // Calculate the quiz result
        QuizResultFragment resultFragment = QuizResultFragment.newInstance(quizResult);
        transaction.replace(R.id.viewPager, resultFragment);
        transaction.commit();
    }

    // Implement the calculateQuizResult() method to calculate the quiz result
    private String calculateQuizResult() {
        // Implement your logic to calculate the result based on user answers
        return "Quiz Result Goes Here";
    }
}