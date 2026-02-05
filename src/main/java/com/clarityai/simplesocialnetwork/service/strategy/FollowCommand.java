package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;

import java.util.regex.Matcher;

public class FollowCommand extends AbstractCommand {

    public FollowCommand(SocialRepository repository) {
        super(repository);
    }

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
