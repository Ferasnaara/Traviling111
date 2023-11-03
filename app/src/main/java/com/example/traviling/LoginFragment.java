package com.example.traviling;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginFragment extends Fragment {

    private EditText Username, Password;
    private Button btnLogin;
    private TextView ForgotPassword, Signup;

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

    @Override
    public void onStart() {
        super.onStart();

        Username = getView().findViewById(R.id.etUsernameLogin);
        Password = getView().findViewById(R.id.etPasswordLogin);
        btnLogin = getView().findViewById(R.id.btnLoginLogin);
        ForgotPassword = getView().findViewById(R.id.tvForgotPasswordLogin);
        Signup = getView().findViewById(R.id.tvSignupLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }}
