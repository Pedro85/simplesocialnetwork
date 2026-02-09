package com.clarityai.simplesocialnetwork.util;

import com.clarityai.simplesocialnetwork.model.Message;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for formatting messages and time displays.
 * Provides methods to format message lists with timestamps in a human-readable format.
 */
public class MessageFormatUtil {

    /**
     * Formats a list of messages for display, optionally showing author names.
     * Messages are sorted in reverse chronological order (newest first) and formatted with relative timestamps.
     *
     * @param messages The list of messages to format
     * @param showAuthor Whether to include the author's username in the output
     * @return A list of formatted message strings ready for display
     */
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

    /**
     * Formats a timestamp as a relative time string (e.g., "5 seconds ago", "3 minutes ago").
     *
     * @param timestamp The instant to format
     * @return A human-readable string representing how long ago the timestamp occurred
     */
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