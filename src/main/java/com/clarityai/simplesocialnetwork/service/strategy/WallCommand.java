package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.model.Message;
import com.clarityai.simplesocialnetwork.repository.SocialRepository;
import com.clarityai.simplesocialnetwork.util.MessageFormatUtil;

import java.util.List;
import java.util.regex.Matcher;

/**
 * Command handler for viewing a user's wall.
 * Processes wall commands in the format: "username wall"
 * A wall displays messages from the user and all users they follow.
 */
public class WallCommand extends AbstractCommand {

    /**
     * Constructs a WallCommand with the specified repository.
     *
     * @param repository The social repository to retrieve wall messages from
     */
    public WallCommand(SocialRepository repository) {
        super(repository);
    }

    /**
     * Handles the wall command by retrieving and displaying all messages visible on the user's wall.
     * The wall includes the user's own messages and messages from all followed users,
     * displayed in reverse chronological order with author names.
     *
     * @param matcher The regex matcher containing the username (group 1)
     */
    @Override
    public void handleCommand(Matcher matcher) {

        final String username = matcher.group(1);

        final List<Message> messages = repository.getWallMessages(username);
        MessageFormatUtil.formatMessages(messages, true).forEach(line -> System.out.println(line));
    }
}
