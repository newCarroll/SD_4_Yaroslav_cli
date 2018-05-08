package ru.spbau.mit.commands;

import ru.spbau.mit.Environment;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * The CommandCd class is for changing current directory
 */
public class CommandCd extends Command{
    public CommandCd(List<String> args){
        super(args);
    }

    /**
     * This method executes command
     *
     * @param is
     * @param os
     * @param environment
     * @throws IOException
     */
    @Override
    public void run(InputStream is, OutputStream os, Environment environment)
            throws IOException{
        if (getArgs().size() > 0){
            setCurrentDir(getArgs().get(0));
        }
    }
}
