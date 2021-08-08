package com.example.woddy;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.woddy.DB.InitDBdata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    Button btnMoveToChatt;
    Button btnMoveToMyPage;
    Button btnLogin;
    Button addWritingsBtn, searchBtn;
    TextView tvDBTest;   // Test용
    TextView btnDBTest;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InitDBdata initDB = new InitDBdata();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        btnLogin = findViewById(R.id.btn_Login);
//        btnMoveToChatt = findViewById(R.id.btn_move_to_chatt);
//        btnMoveToMyPage = findViewById(R.id.btn_move_to_mypage);
//        tvDBTest = findViewById(R.id.tv_dbtest);
//        btnDBTest = findViewById(R.id.btn_dbtest);
//        addWritingsBtn = (Button) findViewById(R.id.addWritingBtn);
//        searchBtn = (Button) findViewById(R.id.searchBtn);
//
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        addWritingsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), AddWritingsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnDBTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                InitDBdata init = new InitDBdata(tvDBTest);
//            }
//        });
//
//        btnMoveToChatt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ChattingList.class);
//                startActivity(intent);
//            }
//        });

    }

    private void testDB() {
        ArrayList<String> alist = new ArrayList<String>();
        db = FirebaseDatabase.getInstance().getReference("/user/userNickName/chattingList/roomNumbers");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    //String str =dataSnapshot.getKey() + " : " +dataSnapshot.getValue();
                    //tvDBTest.append(dataSnapshot.getValue().toString());
                    alist.add(dataSnapshot.getValue().toString());
                    //Toast.makeText(getApplicationContext(), name + "  " + key, Toast.LENGTH_LONG);
                                    
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

}