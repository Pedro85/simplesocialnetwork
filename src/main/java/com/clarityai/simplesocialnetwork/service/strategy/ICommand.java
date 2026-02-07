package com.clarityai.simplesocialnetwork.service.strategy;

import java.util.regex.Matcher;

public interface ICommand {
    void handleCommand(Matcher matcher);
}
