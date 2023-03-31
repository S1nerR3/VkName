package com.example.vkname;

import static com.example.vkname.tre.NetworkUtils.generateURL;
import static com.example.vkname.tre.NetworkUtils.getResponsFromURL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchBotton;
    private TextView result;

    class VKQueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = getResponsFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;

        }
        @Override
        protected void onPostExecute(String response){
            String firstname = null;
            String lastname = null;
            String id_user = null;

            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray jsonArray = jsonResponse.getJSONArray("response");
                JSONObject userInfo = jsonArray.getJSONObject(0);
                firstname = userInfo.getString("first_name");
                lastname = userInfo.getString("last_name");
                id_user = userInfo.getString("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String resultingString = "Name1: " + firstname + "\n" + "Name2: " + lastname + "\n" + "id: " + id_user;
            result.setText(resultingString);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchField = findViewById(R.id.et_search_field);
        searchBotton = findViewById(R.id.b_search_vk);
        result = findViewById(R.id.tv_result);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL generatedUrl = generateURL(searchField.getText().toString());

                new VKQueryTask().execute(generatedUrl);
            }
        };
        searchBotton.setOnClickListener(onClickListener);

    }
}