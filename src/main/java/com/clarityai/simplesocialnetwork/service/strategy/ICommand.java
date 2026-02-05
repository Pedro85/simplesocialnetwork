package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;

public interface ICommand {
    void handleCommand(String command, SocialRepository repository);
}
