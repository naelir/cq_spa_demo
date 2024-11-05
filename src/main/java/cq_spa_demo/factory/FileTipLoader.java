package cq_spa_demo.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cq_spa_demo.Converter;
import cq_spa_demo.model.Tipable;

public class FileTipLoader {
	private static final Logger LOG = LoggerFactory.getLogger(FileTipLoader.class);

	private final String fileName;

	private final String encoding;

	public FileTipLoader(final String fileName, final String encoding) {
		this.fileName = fileName;
		this.encoding = encoding;
	}

	public Map<String, Tipable> load() {
		try (BufferedReader in = Converter.decoder(fileName, encoding)) {
			final Map<String, Tipable> tips = new ConcurrentSkipListMap<>();
			for (String inputLine = in.readLine(); inputLine != null; inputLine = in.readLine()) {
				final String[] parts = inputLine.split("\\|");
				if (parts.length == 4) {
					final String type = parts[0];
					final String id = parts[1];
					final String text = parts[2];
					tips.put(id, new Tipable(type, id, text, Integer.parseInt(parts[3])));
				} else
					LOG.error("invalid line: {}", inputLine);
			}
			return tips;
		} catch (final IOException e) {
			LOG.error("file not found: ", e);
		}
		return Collections.emptyMap();
	}
}
