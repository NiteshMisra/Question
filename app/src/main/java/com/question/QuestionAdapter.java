package com.question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class QuestionAdapter extends PagerAdapter {

    private List<QuestionModel> questionList;
    private Context context;
    private LayoutInflater layoutInflater;

    public QuestionAdapter(List<QuestionModel> questionList, Context context) {
        this.questionList = questionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_question,container,false);

        TextView text = view.findViewById(R.id.item_question_text);
        text.setText(questionList.get(position).getText());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
