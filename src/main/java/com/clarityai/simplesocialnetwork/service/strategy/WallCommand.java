package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;

public class WallCommand implements ICommand {

    @Override
    public void handleCommand(String command, SocialRepository repository) {
        System.out.println("wall command");
    }
}
