package com.clarityai.simplesocialnetwork.util;

import com.clarityai.simplesocialnetwork.model.Message;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MessageFormatUtil {

    // Format output messages
    public static List<String> formatMessages(final List<Message> messages, final boolean showAuthor) {
        // Sort messages
        messages.sort(Comparator.comparing(Message::timestamp).reversed());

        return messages.stream()
                .map(msg -> {
                    String timeAgo = formatTimeAgo(msg.timestamp());
                    if (showAuthor) {
                        return String.format("%s - %s (%s)", msg.username(), msg.message(), timeAgo);
                    } else {
                        return String.format("%s (%s)", msg.message(), timeAgo);
                    }
                })
                .collect(Collectors.toList());
    }

    private static String formatTimeAgo(final Instant timestamp) {
        final Duration duration = Duration.between(timestamp, Instant.now());
        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return seconds + " seconds ago";
        }

        long minutes = duration.toMinutes();
        if (minutes < 60) {
            return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago";
        }

        long hours = duration.toHours();
        return hours + " hour" + (hours > 1 ? "s" : "") + " ago";
    }

}