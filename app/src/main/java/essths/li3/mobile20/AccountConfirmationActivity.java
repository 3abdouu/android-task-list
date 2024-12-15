package essths.li3.mobile20;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AccountConfirmationActivity extends AppCompatActivity {

    private EditText codeEditText;
    private Button verifyButton;
    private int verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_confirmation);

        codeEditText = findViewById(R.id.codeEditText);
        verifyButton = findViewById(R.id.verifyButton);

        verificationCode = getIntent().getIntExtra("code", -1);

        verifyButton.setOnClickListener(v -> {
            String enteredCode = codeEditText.getText().toString().trim();

            if (TextUtils.isEmpty(enteredCode)) {
                Toast.makeText(AccountConfirmationActivity.this, "Veuillez entrer le code", Toast.LENGTH_SHORT).show();
                return;
            }

            if (Integer.parseInt(enteredCode) == verificationCode) {
                Toast.makeText(AccountConfirmationActivity.this, "Compte vérifié avec succès", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AccountConfirmationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(AccountConfirmationActivity.this, "Code incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
