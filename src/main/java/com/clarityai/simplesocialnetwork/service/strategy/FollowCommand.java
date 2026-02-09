package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;

import java.util.regex.Matcher;

/**
 * Command handler for following other users.
 * Processes follow commands in the format: "follower follows followee"
 */
public class FollowCommand extends AbstractCommand {

    /**
     * Constructs a FollowCommand with the specified repository.
     *
     * @param repository The social repository to manage follower relationships
     */
    public FollowCommand(SocialRepository repository) {
        super(repository);
    }

    /**
     * Handles the follow command by creating a following relationship between two users.
     * Validates that users cannot follow themselves and prevents duplicate follows.
     *
     * @param matcher The regex matcher containing the follower (group 1) and followee (group 2) usernames
     */
    @Override
    public void handleCommand(Matcher matcher) {

        final String follower = matcher.group(1);
        final String followee = matcher.group(2);

        if (follower.equals(followee)) {
            System.out.println("You cannot follow yourself.");
        }  else if (repository.getUsersFollowing().containsKey(follower)
                    && repository.getUsersFollowing().get(follower).contains(followee)) {
            System.out.println("You are already following " + followee + ".");
        } else {
            repository.addFollower(follower, followee);
            System.out.println(follower + " is now following " + followee + ".");
        }
    }
}
