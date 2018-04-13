package ru.spbau.mit.commands;

import ru.spbau.mit.Environment;

import java.io.*;
import java.util.List;

/**
 * The CommandAssign class is command that assigns given value to given variable
 */

public class CommandAssign extends Command {
    public CommandAssign(List<String> args) {
        super(args);
    }

    /**
     * This method executes command
     * @param is
     * @param os
     * @param environment
     * @throws IOException
     */

    @Override
    public void run(InputStream is, OutputStream os, Environment environment)
            throws IOException {
        environment.assign(getArgs().get(0), getArgs().get(1));
    }
}