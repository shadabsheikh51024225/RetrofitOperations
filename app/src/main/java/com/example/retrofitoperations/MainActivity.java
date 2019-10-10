package com.example.retrofitoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import Model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    private jasonPlaceHolder jasonPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         *  RETROFIT IS TYPE SAFE REST CLIENT FOR JAVA AND ANDROID CREATED BY SQUARE.
         *
         *
         *
         *
         *
         *  - it provide abstraction layer on top of http class and generate low level code for network handling,
         *  like url generation , multithreading , security  etc..
         *
         *  - Retrofit requires at minimum Java 7 or Android 2.3.
         *
         *
         *
         *
         *
         *
         *  step - 1 :- add dep to gradel.app
         *
         *  -    implementaiton  'com.squareup.retrofit2:converter-gson:2.4.0'
         *  -    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
         *
         *
         *  step - 2 :- add values to pojo.
         *
         *
         *  step - 3 :- add interface to handel the api operations.
         */



         textView = findViewById(R.id.maintextview);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.
                        create()).
                        build();

        //ON ABOVE STATEMENT WE ARE CREATING A RETROFIT OBJECT WITH BUILDER AND ADD CONVERTER FACTORY TO COVERT GSON AND  BUILD OBJECT.


        //finally creating a retrofit object.
        jasonPlaceHolder = retrofit.create(jasonPlaceHolder.class);

        Call<List<Post>> call = jasonPlaceHolder.getPosts();


        //TO AVOID CALL.EXECUTE IN MAIN THREAD WE USE ENQUEUE METHOD TO GET AND SEND RESPONSE.
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {


                //IF RESPONSE IS NOT SUCCESSFUL GET THR RESPONSE CODE
                if(!response.isSuccessful())
                {
                    textView.setText(response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts)
                {

                    //SIMPLE DATA REPRESENTATION ON TEXT VIEW
                    String data = "ID :" +post.getId()+"\n"+"User Id :"+ post.getUserId()+"\n"+"Tittle :"+post.getTitle()+"\n"+"Body :"+post.getText()+"\n\n";

                    textView.append(data);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                //ON FAILURE GET THE RESPONSE CODE.
                textView.setText(t.getMessage());

            }
        });
    }
}
