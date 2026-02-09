package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;

/**
 * Abstract base class for all command implementations.
 * Provides common functionality and shared repository access for concrete command classes.
 */
public abstract class AbstractCommand implements ICommand {

    /**
     * The repository instance used to access and modify social network data.
     */
    protected SocialRepository repository;

    /**
     * Constructs an AbstractCommand with the specified repository.
     *
     * @param repository The social repository to be used by this command
     */
    public AbstractCommand(SocialRepository repository) {
        this.repository = repository;
    }

}
