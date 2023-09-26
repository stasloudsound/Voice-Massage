package com.example.myapplicationactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.EditText;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    static final int RECOGNIZER_REQUEST_CODE = 2;
    static String text;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);

        findViewById(R.id.btn_tree).setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,editText.getText().toString());
            startActivity(intent);

        });

        findViewById(R.id.btn_rec).setOnClickListener(view -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Skajika cto to!");
            startActivityForResult(intent,2);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RECOGNIZER_REQUEST_CODE && resultCode == RESULT_OK && data!=null){
            List<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Log.i("recognizer : " , result.get(0));
            text = result.get(0);
            editText.setText(editText.getText() + " " + text);
        }
    }}

