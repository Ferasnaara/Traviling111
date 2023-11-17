package com.example.traviling;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class AddProductActivity extends AppCompatActivity {
    @SuppressLint("WrongViewCast")
    private EditText Name, Brand, Info, Price = findViewById(R.id.btnStartMain);
    private Button Add;
    private ImageButton ProductPicture;
    private FirebaseServices fbs;

    public static final int PICK_IMAGE = 1;
    Bitmap ProductPho = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(AddProductActivity.this.getContentResolver(), data.getData());
                        ProductPicture.setImageBitmap(bitmap);
                        ProductPho = bitmap;
                        ProductPicture.setRotation(90);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(AddProductActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void add(View view)
    {
        String productName, proInfo, Company, proPhoto, proPrice;
        productName = Name.getText().toString();
        proInfo = Info.getText().toString();
        proPrice = Price.getText().toString();
        Company = Brand.getText().toString();
        if (ProductPicture.getDrawable() == null)
            proPhoto = "no_image";
        else
            proPhoto = UploadImageToFirebase();
        if (productName.trim().isEmpty() || proInfo.trim().isEmpty()
                || proPhoto.trim().isEmpty()||ProductPicture.getDrawable() == null)
        {
            Toast.makeText(this, "error fields empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            fbs.getFire().collection("Products")
                    .add(TAG)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        }
    }

    private String UploadImageToFirebase()
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ProductPho.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        StorageReference ref = fbs.getStorage().getReference("ProductsPicture/" + UUID.randomUUID().toString());
        UploadTask uploadTask = ref.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            }
        });
        return ref.getPath();
    }
    @Override
    public void onStart()
    {
        super.onStart();
        ConnectException();
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                add(view);
            }
        });
    }
    @SuppressLint("WrongViewCast")
    public void ConnectException()
    {
        Name = findViewById(R.id.loginbtn_signup);
        Info = findViewById(R.id.passsignup);
        ProductPicture = findViewById(R.id.editTextText2);
        fbs = FirebaseServices.getInstance();
        ProductPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }
}
