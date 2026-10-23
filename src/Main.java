import java.io.File;



public  class Main {
    public static void main(String[] args) {
        RegistryManager manager = new RegistryManager();

       

        File file = new File("src/students.txt");
        manager.addStudent(file.getAbsolutePath());

         System.out.println("adding student to file...");
        manager.addStudent(file.getAbsolutePath());

        manager.loadStudentsFromFile(file.getPath());
        manager.displayAllStudents();
        manager.groupStudentsByMajor();

        

    }
}