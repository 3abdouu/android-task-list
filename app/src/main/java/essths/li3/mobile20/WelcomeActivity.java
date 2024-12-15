package essths.li3.mobile20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button startButton = findViewById(R.id.startButton);

        // Handle button click to navigate to HomeActivity
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, AddTaskActivity.class);
                startActivity(intent);
                finish(); // Optional: close the WelcomeActivity
            }
        });
    }
}
