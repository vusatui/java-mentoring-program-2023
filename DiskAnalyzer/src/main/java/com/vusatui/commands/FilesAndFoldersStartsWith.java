package com.vusatui.commands;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.restrictions.Required;

@Command(
        name = "ffsw",
        description = "The number of files and folders, divided by the first letters of the alphabet " +
                "(for example, 100,000 files and 200 folders begin with the letter A)"
)
public class FilesAndFoldersStartsWith implements Runnable {

    @Inject
    private HelpOption<SearchForTheFileNameWithTheMaximumNumberOfLetters> help;

    @Required
    @Option(name = { "-p", "--path" },
            description = "Path where to search")
    private String path;

    @Required
    @Option(name = { "-l", "--letter" },
            description = "Start letter")
    private String letter;

    @Required
    @Option(name = { "-o", "--output" },
            description = "Results file output path")
    private String outputPath;


    @Override
    public void run() {
        if (!help.showHelpIfRequested()) {
            try {
                find();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void find() throws IOException {
        System.out.printf("path: %s %n", path);

        MyFileVisitor myFileVisitor = new MyFileVisitor();
        Files.walkFileTree(Path.of(path), myFileVisitor);
        writeResults(String.format("Number of folders starts with %s - %s, number of files starts with %s - %s",
                letter, myFileVisitor.getFoldersCount(), letter, myFileVisitor.getFilesCount()));
    }

    private void writeResults(String message) throws IOException {
        Path op = Path.of(outputPath);
        if (Files.notExists(op)) Files.createFile(op);
        Files.writeString(op, message + "\n", StandardOpenOption.APPEND);
    }

    private final class MyFileVisitor extends SimpleFileVisitor<Path> {

        private long filesCount = 0l;

        private long foldersCount = 0l;

        public long getFilesCount() {
            return filesCount;
        }

        public long getFoldersCount() {
            return foldersCount;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.getFileName().toString().toLowerCase().startsWith(letter)) {
                filesCount++;
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.out.printf("Visit for $s failed with error: %s %n", file.toAbsolutePath(), exc.getMessage());
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult postVisitDirectory(Path dir) {
            if (dir.getFileName().toString().toLowerCase().startsWith(letter)) {
                foldersCount++;
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
