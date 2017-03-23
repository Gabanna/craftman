package de.rgse.craftman.config;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

@SuppressWarnings("serial")
public class Cli extends Options {

	private Cli() {
		addOption("p", "port", true, "the port to run the server on");
	}
	
	public static CommandLine createCommandline(String[] args) throws ParseException {
		CommandLineParser parser = new DefaultParser();
		return parser.parse(new Cli(), args);
	}
	
}
