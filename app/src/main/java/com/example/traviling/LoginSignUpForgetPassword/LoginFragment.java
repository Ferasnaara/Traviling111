package com.example.traviling.LoginSignUpForgetPassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.traviling.R;


public class LoginFragment extends Fragment {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView ForgotPassword;
    private Button btnSignup;


    private static final String ARG_P1 = "p1";
    private static final String ARG_P2 = "p2";

    private String mP1;
    private String mP2;

    public LoginFragment() {
    }


    public static LoginFragment newInstance(String p1, String p2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_P1, p1);
        args.putString(ARG_P2, p2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mP1 = getArguments().getString(ARG_P1);
            mP2 = getArguments().getString(ARG_P2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }


    public void onStart() {
        super.onStart();

        init();


    }

    private void init() {
        etUsername = getView().findViewById(R.id.etEmailLogin);
        etPassword = getView().findViewById(R.id.etPasswordLogin);
        btnLogin = getView().findViewById(R.id.btnLoginLogin);
        ForgotPassword = getView().findViewById(R.id.btnSignupLogin);
        btnSignup = getView().findViewById(R.id.btnSignupLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: check data is not empty
                 // if yes check with firebase
                 // else ask user to enter user/pass

            }
        });
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: goto forgot password
            }
        });
    }
}
