package uk.co.geekonabicycle.icalextractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.filter.Filter;
import net.fortuna.ical4j.filter.Rule;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;

class ICalParser {

	private File calendarFile;
	private List<Rule> rules;

	ICalParser(File calendarFile, List<Rule> rules) {
		this.calendarFile = calendarFile;
		this.rules = rules;
	}

	private Calendar buildCalendar(File calendarFile) throws IOException,
			ParserException {
		FileInputStream fileInputStream = new FileInputStream(calendarFile);
		CalendarBuilder calendarBuilder = new CalendarBuilder();
		return calendarBuilder.build(fileInputStream);
	}

	@SuppressWarnings("unchecked")
	Collection<Component> parse() throws IOException, ParserException {
		Calendar calendar = buildCalendar(calendarFile);
		ComponentList events = calendar.getComponents(Component.VEVENT);
		Filter filter = new Filter(rules.toArray(new Rule[] {}),
				Filter.MATCH_ALL);
		return filter.filter(events);
	}

}
