package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView valueTxt = (TextView)findViewById(R.id.valueTxt);
        Button retrieveBtn = (Button)findViewById(R.id.retrieveBtn);
        Button storeBtn = (Button)findViewById(R.id.storeBtn);

        Web3j web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/89f8b74a4c87488e8b8b0fc7789a8020"));
        Credentials credentials = Credentials.create("07d8c213e8d97e7d44e473c879291636ef222b2a750a4452033fabd8d4c9dd6e");
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        A a = A.load("0x425270f8F684a20331Be97Af2F5fd1ACa5593550",web3, credentials, contractGasProvider);
        a.retrieve().flowable().subscribeOn(Schedulers.io()).subscribe(new Consumer<BigInteger>() {
            @Override
            public void accept(BigInteger bigInteger) throws Exception {
                Log.i("vac","accept" + bigInteger);
                String value = String.valueOf();
                valueTxt.setText(value);
            }
        });
        a.store(new BigInteger("123")).flowable().subscribeOn(Schedulers.io()).subscribe(new Consumer<TransactionReceipt>() {
            @Override
            public void accept(TransactionReceipt transactionReceipt) throws Exception {
                Log.i("vac", "accept: ");
            }
        });

        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}