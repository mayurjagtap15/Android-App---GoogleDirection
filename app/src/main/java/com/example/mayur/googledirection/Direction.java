package com.example.mayur.googledirection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mayur.googledirection.Constant.ALTERNATIVE_PLACEHOLDER;
import static com.example.mayur.googledirection.Constant.BASE_URL_ETA;
import static com.example.mayur.googledirection.Constant.DESTINATION_PLACE_HOLDER;
import static com.example.mayur.googledirection.Constant.DIRECTION_API_KEY;
import static com.example.mayur.googledirection.Constant.KEY_PLACEHOLDER;
import static com.example.mayur.googledirection.Constant.MODE_PLACE_HOLDER;
import static com.example.mayur.googledirection.Constant.ORIGIN_PLACE_HOLDER;

public class Direction extends AppCompatActivity {

    EditText edtSource,edtDestination,edtMode;

    TextView txtTime,txtDistance,txtMode;

    Button btnSearch;

    ProgressBar progress;

    RadioButton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);

        txtTime=findViewById(R.id.time);
        txtDistance=findViewById(R.id.dist);

        edtSource=findViewById(R.id.edtsrc);
        edtDestination=findViewById(R.id.edtdest);

        btnSearch=findViewById(R.id.btnserach);

        progress=findViewById(R.id.progress);

    }


    public  void OnSearchClick ( View view){

        progress.setVisibility(View.VISIBLE);

        String source = edtSource.getText().toString().trim();
        String destination = edtDestination.getText().toString().trim();

      /*if (TextUtils.isEmpty(source)){

            edtSource.setError("Enter the Source");
            edtSource.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(destination)){

            edtDestination.setError("Enter the Desination");
            edtDestination.requestFocus();
            return;
        }

        String mode;

      int modeid= rgd*/

        String url = BASE_URL_ETA+ ORIGIN_PLACE_HOLDER + source + DESTINATION_PLACE_HOLDER + destination + MODE_PLACE_HOLDER + "driving" + ALTERNATIVE_PLACEHOLDER + false + KEY_PLACEHOLDER + DIRECTION_API_KEY;

        DirectionEndPoint apiService = ApiClient.getClient().create(DirectionEndPoint.class);

        Call<DirectionResponse> callDirection = apiService.getDirectionData(url);

        callDirection.enqueue(new Callback<DirectionResponse>() {
            @Override
            public void onResponse(Call<DirectionResponse> call, Response<DirectionResponse> response) {

                progress.setVisibility(View.GONE);

                DirectionResponse directionResponse = response.body();

                ElementsItem element = directionResponse.getRows().get(0).getElements().get(0);

                String distance = element.getDistance().getText();

                String time = element.getDuration().getText();

                txtDistance.setText("Distance in Km   : " + distance);
                txtTime.setText("Time in Minutes : " + time);



            }

            @Override
            public void onFailure(Call<DirectionResponse> call, Throwable t) {

            }
        });
    }

   /* public  void  showToast( String msg ){

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();*/
    }

