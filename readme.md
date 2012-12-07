iCal Extractor
==============

A simple command line utility that takes an iCal file and lets you extract certain events which match criteria to a TSV file.

Pre-requisites
--------------
Maven 3

Building
--------
	mvn build

Running
-------
Grab the .jar file that you've built.

	java -jar icalextractor-1.0.0.jar inputfile outputfile [options]
	Options: 
	-period from to 	Filters output for entries within the stated two ISO dates.
	-title word 	Filters output for entries with the title word.

Examples
--------
To extract all events with a tag '[film]' in their titles.

	java -jar icalextractor-1.0.0.jar calendar.ics films.tsv -title [film]
	
To extract all events with a tag '[meal]' in their titles in 2012 

	java -jar icalextractor-1.0.0.jar calendar.ics films.tsv -title [meal] -period 2012-01-01 2012-12-31	
