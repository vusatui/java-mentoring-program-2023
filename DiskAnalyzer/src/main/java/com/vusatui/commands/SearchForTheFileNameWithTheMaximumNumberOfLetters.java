package com.vusatui.commands;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import javax.inject.Inject;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.restrictions.Required;

@Command(
        name = "sfml",
        description = "Search for the file name with the maximum number " +
                "of letters ‘s’ in the name, display the path to it"
)
public class SearchForTheFileNameWithTheMaximumNumberOfLetters implements Runnable {

    @Inject
    private HelpOption<SearchForTheFileNameWithTheMaximumNumberOfLetters> help;

    @Required
    @Option(name = { "-p", "--path" },
            description = "Path where to search")
    private String path;

    @Required
    @Option(name = { "-l", "--letter" },
            description = "Letter to find")
    private String letter;

    @Required
    @Option(name = { "-o", "--output" },
            description = "Results file output path")
    private String outputPath;

    @Override
    public void run() {
        if (!help.showHelpIfRequested()) {
            try {
                search();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void search() throws IOException {
        System.out.printf("path: %s \n", path);
        System.out.printf("letter: %s \n", letter);
        System.out.printf("outputPath: %s \n", outputPath);

        MyFileVisitor myFileVisitor = new MyFileVisitor();
        Files.walkFileTree(Path.of(path), myFileVisitor);

        writeResults(
                myFileVisitor.getCandidateFileName() == null
                        ? String.format("There are no files with %s letter in name.", letter)
                        : String.format("File %s contains %s occurrences of \"%s\"",
                        myFileVisitor.getCandidateFileName(), myFileVisitor.getCandidateLettersCount(), letter)
        );
    }

    private void writeResults(String message) throws IOException {
        Path op = Path.of(outputPath);
        if (Files.notExists(op)) Files.createFile(op);
        Files.writeString(op, message + "\n", StandardOpenOption.APPEND);
    }


    private final class MyFileVisitor extends SimpleFileVisitor<Path> {

        private static final int FIRST_EL_INDEX = 0;

        private String candidateFileName;

        private long candidateLettersCount = 0;


        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            String fileName = file.getFileName().toString().toLowerCase();
            long count = fileName.chars().filter(c -> (char) c == letter.charAt(FIRST_EL_INDEX)).count();

            if (count > candidateLettersCount) {
                candidateFileName = fileName;
                candidateLettersCount = count;
            }

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.out.printf("Visit for $s failed with error: %s \n", file.toAbsolutePath(), exc.getMessage());
            return FileVisitResult.CONTINUE;
        }

        public String getCandidateFileName() {
            return candidateFileName;
        }

        public long getCandidateLettersCount() {
            return candidateLettersCount;
        }
    }
}
