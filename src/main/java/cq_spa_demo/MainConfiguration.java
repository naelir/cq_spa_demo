package cq_spa_demo;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cq_spa_demo.controller.QuestionsController;
import cq_spa_demo.controller.QuestionsController.Builder;
import cq_spa_demo.questions.*;

//@formatter:off
@Configuration
public class MainConfiguration {
	private static final Logger LOG = LoggerFactory.getLogger(MainConfiguration.class);
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final String QUESTIONS_FILE = "questions.txt";
	private static final String INDICES_FILE = "indices.txt";
  
	@Bean
	public Builder main() {
		final Questions questions = new FileQuestionsLoader(QUESTIONS_FILE, DEFAULT_ENCODING).getQuestions();
		final Map<Integer, RawTip> tips = questions.getTips();
		final Map<Integer, RawSelectable> selectables = questions.getSelectables();
		final IRawQuestionFactory rawQuestionFactory = new RawQuestionFactory(tips, selectables);
		final List<Integer> skipLines = new SkipLinesFactory(INDICES_FILE, DEFAULT_ENCODING).getSkipLines();
		final SkipLinesWriter skipLinesWriter = new SkipLinesWriter(INDICES_FILE, DEFAULT_ENCODING);
 
		for (final Integer skipLine : skipLines) {
			selectables.remove(skipLine.intValue());
			tips.remove(skipLine.intValue());
		}
		LOG.info("after removing. selectables: {}, tips {}", selectables.size(), tips.size());
		final QuestionsController.Builder builder = new QuestionsController.Builder()
			.setFactory(rawQuestionFactory)
			.setSkipLines(skipLines)
			.setSkipLinesWriter(skipLinesWriter);
		return builder;
	}
}
