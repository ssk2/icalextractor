package co.uk.geekonabicycle.icalextractor;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {

		String queryTerm = args[0];
		String inputFilePath = args[1];
		String outputFilePath = args[2];

		List<Entry> entries = extractEntries(inputFilePath);

		List<Entry> filteredEntries = filterEntriesBySummary(queryTerm, entries);

		writeTsvFile(filteredEntries, outputFilePath);
	}

	private static void writeTsvFile(List<Entry> entries, String filePath)
			throws IOException, ParseException {
		File output = new File(filePath);
		List<String> outputLines = Lists.newArrayList();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YYYY");

		for (Entry entry : entries) {
			try {
				Date dtStart = entry.getDtStart();
				String line =  dateFormatter.format(dtStart) + "\t"
						+ entry.getSummary();
				outputLines.add(line);
			} catch (ParseException e) {
				// Crappy entry, continue
			}
		}

		FileUtils.writeLines(output, outputLines);
	}

	private static List<Entry> filterEntriesBySummary(String queryTerm,
			List<Entry> entries) {
		List<Entry> filteredEntries = Lists.newArrayList();

		for (Entry entry : entries) {
			if (entry.getSummary().contains(queryTerm)) {
				filteredEntries.add(entry);
			}
		}

		return filteredEntries;
	}

	private static List<Entry> extractEntries(String filePath)
			throws IOException {
		File input = new File(filePath);
		List<String> inputLines = FileUtils.readLines(input);
		Entry currentEntry = new Entry();
		List<Entry> entries = Lists.newArrayList();

		for (String inputLine : inputLines) {
			if (inputLine.indexOf(":") > 0) {
				String fieldName = inputLine.substring(0,
						inputLine.indexOf(":"));
				String fieldValue = inputLine
						.substring(inputLine.indexOf(":") + 1);

				if (fieldName.equals("BEGIN") && fieldValue.equals("VEVENT")) {
					currentEntry = new Entry();

				} else if (fieldName.equals("END")
						&& fieldValue.equals("VEVENT")) {
					entries.add(currentEntry);

				} else if (fieldName.equals("DTSTART")) {
					currentEntry.setDtStart(fieldValue);
				} else if (fieldName.equals("DTEND")) {
					currentEntry.setDtEnd(fieldValue);
				} else if (fieldName.equals("DTSTAMP")) {
					currentEntry.setDtStamp(fieldValue);
				} else if (fieldName.equals("UID")) {
					currentEntry.setUid(fieldValue);
				} else if (fieldName.equals("CREATED")) {
					currentEntry.setCreated(fieldValue);
				} else if (fieldName.equals("DESCRIPTION")) {
					currentEntry.setDescription(fieldValue);
				} else if (fieldName.equals("LAST-MODIFIED")) {
					currentEntry.setLastModified(fieldValue);
				} else if (fieldName.equals("LOCATION")) {
					currentEntry.setLocation(fieldValue);
				} else if (fieldName.equals("SEQUENCE")) {
					currentEntry.setSequence(fieldValue);
				} else if (fieldName.equals("STATUS")) {
					currentEntry.setStatus(fieldValue);
				} else if (fieldName.equals("SUMMARY")) {
					currentEntry.setSummary(fieldValue);
				} else if (fieldName.equals("TRANSP")) {
					currentEntry.setTransp(fieldValue);
				}
			}
		}
		return entries;
	}
}
