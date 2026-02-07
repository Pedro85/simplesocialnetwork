package com.clarityai.simplesocialnetwork;

import com.clarityai.simplesocialnetwork.model.Message;
import com.clarityai.simplesocialnetwork.repository.SocialRepository;
import com.clarityai.simplesocialnetwork.service.SocialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleSocialNetworkTest {

    private SocialService service;
    private SocialRepository repository;

    @BeforeEach
    void setUp() {
        service = new SocialService();
        repository = service.getRepository();
    }

    @Test
    void testPostMessage() {
        service.commandHandler("Ana -> I love the weather today");

        assertNotNull(repository.getUserMessages("Ana"));
        assertNotEquals(new ArrayList<>(), repository.getUserMessages("Ana"));
        assertEquals(1, repository.getUserMessages("Ana").size());
    }

    @Test
    void testPostMessages() {
        service.commandHandler("Ana -> I am tired of this weather");
        service.commandHandler("Ana -> It´s raining again");

        assertNotNull(repository.getUserMessages("Ana"));
        assertNotEquals(new ArrayList<>(), repository.getUserMessages("Ana"));
        assertEquals(2, repository.getUserMessages("Ana").size());
    }

    @Test
    void testReadMessages() {
        service.commandHandler("Ana -> I am tired of this weather");
        service.commandHandler("Ana -> It´s raining again");

        List<Message> messages = repository.getUserMessages("Ana");

        assertEquals(2, messages.size());
        assertEquals("I am tired of this weather", messages.get(0).message());
        assertEquals("It´s raining again", messages.get(1).message());
    }

    @Test
    void testReadMessagesNonExistingUser() {
        assertTrue(repository.getUserMessages("Bob").isEmpty());
    }

    @Test
    void testFollowUser() {
        service.commandHandler("Ana follows Bob");

        assertTrue(repository.getUserFollowees("Ana").contains("Bob"));
    }

    @Test
    void testFollowUserFail() {
        service.commandHandler("Ana follows Ana");

        assertFalse(repository.getUserFollowees("Ana").contains("Ana"));
    }

    @Test
    void testUnfollowUser() {
        service.commandHandler("Ana follows Bob");

        assertTrue(repository.getUserFollowees("Ana").contains("Bob"));

        service.commandHandler("Ana unfollows Bob");

        assertFalse(repository.getUserFollowees("Ana").contains("Bob"));
    }

    @Test
    void testUnfollowUserFail() {
        service.commandHandler("Ana follows Bob");

        assertTrue(repository.getUserFollowees("Ana").contains("Bob"));

        service.commandHandler("Ana unfollows Bobe");

        assertTrue(repository.getUserFollowees("Ana").contains("Bob"));
    }

    @Test
    void testViewWall() {
        service.commandHandler("Ana -> I am tired of this weather");
        service.commandHandler("Ana -> It´s raining again");
        service.commandHandler("Bob -> I love rain");

        assertEquals(2, repository.getWallMessages("Ana").size());
        assertEquals(1, repository.getWallMessages("Bob").size());

        service.commandHandler("Ana follows Bob");

        assertEquals(3, repository.getWallMessages("Ana").size());
        assertEquals(1, repository.getWallMessages("Bob").size());

        service.commandHandler("Ana unfollows Bob");
        service.commandHandler("Bob follows Ana");

        assertEquals(2, repository.getWallMessages("Ana").size());
        assertEquals(3, repository.getWallMessages("Bob").size());
    }

    @Test
    void testViewWallFail() {
        service.commandHandler("Ana -> I am tired of this weather");
        service.commandHandler("Ana -> It´s raining again");
        service.commandHandler("Bob -> I love rain");

        assertEquals(2, repository.getWallMessages("Ana").size());
        assertEquals(1, repository.getWallMessages("Bob").size());

        service.commandHandler("Ana follows Bob");

        assertEquals(3, repository.getWallMessages("Ana").size());
        assertEquals(1, repository.getWallMessages("Bob").size());

        service.commandHandler("Ana unfollows Bob");
        service.commandHandler("Bob follows Ana");

        assertNotEquals(3, repository.getWallMessages("Ana").size());
        assertNotEquals(1, repository.getWallMessages("Bob").size());
    }
}