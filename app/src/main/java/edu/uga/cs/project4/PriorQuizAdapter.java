package edu.uga.cs.project4;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
/**
 * The PriorQuizAdapter class is a RecyclerView adapter for displaying a list of prior quiz results.
 * It binds the data of QuizResult objects to the corresponding views in the RecyclerView.
 */
public class PriorQuizAdapter extends RecyclerView.Adapter<PriorQuizAdapter.QuizResultViewHolder> {

    private List<QuizResult> quizResultsList;
    /**
     * Constructs a new PriorQuizAdapter with the given list of QuizResult objects.
     */
    public PriorQuizAdapter(List<QuizResult> quizResultsList) {
        this.quizResultsList = quizResultsList;
    }
    /**
     * Called when the RecyclerView needs a new ViewHolder of the given type to represent an item.
     */
    @NonNull
    @Override
    public QuizResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_prior_quiz_list, parent, false);
        return new QuizResultViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    @Override
    public void onBindViewHolder(@NonNull QuizResultViewHolder holder, int position) {
        QuizResult quizResult = quizResultsList.get(position);

        holder.quizDateTimeTextView.setText("Date and Time: " + quizResult.getQuizDate());
        holder.quizScoreTextView.setText("Score: " + quizResult.getQuizResult() + " / " + quizResult.getQuestionsAnswered());
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    @Override
    public int getItemCount() {
        return quizResultsList.size();
    }
    /**
     * The QuizResultViewHolder class represents a ViewHolder for each item in the RecyclerView.
     * It holds references to the views representing quiz date and score.
     */
    public class QuizResultViewHolder extends RecyclerView.ViewHolder {
        public TextView quizDateTimeTextView;
        public TextView quizScoreTextView;

        /**
         * Constructs a new QuizResultViewHolder with the given View.
         */
        public QuizResultViewHolder(View itemView) {
            super(itemView);
            quizDateTimeTextView = itemView.findViewById(R.id.quizDateTimeTextView);
            quizScoreTextView = itemView.findViewById(R.id.quizScoreTextView);
        }
    }
}
