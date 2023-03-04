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
        name = "tfl",
        description = "Print Top-5 largest files (by size in bytes)"
)
public class TopFiveLargestFiles implements Runnable {

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
        List<FileRecord> top = myFileVisitor.getTop();
        top.sort((a, b) -> Long.compare(b.getSize(), a.getSize()));
        List<String> fileRecords = top.subList(0, 5)
                .stream().map(fileRecord ->
                        String.format("%s - %s bytes", fileRecord.getName(), fileRecord.getSize())).toList();

        writeResults(String.format("Top largest file under %s directory:%n %s",
                path, String.join("\n", fileRecords)));
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
