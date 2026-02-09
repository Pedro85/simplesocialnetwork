package com.clarityai.simplesocialnetwork.model;

import java.time.Instant;

/**
 * Represents a message in the social network.
 * This record stores information about a user's post including the author, content, and timestamp.
 *
 * @param username The username of the person who posted the message
 * @param message The content of the message
 * @param timestamp The instant when the message was posted
 */
public record Message(String username, String message, Instant timestamp) {}