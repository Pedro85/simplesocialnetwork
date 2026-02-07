package com.clarityai.simplesocialnetwork.model;

import java.time.Instant;

public record Message(String username, String message, Instant timestamp) {}