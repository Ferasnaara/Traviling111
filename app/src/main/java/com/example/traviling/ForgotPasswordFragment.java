package com.example.traviling;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class  ForgotPasswordFragment extends Fragment
{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private EditText btnForgot;
    private Button etEmailforgot;
    private FirebaseAuth mAuth;

    private void Reset() {

        etEmailforgot = getView().findViewById(R.id.Emailtextlogin);
        btnForgot = getView().findViewById(R.id.forgotpassbtn);
        mAuth = FirebaseAuth.getInstance();
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = btnForgot.getText().toString();
                if (email.trim().isEmpty()) {
                    Toast.makeText(getContext(), "SOMETHING FAILED ! " + "", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Email Has send" + "", Toast.LENGTH_SHORT).show();
                        LoginFragment LogInFragment=new LoginFragment();
                        FragmentManager manager=getFragmentManager();
                        manager.beginTransaction().replace(R.id.forgotpassbtn,LogInFragment,LogInFragment.getTag()).commit();
                    }
                });
            }
        });
    }
    public ForgotPasswordFragment() {
    }
    public static ForgotPasswordFragment newInstance(String param1, String param2) {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_up_fragment, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        Reset();
    }
}