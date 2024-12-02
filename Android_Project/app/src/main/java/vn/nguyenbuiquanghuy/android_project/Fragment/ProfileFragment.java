package vn.nguyenbuiquanghuy.android_project.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;

import vn.nguyenbuiquanghuy.android_project.R;

public class ProfileFragment extends Fragment {


    private ActivityResultLauncher<Intent> activityResultLauncher;

    TextView profileName, profileEmail;
    ImageView profileAvatar;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        // Ánh xạ các đối tượng trong layout
        profileName = view.findViewById(R.id.txtProfilename);
        profileEmail = view.findViewById(R.id.txtProfileEmail);
        profileAvatar = view.findViewById(R.id.avatar);

        // Nhận dữ liệu từ Bundle (Arguments)
        Bundle bundle = getArguments();
        if (bundle != null) {
            String nameUser = bundle.getString("username");
            String emailUser = bundle.getString("email");

            // Cập nhật giao diện
            profileName.setText(nameUser);
            profileEmail.setText(emailUser);
        }


        profileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return view;
    }
}
