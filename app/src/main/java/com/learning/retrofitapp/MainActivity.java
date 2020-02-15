package com.learning.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.learning.retrofitapp.API_Services.IStudentAPI;
import com.learning.retrofitapp.models.Students;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txtStudentList;
    IStudentAPI studentAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtStudentList=findViewById(R.id.txtStudents);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://qr-attendance-system.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        studentAPI=retrofit.create(IStudentAPI.class);
        getAll();
        //getById();
        //addStudent();

    }

    private void addStudent() {
        Students student=new Students(108,"Ekta","Java");
        Call<Students> call=studentAPI.createStudent(student);

        call.enqueue(new Callback<Students>() {
            @Override
            public void onResponse(Call<Students> call, Response<Students> response) {
                if(!response.isSuccessful()){
                    txtStudentList.setText("Issue: "+response.code());
                }
                else {
                    txtStudentList.setText("Details Added Successfully!!");
                }
            }

            @Override
            public void onFailure(Call<Students> call, Throwable t) {

            }
        });
    }

    private void getAll(){
        Call<List<Students>> call=studentAPI.getStudents();

        call.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                if(!response.isSuccessful()){
                    txtStudentList.setText("Issue: "+response.code());
                }
                List<Students> studentsList=response.body();
                for(Students student:studentsList)
                {
                    String details="";
                    details+="Student id:"+student.getStudentId()+"\n";
                    details+="Student Name: "+student.getStudentName()+"\n";
                    details+="Batch Code: "+student.getBatch()+"\n\n";

                    txtStudentList.append(details);

                }
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {
                txtStudentList.setText(t.getMessage());
            }
        });
    }

    public void getById() {
        Call<List<Students>> call=studentAPI.getStudents(103);

        call.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                if(!response.isSuccessful()){
                    txtStudentList.setText("Issue: "+response.code());
                }
                List<Students> studentsList=response.body();
                for(Students student:studentsList)
                {
                    String details="";
                    details+="Student id:"+student.getStudentId()+"\n";
                    details+="Student Name: "+student.getStudentName()+"\n";
                    details+="Batch Code: "+student.getBatch()+"\n\n";

                    txtStudentList.append(details);

                }
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {
                txtStudentList.setText(t.getMessage());
            }
        });
    }
}
