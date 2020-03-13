package com.techneapps.triviaapp.view.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techneapps.triviaapp.databinding.SingleQuizHistoryBinding;

public class QuizHistoryAdapterViewHolder extends RecyclerView.ViewHolder {
    /*
     * view holder for our quiz history single item
     */
    public SingleQuizHistoryBinding singleQuizHistoryBinding;
    public QuizHistoryAdapterViewHolder(@NonNull SingleQuizHistoryBinding singleQuizHistoryBinding) {
        super(singleQuizHistoryBinding.getRoot());
        this.singleQuizHistoryBinding=singleQuizHistoryBinding;
    }
}
