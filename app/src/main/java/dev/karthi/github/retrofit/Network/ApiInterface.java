package dev.karthi.github.retrofit.Network;


import java.util.List;

import dev.karthi.github.retrofit.Model.CommentsResponse;
import dev.karthi.github.retrofit.Model.PostResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {



    @GET("/posts")
    Call<List<PostResponse>> getPosts();

    @GET("/posts/1")
    Call<PostResponse> getSinglePost();

    @GET("/comments")
    Call<List<CommentsResponse>> getComments(@Query("postId")int postId);


}
