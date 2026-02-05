package com.clarityai.simplesocialnetwork.repository;

import com.clarityai.simplesocialnetwork.model.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialRepository {

    private final Map<String, List<Message>> usersMessages = new HashMap<>();
    private final Map<String, Set<String>> usersFollowing = new HashMap<>();

    public Map<String, List<Message>> getUsersMessages() {
        return usersMessages;
    }

    public Map<String, Set<String>> getUsersFollowing() {
        return usersFollowing;
    }
}
