package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;

import java.util.regex.Matcher;

public class UnfollowCommand extends AbstractCommand {

    public UnfollowCommand(SocialRepository repository) {
        super(repository);
    }

    @Override
    public void handleCommand(Matcher matcher) {

        final String follower = matcher.group(1);
        final String followee = matcher.group(2);

        if (follower.equals(followee)) {
            System.out.println("You cannot unfollow yourself.");
        }  else if (!repository.getUsersFollowing().get(follower).contains(followee)) {
            System.out.println("You are not following " + followee + ".");
        } else {
            repository.removeFollower(follower, followee);
            System.out.println("You unfollowed " + followee + ".");
        }
    }
}
