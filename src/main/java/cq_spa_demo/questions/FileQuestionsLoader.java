package cq_spa_demo.questions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileQuestionsLoader implements IQuestionsLoader {
	private static final Logger LOG = LoggerFactory.getLogger(FileQuestionsLoader.class);

	private final Random generator;

	private final String fileName;

	private final String encoding;

	public FileQuestionsLoader(final String fileName, final String encoding) {
		this.fileName = fileName;
		this.encoding = encoding;
		this.generator = new Random();
	}

	private Option[] createOptions(final String[] rawOptions) {
		final Option[] options = new Option[rawOptions.length];
		for (int i = 0; i < rawOptions.length; i++)
			if (i == 0)
				options[0] = new Option(rawOptions[0], true);
			else
				options[i] = new Option(rawOptions[i], false);
		return options;
	}

	@Override
	public Questions getQuestions() {
		try (
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream(this.fileName), this.encoding))) {
			return this.readQuestions(in);
		} catch (final IOException e) {
			LOG.debug("{}", e);
			throw new RuntimeException(e);
		}
	}

	private Questions readQuestions(final BufferedReader in) throws IOException {
		final Map<Integer, RawSelectable> questions = new ConcurrentHashMap<>();
		final Map<Integer, RawTip> tips = new ConcurrentHashMap<>();
		int line = 0;
		for (String inputLine = in.readLine(); inputLine != null; inputLine = in.readLine()) {
			final String[] question = inputLine.split("\\|");
			if (question.length == 5) {
				final String[] rawOptions = new String[4];
				System.arraycopy(question, 1, rawOptions, 0, 4);
				final Option[] options = this.createOptions(rawOptions);
				this.shuffle(options);
				questions.put(line, new RawSelectable(line, question[0], options));
			}
			if (question.length == 2)
				tips.put(line, new RawTip(line, question[0], Integer.parseInt(question[1])));
			line++;
		}
		LOG.debug("loaded {} selectables, {} tipables", questions.size(), tips.size());
		return new Questions(questions, tips);
	}

	private void shuffle(final Option[] options) {
		for (int i = options.length - 1; i > 0; i--) {
			final int index = this.generator.nextInt(i + 1);
			final Option temp = options[index];
			options[index] = options[i];
			options[i] = temp;
		}
	}
}
