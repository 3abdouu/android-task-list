package essths.li3.mobile20;

import java.util.ArrayList;

public class TaskData {
    private static ArrayList<String> taskList = new ArrayList<>();

    public static ArrayList<String> getTaskList() {
        return taskList;
    }

    public static void addTask(String task) {
        taskList.add(task);
    }

    public static void removeTask(int position) {
        taskList.remove(position);
    }
}

