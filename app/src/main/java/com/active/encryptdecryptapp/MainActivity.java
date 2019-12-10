package com.active.encryptdecryptapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button encrypt,decrypt,encrypt_submit,decrypt_submit;
    EditText encrypt_text,decrypt_taxt;
    TextView encrypted_text,decrpted_text;
    LinearLayout main_layout;
    View encrypt_view,decrypt_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMainUi();
        setEncryptUi();
        setDecryptUi();
    }

    private void setDecryptUi() {
        decrypt_view=(View)findViewById(R.id.decrypt_view);
        decrypt_taxt=(EditText)findViewById(R.id.decrypt_text);
        decrpted_text=(TextView)findViewById(R.id.decrypted_text);
        decrypt_submit=(Button)findViewById(R.id.decrypt_submit);
        decrypt_taxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                decrpted_text.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        decrypt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(decrypt_taxt.getText().toString().trim().length()>0){
                    callDecryptionMethod();
                    hideKeyboard(v);
                }else {
                     Toast.makeText(MainActivity.this, "Please enter text for decryption", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void callDecryptionMethod() {
        String decrypt_str=decrypt_taxt.getText().toString();
        decrpted_text.setText(Decryption(decrypt_str));
    }

    private void setEncryptUi() {
        encrypt_view=(View)findViewById(R.id.encrypt_view);
        encrypt_text=(EditText)findViewById(R.id.encrypt_text);
        encrypted_text=(TextView)findViewById(R.id.encrypted_text);
        encrypt_submit=(Button)findViewById(R.id.encrypt_submit);
        encrypt_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                encrypted_text.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        encrypt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(encrypt_text.getText().toString().trim().length()>0){
                    callEncryptionMethod();
                    hideKeyboard(v);
                }else {
                    Toast.makeText(MainActivity.this, "Please enter text for encryption", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void callEncryptionMethod() {
        String encrypt_str=encrypt_text.getText().toString();
        encrypted_text.setText(Encryption(encrypt_str));
    }

    private void setUpMainUi() {
        main_layout=(LinearLayout)findViewById(R.id.main_layout);
        encrypt=(Button)findViewById(R.id.encrypt);
        decrypt=(Button)findViewById(R.id.decrypt);
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_layout.setVisibility(View.GONE);
                decrypt_view.setVisibility(View.GONE);
                encrypt_view.setVisibility(View.VISIBLE);
                encrypt_text.setText("");
                encrypted_text.setText("");
            }
        });
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_layout.setVisibility(View.GONE);
                encrypt_view.setVisibility(View.GONE);
                decrypt_view.setVisibility(View.VISIBLE);
                decrypt_taxt.setText("");
                decrpted_text.setText("");
            }
        });
    }



    @Override
    public void onBackPressed(){
        if(main_layout.getVisibility()==View.VISIBLE){
            super.onBackPressed();
        }else {
            main_layout.setVisibility(View.VISIBLE);
            encrypt_view.setVisibility(View.GONE);
            decrypt_view.setVisibility(View.GONE);
        }


    }




    public String Encryption(String string)
    {
        string=string+" ";
        String encrypt_sum="";
        int count=1;
        for(int i=0;i<string.length()-1;i++) {

                if (string.charAt(i) == string.charAt(i + 1)) {
                    count++;
                    continue;
                }
                encrypt_sum = encrypt_sum + string.charAt(i) + count;
                count = 1;

        }
        System.out.print(encrypt_sum);
        Log.e("Encryption","--------->"+encrypt_sum);
        return encrypt_sum;
    }

    public String Decryption(String string)
    {
        String decrypt_char="",decrypt_sum="",rev_count="";
        for(int j=0;j<string.length();j++)
        {
            if(Character.isDigit(string.charAt(j))){

                rev_count=String.valueOf(string.charAt(j));
                int r_count=Integer.parseInt(rev_count);
                for(int k=0;k<r_count;k++){
                    decrypt_sum=decrypt_sum + decrypt_char;
                }
            }else{
                decrypt_char=String.valueOf(string.charAt(j));
            }
        }
        System.out.print(decrypt_sum);
        Log.e("Decryption","--------->"+decrypt_sum);
        return decrypt_sum;
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
    }
}
