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
public class CommandLs extends Command{
    public CommandLs(List<String> args){
        super(args);
    }

    /**
     * This method executes command for one file or input stream
     * @param dir
     * @param os
     */
    private void handleOneArgument(File dir, OutputStream os){
        PrintWriter out = new PrintWriter(os);
        out.println(Arrays.toString(Objects.requireNonNull(dir.list())));
        out.flush();
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
            throws IOException{
        if (getArgs().size() == 0){
            handleOneArgument(new File(getCurrentDir() + "/"), os);
        } else{
            try{
            	handleOneArgument(new File(getCurrentDir() + "/" + getArgs().get(0)), os);        
	    } catch (Exception e){
		System.err.print("There is no file\n");
            }
	}
    }
}
