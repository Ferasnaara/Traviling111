package com.example.traviling;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.example.traviling.LoginSignUpForgetPassword.ForgotPasswordFragment;
import com.example.traviling.LoginSignUpForgetPassword.LoginFragment;
import com.example.traviling.LoginSignUpForgetPassword.SignUpFragment;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        private void gotoSignpFragment() {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayoutMain, new SignUpFragment());
            ft.commit();
        }

        private void gotoLoginFragment () {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayoutMain, new LoginFragment());
            ft.commit();
        }

        private void gotoForgotPasswordFragment() {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayoutMain, new ForgotPasswordFragment());
            ft.commit();
        }

        private void gotoAddCardFragment() {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayoutMain, new introFragment());
            ft.commit();
        }
    }
}

