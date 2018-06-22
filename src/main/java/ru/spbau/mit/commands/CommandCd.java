package ru.spbau.mit.commands;

import ru.spbau.mit.Environment;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * The CommandCd class is for changing current directory
 */
public class CommandCd extends Command {
    public CommandCd(List<String> args) {
        super(args);
    }

    /**
     * This method executes command
     */
    @Override
    public void run(InputStream is, OutputStream os, Environment environment)
            throws IOException {
        if (getArgs().size() > 0) {
            File dir = new File(getArgs().get(0));
            boolean exists = dir.exists();
            if (!exists) {
                throw new IOException(dir.getName() + ": No such file or directory");
            }
            if (dir.isDirectory()) {
                setCurrentDir(getArgs().get(0));
            } else {
                throw new IOException(dir.getName() + ": No such file or directory");
            }
        }
    }
}
