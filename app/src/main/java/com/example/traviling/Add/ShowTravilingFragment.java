package com.example.traviling.Add;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traviling.FirebaseServices;
import com.example.traviling.MyAdapter;
import com.example.traviling.R;
import com.example.traviling.Traviling;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ShowTravilingFragment extends Fragment {

    private FirebaseServices fbs;
    private ArrayList<Traviling> t;
    private RecyclerView tr;
    private MyAdapter adapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public  ShowTravilingFragment() {
    }

    public static ShowTravilingFragment newInstance(String param1, String param2) {
        ShowTravilingFragment fragment = new ShowTravilingFragment();
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

    @SuppressLint("WrongViewCast")
    @Override
    public void onStart() {
        super.onStart();

        fbs = FirebaseServices.getInstance();
        t = new ArrayList<>();
        tr = getView().findViewById(R.id.btnForgotLogin);
        adapter = new MyAdapter(getActivity(), t);
        tr.setAdapter(adapter);
        tr.setHasFixedSize(true);
        tr.setLayoutManager(new LinearLayoutManager(getActivity()));
        fbs.getFire().collection("planets").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot dataSnapshot: queryDocumentSnapshots.getDocuments()){
                    Traviling travil = dataSnapshot.toObject(Traviling.class);

                    travil.setDescription(travil);
                }
                adapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();
                Log.e("ShowProductFragment", e.getMessage());
            }
        });
    }
}