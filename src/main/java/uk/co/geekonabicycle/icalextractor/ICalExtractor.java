package uk.co.geekonabicycle.icalextractor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.filter.Rule;
import net.fortuna.ical4j.model.Component;

public class ICalExtractor {

	private ICalParser iCalParser;
	private TSVWriter tsvWriter;

	public ICalExtractor(File inputFile, File outputFile, List<Rule> rules) {
		iCalParser = new ICalParser(inputFile, rules);
		tsvWriter = new TSVWriter(outputFile);
	}

	public int extract() throws IOException, ParserException {
		Collection<Component> parsedEntries = iCalParser.parse();
		tsvWriter.writeTsv(parsedEntries);
		return parsedEntries.size();
	}
}
