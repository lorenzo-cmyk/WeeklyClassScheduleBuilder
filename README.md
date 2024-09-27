# Weekly Class Schedule Builder (WCSB)

WCSB is a command-line tool designed to help you create and manage weekly class schedules. It allows you to generate
example JSON files and convert these JSON files into ICS files to be imported in online calendars such as MS Outlook.

## Requirements

- Java 23

## Usage

To use WCSB, you can use the following command-line options:

- `-h`, `--help`: Displays help information.
- `-v`, `--version`: Displays the current version of the program.
- `-e`, `--example`: Generates an example JSON file. Must be used with `-o` (output).
- `-s`, `--startDay`: Specifies the week start date. Must be a Monday formatted as `YYYY-MM-DD`.
- `-o`, `--output`: Specifies the output file path for saving the generated result.
- `-i`, `--input`: Specifies the input JSON file to be processed into a usable ICS file. Must be used with `-s` (
  startDay) and `-o` (output).

### Examples

1. Display help information:
    ```sh
    java -jar WCSB.jar -h
    ```

2. Display version information:
    ```sh
    java -jar WCSB.jar -v
    ```

3. Generate an example JSON file:
    ```sh
    java -jar WCSB.jar -e -o /path/to/output/example.json
    ```

4. Convert a JSON file to an ICS file:
    ```sh
    java -jar WCSB.jar -i /path/to/input/schedule.json -s 2023-10-02 -o /path/to/output/schedule.ics
    ```
