package com.techneapps.triviaapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techneapps.triviaapp.R;
import com.techneapps.triviaapp.database.models.QuizHistory;
import com.techneapps.triviaapp.databinding.SingleQuizHistoryBinding;
import com.techneapps.triviaapp.view.adapter.viewholder.QuizHistoryAdapterViewHolder;

import java.util.List;

public class QuizHistoryAdapter extends RecyclerView.Adapter<QuizHistoryAdapterViewHolder> {
    private Context context;
    private List<QuizHistory> quizHistories;

    public QuizHistoryAdapter(Context context, List<QuizHistory> quizHistories) {
        this.context = context;
        this.quizHistories = quizHistories;
    }

    @NonNull
    @Override
    public QuizHistoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleQuizHistoryBinding singleQuizHistoryBinding=DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.single_quiz_history,parent,false);
        return new QuizHistoryAdapterViewHolder(singleQuizHistoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizHistoryAdapterViewHolder quizHistoryAdapterViewHolder, int position) {
        //binding data to our view holder
        quizHistoryAdapterViewHolder.singleQuizHistoryBinding.setQuizHistory(quizHistories.get(getItemCount()-1-position));
    }

    @Override
    public int getItemCount() {
        return quizHistories.size();
    }
}
