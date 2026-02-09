package com.clarityai.simplesocialnetwork.repository;

import com.clarityai.simplesocialnetwork.model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Repository class that manages data storage for the Simple Social Network.
 * Handles storage and retrieval of user messages and follower relationships using in-memory data structures.
 */
public class SocialRepository {

    private final Map<String, List<Message>> userMessages = new HashMap<>();
    private final Map<String, Set<String>> userFollowing = new HashMap<>();

    /**
     * Adds a message to a user's timeline.
     * If the user does not exist in the repository, creates a new entry.
     *
     * @param username The username of the message author
     * @param message The message to be added
     */
    public void addUserMessage(String username, Message message) {
        if (!userMessages.containsKey(username)) {
            userMessages.put(username, new ArrayList<>());
        }
        userMessages.get(username).add(message);
    }

    /**
     * Returns the complete map of all users and their messages.
     *
     * @return A map with usernames as keys and lists of messages as values
     */
    public Map<String, List<Message>> getUsersMessages() {
        return userMessages;
    }

    /**
     * Checks if a user has posted any messages.
     *
     * @param username The username to check
     * @return true if the user has posted messages, false otherwise
     */
    public boolean userHasMessages(String username) {
        return userMessages.containsKey(username);
    }

    /**
     * Retrieves all messages posted by a specific user.
     *
     * @param username The username whose messages to retrieve
     * @return A list of messages, or an empty list if the user has no messages
     */
    public List<Message> getUserMessages(String username) {
        return userMessages.getOrDefault(username, new ArrayList<>());
    }

    /**
     * Adds a following relationship between two users.
     * If the follower does not exist in the repository, creates a new entry.
     *
     * @param follower The username of the person who is following
     * @param followee The username of the person being followed
     */
    public void addFollower(String follower, String followee) {
        if (!userFollowing.containsKey(follower)) {
            userFollowing.put(follower, new java.util.HashSet<>());
        }
        userFollowing.get(follower).add(followee);
    }

    /**
     * Removes a following relationship between two users.
     *
     * @param follower The username of the person who is unfollowing
     * @param followee The username of the person being unfollowed
     */
    public void removeFollower(String follower, String followee) {
        if (userFollowing.containsKey(follower)) {
            userFollowing.get(follower).remove(followee);
        }
    }

    /**
     * Returns the complete map of all users and their following relationships.
     *
     * @return A map with usernames as keys and sets of followed usernames as values
     */
    public Map<String, Set<String>> getUsersFollowing() {
        return userFollowing;
    }

    /**
     * Checks if a user is following anyone.
     *
     * @param username The username to check
     * @return true if the user is following at least one other user, false otherwise
     */
    public boolean userHasFollowees(String username) {
        return userFollowing.containsKey(username);
    }

    /**
     * Retrieves the set of users that a given user is following.
     *
     * @param username The username whose followees to retrieve
     * @return A set of usernames being followed, or an empty set if not following anyone
     */
    public Set<String> getUserFollowees(String username) {
        return userFollowing.getOrDefault(username, new HashSet<>());
    }

    /**
     * Retrieves all messages for a user's wall.
     * The wall includes the user's own messages and messages from all users they follow.
     *
     * @param username The username whose wall messages to retrieve
     * @return A list of all messages visible on the user's wall
     */
    public List<Message> getWallMessages(String username) {
        final Set<String> users = userFollowing.getOrDefault(username, new HashSet<>());
        users.add(username);

        final List<Message> messages = new ArrayList<>();
        for (String user : users) {
            messages.addAll(userMessages.getOrDefault(user, new ArrayList<>()));
        }

        return messages;
    }
}
