package com.example.faster.secure_me;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;

public class MainActivity extends Activity {

    Button start, choose, secure, insecure, download, navigate_secure, navigate_insecure;
    TextView fileName, decryptedFileName;
    EditText encryptNumber, encryptPassword, decryptNumber, decryptPassword, fname;
    File dataFile;
    Uri dataPath;
    FirebaseStorage storage;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        choose = (Button) findViewById(R.id.choose);
        secure = (Button) findViewById(R.id.secure);
        download = (Button) findViewById(R.id.download);
        navigate_secure = (Button) findViewById(R.id.secure_navigate);
        insecure = (Button) findViewById(R.id.insecure);
        navigate_insecure = (Button) findViewById(R.id.insecure_navigate);
        fileName = (TextView) findViewById(R.id.fileName);
        decryptedFileName = (TextView) findViewById(R.id.decryptFileName);
        fname = (EditText) findViewById(R.id.fname);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();



    }

    @Override
    public void onBackPressed() {
        setContentView(R.layout.navigator);
    }
    public void start(View v) {
        setContentView(R.layout.navigator);
    }
    public void navigate_secure(View v) {
        setContentView(R.layout.secure);
    }
    public void navigate_insecure(View v) {
        setContentView(R.layout.insecure);
        Toast.makeText(this, "Select Encrypted File!", Toast.LENGTH_SHORT).show();
    }
    public void navy_upload(View v) {
        setContentView(R.layout.upload);
    }
    public void navy_download(View v) {
        setContentView(R.layout.download);
    }

    public void choose(View v) {
        new MaterialFilePicker()
                .withActivity(MainActivity.this)
                .withRequestCode(5)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == RESULT_OK) {
            dataFile = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
            dataPath = Uri.fromFile(dataFile);
            Toast.makeText(this, "File Selected:" + dataFile.getName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "File Not Selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void secure(View v) {
        encryptPassword = (EditText) findViewById(R.id.encryptPassword);
        encryptNumber = (EditText) findViewById(R.id.encryptNumber);
        if (dataFile == null && encryptNumber.length()==0&&encryptPassword.length()==0) {
            Toast.makeText(this, "Fill all field", Toast.LENGTH_SHORT).show();
        } else {

            String number = encryptNumber.getText().toString();
            String password = encryptPassword.getText().toString();
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Encrypting...");
            dialog.show();
            randomProcessor r = new randomProcessor();
            r.encrypt(dataFile, number, password);
            dialog.dismiss();
            Toast.makeText(this, "encrypted", Toast.LENGTH_SHORT).show();
        }
    }
    public void insecure(View v) {
        decryptNumber = (EditText) findViewById(R.id.decryptNumber);
        decryptPassword = (EditText) findViewById(R.id.decryptPassword);
        if (dataFile == null && decryptNumber.length() == 0 && decryptPassword.length() == 0) {
            Toast.makeText(getApplicationContext(), "Fill all field", Toast.LENGTH_SHORT).show();
        } else {
            String number = decryptNumber.getText().toString();
            String password = decryptPassword.getText().toString();
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Decrypting...");
            dialog.show();
            randomProcessor r = new randomProcessor();
            r.decrypt(dataFile, number, password);
            dialog.dismiss();
            Toast.makeText(this, "Decrypted", Toast.LENGTH_SHORT).show();
        }
    }

    public void upload(View v) {
        if (dataPath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();

            StorageReference ref = storageReference.child(dataFile.getName());
            ref.putFile(dataPath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed" + exception, Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }

    public void download(View v) {
        fname = (EditText) findViewById(R.id.fname);
        if (fname.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter File Name", Toast.LENGTH_SHORT).show();
        } else {
            String fn = fname.getText().toString();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Downloading");
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();


            try {
                StorageReference dreference = storage.getReferenceFromUrl("gs://faster-5b764.appspot.com").child(fn);
                File sd = Environment.getExternalStorageDirectory();
                File dir = new File(sd.getAbsolutePath(), "Secure_me");
                File fold = new File(dir.getAbsolutePath(), "Downloads");
                if (!dir.exists() && fold.exists()) {
                    dir.mkdir();
                    fold.mkdir();
                }
                    final File localFile = new File(fold.getAbsolutePath(), fn);
                    dreference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Downloaded", Toast.LENGTH_SHORT).show();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed" + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch(Exception e){
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

