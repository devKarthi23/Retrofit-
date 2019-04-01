package dev.karthi.github.retrofit;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.karthi.github.retrofit.Model.CommentsResponse;
import dev.karthi.github.retrofit.Model.PostResponse;
import dev.karthi.github.retrofit.Network.APIClient;
import dev.karthi.github.retrofit.Network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3;
    TextView ResponseView;
    PostResponse postResponse=new PostResponse();
    List<PostResponse> postResponseList=new ArrayList<>();
    List<CommentsResponse> commentsResponses=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        ResponseView=findViewById(R.id.textView);


        ApiInterface apiInterface= APIClient.getRetrofitInstance().create(ApiInterface.class);

        final Call<List<PostResponse>> listCall=apiInterface.getPosts();
        final Call<PostResponse> singleResponseCall=apiInterface.getSinglePost();
        final Call<List<CommentsResponse>> commentsResponseCall=apiInterface.getComments(1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    singleResponseCall.enqueue(new Callback<PostResponse>() {
                        @Override
                        public void onResponse(Call<PostResponse> call, retrofit2.Response<PostResponse> response) {
                            if (response.body()!=null)
                            {
//
                                ResponseView.setText(""+postResponse);
                            }

                        }

                        @Override
                        public void onFailure(Call<PostResponse> call, Throwable t) {
Log.d("Sdvsdvsv",""+t.getMessage());
                            Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              try
              {
                  listCall.enqueue(new Callback<List<PostResponse>>() {
                      @Override
                      public void onResponse(Call<List<PostResponse>> call, retrofit2.Response<List<PostResponse>> response) {
                          if (response.body()!=null)
                          {
                              ResponseView.setText(""+response.body());
                          }
                      }

                      @Override
                      public void onFailure(Call<List<PostResponse>> call, Throwable t) {
                          Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                      }
                  });
              }catch (Exception e)
              {
                  Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
              }
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    commentsResponseCall.enqueue(new Callback<List<CommentsResponse>>() {
                        @Override
                        public void onResponse(Call<List<CommentsResponse>> call, Response<List<CommentsResponse>> response) {
                            if (response.body()!=null)
                            {
                                ResponseView.setText(""+response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<CommentsResponse>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch (Exception e)
                {

                }
            }
        });





    }


}
