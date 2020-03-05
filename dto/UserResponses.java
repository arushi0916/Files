package com.cg.dto;

public class UserResponses {
	private String question;
	private String answer;
	public UserResponses() {
		
	}
	public UserResponses(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "UserResponses [question=" + question + ", answer=" + answer + "]";
	}
	
	
	
}
