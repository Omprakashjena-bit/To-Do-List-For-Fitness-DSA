
import java.util.*;

class Workout {
    int id;
    String exercise;
    int sets;
    int reps;
    double weight;

    Workout(int id, String exercise, int sets, int reps, double weight) {
        this.id = id;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    void display() {
        System.out.println("ID: " + id +
                " | Exercise: " + exercise +
                " | Sets: " + sets +
                " | Reps: " + reps +
                " | Weight: " + weight);
    }
}

public class FitnessTodoList {

    static LinkedList<Workout> workouts = new LinkedList<>();
    static Stack<Workout> undoStack = new Stack<>();
    static Queue<String> workoutQueue = new LinkedList<>();
    static HashMap<String, List<String>> exerciseCategories = new HashMap<>();

    static Scanner sc = new Scanner(System.in);
    static int idCounter = 1;

    // Initialize Exercise Categories (Hashing)
    static void initializeExercises() {

        exerciseCategories.put("Chest",
                Arrays.asList("Bench Press","Incline Press","Push Ups"));

        exerciseCategories.put("Back",
                Arrays.asList("Pull Ups","Deadlift","Rows"));

        exerciseCategories.put("Legs",
                Arrays.asList("Squats","Leg Press","Lunges"));

        exerciseCategories.put("Shoulders",
                Arrays.asList("Shoulder Press","Lateral Raise"));

        exerciseCategories.put("Arms",
                Arrays.asList("Biceps Curl","Triceps Pushdown"));
    }

    // Add Workout
    static void addWorkout() {

        System.out.print("Exercise name: ");
        String exercise = sc.nextLine();

        System.out.print("Sets: ");
        int sets = sc.nextInt();

        System.out.print("Reps: ");
        int reps = sc.nextInt();

        System.out.print("Weight: ");
        double weight = sc.nextDouble();
        sc.nextLine();

        Workout w = new Workout(idCounter++, exercise, sets, reps, weight);

        workouts.add(w);      // LinkedList insertion
        undoStack.push(w);    // Stack push

        System.out.println("Workout added.");
    }

    // Display Workouts
    static void viewWorkouts() {

        if(workouts.isEmpty()){
            System.out.println("No workouts found.");
            return;
        }

        for(Workout w : workouts){
            w.display();
        }
    }

    // Search Workout (Linear Search)
    static void searchWorkout(){

        System.out.print("Enter exercise to search: ");
        String name = sc.nextLine();

        boolean found = false;

        for(Workout w : workouts){

            if(w.exercise.equalsIgnoreCase(name)){
                w.display();
                found = true;
            }

        }

        if(!found)
            System.out.println("Workout not found.");
    }

    // Delete Workout
    static void deleteWorkout(){

        System.out.print("Enter workout ID: ");
        int id = sc.nextInt();

        Iterator<Workout> it = workouts.iterator();

        while(it.hasNext()){

            Workout w = it.next();

            if(w.id == id){
                it.remove();
                System.out.println("Workout deleted.");
                return;
            }

        }

        System.out.println("Workout not found.");
    }

    // Undo Last Workout (Stack)
    static void undoWorkout(){

        if(undoStack.isEmpty()){
            System.out.println("Nothing to undo.");
            return;
        }

        Workout last = undoStack.pop();
        workouts.remove(last);

        System.out.println("Last workout removed.");
    }

    // Queue Upcoming Workout
    static void addUpcomingWorkout(){

        System.out.print("Enter workout for future: ");
        String name = sc.nextLine();

        workoutQueue.add(name);

        System.out.println("Added to workout queue.");
    }

    static void viewUpcomingWorkouts(){

        if(workoutQueue.isEmpty()){
            System.out.println("No upcoming workouts.");
            return;
        }

        System.out.println("Upcoming Workouts:");

        for(String w : workoutQueue){
            System.out.println(w);
        }
    }

    // Sorting Exercises
    static void sortWorkouts(){

        workouts.sort(Comparator.comparing(w -> w.exercise));

        System.out.println("Workouts sorted.");
    }

    // Show Exercise Categories
    static void showExercises(){

        for(String category : exerciseCategories.keySet()){

            System.out.println("\n" + category + ":");

            for(String exercise : exerciseCategories.get(category)){
                System.out.println("- " + exercise);
            }

        }
    }

    public static void main(String[] args) {

        initializeExercises();

        while(true){

            System.out.println("\n===== FITNESS TO-DO LIST =====");

            System.out.println("1 Add Workout");
            System.out.println("2 View Workouts");
            System.out.println("3 Search Workout");
            System.out.println("4 Delete Workout");
            System.out.println("5 Undo Last Workout");
            System.out.println("6 Add Upcoming Workout");
            System.out.println("7 View Upcoming Workouts");
            System.out.println("8 Show Exercise Categories");
            System.out.println("9 Sort Workouts");
            System.out.println("10 Exit");

            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1: addWorkout(); break;
                case 2: viewWorkouts(); break;
                case 3: searchWorkout(); break;
                case 4: deleteWorkout(); break;
                case 5: undoWorkout(); break;
                case 6: addUpcomingWorkout(); break;
                case 7: viewUpcomingWorkouts(); break;
                case 8: showExercises(); break;
                case 9: sortWorkouts(); break;
                case 10: System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }

        }

    }

}