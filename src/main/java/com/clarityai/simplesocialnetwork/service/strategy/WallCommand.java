package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.model.Message;
import com.clarityai.simplesocialnetwork.repository.SocialRepository;
import com.clarityai.simplesocialnetwork.util.MessageFormatUtil;

import java.util.List;
import java.util.regex.Matcher;

public class WallCommand extends AbstractCommand {

    public WallCommand(SocialRepository repository) {
        super(repository);
    }

    @Override
    public void handleCommand(Matcher matcher) {

        final String username = matcher.group(1);

        final List<Message> messages = repository.getWallMessages(username);
        MessageFormatUtil.formatMessages(messages, true).forEach(line -> System.out.println(line));
    }
}
