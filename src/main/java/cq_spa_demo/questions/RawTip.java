package cq_spa_demo.questions;

public final class RawTip {
	private final String question;

	private final int answer;

	private int line;

	public RawTip(final int line, final String question, final int answer) {
		this.line = line;
		this.question = question;
		this.answer = answer;
	}

	public int getAnswer() {
		return this.answer;
	}

	public int getLine() {
		return this.line;
	}

	public String getQuestion() {
		return this.question;
	}

	@Override
	public String toString() {
		return "RawTip [question=" + this.question + ", answer=" + this.answer + "]";
	}
}
