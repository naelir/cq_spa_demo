package cq_spa_demo.questions;

import java.util.Map;

public class Questions {
	private final Map<Integer, RawSelectable> selectables;

	private final Map<Integer, RawTip> tips;

	public Questions(final Map<Integer, RawSelectable> selectables, final Map<Integer, RawTip> tips) {
		this.selectables = selectables;
		this.tips = tips;
	}

	public Map<Integer, RawSelectable> getSelectables() {
		return this.selectables;
	}

	public Map<Integer, RawTip> getTips() {
		return this.tips;
	}
}
