package ru.spbau.mit.commands;

import ru.spbau.mit.Environment;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The CommandLs class is for printing content of (current) directory or just
 * name of file if file is given
 */
public class CommandLs extends Command {
    public CommandLs(List<String> args) {
        super(args);
    }

    /**
     * This method executes command for one file or input stream
     */
    private void handleOneArgument(File dir, OutputStream os) throws IOException {
        boolean exists = dir.exists();
        if (!exists) {
            throw new IOException(dir.getName() + ": No such file or directory");
        }
        PrintWriter out = new PrintWriter(os);
        if (dir.isFile()) {
            out.println(dir);
        } else if (dir.isDirectory()) {
            out.println(Arrays.toString(Objects.requireNonNull(dir.list())));
        }
        out.flush();
    }

    /**
     * This method executes command
     */
    @Override
    public void run(InputStream is, OutputStream os, Environment environment)
            throws IOException {
        if (getArgs().size() == 0) {
            handleOneArgument(new File(getCurrentDir() + File.separator), os);
        } else if (getArgs().size() > 1) {
            throw new IOException("ls: too many argumnts");
        } else {
            handleOneArgument(new File(getCurrentDir() + File.separator + getArgs().get(0)), os);
        }
    }
}