package com.learning.retrofitapp.API_Services;

import java.util.List;

import com.learning.retrofitapp.models.Students;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IStudentAPI {

    @GET("students")
    Call<List<Students>> getStudents();

    @GET("students/{id}")
    Call<List<Students>> getStudents(@Path("id") int stdId);

    @POST("students")
    Call<Students> createStudent(@Body Students students);
}
