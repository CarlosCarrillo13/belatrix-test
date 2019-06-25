package com.carrillo.belatrixapplication.configuration.commons.constants;

public class FilterConstants {

	public static final String HASHTAG = "(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)";
	public static final String TWIITER = "(?<=^|(?<=[^a-zA-Z0-9-_\\.]))@[A-Za-z0-9_-]+";
	public static final String UPPER = "(\\b[A-Z]{1}[a-z]+)( )([A-Z]{1}[a-z]+\\b)";

}
