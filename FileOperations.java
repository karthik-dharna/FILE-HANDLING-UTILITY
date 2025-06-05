import java.io.*;
import java.util.Scanner;

public class FileOperations {
    private static final String FILE_NAME = "example.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void writeFile() {
        System.out.println("\n===============================");
        System.out.println("      Write to File");
        System.out.println("===============================");

        System.out.print("Enter text to write: ");
        String content = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(content);
            System.out.println("✅ File written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    public static void readFile() {
        System.out.println("\n===============================");
        System.out.println("      Read File");
        System.out.println("===============================");

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("File not found!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("\nFile Content:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("   > " + line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static void modifyFile() {
        System.out.println("\n===============================");
        System.out.println("      Modify File");
        System.out.println("===============================");

        System.out.print("Enter text to append: ");
        String newContent = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write("\n" + newContent);
            System.out.println("✅ File modified successfully.");
        } catch (IOException e) {
            System.err.println("Error modifying file: " + e.getMessage());
        }
    }

    public static void replaceWord() {
        System.out.println("\n===============================");
        System.out.println("      Replace Word in File");
        System.out.println("===============================");

        System.out.print("Enter word to replace: ");
        String oldWord = scanner.nextLine();
        System.out.print("Enter new word: ");
        String newWord = scanner.nextLine();

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println(" File not found!");
                return;
            }

            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line.replace(oldWord, newWord)).append("\n");
                }
            }

            try (FileWriter writer = new FileWriter(FILE_NAME)) {
                writer.write(fileContent.toString());
            }

            System.out.println("✅ Word replaced successfully!");
        } catch (IOException e) {
            System.err.println("Error replacing word: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===============================");
            System.out.println("      Choose an Option");
            System.out.println("===============================");
            System.out.println("[1] Write to File");
            System.out.println("[2] Read File");
            System.out.println("[3] Modify File");
            System.out.println("[4] Replace Word in File");
            System.out.println("[5] Exit");
            System.out.print("\n>> Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1: writeFile(); break;
                case 2: readFile(); break;
                case 3: modifyFile(); break;
                case 4: replaceWord(); break;
                case 5: System.out.println("\n🚀 Exiting program. Goodbye!"); return;
                default: System.out.println(" Invalid choice. Please try again.");
            }
        }
    }
}
