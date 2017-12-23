package cq_spa_demo.factory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cq_spa_demo.model.Translatable;

public class FileTranslatableLoader {
	private static final Logger LOG = LoggerFactory.getLogger(FileTranslatableLoader.class);

	private final String fileName;

	private final String encoding;

	public FileTranslatableLoader(final String fileName, final String encoding) {
		this.fileName = fileName;
		this.encoding = encoding;
	}

	public Map<String, Translatable> load() {
		try (
			FileInputStream fis = new FileInputStream(this.fileName);
			InputStreamReader isr = new InputStreamReader(fis, this.encoding);
			BufferedReader in = new BufferedReader(isr)) {
			final Map<String, Translatable> translatables = new ConcurrentSkipListMap<>();
			for (String inputLine = in.readLine(); inputLine != null; inputLine = in.readLine()) {
				final String[] line = inputLine.split("\\|");
				if (line.length == 3) {
					final String id = line[0];
					final String forTranslate = line[1];
					final String toTranslate = line[2];
					translatables.put(id, new Translatable(id, forTranslate, toTranslate));
				} else
					LOG.error("invalid line: {}", inputLine);
			}
			return translatables;
		} catch (final IOException e) {
			LOG.error("file not found: ", e);
		}
		return Collections.emptyMap();
	}
}
