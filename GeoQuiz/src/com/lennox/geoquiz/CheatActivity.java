package com.lennox.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	private static final String TAG = "CheatActivity";

	private Button mShowAnswerButton;
	private boolean isCorrectAnswer;
	private TextView mTextView;
	
	public static final String EXTRA_ANSWER_IS_TRUE =
	        "com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN =
	        "com.bignerdranch.android.geoquiz.answer_shown";

	private void setAnswerShownResult(boolean isAnswerShown)
	{
		Intent intent = new Intent();
		intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, intent);
		
		
	}
	@Override
    protected void onCreate(Bundle savedInstanceState)  {
    	Log.d(TAG, "onCreate(Bundle) called");
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_cheat);
    	
    	isCorrectAnswer = getIntent().getBooleanExtra(GeoQuiz.EXTRA_ANSWER_IS_TRUE, false);
    	
    	mTextView = (TextView)findViewById(R.id.answerTextView);
    	mShowAnswerButton = (Button)findViewById(R.id.showAnswerButton);
    	
    	setAnswerShownResult(false);
    	mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isCorrectAnswer)
				{
					mTextView.setText(R.string.true_button);
				}
				else
				{
					mTextView.setText(R.string.false_button);
				}
				
				setAnswerShownResult(true);
			}
		});
    }

}
