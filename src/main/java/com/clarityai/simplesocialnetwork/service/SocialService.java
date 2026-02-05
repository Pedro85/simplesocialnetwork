package com.clarityai.simplesocialnetwork.service;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;
import com.clarityai.simplesocialnetwork.service.strategy.FollowCommand;
import com.clarityai.simplesocialnetwork.service.strategy.PostCommand;
import com.clarityai.simplesocialnetwork.service.strategy.ReadCommand;
import com.clarityai.simplesocialnetwork.service.strategy.UnfollowCommand;
import com.clarityai.simplesocialnetwork.service.strategy.WallCommand;
import com.clarityai.simplesocialnetwork.util.Commands;

import java.util.regex.Matcher;

public class SocialService {

    private final SocialRepository socialRepository;

    private final PostCommand postCommand;
    private final FollowCommand followCommand;
    private final UnfollowCommand unfollowCommand;
    private final WallCommand wallCommand;
    private final ReadCommand readCommand;

    private Matcher matcher;

    public SocialService() {
        this.socialRepository = new SocialRepository();
        this.postCommand = new PostCommand(socialRepository);
        this.followCommand = new FollowCommand(socialRepository);
        this.unfollowCommand = new UnfollowCommand(socialRepository);
        this.wallCommand = new WallCommand(socialRepository);
        this.readCommand = new ReadCommand(socialRepository);
    }

    public void commandHandler(final String input) {

        if ((matcher = Commands.PATTERN_POST.matcher(input)).matches()) {
            postCommand.handleCommand(matcher);
        } else if ((matcher = Commands.PATTERN_FOLLOWS.matcher(input)).matches()) {
            followCommand.handleCommand(matcher);
        } else if ((matcher = Commands.PATTERN_FOLLOWS.matcher(input)).matches()) {
            unfollowCommand.handleCommand(matcher);
        } else if ((matcher = Commands.PATTERN_WALL.matcher(input)).matches()) {
            wallCommand.handleCommand(matcher);
        } else if (!Commands.EXIT.equalsIgnoreCase(input) && (matcher = Commands.PATTERN_READ.matcher(input)).matches()) {
            readCommand.handleCommand(matcher);
        }

    }
}
