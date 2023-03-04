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
        name = "afs",
        description = "The average file size in the specified directory or any its subdirectory."
)
public class AverageFileSize implements Runnable {

    @Inject
    private HelpOption<SearchForTheFileNameWithTheMaximumNumberOfLetters> help;

    @Required
    @Option(name = { "-p", "--path" },
            description = "Path where to search")
    private String path;

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
        long avr = myFileVisitor.getTop().stream().map(FileRecord::getSize).reduce(0L, Long::sum) / myFileVisitor.getTop().size();
        writeResults(String.format("The average size of file in %s directory is %s", path, avr));
    }

    private void writeResults(String message) throws IOException {
        Path op = Path.of(outputPath);
        if (Files.notExists(op)) Files.createFile(op);
        Files.writeString(op, message + "\n", StandardOpenOption.APPEND);
    }

    private final class MyFileVisitor extends SimpleFileVisitor<Path> {

        private final List<FileRecord> top = new LinkedList<>();

        public List<FileRecord> getTop() {
            return top;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            top.add(new FileRecord(file.getFileName().toString(), attrs.size()));
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.out.printf("Visit for $s failed with error: %s %n", file.toAbsolutePath(), exc.getMessage());
            return FileVisitResult.CONTINUE;
        }
    }

    private final class FileRecord {

        private final String name;

        private final long size;

        private FileRecord(String name, long size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public long getSize() {
            return size;
        }
    }
}
