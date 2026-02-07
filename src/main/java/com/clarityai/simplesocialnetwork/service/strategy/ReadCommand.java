package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.model.Message;
import com.clarityai.simplesocialnetwork.repository.SocialRepository;
import com.clarityai.simplesocialnetwork.util.MessageFormatUtil;

import java.util.List;
import java.util.regex.Matcher;

public class ReadCommand extends AbstractCommand {

    public ReadCommand(SocialRepository repository) {
        super(repository);
    }

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

