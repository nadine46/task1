import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.io.PrintWriter;
import java.util.Scanner;


public class RegistryManager {
    private List<Student> students = new ArrayList<>();

    public void saveStudentsToFile(int id, String name, double gpa, String major) {
        try (FileWriter writer = new FileWriter("src/newStudents.txt", true)) {
            writer.write(id + "," + name + "," + gpa + "," + major);
            writer.write("\n");
            System.out.println("Students saved successfully");

        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }
    }

    public void loadStudentsFromFile(String fileName) {

        students.clear();

        try (

                BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean headerSkipped = false;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                String[] data = line.split(",");
                if (data.length != 4) {
                    System.out.println("invalid student record: " + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    double gpa = Double.parseDouble(data[2].trim());
                    String major = data[3].trim();
                    Student student = new Student(id, name, gpa, major);
                    students.add(student);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID or grade: " + line);
                }
            }
            System.out.println("Student loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void displayAllStudents() {
        System.out.println("\nALL STUDENTS");

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void groupStudentsByMajor() {
        Map<String, List<String>> groups = new LinkedHashMap<>();
        for (Student student : students) {
            String major = student.getMajor();
            if (!groups.containsKey(major)) {
                groups.put(major, new ArrayList<>());
            }
            groups.get(major).add(student.getName());
        }
        System.out.println("\nSTUDENTS GROUPED BY MAJOR");
        for (Map.Entry<String, List<String>> entry : groups.entrySet()) {
            System.out.println("\n" + entry.getKey());

            for (String name : entry.getValue()) {
                System.out.println(name);
            }
        }
    }

    public void addStudent(String fileName) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter student ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter student name: ");
        String name = input.nextLine();

        System.out.print("Enter student GPA: ");
        double gpa = input.nextDouble();
        input.nextLine();

        System.out.print("Enter student major: ");
        String major = input.nextLine();

        Student student = new Student(id, name, gpa, major);

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));

            writer.println(id + "," + name + "," + gpa + "," + major);

            students.add(student);

            System.out.println("Student added successfully!");

        } catch (IOException e) {
            System.out.println("there was an Error on writting to file: " + e.getMessage());
        }
    }

}
