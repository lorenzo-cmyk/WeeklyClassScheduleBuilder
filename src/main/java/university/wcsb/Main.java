package university.wcsb;

import org.apache.commons.cli.*;
import university.wcsb.core.ConvertJSONtoICS;
import university.wcsb.core.GenerateExampleJSONFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();
        CommandLine commandLine;

        try {
            commandLine = commandLineParser.parse(options, args);

            if (commandLine.hasOption("v")) {
                if (commandLine.getOptions().length != 1) {
                    throw new ParseException("Invalid combination of options provided. " +
                            "Please use the -h flag for help.");
                }
                System.out.println("WCSB - Weekly Class Schedule Builder V0.1");

                return;
            }

            if (commandLine.hasOption("h")) {
                if (commandLine.getOptions().length != 1) {
                    throw new ParseException("Invalid combination of options provided. " +
                            "Please use the -h flag for help.");
                }
                helpFormatter.printHelp("WCSB", options);

                return;
            }

            if (commandLine.hasOption("e")) {
                if (!commandLine.hasOption("o")) {
                    throw new ParseException("Invalid combination of options provided. " +
                            "Please use the -h flag for help.");
                }
                if (commandLine.getOptions().length != 2) {
                    throw new ParseException("Invalid combination of options provided. " +
                            "Please use the -h flag for help.");
                }
                Path outputPath = validateOutputPath(commandLine.getOptionValue("o"));

                GenerateExampleJSONFile.generateExampleJSONFile(outputPath);

                return;
            }

            if (commandLine.hasOption("i")) {
                if (!commandLine.hasOption("s") || !commandLine.hasOption("o")) {
                    throw new ParseException("Invalid combination of options provided. " +
                            "Please use the -h flag for help.");
                }
                if (commandLine.getOptions().length != 3) {
                    throw new ParseException("Invalid combination of options provided. " +
                            "Please use the -h flag for help.");
                }
                Path inputPath = validateInputPath(commandLine.getOptionValue("i"));
                LocalDate startDay = validateStartDate(commandLine.getOptionValue("s"));
                Path outputPath = validateOutputPath(commandLine.getOptionValue("o"));

                ConvertJSONtoICS.convertJSONtoICS(inputPath, startDay, outputPath);

                return;
            }

            if (commandLine.hasOption("d")) {
                if (!commandLine.hasOption("s") || !commandLine.hasOption("o")) {
                    throw new ParseException("Invalid combination of options provided. " +
                            "Please use the -h flag for help.");
                }
                if (commandLine.getOptions().length != 3) {
                    throw new ParseException("Invalid combination of options provided. " +
                            "Please use the -h flag for help.");
                }
                Path inputDirectoryPath = validateInputDirectoryPath(commandLine.getOptionValue("d"));
                LocalDate startDay = validateStartDate(commandLine.getOptionValue("s"));
                Path outputDirectoryPath = validateOutputDirectoryPath(commandLine.getOptionValue("o"));

                ConvertJSONtoICS.convertJSONDirectoryToICS(inputDirectoryPath, startDay, outputDirectoryPath);

                return;
            }

            helpFormatter.printHelp("WCSB", options);
        } catch (ParseException e) {
            System.out.println("Error - " + e.getMessage());
        }
    }

    private static Options addOptions() {
        Options options = new Options();
        options.addOption("h", "help", false,
                "Displays help information.");
        options.addOption("v", "version", false,
                "Displays the current version of the program.");
        options.addOption("e", "example", false,
                "Generates an example JSON file. " +
                        "Must be used with -o (output).");
        options.addOption("s", "startDay", true,
                "Specifies the week start date. " +
                        "Must be a Monday formatted as YYYY-MM-DD.");
        options.addOption("o", "output", true,
                "Specifies the output file path for saving the generated result.");
        options.addOption("i", "input", true,
                "Specifies the input JSON file to be processed into a usable ICS file. " +
                        "Must be used with -s (startDay) and -o (output).");
        options.addOption("d", "directory", true,
                "Specifies the input directory - containing multiple JSON files - to perform a batch " +
                        "conversion into usable ICS files. Must be used with -s (startDay) and -o (output). " +
                        "The output flag should specify an output directory. " +
                        "The destination folder will be created automatically.");
        return options;
    }

    private static Path validateOutputPath(String path) throws ParseException {
        if (path == null || path.isBlank()) {
            throw new ParseException("No output file path provided. " +
                    "Please provide a valid file path.");
        }

        Path toBeCreatedFilePath = Paths.get(path);
        if (toBeCreatedFilePath.toFile().exists()) {
            if (toBeCreatedFilePath.toFile().isFile()) {
                throw new ParseException("A file already exists at the output file path provided. " +
                        "Please provide a valid file path.");
            }
            if (toBeCreatedFilePath.toFile().isDirectory()) {
                throw new ParseException("The output file path provided is a directory. " +
                        "Please provide a valid file path.");
            }
        }
        return toBeCreatedFilePath;
    }

    private static Path validateInputPath(String path) throws ParseException {
        if (path == null || path.isBlank()) {
            throw new ParseException("No input file path provided. " +
                    "Please provide a valid file path.");
        }

        Path toBeReadFilePath = Paths.get(path);
        if (!toBeReadFilePath.toFile().exists()) {
            throw new ParseException("The input file path provided does not exist. " +
                    "Please provide a valid file path.");
        }
        if (toBeReadFilePath.toFile().isDirectory()) {
            throw new ParseException("The input file path provided is a directory. " +
                    "Please provide a valid file path.");
        }
        return toBeReadFilePath;
    }

    private static Path validateInputDirectoryPath(String path) throws ParseException {
        if (path == null || path.isBlank()) {
            throw new ParseException("No directory path provided. " +
                    "Please provide a valid directory path.");
        }

        Path toBeReadDirectoryPath = Paths.get(path);
        if (!toBeReadDirectoryPath.toFile().exists()) {
            throw new ParseException("The directory path provided does not exist. " +
                    "Please provide a valid directory path.");
        }
        if (toBeReadDirectoryPath.toFile().isFile()) {
            throw new ParseException("The directory path provided is a file. " +
                    "Please provide a valid directory path.");
        }
        return toBeReadDirectoryPath;
    }

    private static Path validateOutputDirectoryPath(String path) throws ParseException {
        if (path == null || path.isBlank()) {
            throw new ParseException("No directory path provided. " +
                    "Please provide a valid directory path.");
        }

        Path toBeCreatedDirectoryPath = Paths.get(path);
        if (toBeCreatedDirectoryPath.toFile().exists()) {
            if (toBeCreatedDirectoryPath.toFile().isFile()) {
                throw new ParseException("A file already exists at the output directory path provided. " +
                        "Please provide a valid directory path.");
            }
            if (toBeCreatedDirectoryPath.toFile().isDirectory()) {
                throw new ParseException("The output directory already exists. " +
                        "Please provide a valid directory path.");
            }
        }
        return toBeCreatedDirectoryPath;
    }

    private static LocalDate validateStartDate(String startDate) throws ParseException {
        if (startDate == null || startDate.isBlank()) {
            throw new ParseException("No start date provided. " +
                    "Please provide a valid start date.");
        }

        LocalDate startDay = LocalDate.parse(startDate);
        if (startDay.getDayOfWeek() != DayOfWeek.MONDAY) {
            throw new ParseException("The start date provided is not a Monday. " +
                    "Please provide a valid start date.");
        }
        return startDay;
    }
}
