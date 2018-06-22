import org.junit.*;
import ru.spbau.mit.Environment;
import ru.spbau.mit.Lexer;
import ru.spbau.mit.Parser;
import ru.spbau.mit.commands.Command;
import ru.spbau.mit.commands.PipeCommand;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.io.IOException;
import java.util.List;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;


public class TesterLs{
    private Lexer lexer = new Lexer();
    private Parser parser = new Parser();
    private Environment environment = new Environment();


    @Test(expected = IOException.class)
    public void testWrongDir() throws IOException {
        List<String> tokens = lexer.parseWords("ls  nothing");
        List<String> tokensAfterSubstitution = lexer.substituteVariables(tokens, environment);
        List<Command> commands = parser.parseCommands(tokensAfterSubstitution);
        PipeCommand pipeCommand = new PipeCommand(commands);
        pipeCommand.run(System.in, System.out, environment);
    }

    @Test
    public void testEmpty() throws IOException  {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        System.setOut(new PrintStream(b, true));
        List<String> tokens = lexer.parseWords("cd src/main/java| ls ");
        List<String> tokensAfterSubstitution = lexer.substituteVariables(tokens, environment);
        List<Command> commands = parser.parseCommands(tokensAfterSubstitution);
        PipeCommand pipeCommand = new PipeCommand(commands);
        pipeCommand.run(System.in, System.out, environment);
        List<String> folders  = Arrays.asList("ru");
        assertThat(folders.toString(), is(b.toString().trim()));
    }

    @Test
    public void testLs() throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        System.setOut(new PrintStream(b, true));
        List<String> tokens = lexer.parseWords("ls ru/spbau");
        List<String> tokensAfterSubstitution = lexer.substituteVariables(tokens, environment);
        List<Command> commands = parser.parseCommands(tokensAfterSubstitution);
        PipeCommand pipeCommand = new PipeCommand(commands);
        pipeCommand.run(System.in, System.out, environment);
        List<String> folders  = Arrays.asList("mit");
        assertThat(folders.toString(), is(b.toString().trim()));
    }
}