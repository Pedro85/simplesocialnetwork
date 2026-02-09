package com.clarityai.simplesocialnetwork.service.strategy;

import java.util.regex.Matcher;

/**
 * Command interface for the Strategy pattern implementation.
 * All command handlers must implement this interface to process user input.
 */
public interface ICommand {
    /**
     * Handles a command by processing the matched input pattern.
     *
     * @param matcher The regex matcher containing the parsed command groups
     */
    void handleCommand(Matcher matcher);
}
