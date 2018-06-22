import org.junit.*;
import ru.spbau.mit.Environment;
import ru.spbau.mit.Lexer;
import ru.spbau.mit.Parser;
import ru.spbau.mit.commands.Command;
import ru.spbau.mit.commands.PipeCommand;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class TesterCd {
    private Lexer lexer = new Lexer();
    private Parser parser = new Parser();
    private Environment environment = new Environment();

    @Test
    public void testCd() throws IOException {
        List<String> tokens = lexer.parseWords("cd src");
        String prevFolder = System.getProperty("user.dir");
        List<String> tokensAfterSubstitution = lexer.substituteVariables(tokens, environment);
        List<Command> commands = parser.parseCommands(tokensAfterSubstitution);
        PipeCommand pipeCommand = new PipeCommand(commands);
        pipeCommand.run(System.in, System.out, environment);
        assertEquals(prevFolder + File.separator + "src", System.getProperty("user.dir"));
    }

    @Test(expected = IOException.class)
    public void testWrongDir() throws IOException {
        List<String> tokens = lexer.parseWords("cd  nothing");
        List<String> tokensAfterSubstitution = lexer.substituteVariables(tokens, environment);
        List<Command> commands = parser.parseCommands(tokensAfterSubstitution);
        PipeCommand pipeCommand = new PipeCommand(commands);
        pipeCommand.run(System.in, System.out, environment);
    }

    @Test
    public void testEmpty() throws IOException  {
        List<String> tokens = lexer.parseWords("cd ");
        String prevFolder = System.getProperty("user.dir");
        List<String> tokensAfterSubstitution = lexer.substituteVariables(tokens, environment);
        List<Command> commands = parser.parseCommands(tokensAfterSubstitution);
        PipeCommand pipeCommand = new PipeCommand(commands);
        pipeCommand.run(System.in, System.out, environment);
        assertEquals(prevFolder, System.getProperty("user.dir"));
    }
}