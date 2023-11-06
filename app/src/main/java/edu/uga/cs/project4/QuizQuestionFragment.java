package edu.uga.cs.project4;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class QuizQuestionFragment extends Fragment {

    // Key for the question argument
    private static final String ARG_QUESTION = "question";

    public QuizQuestionFragment() {
        // Required empty public constructor
    }

    // Factory method to create a new instance of QuizQuestionFragment
    public static QuizQuestionFragment newInstance(String question) {
        QuizQuestionFragment fragment = new QuizQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_question, container, false);

        // Retrieve the question from the arguments
        String question = getArguments().getString(ARG_QUESTION);

        // Find the TextView for the question and set its text
        TextView questionTextView = view.findViewById(R.id.questionTextView);
        questionTextView.setText(question);

        // Add code to handle answer choices here using RadioGroup and RadioButtons

        return view;
    }
}