package com.clarityai.simplesocialnetwork.util;

import java.util.regex.Pattern;

/**
 * Utility class containing command constants and regex patterns for parsing user input.
 * Defines all available commands in the social network and their corresponding pattern matchers.
 */
public class Commands {

    // Command keywords
    public static final String POST = "->";
    public static final String WALL = "wall";
    public static final String FOLLOWS = "follows";
    public static final String UNFOLLOWS = "unfollows";
    public static final String EXIT = "exit";

    // Regex patterns for the commands
    public static final Pattern PATTERN_POST = Pattern.compile("^(.+) -> (.+)$");
    public static final Pattern PATTERN_FOLLOWS = Pattern.compile("^(.+) follows (.+)$");
    public static final Pattern PATTERN_UNFOLLOWS = Pattern.compile("^(.+) unfollows (.+)$");
    public static final Pattern PATTERN_WALL = Pattern.compile("^(.+) wall$");
    public static final Pattern PATTERN_READ = Pattern.compile("^(.+)$");
}
