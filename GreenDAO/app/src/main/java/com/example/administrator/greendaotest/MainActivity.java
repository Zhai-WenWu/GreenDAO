package com.example.administrator.greendaotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.greendaotest.db.GreenDaoUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Student> studentList = new ArrayList<>();
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button btn_query_all = findViewById(R.id.btn_query_all);
        tv_content = findViewById(R.id.tv_content);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        btn_query_all.setOnClickListener(this);

        for (int i = 0; i < 100; i++) {
            Student student = new Student((long) i, "huang" + i, 25, "666" + i,"男",i);
            studentList.add(student);
        }

        GreenDaoUtils.getInstance().insertListBaseUser(studentList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
//                StudentDaoOpe.insertData(this, studentList);
                GreenDaoUtils.getInstance().insertListBaseUser(studentList);
                break;
            case R.id.button2:
                Student student = new Student((long) 5, "haung" + 5, 25, "123456", "男", 8);
                /**
                 * 根据特定的对象删除
                 */
//                StudentDaoOpe.deleteData(this, student);
                GreenDaoUtils.getInstance().deleteBaseUser(student);
                /**
                 * 根据主键删除
                 */
//                StudentDaoOpe.deleteByKeyData(this, 7);
//                StudentDaoOpe.deleteAllData(this);
                break;
            case R.id.button3:
//                student = new Student((long) 2, "caojin", 1314,"888888");
//                StudentDaoOpe.updateData(this, student);
//                GreenDaoUtils.getInstance().
                break;
            case R.id.button4:
                Student students = GreenDaoUtils.getInstance().getBaseUser(5);
                tv_content.setText("id:" + students.getId() + "age:" + students.getAge() + "name:" + students.getName() + "sex" + students.getI());
//                for (int i = 0; i < students.size(); i++) {
//                    Log.i("Log", students.get(i).getName());
//                }
                break;
        }
    }
}
