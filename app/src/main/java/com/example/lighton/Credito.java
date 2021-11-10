package com.example.lighton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class Credito extends AppCompatActivity {

    private static final int SCAN_RESULT = 100;
    private TextView textViewTarjeta;
    private TextView textViewFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credito);
        init();
    }

    private void init(){
        textViewTarjeta = (TextView)  findViewById(R.id.textViewTarjeta);
        textViewFecha = (TextView)  findViewById(R.id.textViewFecha);
    }

    public void scanearTarjeta(View view) {
        Intent intent = new Intent(this, CardIOActivity.class)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
        startActivityForResult(intent, SCAN_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SCAN_RESULT){
            if(data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)){
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                textViewTarjeta.setText(scanResult.getRedactedCardNumber());

                if(scanResult.isExpiryValid()){
                    String mes = String.valueOf(scanResult.expiryMonth);
                    String an = String.valueOf(scanResult.expiryYear);
                    textViewFecha.setText(mes + "/" + an);
                }
            }
        }
    }
}