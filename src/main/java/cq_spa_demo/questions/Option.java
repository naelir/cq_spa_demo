package cq_spa_demo.questions;

public class Option {

	private final String text;

	private final boolean isCorrect;

	public Option(final String text, final boolean isCorrect) {
		this.text = text;
		this.isCorrect = isCorrect;
	}

	public String getText() {
		return this.text;
	}

	public boolean isCorrect() {
		return this.isCorrect;
	}

}
