package com.lennox.geoquiz;

public class TrueFalse {

	private int mQuestion;
	
	public int getQuestion() {
		return mQuestion;
	}
	public void setQuestion(int question) {
		mQuestion = question;
	}
	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}
	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}
	private boolean mTrueQuestion;
	public TrueFalse(int question, boolean trueQuestion)
	{
		mQuestion = question;
		mTrueQuestion = trueQuestion;
	}
}
