package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;

public class FollowCommand implements ICommand {

    @Override
    public void handleCommand(String command, SocialRepository repository) {
        System.out.println("follow command");
    }
}
