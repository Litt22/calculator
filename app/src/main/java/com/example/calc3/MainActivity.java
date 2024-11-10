package com.example.calc3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    TextView resTextView;
    CalculatorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
        resTextView = findViewById(R.id.resultTextView);
        viewModel.getDisplay().observe(this, r -> resTextView.setText(r));

        butIni();
    }

    void butIni() {
        Button[] buttons = new Button[]{
                findViewById(R.id.buttonNull),
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9)


        };
        for (int i = 0; i < buttons.length; i++) {
            final String value = String.valueOf(i);
            buttons[i].setOnClickListener(v -> viewModel.appendInput(value));

        }
        findViewById(R.id.buttonPlus).setOnClickListener(v -> viewModel.setOperator("+"));
        findViewById(R.id.buttonDelenie).setOnClickListener(v -> viewModel.setOperator("/"));
        findViewById(R.id.buttonDelete).setOnClickListener(v -> viewModel.deleteInput());
        findViewById(R.id.buttonDote).setOnClickListener(v -> viewModel.appendInput("."));
        findViewById(R.id.buttonUmno).setOnClickListener(v -> viewModel.setOperator("*"));
        findViewById(R.id.buttonMinus).setOnClickListener(v -> viewModel.setOperator("-"));
        findViewById(R.id.buttonC).setOnClickListener(v -> viewModel.clear());
        findViewById(R.id.buttonRavno).setOnClickListener(v -> viewModel.calcRes());

    }


}