package uk.co.geekonabicycle.icalextractor;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import uk.co.geekonabicycle.icalextractor.rules.TitleRule;


import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.filter.PeriodRule;
import net.fortuna.ical4j.filter.Rule;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Period;

import com.google.common.collect.Lists;

public class Main {

	private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static void main(String[] args) throws IOException, ParseException {
		if (args.length < 2)
			printUsageAndExit();

		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);

		if (!inputFile.exists() || !inputFile.canRead()) {
			printUsageAndExit();
		}

		List<Rule> eventRules = Lists.newArrayList();

		for (int arg = 2; arg < args.length; arg++) {
			if (args[arg].toLowerCase().equals("-period")) {
				try {
					DateTime periodStart = new DateTime(
							dateTimeFormatter.parse(args[arg + 1]));
					DateTime periodEnd = new DateTime(
							dateTimeFormatter.parse(args[arg + 2]));
					arg += 2;

					eventRules.add(new PeriodRule(new Period(periodStart,
							periodEnd)));
				} catch (ArrayIndexOutOfBoundsException exception) {
					printUsageAndExit();
				} catch (IllegalArgumentException exception) {
					printUsageAndExit();
				}
			} else if (args[arg].toLowerCase().equals("-title")) {
				try {
					String titleFilter = args[arg + 1];
					arg++;

					eventRules.add(new TitleRule(titleFilter));
				} catch (ArrayIndexOutOfBoundsException exception) {
					printUsageAndExit();
				}
			} else
				printUsageAndExit();
		}

		ICalExtractor iCalExtractor = new ICalExtractor(inputFile, outputFile,
				eventRules);

		try {
			int numberOfEntries = iCalExtractor.extract();
			System.out.println("Wrote " + numberOfEntries + " entries to "
					+ outputFile.getPath());
		} catch (ParserException e) {
			System.out.println("Failed to parse iCal fil.");
		}
	}

	private static void printUsageAndExit() {
		System.out
				.println("Usage: java -jar icalextractor-VERSION.jar inputfile outputfile [options]");
		System.out
				.println("Options: \n -period from to \t Filters output for entries within the stated two ISO dates."
						+ "\n -title word \t Filters output for entries with the title word.");
		System.exit(1);
	}
}
