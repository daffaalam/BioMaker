package com.daffaalam.biomaker.function;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.daffaalam.biomaker.BuildConfig;
import com.daffaalam.biomaker.R;

@SuppressLint("Registered")
public class AllFunction extends AppCompatActivity {

    private Context context_function;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context_function = AllFunction.this;
    }

    protected void MyToast(String text) {
        Toast.makeText(context_function, text, Toast.LENGTH_SHORT).show();
    }

    protected void MyIntentClass(Class destination) {
        startActivity(new Intent(context_function, destination));
    }

    protected void MyIntentView(Uri parsing) {
        startActivity(new Intent(Intent.ACTION_VIEW, parsing));
    }

    protected void DialogAboutMe() {
        new AlertDialog.Builder(context_function)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("About This App")
                .setMessage("\nProject ini dibangun oleh :\n" +
                        "Abiyyu Daffa\' Alam\n")
                .setNeutralButton("FeedBack", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(Intent.createChooser(new Intent(Intent.ACTION_SEND).putExtra(Intent.EXTRA_EMAIL, new String[]{"daffaalam@gmail.com"}).putExtra(Intent.EXTRA_SUBJECT, "FeedBack \"" + getResources().getString(R.string.app_name) + "\" (" + BuildConfig.VERSION_NAME + " version)").setType("message/rfc822"), "Send Your FeedBack"));
                    }
                })
                .setNegativeButton("See More", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyIntentView(Uri.parse("https://telegra.ph/daffaalam-06-06"));
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .create().show();
    }

    protected void DialogExitApp() {
        new AlertDialog.Builder(context_function)
                .setMessage("Yakin ingin keluar?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .show();
    }
}
