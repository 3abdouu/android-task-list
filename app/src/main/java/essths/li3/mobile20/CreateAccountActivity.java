package essths.li3.mobile20;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, phoneEditText, passwordEditText, confirmPasswordEditText;
    private Button createAccountButton;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        createAccountButton = findViewById(R.id.createAccountButton);

        db = new DatabaseHelper(this); // Initialize DatabaseHelper

        createAccountButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            // Validate inputs
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) ||
                    TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(CreateAccountActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(CreateAccountActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                return;
            }

            // Generate a random verification code
            int verificationCode = new Random().nextInt(900000) + 100000;

            // Send verification code (this is just a simulation for now)
            Toast.makeText(CreateAccountActivity.this, "Code envoyé : " + verificationCode, Toast.LENGTH_LONG).show();

            // Insert the new user into the database
            boolean result = db.insertUser(username, email, phone, password);
            if (result) {
                // Proceed to the next activity with phone number and verification code
                Intent intent = new Intent(CreateAccountActivity.this, AccountConfirmationActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra("code", verificationCode); // Pass the verification code
                startActivity(intent);
            } else {
                Toast.makeText(CreateAccountActivity.this, "Erreur lors de la création du compte", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
