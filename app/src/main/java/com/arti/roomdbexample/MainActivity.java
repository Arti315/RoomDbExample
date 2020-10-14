package com.arti.roomdbexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button savebtn,deletebtn,readbtn,updatebtn;
    static Mydatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.etd1);
        e2=findViewById(R.id.etd2);
        e3=findViewById(R.id.etd3);
        e4=findViewById(R.id.etd4);
        deletebtn=findViewById(R.id.btn_delete);
        updatebtn=findViewById(R.id.btn_update);
        savebtn=findViewById(R.id.btn_save);
        readbtn=findViewById(R.id.btn_read);

         mydatabase = Room.databaseBuilder(getApplicationContext(), Mydatabase.class, "userdb").
                allowMainThreadQueries().fallbackToDestructiveMigration().build();

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

            }
        });

        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
    }

    public void saveData(){
        int userid=Integer.parseInt(e1.getText().toString());
        String username=e2.getText().toString();
        String useremail=e3.getText().toString();

        User user = new User();
        user.setId(userid);
        user.setName(username);
        user.setEmail(useremail);

        mydatabase.myDao().addUser(user);
        Toast.makeText(MainActivity.this, " Data Added Successfully", Toast.LENGTH_SHORT).show();

        e1.setText("");
        e2.setText("");
       e3.setText("");

    }

    public void getData(){
        List<User> users=mydatabase.myDao().getUser();
        String info=" ";

        for (User usr:users){
            int id=usr.getId();
            String name=usr.getName();
            String email=usr.getEmail();
            info=info+"\n\n"+"Id : "+id+"\n Name :"+name+"\n Email :"+email;

            Toast.makeText(this, ""+info, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(){
        int id=Integer.parseInt(e1.getText().toString());
        User user=new User();
        user.setId(id);

        mydatabase.myDao().deleteUser(user);

        Toast.makeText(this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

    public void updateData(){
        int id=Integer.parseInt(e1.getText().toString());
        String name=e2.getText().toString();
        String email=e3.getText().toString();

        User use=new User();
        use.setId(id);
        use.setName(name);
        use.setEmail(email);

        mydatabase.myDao().updateUser(use);
        Toast.makeText(this, "Data Updated Succefully", Toast.LENGTH_SHORT).show();
    }

}





