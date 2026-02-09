package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.model.Message;
import com.clarityai.simplesocialnetwork.repository.SocialRepository;

import java.time.Instant;
import java.util.regex.Matcher;

/**
 * Command handler for posting messages to the social network.
 * Processes post commands in the format: "username -> message"
 */
public class PostCommand extends AbstractCommand {

    /**
     * Constructs a PostCommand with the specified repository.
     *
     * @param repository The social repository to store messages
     */
    public PostCommand(SocialRepository repository) {
        super(repository);
    }

    /**
     * Handles the post command by extracting the username and message from the matcher,
     * creating a new message with the current timestamp, and storing it in the repository.
     *
     * @param matcher The regex matcher containing the username (group 1) and message text (group 2)
     */
    @Override
    public void handleCommand(Matcher matcher) {

        final String username = matcher.group(1);
        final String message = matcher.group(2);

        repository.addUserMessage(username, new Message(username, message, Instant.now()));

        System.out.println("Message posted.");
    }
}
