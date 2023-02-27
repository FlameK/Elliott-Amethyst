package api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class Log
{
	private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

	public Log()
	{
	}

	public static void log(Level level, String prefix, Object logMsg)
	{
		System.out.printf("[%s] [%s - %s] %s%n", formatter.format(new Date()), level.getName(), prefix, logMsg);
	}

	public static void log(Level level, Object logMsg)
	{
		System.out.printf("[%s] [%s] %s%n", formatter.format(new Date()), level.getName(), logMsg);
	}

	public static void log(String prefix, Object logMsg)
	{
		System.out.printf("[%s] [%s] %s%n", formatter.format(new Date()), prefix, logMsg);
	}

	public static void info(String prefix, Object logMsg)
	{
		log(Level.INFO, prefix, logMsg);
	}

	public static void severe(String prefix, Object logMsg)
	{
		log(Level.SEVERE, prefix, logMsg);
	}

	public static void fine(String prefix, Object logMsg)
	{
		log(Level.FINE, prefix, logMsg);
	}

	public static void info(Object logMsg)
	{
		log(Level.INFO, logMsg);
	}

	public static void severe(Object logMsg)
	{
		log(Level.SEVERE, logMsg);
	}

	public static void fine(Object logMsg)
	{
		log(Level.FINE, logMsg);
	}
}

