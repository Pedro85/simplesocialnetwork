package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;

public abstract class AbstractCommand implements ICommand {

    protected SocialRepository repository;

    public AbstractCommand(SocialRepository repository) {
        this.repository = repository;
    }

}
