package com.example.mayur.googledirection;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Url;
import retrofit2.Call;

public interface DirectionEndPoint {

    @GET()

    public Call<DirectionResponse> getDirectionData(@Url String url);


}
