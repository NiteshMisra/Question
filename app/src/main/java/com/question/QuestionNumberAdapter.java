package com.question;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class QuestionNumberAdapter extends RecyclerView.Adapter<QuestionNumberAdapter.NumberViewHolder>{

    private List<Integer> numberList;
    private Context context;
    private EventListener listener;
    private int currentPosition = 0;

    public interface EventListener{
        void onEvent(int position);
    }

    QuestionNumberAdapter(List<Integer> numberList, Context context, EventListener listener) {
        this.numberList = numberList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_number,parent,false);
        RecyclerView.LayoutParams lp  = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        return new NumberViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, final int position) {
        Integer uploadCurrent = numberList.get(position);
        holder.number.setText(String.valueOf(uploadCurrent));
        if (position == currentPosition){
            holder.number.setTextColor(Color.parseColor("#FCFCFC"));
            holder.number.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.dot_white);
            holder.number.setBackgroundResource(R.drawable.question_number_background_colour);
        }else{
            holder.number.setTextColor(Color.parseColor("#131b37"));
            holder.number.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.dot_colour);
            holder.number.setBackgroundResource(R.drawable.question_number_background_white);
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEvent(position);
                currentPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return numberList.size();
    }

    class NumberViewHolder extends RecyclerView.ViewHolder{

        NumberViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        TextView number = itemView.findViewById(R.id.item_question_no);
        LinearLayout layout = itemView.findViewById(R.id.item_question_no_layout);
    }

    void setCurrentPosition(int currentPosition){
        this.currentPosition = currentPosition;
        notifyDataSetChanged();
    }

}
