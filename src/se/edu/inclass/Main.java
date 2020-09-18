package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        //System.out.println("Printing deadlines");
        //printDeadlines(tasksData);

        //System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        printDeadlinesUsingStreams(tasksData);

        System.out.println("filter by string: " + System.lineSeparator());

        for (Task t : filterByString(tasksData, "11")) {
            System.out.println(t);
        }

        //printData(tasksData);

        //System.out.println("Printing deadlines");
       // printDeadlines(tasksData);

        //System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        printDataUsingStreams(tasksData);
        System.out.println(System.lineSeparator());
       // printDeadlinesUsingStream(tasksData);
        System.out.println(System.lineSeparator());
        System.out.println(countDeadlinesUsingStreams(tasksData));

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static int countDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Calculating count using streams");
        int count = 0;
        count = (int) tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .count();

        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Printing data using streams");
        tasksData.stream()
                .forEach(System.out::println);

    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Printing deadline using Streams");
        tasksData.stream()
                .filter((s) -> s instanceof Deadline)
                .sorted((a,b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }

    // filter task by a string
    public static ArrayList<Task> filterByString(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasksData.stream()
                .filter((s) -> s.getDescription().contains(filterString))
                .collect(toList());

        return filteredTaskList;
    }
}
