package com.clarityai.simplesocialnetwork.service;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;
import com.clarityai.simplesocialnetwork.service.strategy.FollowCommand;
import com.clarityai.simplesocialnetwork.service.strategy.PostCommand;
import com.clarityai.simplesocialnetwork.service.strategy.ReadCommand;
import com.clarityai.simplesocialnetwork.service.strategy.WallCommand;
import com.clarityai.simplesocialnetwork.util.Commands;

import java.util.regex.Matcher;

public class SocialService {

    private final SocialRepository socialRepository;

    private final PostCommand postCommand;
    private final FollowCommand followCommand;
    private final WallCommand wallCommand;
    private final ReadCommand readCommand;

    Matcher matcher;

    public SocialService() {
        this.socialRepository = new SocialRepository();
        this.postCommand = new PostCommand();
        this.followCommand = new FollowCommand();
        this.wallCommand = new WallCommand();
        this.readCommand = new ReadCommand();
    }

    public void commandHandler(final String input) {

        if ((matcher = Commands.PATTERN_POST.matcher(input)).matches()) {
            postCommand.handleCommand(input, socialRepository);
        } else if ((matcher = Commands.PATTERN_FOLLOWS.matcher(input)).matches()) {
            followCommand.handleCommand(input, socialRepository);
        } else  if ((matcher = Commands.PATTERN_WALL.matcher(input)).matches()) {
            wallCommand.handleCommand(input, socialRepository);
        } else  if ((matcher = Commands.PATTERN_READ.matcher(input)).matches()) {
            readCommand.handleCommand(input, socialRepository);
        } else {
            System.out.println("Invalid command. Please try again.");
        }

    }
}
