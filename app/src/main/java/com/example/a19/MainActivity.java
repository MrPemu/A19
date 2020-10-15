package com.example.a19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");

        Intent intent = new Intent(this, SecondActivity.class);

        startActivity(intent);
    }

    public void decrement(View view) {
        if (quantity > 0) quantity--;
        display(quantity);
    }

    public void increment(View view) {
        if (quantity < 25) quantity++;
        display(quantity);
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.textView_count);
        quantityTextView.setText(String.valueOf(number));
    }

    public void submitOrder(View view) {
        double unitPrice = 1.50;

        EditText nombreEditText = findViewById(R.id.editText_enter);

        String name = nombreEditText.getText().toString();
        String text_quantity = name + ", you ordered " + quantity + " coffees with"; // R.string.coffees

        CheckBox whippedCreamCheckbox = findViewById(R.id.check_whipped_cream);
        boolean whippedCreamChecked = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = findViewById(R.id.check_chocolate);
        boolean chocolateChecked = chocolateCheckbox.isChecked();

        if (whippedCreamChecked) {
            text_quantity += " whipped cream";
            unitPrice += 0.50;
            if (chocolateChecked) text_quantity += " and";
        }

        if (chocolateChecked) {
            text_quantity += " chocolate";
            unitPrice += 0.50;
        }

        double totalPrice = unitPrice * quantity;
        String text_price = "Total price is " + totalPrice + "€";

        String totalText = text_quantity + "\n" + text_price;

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("texto", totalText);
        startActivity(intent);
    }

}