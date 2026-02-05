package com.clarityai.simplesocialnetwork.repository;

import com.clarityai.simplesocialnetwork.model.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialRepository {

    private final Map<String, List<Message>> userMessages = new HashMap<>();
    private final Map<String, Set<String>> userFollowing = new HashMap<>();

    public void addUserMessage(String username, Message message) {
        if (!userMessages.containsKey(username)) {
            userMessages.put(username, new ArrayList<>());
        }
        userMessages.get(username).add(message);
    }

    public Map<String, List<Message>> getUsersMessages() {
        return userMessages;
    }

    public boolean userHasMessages(String username) {
        return userMessages.containsKey(username);
    }

    public List<Message> getUserMessages(String username) {
        return userMessages.getOrDefault(username, new ArrayList<>());
    }

    public void addFollower(String follower, String followee) {
        if (!userFollowing.containsKey(follower)) {
            userFollowing.put(follower, new java.util.HashSet<>());
        }
        userFollowing.get(follower).add(followee);
    }

    public void removeFollower(String follower, String followee) {
        if (userFollowing.containsKey(follower)) {
            userFollowing.get(follower).remove(followee);
        }
    }

    public Map<String, Set<String>> getUsersFollowing() {
        return userFollowing;
    }

    public boolean userHasFollowees(String username) {
        return userFollowing.containsKey(username);
    }

    public Set<String> getUserFollowees(String username) {
        return userFollowing.getOrDefault(username, new HashSet<>());
    }

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
