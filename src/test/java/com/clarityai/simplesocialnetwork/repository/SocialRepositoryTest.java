package com.clarityai.simplesocialnetwork.repository;

import com.clarityai.simplesocialnetwork.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SocialRepositoryTest {

    private SocialRepository repository;

    @BeforeEach
    void setUp() {
        repository = new SocialRepository();
    }

    @Test
    void testAddUserMessageNewUser() {
        Message message = new Message("Ana", "Hello, world!", Instant.now());

        repository.addUserMessage("Ana", message);

        List<Message> messages = repository.getUserMessages("Ana");
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));
    }

    @Test
    void testAddUserMessageExistingUser() {
        Message message1 = new Message("Ana", "First message", Instant.now());
        Message message2 = new Message("Ana", "Second message", Instant.now().plusSeconds(60));

        repository.addUserMessage("Ana", message1);
        repository.addUserMessage("Ana", message2);

        List<Message> messages = repository.getUserMessages("Ana");
        assertEquals(2, messages.size());
        assertTrue(messages.contains(message1));
        assertTrue(messages.contains(message2));
    }

    @Test
    void testGetUserMessagesExistingUser() {
        Message message = new Message("Ana", "Hello, world!", Instant.now());
        repository.addUserMessage("Ana", message);

        List<Message> messages = repository.getUserMessages("Ana");

        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));
    }

    @Test
    void testGetUserMessagesNonExistingUser() {
        List<Message> messages = repository.getUserMessages("Bob");

        assertTrue(messages.isEmpty());
    }

    @Test
    void testUserHasMessagesTrue() {
        Message message = new Message("Ana", "Hello, world!", Instant.now());
        repository.addUserMessage("Ana", message);

        assertTrue(repository.userHasMessages("Ana"));
    }

    @Test
    void testUserHasMessagesFalse() {
        assertFalse(repository.userHasMessages("Bob"));
    }

    @Test
    void testAddFollowerNewFollower() {
        repository.addFollower("Ana", "Bob");

        Set<String> followees = repository.getUserFollowees("Ana");
        assertEquals(1, followees.size());
        assertTrue(followees.contains("Bob"));
    }

    @Test
    void testAddFollowerExistingFollower() {
        repository.addFollower("Ana", "Bob");
        repository.addFollower("Ana", "Charlie");

        Set<String> followees = repository.getUserFollowees("Ana");
        assertEquals(2, followees.size());
        assertTrue(followees.contains("Bob"));
        assertTrue(followees.contains("Charlie"));
    }

    @Test
    void testAddFollowerSameFolloweeTwice() {
        repository.addFollower("Ana", "Bob");
        repository.addFollower("Ana", "Bob"); // Duplicate

        Set<String> followees = repository.getUserFollowees("Ana");
        assertEquals(1, followees.size());
        assertTrue(followees.contains("Bob"));
    }

    @Test
    void testRemoveFollowerExisting() {
        repository.addFollower("Ana", "Bob");
        repository.removeFollower("Ana", "Bob");

        Set<String> followees = repository.getUserFollowees("Ana");
        assertFalse(followees.contains("Bob"));
    }

    @Test
    void testRemoveFollowerNonExisting() {
        repository.addFollower("Ana", "Bob");
        repository.removeFollower("Ana", "Charlie"); // Non-existent

        Set<String> followees = repository.getUserFollowees("Ana");
        assertEquals(1, followees.size());
        assertTrue(followees.contains("Bob"));
    }

    @Test
    void testUserHasFolloweesTrue() {
        repository.addFollower("Ana", "Bob");

        assertTrue(repository.userHasFollowees("Ana"));
    }

    @Test
    void testUserHasFolloweesFalse() {
        assertFalse(repository.userHasFollowees("Ana"));
    }

    @Test
    void testGetUserFolloweesExistingUser() {
        repository.addFollower("Ana", "Bob");
        repository.addFollower("Ana", "Charlie");

        Set<String> followees = repository.getUserFollowees("Ana");

        assertEquals(2, followees.size());
        assertTrue(followees.contains("Bob"));
        assertTrue(followees.contains("Charlie"));
    }

    @Test
    void testGetUserFolloweesNonExistingUser() {
        Set<String> followees = repository.getUserFollowees("Bob");

        assertTrue(followees.isEmpty());
    }

    @Test
    void testGetWallMessagesUserOnly() {
        Message message = new Message("Ana", "Hello, world!", Instant.now());
        repository.addUserMessage("Ana", message);

        List<Message> wallMessages = repository.getWallMessages("Ana");

        assertEquals(1, wallMessages.size());
        assertEquals(message, wallMessages.get(0));
    }

    @Test
    void testGetWallMessagesWithFollowees() {
        Message AnaMessage = new Message("Ana", "Ana's message", Instant.now());
        Message bobMessage = new Message("Bob", "Bob's message", Instant.now().plusSeconds(60));

        repository.addUserMessage("Ana", AnaMessage);
        repository.addUserMessage("Bob", bobMessage);
        repository.addFollower("Ana", "Bob");

        List<Message> wallMessages = repository.getWallMessages("Ana");

        assertEquals(2, wallMessages.size());
        assertTrue(wallMessages.contains(AnaMessage));
        assertTrue(wallMessages.contains(bobMessage));
    }

    @Test
    void testGetWallMessagesCircularFollowing() {
        Message AnaMessage = new Message("Ana", "Ana's message", Instant.now());
        Message bobMessage = new Message("Bob", "Bob's message", Instant.now().plusSeconds(60));

        repository.addUserMessage("Ana", AnaMessage);
        repository.addUserMessage("Bob", bobMessage);
        repository.addFollower("Ana", "Bob");
        repository.addFollower("Bob", "Ana");

        List<Message> AnaWall = repository.getWallMessages("Ana");
        List<Message> bobWall = repository.getWallMessages("Bob");

        assertEquals(2, AnaWall.size());
        assertEquals(2, bobWall.size());
    }

    @Test
    void testGetWallMessagesEmptyWall() {
        List<Message> wallMessages = repository.getWallMessages("Ana");

        assertTrue(wallMessages.isEmpty());
    }

    @Test
    void testSpecialCharactersInUsernames() {
        String specialUsername = "用户@#$%";
        Message message = new Message(specialUsername, "Hello, world!", Instant.now());

        repository.addUserMessage(specialUsername, message);
        repository.addFollower("Ana", specialUsername);

        List<Message> messages = repository.getUserMessages(specialUsername);
        Set<String> followees = repository.getUserFollowees("Ana");

        assertEquals(1, messages.size());
        assertTrue(followees.contains(specialUsername));
    }

    @Test
    void testLargeNumberOfMessages() {
        // Add 100 messages
        for (int i = 0; i < 100; i++) {
            Message message = new Message(
                    "Ana",
                    "Message " + i,
                    Instant.now().plusSeconds((long) i * 60)
            );
            repository.addUserMessage("Ana", message);
        }

        List<Message> messages = repository.getUserMessages("Ana");
        assertEquals(100, messages.size());
    }

    @Test
    void testLargeNumberOfFollowees() {
        // Add 50 followees
        for (int i = 0; i < 50; i++) {
            repository.addFollower("Ana", "User" + i);
        }

        Set<String> followees = repository.getUserFollowees("Ana");
        assertEquals(50, followees.size());
    }
}