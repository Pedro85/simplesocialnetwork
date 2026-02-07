# Simple Social Network

## Overview
A simple console-based social networking application that supports posting, reading, following, unfollowing and viewing a consolidated wall. The implementation is intentionally lightweight and in-memory, according to the coding challenge requirements.

## Approach and design
- Core model:
    - Users are identified by a `String` username.
    - `Message` objects contain text and a timestamp.
    - A simple in-memory repository uses a `Map<String, User>` to store users, each with:
        - a list of their own messages (append-only).
        - a `Set<String>` of followed usernames.
- Command parsing:
    - Commands are read from the console and always start with the user's name.
    - Command formats are treated as input tokens and parsed into one of four operations: posting, reading, following, unfollowing, or wall.
    - Example command formats (used as code/exact strings):
        - `Ana -> I love the weather today`
        - `Ana`
        - `Bob follows Ana`
        - `Bob unfollows Ana`
        - `Bob wall`

## Testing
- JUnit dependency was added to `pom.xml`.
- Tests cover core scenarios: posting, reading, following, unfollowing, and wall aggregation.
- Test class: `src/test/java/.../SocialNetworkServiceTest.java` (contains tests for command parsing, message ordering, and follow relationships).
- Test class: `src/test/java/.../SocialRepositoryTest.java` (contains tests for the majority of the Repository class).

## Assumptions
- Time is recorded using `Instant` and output is displayed using human-friendly relative times (e.g., "5 minutes ago") in the console.
- Usernames are treated as case-sensitive.
- Single-process, single-threaded console application; no concurrency control is implemented in the in-memory structures.
- No persistence: all data lives in memory for the process lifetime in one single Repository.

## Trade-offs
- Simplicity over scalability: in-memory Map and lists are easy to implement and reason about, but not suitable for large datasets or multiple processes.
- Sorting on read (wall) keeps writes cheap but means heavy read cost when many messages are aggregated. For large scale, consider maintaining a merged feed or using a priority queue when constructing a paged wall.
- No dependency injection, frameworks or parsing library to keep the exercise focused on core logic and testability, according to the requirements.

## Extensibility
- Persist data by replacing in-memory repository with multiple repositories with a DAOs backed by a database.
- Add pagination or streaming for `wall` to handle large message sets.
- Support case-insensitive usernames or user registration flow.
- Add concurrency controls and synchronization to support multithreaded command processing.
- Expose functionality via a REST API and add integration tests.

## How to run
- Run tests:
    - `mvn clean test`
- Build:
    - `mvn clean package`
- Run the console app:
    - Use the project's main class with `mvn exec:java` or run the produced jar, e.g. `java -jar target/simplesocialnetwork-<version>.jar` (if an executable jar is produced).

## Notes
- Focus was kept on clean, well-tested core logic and clear command behavior.
- Implementation choices were documented to make future changes straightforward.