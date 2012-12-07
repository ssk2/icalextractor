package uk.co.geekonabicycle.icalextractor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;

class TSVWriter {
	private File outputFile;

	TSVWriter(File outputFile) {
		this.outputFile = outputFile;
	}

	void writeTsv(Collection<Component> eventsToWrite) throws IOException {
		outputFile.createNewFile();
		List<String> outputLines = Lists.newArrayList();

		for (Component event : eventsToWrite) {
			String startDate = ((VEvent) event).getStartDate().getDate().toGMTString();
			String endDate = ((VEvent) event).getEndDate().getDate().toGMTString();
			String summary = ((VEvent) event).getSummary().getValue();
			String location = ((VEvent) event).getLocation().getValue();
			String tab = "\t";
			String line = startDate + tab + endDate + tab + summary + tab
					+ location;
			outputLines.add(line);
		}

		FileUtils.writeLines(outputFile, outputLines);
	}
}
