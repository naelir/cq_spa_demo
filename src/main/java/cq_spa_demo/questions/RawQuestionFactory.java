package cq_spa_demo.questions;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class RawQuestionFactory implements IRawQuestionFactory {
	private static final RawTip DEFAULT_TIP = new RawTip(-1, "no tips", 1);

	private static final RawSelectable DEFAULT_QUESTION = new RawSelectable(-1, "no questions", new Option[] {
			new Option("$", true), new Option("%", false), new Option("/", false), new Option("#", false) });

	private final Map<Integer, RawSelectable> selectables;

	private final Map<Integer, RawTip> tips;

	public RawQuestionFactory(final Map<Integer, RawTip> tips, final Map<Integer, RawSelectable> selectables) {
		this.tips = tips;
		this.selectables = selectables;
	}

	@Override
	public RawSelectable getSelectable() {
		if (this.selectables.isEmpty())
			return RawQuestionFactory.DEFAULT_QUESTION;
		else {
			final Iterator<Entry<Integer, RawSelectable>> iterator = this.selectables.entrySet().iterator();
			final Entry<Integer, RawSelectable> next = iterator.next();
			iterator.remove();
			return next.getValue();
		}
	}

	@Override
	public RawTip getTip() {
		if (this.tips.isEmpty())
			return RawQuestionFactory.DEFAULT_TIP;
		else {
			final Iterator<Entry<Integer, RawTip>> iterator = this.tips.entrySet().iterator();
			final Entry<Integer, RawTip> next = iterator.next();
			iterator.remove();
			return next.getValue();
		}
	}
}
