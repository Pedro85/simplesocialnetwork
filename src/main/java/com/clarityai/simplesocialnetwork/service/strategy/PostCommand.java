package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;

public class PostCommand implements ICommand {

    @Override
    public void handleCommand(String command, SocialRepository repository) {
        System.out.println("post command");
    }
}
