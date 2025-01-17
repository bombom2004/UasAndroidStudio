package com.stmik.op;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = null;
    private boolean isOperatorClicked = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // Pastikan file layout sesuai dengan nama

        // Inisialisasi TextView
        tvDisplay = findViewById(R.id.tvDisplay);

        // Inisialisasi Tombol Angka
        int[] numberButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9
        };

        for (int i = 0; i < numberButtonIds.length; i++) {
            Button button = findViewById(numberButtonIds[i]);
            button.setOnClickListener(view -> {
                if (isOperatorClicked) {
                    tvDisplay.setText("");
                    isOperatorClicked = false;
                }
                String currentText = tvDisplay.getText().toString();
                String buttonText = ((Button)view).getText().toString(); // Ambil teks tombol
                tvDisplay.setText(currentText + buttonText); // Menambahkan angka tombol yang diklik
            });
        }

        // Inisialisasi Tombol Operasi
        findViewById(R.id.btnAdd).setOnClickListener(view -> setOperator("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(view -> setOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(view -> setOperator("×"));
        findViewById(R.id.btnDivide).setOnClickListener(view -> setOperator("÷"));

        // Tombol Sama Dengan
        findViewById(R.id.btnEquals).setOnClickListener(view -> {
            String displayText = tvDisplay.getText().toString();
            secondNumber = displayText.isEmpty() ? 0 : Double.parseDouble(displayText);
            double result = calculateResult();
            tvDisplay.setText(String.valueOf(result));
            operator = null;
        });

        // Tombol Clear
        findViewById(R.id.btnClear).setOnClickListener(view -> {
            tvDisplay.setText("0");
            firstNumber = 0;
            secondNumber = 0;
            operator = null;
        });
    }

    private void setOperator(String op) {
        String displayText = tvDisplay.getText().toString();
        firstNumber = displayText.isEmpty() ? 0 : Double.parseDouble(displayText);
        operator = op;
        isOperatorClicked = true;
    }

    private double calculateResult() {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "×":
                return firstNumber * secondNumber;
            case "÷":
                if (secondNumber != 0) {
                    return firstNumber / secondNumber;
                } else {
                    return Double.NaN; // Menghindari pembagian dengan nol
                }
            default:
                return 0;
        }
    }
}
