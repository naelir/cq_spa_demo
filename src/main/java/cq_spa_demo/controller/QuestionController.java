package cq_spa_demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cq_spa_demo.factory.IQuestionFactory;
import cq_spa_demo.model.Translatable;
import cq_spa_demo.model.Selectable;
import cq_spa_demo.model.Tipable;
import cq_spa_demo.questions.LineWriter;

@RestController
public class QuestionController {
	private static final String DEFAULT_THEME = "default_theme";

	private final IQuestionFactory questionFactory;

	private final LineWriter translatablesWriter;

	private final Map<String, Translatable> translatables;

	private final LineWriter translatedWriter;

	private final Map<String, Selectable> selectables;

	private final LineWriter selectablesWriter;

	@Autowired
	public QuestionController(final Builder builder) {
		this.questionFactory = builder.factory;
		this.translatablesWriter = builder.translatablesWriter;
		this.translatables = builder.translatables;
		this.translatedWriter = builder.translatedWriter;
		this.selectables = builder.selectables;
		this.selectablesWriter = builder.selectablesWriter;
	}

	@RequestMapping(method = RequestMethod.GET, value = "selectable")
	public Selectable getSelectable() {
		final Selectable fourOptionsQuestion = this.questionFactory.getSelectable();
		return fourOptionsQuestion;
	}

	@RequestMapping(method = RequestMethod.POST, value = "selectable", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void submitSelectable(@RequestBody final Selectable selectable) {
		final String theme = selectable.getTheme();
		if (theme == null || theme.isEmpty())
			selectable.setTheme(DEFAULT_THEME);
		this.translatedWriter.write(selectable, true);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "selectable/{id}")
	public void deleteSelectable(@PathVariable final String id) {
		this.selectables.remove(id);
		this.selectablesWriter.write(this.selectables, false);
	}

	@RequestMapping(method = RequestMethod.GET, value = "tipable")
	public Tipable getTipable() {
		final Tipable tipQuestion = this.questionFactory.getTip();
		return tipQuestion;
	}

	@RequestMapping(method = RequestMethod.GET, value = "translatable")
	public Translatable getTranslatable() {
		final Translatable translatable = this.questionFactory.getTranslatable();
		return translatable;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "translatable/{id}")
	public void deleteTranslatable(@PathVariable final String id) {
		this.translatables.remove(id);
		this.translatablesWriter.write(this.translatables, false);
	}

	public static final class Builder {
		Map<String, Selectable> selectables;

		IQuestionFactory factory;

		LineWriter translatablesWriter;

		LineWriter selectablesWriter;

		Map<String, Translatable> translatables;

		LineWriter translatedWriter;

		public QuestionController build() {
			return new QuestionController(this);
		}

		public Builder setFactory(final IQuestionFactory factory) {
			this.factory = factory;
			return this;
		}

		public Builder setSelectablesWriter(final LineWriter selectablesWriter) {
			this.selectablesWriter = selectablesWriter;
			return this;
		}

		public Builder setTranslatablesWriter(final LineWriter translatablesWriter) {
			this.translatablesWriter = translatablesWriter;
			return this;
		}

		public Builder setTranslatables(final Map<String, Translatable> translatables) {
			this.translatables = translatables;
			return this;
		}

		public Builder setSelectables(Map<String, Selectable> selectables) {
			this.selectables = selectables;
			return this;
		}

		public Builder setTempSelectableWriter(LineWriter tempSelectableWriter) {
			this.translatedWriter = tempSelectableWriter;
			return this;
		}
	}
}
