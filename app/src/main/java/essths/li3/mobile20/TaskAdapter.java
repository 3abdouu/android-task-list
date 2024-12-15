package essths.li3.mobile20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<String> taskList;

    public TaskAdapter(ArrayList<String> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        String taskTitle = taskList.get(position);
        holder.taskTitleTextView.setText(taskTitle);

        // Supprimer la tâche lorsqu'on clique sur le bouton
        holder.deleteButton.setOnClickListener(v -> {
            TaskData.removeTask(position); // Supprimer la tâche de TaskData
            notifyItemRemoved(position); // Rafraîchir l'affichage
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox taskCheckBox;
        TextView taskTitleTextView;
        Button deleteButton; // Bouton de suppression

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskCheckBox = itemView.findViewById(R.id.taskCheckBox);
            taskTitleTextView = itemView.findViewById(R.id.taskTitleTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton); // Initialiser le bouton de suppression
        }
    }
}
