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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import vn.nguyenbuiquanghuy.android_project.R;

public class ProfileFragment extends Fragment {


    private ActivityResultLauncher<Intent> activityResultLauncher;

    TextView profileName, profileEmail;
    ImageView profileAvatar;
    FirebaseAuth auth;
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
