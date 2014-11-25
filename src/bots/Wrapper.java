package bots;

import bots.calc.CalculationInterpreter;
import bots.logger.ErrorLogger;

import java.util.Scanner;

public class Wrapper {
	private CalculationInterpreter ci;

	public Wrapper() {
		ci = new CalculationInterpreter();
		init();
	}

	public String evaluateMessage(String msg) {
		if (!msg.startsWith("?")) {
			return null;
		}
		int commandEnd = msg.indexOf(" ");
		String command;
		String args = null;
		if (commandEnd > 0) {
			command = msg.substring(1, commandEnd);
			args = msg.substring(commandEnd+1);
		} else {
			command = msg.substring(1);
		}

		switch (command) {
			case "reload":
				init();
				return "Successfully reloaded.";
			case "calc":
				return ci.getAnswer(args);
		}
		return null;
	}

	private void init() {
		ErrorLogger.init();
		ci.init();
	}

	public static void main(String[] args) {
		Wrapper cb = new Wrapper();
		Scanner scan = new Scanner(System.in, "UTF-8");
		while (scan.hasNextLine()) {
			System.out.println(cb.evaluateMessage(scan.nextLine()));
		}
	}
}
