package com.example.vkname.tre;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.net.Uri;
import java.util.Scanner;

public class NetworkUtils {
    private static final String VK_URL = "https://dev.vk.com";
    private static final String Vk_user_get = "/method/users.get";
    private static final String Vk_User_id = "user_ids";
    private static final String Vk_version = "v";
    private static final String ACCESS_TOKEN = "access_token";

    public static URL generateURL(String userId){
        Uri builtUri = Uri.parse(VK_URL + Vk_user_get)
                .buildUpon()
                .appendQueryParameter(Vk_User_id, userId)
                .appendQueryParameter(Vk_version, "5.131")
                .appendQueryParameter(ACCESS_TOKEN, "d6925db1d6925db1d6925db195d58172cedd692d6925db1b288ac33bc5cc10e7ef0f06d")
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String getResponsFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
    try {
        InputStream in = urlConnection.getInputStream();
        Scanner scaner = new Scanner(in);
        scaner.useDelimiter("\\A");
        boolean hasInput = scaner.hasNext();
        if (hasInput) {
            return scaner.next();
        } else {
            return null;
        }
    }finally {
        urlConnection.disconnect();
        }
    }
}
