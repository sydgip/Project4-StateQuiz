package edu.uga.cs.project4;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
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
}