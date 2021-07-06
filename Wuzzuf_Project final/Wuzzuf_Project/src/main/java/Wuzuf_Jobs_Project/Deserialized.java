package Wuzuf_Jobs_Project;

public class Deserialized {
    public static void test(){
        EntryDAO dao = new EntryDAO("C:\\Users\\georg\\IdeaProjects\\Wuzzuf_Jobs_Projects\\src\\main\\resources\\Wuzzuf_Jobs.csv");
        System.out.println("Successfully loaded the file");
    }
}
