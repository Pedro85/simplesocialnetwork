package com.clarityai.simplesocialnetwork.app;

import com.clarityai.simplesocialnetwork.service.SocialService;
import com.clarityai.simplesocialnetwork.util.Commands;

import java.util.Scanner;

/**
 * Main application class that handles user interaction for the Simple Social Network.
 * Manages the command-line interface and processes user input through various social network commands.
 */
public class SocialApp {

    /**
     * Runs the social network application, displaying the interface and processing user commands.
     * The application continues to run until the user enters the 'exit' command.
     * Supported commands include posting messages, following/unfollowing users, reading timelines, and viewing walls.
     */
    public void run() {
        final SocialService service = new SocialService();

        final Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println(" ***** [Clarity AI] Simple Social Network ***** ");
        System.out.println(" Commands available:");
        System.out.println("   <user name> -> <message>          (post a message) ");
        System.out.println("   <user name>                       (view someones' timeline) ");
        System.out.println("   <user name> follows <user name>   (follow user) ");
        System.out.println("   <user name> unfollows <user name> (unfollow user) ");
        System.out.println("   <user name> wall                  (view wall) ");
        System.out.println("   exit                              (exit) \n");

        while (!input.equalsIgnoreCase(Commands.EXIT)) {

            System.out.print(" > ");
            input = scanner.nextLine().trim();

            try {
                service.commandHandler(input);
            } catch (final Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();

        System.out.println("Come back soon!");
    }

}
