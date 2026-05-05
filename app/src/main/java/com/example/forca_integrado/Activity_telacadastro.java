package com.example.forca_integrado;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_telacadastro extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private EditText textoDaPalavra;
    private Button btnCadastrar, btnListar;
    private RadioGroup grupo;
    private String categoriaSelecionada, palavra;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telacadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textoDaPalavra = findViewById(R.id.textPalavra);
        btnCadastrar = findViewById(R.id.button3);
        btnCadastrar.setOnClickListener(this);
        btnListar = findViewById(R.id.button4);
        btnListar.setOnClickListener(this);
        grupo = findViewById(R.id.idGroup);
        grupo.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnCadastrar){

        }
        if(v == btnListar){

        }
    }

    @Override
    public void onCheckedChanged(@NonNull RadioGroup radioGroup, int i) {
        if(radioGroup == grupo){
            RadioButton temporario = findViewById(i);
            Toast.makeText(Activity_telacadastro.this, temporario.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}