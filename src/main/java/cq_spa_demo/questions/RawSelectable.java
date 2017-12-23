package cq_spa_demo.questions;

import java.util.Arrays;

public final class RawSelectable {
	private final String question;

	private final Option[] options;

	private final int line;

	public RawSelectable(final int line, final String question, final Option[] options) {
		this.line = line;
		this.question = question;
		this.options = options;
	}

	public int getLine() {
		return this.line;
	}

	public Option[] getOptions() {
		return this.options;
	}

	public String getQuestion() {
		return this.question;
	}

	@Override
	public String toString() {
		return "RawQuestion [question=" + this.question + ", options=" + Arrays.toString(this.options) + "]";
	}
}
