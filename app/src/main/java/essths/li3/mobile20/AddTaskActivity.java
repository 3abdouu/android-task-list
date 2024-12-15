package essths.li3.mobile20;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    private TaskAdapter taskAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Charge le fichier menu_main.xml dans l'activité
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            // Redirige l'utilisateur vers l'écran de connexion
            Intent intent = new Intent(AddTaskActivity.this, LoginActivity.class);
            // Ajoute un flag pour que LoginActivity remplace l'activité actuelle dans la pile
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish(); // Ferme l'activité actuelle (AddTaskActivity)
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        EditText taskTitleEditText = findViewById(R.id.taskTitleEditText);
        Button saveButton = findViewById(R.id.saveButton);
        RecyclerView tasksRecyclerView = findViewById(R.id.tasksRecyclerView);

        // Récupère la liste des tâches de TaskData
        ArrayList<String> taskList = TaskData.getTaskList();
        taskAdapter = new TaskAdapter(taskList);

        // Configure le RecyclerView
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksRecyclerView.setAdapter(taskAdapter);

        // Ajouter une tâche lorsqu'on clique sur le bouton "Enregistrer"
        saveButton.setOnClickListener(v -> {
            String taskTitle = taskTitleEditText.getText().toString().trim();

            if (!taskTitle.isEmpty()) {
                // Ajoute la tâche dans TaskData et notifie l'adaptateur
                TaskData.addTask(taskTitle);
                taskAdapter.notifyItemInserted(taskList.size() - 1);

                // Réinitialise le champ de saisie
                taskTitleEditText.setText("");
            }
        });
    }
}
