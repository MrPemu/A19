package com.example.a19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    int quantity = 2;
    final int code = 1;

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

    String totalText;

    public void submitOrder(View view) {
        double unitPrice = 1.50;

        EditText nombreEditText = findViewById(R.id.editText_enter);

        String name = nombreEditText.getText().toString();
        String text_quantity = name + getString(R.string.youordered) + " " + quantity + " " + getString(R.string.coffeeswith) + " ";

        CheckBox whippedCreamCheckbox = findViewById(R.id.check_whipped_cream);
        boolean whippedCreamChecked = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = findViewById(R.id.check_chocolate);
        boolean chocolateChecked = chocolateCheckbox.isChecked();

        if (whippedCreamChecked) {
            text_quantity += getString(R.string.whipped_cream);
            unitPrice += 0.50;
            if (chocolateChecked) text_quantity += " " + getString(R.string.and) + " ";
        }

        if (chocolateChecked) {
            text_quantity += getString(R.string.chocolate);
            unitPrice += 0.50;
        }

        double totalPrice = unitPrice * quantity;
        String text_price = getString(R.string.totalprice) + " " + totalPrice + "€";

        EditText emailEditText = findViewById(R.id.email);
        String email = emailEditText.getText().toString();
        String yourEmailIs = getString(R.string.youremail) + " " + email;

        totalText = text_quantity + "\n" + text_price + "\n" + yourEmailIs;

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("texto", totalText);
        startActivityForResult(intent, code);
    }

    public void sendEmail() {

        String message = totalText;

        EditText emailEditText = findViewById(R.id.email);
        String email = emailEditText.getText().toString();

        Intent sendIntent = new Intent();
        
        sendIntent.setAction(sendIntent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("mailto:"));
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.affair));

        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        } else {
            Toast.makeText(this, "There is no email client installed", Toast.LENGTH_SHORT).show();
        }
    }

    public void toast() {
        Toast toast = Toast.makeText(this, R.string.correction, Toast.LENGTH_LONG);
        toast.show();
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == code && resultCode == RESULT_OK) {
            sendEmail();
        }
        else if (requestCode == code && resultCode == RESULT_CANCELED) {
            toast();
        }
    }
}