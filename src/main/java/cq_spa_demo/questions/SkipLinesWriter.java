package cq_spa_demo.questions;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkipLinesWriter {
	private static final Logger LOG = LoggerFactory.getLogger(SkipLinesWriter.class);

	private final String encoding;

	private final String fileName;

	public SkipLinesWriter(final String fileName, final String encoding) {
		this.fileName = fileName;
		this.encoding = encoding;
	}

	public void write(final List<Integer> skipLines) {
		try (
			FileOutputStream fos = new FileOutputStream(this.fileName);
			OutputStreamWriter or = new OutputStreamWriter(fos, this.encoding);
			BufferedWriter br = new BufferedWriter(or)) {
			final String stringify = skipLines.stream().map(Object::toString).collect(Collectors.joining(","));
			or.write(stringify);
		} catch (final IOException e) {
			LOG.debug("{}", e);
			throw new RuntimeException(e);
		}
	}
}
