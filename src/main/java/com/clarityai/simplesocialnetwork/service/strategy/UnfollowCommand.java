package com.clarityai.simplesocialnetwork.service.strategy;

import com.clarityai.simplesocialnetwork.repository.SocialRepository;

import java.util.regex.Matcher;

/**
 * Command handler for unfollowing users.
 * Processes unfollow commands in the format: "follower unfollows followee"
 */
public class UnfollowCommand extends AbstractCommand {

    /**
     * Constructs an UnfollowCommand with the specified repository.
     *
     * @param repository The social repository to manage follower relationships
     */
    public UnfollowCommand(SocialRepository repository) {
        super(repository);
    }

    /**
     * Handles the unfollow command by removing a following relationship between two users.
     * Validates that users cannot unfollow themselves and that the relationship exists.
     *
     * @param matcher The regex matcher containing the follower (group 1) and followee (group 2) usernames
     */
    @Override
    public void handleCommand(Matcher matcher) {

        final String follower = matcher.group(1);
        final String followee = matcher.group(2);

        if (follower.equals(followee)) {
            System.out.println("You cannot unfollow yourself.");
        }  else if (!repository.userHasFollowees(follower) || !repository.getUsersFollowing().get(follower).contains(followee)) {
            System.out.println("You are not following " + followee + ".");
        } else {
            repository.removeFollower(follower, followee);
            System.out.println("You unfollowed " + followee + ".");
        }
    }
}
