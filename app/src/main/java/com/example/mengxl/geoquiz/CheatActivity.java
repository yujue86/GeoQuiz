package com.example.mengxl.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends AppCompatActivity {

    private Button mShowAnswer;
    private TextView mAnswerTextView;

    private boolean mAnswerIs;
    private boolean mIsCheater;

    public static final String EXTRA_ANSWER_IS = "mengxl.answer_is";
    public static final String EXTRA_IS_CHEATER = "mengxl.is_cheater";
    private static final String KEY_ISCHEATER = "ischeater";

    private void setAnswerShown(){
        if(mAnswerIs)
            mAnswerTextView.setText(R.string.true_button);
        else
            mAnswerTextView.setText(R.string.false_button);
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        mIsCheater = isAnswerShown;
        Intent data = new Intent();
        data.putExtra(EXTRA_IS_CHEATER,isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setAnswerShownResult(false);

        mAnswerIs = getIntent().getBooleanExtra(EXTRA_ANSWER_IS,false);

        mAnswerTextView = (TextView) findViewById(R.id.show_answer_textview);

        mShowAnswer = (Button)findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswerShown();
                setAnswerShownResult(true);
            }
        });

        if(savedInstanceState != null){
            boolean isCheater = savedInstanceState.getBoolean(KEY_ISCHEATER);
            if(isCheater) {
                setAnswerShown();
                setAnswerShownResult(true);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_ISCHEATER,mIsCheater);
    }
}
