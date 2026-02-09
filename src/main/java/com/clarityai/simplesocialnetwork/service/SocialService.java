package com.clarityai.simplesocialnetwork.service;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;
import com.clarityai.simplesocialnetwork.service.strategy.FollowCommand;
import com.clarityai.simplesocialnetwork.service.strategy.PostCommand;
import com.clarityai.simplesocialnetwork.service.strategy.ReadCommand;
import com.clarityai.simplesocialnetwork.service.strategy.UnfollowCommand;
import com.clarityai.simplesocialnetwork.service.strategy.WallCommand;
import com.clarityai.simplesocialnetwork.util.Commands;

import java.util.regex.Matcher;

/**
 * Service layer for the Simple Social Network application.
 * Manages command parsing and routing to appropriate command handlers using the Strategy pattern.
 * This service orchestrates interactions between the user input and the repository layer.
 */
public class SocialService {

    private final SocialRepository repository;

    private final PostCommand postCommand;
    private final FollowCommand followCommand;
    private final UnfollowCommand unfollowCommand;
    private final WallCommand wallCommand;
    private final ReadCommand readCommand;

    private Matcher matcher;

    /**
     * Constructs a new SocialService with an initialized repository and command handlers.
     * Creates instances of all command strategies for handling different user actions.
     */
    public SocialService() {
        this.repository = new SocialRepository();
        this.postCommand = new PostCommand(repository);
        this.followCommand = new FollowCommand(repository);
        this.unfollowCommand = new UnfollowCommand(repository);
        this.wallCommand = new WallCommand(repository);
        this.readCommand = new ReadCommand(repository);
    }

    /**
     * Handles incoming user commands by matching them against predefined patterns.
     * Routes the command to the appropriate strategy handler based on the input pattern.
     *
     * @param input The user input string to be parsed and executed
     */
    public void commandHandler(final String input) {

        if ((matcher = Commands.PATTERN_POST.matcher(input)).matches()) {
            postCommand.handleCommand(matcher);
        } else if ((matcher = Commands.PATTERN_FOLLOWS.matcher(input)).matches()) {
            followCommand.handleCommand(matcher);
        } else if ((matcher = Commands.PATTERN_UNFOLLOWS.matcher(input)).matches()) {
            unfollowCommand.handleCommand(matcher);
        } else if ((matcher = Commands.PATTERN_WALL.matcher(input)).matches()) {
            wallCommand.handleCommand(matcher);
        } else if (!Commands.EXIT.equalsIgnoreCase(input) && (matcher = Commands.PATTERN_READ.matcher(input)).matches()) {
            readCommand.handleCommand(matcher);
        }
    }

    /**
     * Returns the social repository instance used by this service.
     *
     * @return The SocialRepository instance managing user data
     */
    public SocialRepository getRepository() {
        return repository;
    }
}
