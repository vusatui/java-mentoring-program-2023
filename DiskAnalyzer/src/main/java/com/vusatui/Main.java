package com.vusatui;

import com.github.rvesse.airline.Cli;
import com.github.rvesse.airline.help.Help;
import com.vusatui.commands.SearchForTheFileNameWithTheMaximumNumberOfLetters;
import com.vusatui.commands.TopFiveLargestFiles;

@com.github.rvesse.airline.annotations.Cli(
        name = "sda",
        description = "A simple disk analyzer",
        defaultCommand = Help.class,
        commands = {
                Help.class,
                SearchForTheFileNameWithTheMaximumNumberOfLetters.class,
                TopFiveLargestFiles.class
        }
)
public class Main {

    public static void main(String[] args) {
        Cli<Runnable> cli = new Cli(Main.class);
        Runnable cmd = cli.parse(args);
        cmd.run();
    }
}