package cq_spa_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cq_spa_demo.questions.IRawQuestionFactory;
import cq_spa_demo.questions.RawSelectable;
import cq_spa_demo.questions.RawTip;
import cq_spa_demo.questions.SkipLinesWriter;

@RestController
public class QuestionsController {
	private final IRawQuestionFactory factory;

	private final SkipLinesWriter skipLinesWriter;

	private final List<Integer> skipLines;

	@Autowired
	public QuestionsController(final Builder builder) {
		this.factory = builder.factory;
		this.skipLinesWriter = builder.skipLinesWriter;
		this.skipLines = builder.skipLines;
	}

	@RequestMapping(method = RequestMethod.GET, value = "question")
	public RawSelectable getQuestion() {
		final RawSelectable fourOptionsQuestion = this.factory.getSelectable();
		this.skipLines.add(fourOptionsQuestion.getLine());
		this.skipLinesWriter.write(this.skipLines);
		return fourOptionsQuestion;
	}

	@RequestMapping(method = RequestMethod.GET, value = "tip")
	public RawTip getTip() {
		final RawTip tipQuestion = this.factory.getTip();
		this.skipLines.add(tipQuestion.getLine());
		this.skipLinesWriter.write(this.skipLines);
		return tipQuestion;
	}

	public static final class Builder {
		IRawQuestionFactory factory;

		SkipLinesWriter skipLinesWriter;

		List<Integer> skipLines;

		public QuestionsController build() {
			return new QuestionsController(this);
		}

		public Builder setFactory(final IRawQuestionFactory factory) {
			this.factory = factory;
			return this;
		}

		public Builder setSkipLines(final List<Integer> skipLines) {
			this.skipLines = skipLines;
			return this;
		}

		public Builder setSkipLinesWriter(final SkipLinesWriter skipLinesWriter) {
			this.skipLinesWriter = skipLinesWriter;
			return this;
		}
	}
}
