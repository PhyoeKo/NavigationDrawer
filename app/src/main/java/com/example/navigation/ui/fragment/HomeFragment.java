package com.example.navigation.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation.R;

import java.io.File;
import java.io.FileOutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    Context context;
    Button createFolder, btn_delete, btn_save;
    String folderName = "MyFolder";
    TextView textView;
    ImageView imageView;
    Bitmap bitmap;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = this.getContext();
        createFolder = view.findViewById(R.id.button);
        btn_delete = view.findViewById(R.id.btn_delete);
        textView = view.findViewById(R.id.tv);
        btn_save = view.findViewById(R.id.btn_save);
        imageView = view.findViewById(R.id.imageView);
        bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ;


        createFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFolder(folderName);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFolder(folderName);
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createDirectoryAndSaveFile(bitmap, "profile.png");
            }
        });
    }

    public void createFolder(String fname) {
        String myfolder = Environment.getExternalStorageDirectory() + "/" + fname;
        File f = new File(myfolder);
        if (!f.exists())
            try {
                f.mkdir();
                Toast.makeText(getActivity(), myfolder + "  created.", Toast.LENGTH_SHORT).show();
                textView.setText(myfolder);
            } catch (Exception e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();

            }
        else
            Toast.makeText(getActivity(), myfolder + " already exits.", Toast.LENGTH_SHORT).show();
    }

    public void deleteFolder(String fname) {
        String myfolder = Environment.getExternalStorageDirectory() + "/" + fname;
        File f = new File(myfolder);
        if (f.exists())
            try {
                f.delete();
                Toast.makeText(getActivity(), myfolder + "  Deleted.", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        else
            Toast.makeText(getActivity(), myfolder + " already deleted", Toast.LENGTH_SHORT).show();
    }


    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {
        String string_path = Environment.getExternalStorageDirectory() + "/MyPhoto";
        File path = new File(string_path);

        if (!path.exists()) {
            File newDir = new File(string_path);
            newDir.mkdirs();
        }

        File file = new File(new File(string_path), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(getActivity(), "Saved to "+ string_path,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
