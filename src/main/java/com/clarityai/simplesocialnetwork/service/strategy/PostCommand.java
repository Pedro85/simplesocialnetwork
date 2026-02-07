package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.model.Message;
import com.clarityai.simplesocialnetwork.repository.SocialRepository;

import java.time.Instant;
import java.util.regex.Matcher;

public class PostCommand extends AbstractCommand {

    public PostCommand(SocialRepository repository) {
        super(repository);
    }

    @Override
    public void handleCommand(Matcher matcher) {

        final String username = matcher.group(1);
        final String message = matcher.group(2);

        repository.addUserMessage(username, new Message(username, message, Instant.now()));

        System.out.println("Message posted.");
    }
}
