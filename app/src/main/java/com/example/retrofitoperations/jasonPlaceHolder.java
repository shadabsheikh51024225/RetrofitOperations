package com.example.retrofitoperations;

import java.util.List;
import java.util.Map;

import Model.Comment;
import Model.Post;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface jasonPlaceHolder {



    //CALL IS RETROFIT FUNC THAT IS REQUESTING LIST OF POST ..// ANOTHER FEATURE FROM RETROFIT// DIFFERENT EMD POINTS HANDLING.


    @GET("posts")
    Call<List<Post>> getPosts();


    // URL - https://jsonplaceholder.typicode.com/posts.


    // WHERE -https://jsonplaceholder.typicode.com/ IS BASE URL.


    // WHERE - /posts RELATIVE  URL




    // DYNAMIC URL :- https://jsonplaceholder.typicode.com/posts/1/comments

    // WHERE - /posts/1/comments     IS RELATIVE  URL

    // THIS ID WILL GIVE US FREEDOM TO REQUEST DIFFERENT POSTS.
    // WORKING WITH DIFFERENT END POINTS.
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);


    //IN GET REQUEST WE GET THE DATA FROM REQUESTED BODY.

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);



    @GET
    Call<List<Comment>> getComments(@Url String url);






    /**
     * IN POST REQUEST WE FORM THE BODY AND PUT DATA IN IT
     */

    @POST("posts")
    Call<Post> createPost(@Body Post post);


    /**
     *  ANOTHER WAY OF SEND DATA THROUGH URL ENCODING.
     *
     */

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );


    /**
     *  ANOTHER WAY OF SEND DATA THROUGH URL ENCODING, FIELD MAP FOR .
     *
     */
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);


    /**
     *  PUT AND PATCH ARE USED TO UPDATE VALUE ON SERVER
     */



    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);


    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);
}
