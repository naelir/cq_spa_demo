package cq_spa_demo.questions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkipLinesFactory {
	private static final Logger LOG = LoggerFactory.getLogger(SkipLinesFactory.class);

	private final String fileName;

	private final String encoding;

	public SkipLinesFactory(final String fileName, final String encoding) {
		this.fileName = fileName;
		this.encoding = encoding;
	}

	public List<Integer> getSkipLines() {
		final List<Integer> skipLines = new ArrayList<>();
		try (
			FileInputStream fin = new FileInputStream(this.fileName);
			InputStreamReader inr = new InputStreamReader(fin, this.encoding);
			BufferedReader in = new BufferedReader(inr)) {
			for (String inputLine = in.readLine(); inputLine != null; inputLine = in.readLine()) {
				final String[] indices = inputLine.split(",");
				for (final String indice : indices)
					skipLines.add(Integer.valueOf(indice));
			}
			LOG.debug("loaded {} skip lines", skipLines.size());
		} catch (final IOException e) {
			LOG.debug("{}", e);
			throw new RuntimeException(e);
		}
		return skipLines;
	}
}