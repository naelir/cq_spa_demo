package cq_spa_demo.factory;

import cq_spa_demo.model.Translatable;
import cq_spa_demo.model.Selectable;
import cq_spa_demo.model.Tipable;

public interface IQuestionFactory {
	Selectable getSelectable();

	Tipable getTip();

	Translatable getTranslatable();
}