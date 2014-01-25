package com.lennox.geoquiz;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuiz extends Activity {

	private Button mTrueButton;
	private Button mFalseButton;
	private TextView mQuestionTextView;
	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	private static final String CHEATER_INDEX = "cheater";

	
	private int mCurrentIndex = 0;
	private Button mNextButton;
	private Button mPrevButton;
	private Button mCheatButton;
	private boolean mIsCheater;
	
	public static final String EXTRA_ANSWER_IS_TRUE="com.lennox.geoquiz.answer_is_true";
	
	//private ImageButton mNextButton;
	//private ImageButton mPrevButton;
	private TrueFalse[] mQuestionBank = new TrueFalse[] {
		new TrueFalse(R.string.question_africa, false),
		new TrueFalse(R.string.question_americas, true),
		new TrueFalse(R.string.question_asia, true),
		new TrueFalse(R.string.question_mideast, false),
		new TrueFalse(R.string.questions_oceans, true)
	};
	
	@Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
    	super.onSaveInstanceState(savedInstanceState);
    	savedInstanceState.putInt(KEY_INDEX,  mCurrentIndex);
    	
    	savedInstanceState.putBoolean(CHEATER_INDEX,  mIsCheater);
    	Log.d(TAG,  "onSaveInstanceState() called.");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
    protected void onCreate(Bundle savedInstanceState)  {
    	Log.d(TAG, "onCreate(Bundle) called");
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.activity_geo_quiz);
        
    		if (savedInstanceState != null) 
    			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
    		else 
    			mCurrentIndex = 0;
        
    		mTrueButton = (Button)findViewById(R.id.true_button);
    		mTrueButton.setOnClickListener(new View.OnClickListener() {
    			@Override
    			public void onClick(View v) {
				checkAnswer(true);
			}
		});
        
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkAnswer(false);
			}
		});
        
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();
        
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIsCheater = false;
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});
        
        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIsCheater = false;
				mCurrentIndex = Math.abs((mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length);
				updateQuestion();
			}
		});
        
        
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GeoQuiz.this, CheatActivity.class);
				intent.putExtra(EXTRA_ANSWER_IS_TRUE, mQuestionBank[mCurrentIndex].isTrueQuestion());
				startActivityForResult(intent, 0);
			}
		});
        
        mIsCheater =  ((savedInstanceState != null) ? savedInstanceState.getBoolean(CHEATER_INDEX): false);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
        	ActionBar actionBar = getActionBar();
        	actionBar.setSubtitle("Geo Quiz");
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	if (intent == null) 
    		return;
    	mIsCheater = intent.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }
    private void checkAnswer(boolean userPressedTrue) 
    {
    	boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
    	int messageResId = 0;
    	
    	if (mIsCheater)
    	{
    		messageResId = R.string.judgement_toast;
    	}
    	else 
    	{
    		if (userPressedTrue == answerIsTrue)
    		{
    			messageResId = R.string.true_button;
    		}
    		else 
    		{
    			messageResId = R.string.false_button;
    		}
    	}
    	
    	Toast.makeText(this,  messageResId,  Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.geo_quiz, menu);
        return true;
    }
    
    private void updateQuestion() {
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
    }
    
}
