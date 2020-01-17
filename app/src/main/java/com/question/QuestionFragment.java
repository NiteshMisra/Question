package com.question;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment implements QuestionNumberAdapter.EventListener {


    private ViewPager questionViewPager;
    private QuestionNumberAdapter questionNumberAdapter;
    private List<Integer> questionNoList;
    private List<QuestionModel> questionList;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.question_number_recyclerView);
        questionViewPager = view.findViewById(R.id.question_viewpager);
        questionNoList  = new ArrayList<>();
        questionList = new ArrayList<>();


        //******************************** After API call you have edit only this much part **************************//
        //********************************************** From Here **********************************************//

        questionList.add(new QuestionModel("Multiple oorrect answer type"));
        questionList.add(new QuestionModel("Multiple oorrect answer type"));
        questionList.add(new QuestionModel("Multiple oorrect answer type"));
        questionList.add(new QuestionModel("Multiple oorrect answer type"));
        questionList.add(new QuestionModel("Multiple oorrect answer type"));
        questionList.add(new QuestionModel("Multiple oorrect answer type"));
        questionList.add(new QuestionModel("Multiple oorrect answer type"));
        questionList.add(new QuestionModel("Multiple oorrect answer type"));
        questionList.add(new QuestionModel("Multiple oorrect answer type"));
        questionList.add(new QuestionModel("Multiple oorrect answer type"));
        questionList.add(new QuestionModel("Multiple oorrect answer type"));
        QuestionAdapter questionAdapter = new QuestionAdapter(questionList,getActivity());
        questionViewPager.setAdapter(questionAdapter);

        // these line ( line number 65-72 ) will generate recycler view present at top of length of size of question number

        int length = questionList.size();
        for (int i = 0 ; i < length;i++){
            questionNoList.add(i+1);
        }
        questionNumberAdapter = new QuestionNumberAdapter(questionNoList,getActivity(),this);
        recyclerView.setAdapter(questionNumberAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));
        questionNumberAdapter.notifyDataSetChanged();


        //********************************************** To Here **********************************************//

        questionViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                questionNumberAdapter.setCurrentPosition(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TextView nextBtn = view.findViewById(R.id.question_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionViewPager.setCurrentItem(questionViewPager.getCurrentItem() + 1,true);
            }
        });


    }


    @Override
    public void onEvent(int pos) {
        questionViewPager.setCurrentItem(pos);
    }
}
