package uk.co.geekonabicycle.icalextractor.rules;

import net.fortuna.ical4j.filter.Rule;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Summary;

public class TitleRule implements Rule {

	private String title;

	public TitleRule(String title) {
		this.title = title;
	}

	@Override
	public boolean match(Object event) {
		if (event instanceof VEvent) {
			Summary summary = ((VEvent) event).getSummary();
			return summary.getValue().contains(title);
		}
		return false;
	}

}
