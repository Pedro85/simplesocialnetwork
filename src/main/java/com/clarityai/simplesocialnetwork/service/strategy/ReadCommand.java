package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.model.Message;
import com.clarityai.simplesocialnetwork.repository.SocialRepository;
import com.clarityai.simplesocialnetwork.util.MessageFormatUtil;

import java.util.List;
import java.util.regex.Matcher;

/**
 * Command handler for reading a user's timeline.
 * Processes read commands in the format: "username"
 */
public class ReadCommand extends AbstractCommand {

    /**
     * Constructs a ReadCommand with the specified repository.
     *
     * @param repository The social repository to retrieve messages from
     */
    public ReadCommand(SocialRepository repository) {
        super(repository);
    }

    /**
     * Handles the read command by retrieving and displaying all messages posted by the specified user.
     * Messages are formatted and displayed in reverse chronological order.
     *
     * @param matcher The regex matcher containing the username (group 1)
     */
    @Override
    public void handleCommand(Matcher matcher) {

        final String username = matcher.group(1);

        if (repository.userHasMessages(username)) {
            final List<Message> messages = repository.getUserMessages(username);
            MessageFormatUtil.formatMessages(messages, false).forEach(line -> System.out.println(line));
        } else {
            System.out.println(username + " has no messages.");
        }
    }
}

