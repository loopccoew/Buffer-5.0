import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class TaskManagerGUI extends JFrame {
    private ArrayList<Task> tasks;
    private JTextArea taskTextArea;

    public TaskManagerGUI() {
        tasks = new ArrayList<>();

        setTitle("Task Manager");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        taskTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(taskTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        JButton viewButton = new JButton("View Tasks");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTasks();
            }
        });

        JButton deleteButton = new JButton("Delete Task");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void addTask() {
        String title = JOptionPane.showInputDialog("Enter task title:");
        String description = JOptionPane.showInputDialog("Enter task description:");
        String deadlineStr = JOptionPane.showInputDialog("Enter task deadline (format: dd/MM/yyyy HH:mm):");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date deadline;
        try {
            deadline = dateFormat.parse(deadlineStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format! Please enter in the format: dd/MM/yyyy HH:mm");
            return;
        }

        Task newTask = new Task(title, description, deadline);
        tasks.add(newTask);
        Collections.sort(tasks, new TaskComparator()); // Sort tasks based on priority
        taskTextArea.append("Task added successfully!\n");
    }

    private void viewTasks() {
        if (tasks.isEmpty()) {
            taskTextArea.setText("No tasks to display.");
        } else {
            taskTextArea.setText("");
            for (Task task : tasks) {
                taskTextArea.append(task.toString() + "\n");
            }
        }
    }

    private void deleteTask() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tasks to delete.");
        } else {
            String[] taskTitles = new String[tasks.size()];
            for (int i = 0; i < tasks.size(); i++) {
                taskTitles[i] = tasks.get(i).getTitle();
            }
            String selectedTitle = (String) JOptionPane.showInputDialog(this, "Select task to delete:",
                    "Delete Task", JOptionPane.PLAIN_MESSAGE, null, taskTitles, taskTitles[0]);
            if (selectedTitle != null) {
                for (Task task : tasks) {
                    if (task.getTitle().equals(selectedTitle)) {
                        tasks.remove(task);
                        taskTextArea.append("Task '" + selectedTitle + "' deleted successfully!\n");
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskManagerGUI();
            }
        });
    }
}

class Task {
    private String title;
    private String description;
    private Date deadline;

    public Task(String title, String description, Date deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Description: " + description + ", Deadline: " + deadline;
    }
}

class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t1.getDeadline().compareTo(t2.getDeadline());
    }
}
