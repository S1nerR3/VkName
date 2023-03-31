package com.example.vkname.tre;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.net.Uri;
import java.util.Scanner;

public class NetworkUtils {
    private static final String VK_URL = "https:/api.vk.com";
    private static final String Vk_user_get = "/method/users.get";
    private static final String Vk_User_id = "user_ids";
    private static final String Vk_version = "v";
    private static final String ACCESS_TOKEN = "access_token";

    public static URL generateURL(String userId){
        Uri builtUri = Uri.parse(VK_URL + Vk_user_get)
                .buildUpon()
                .appendQueryParameter(Vk_User_id, userId)
                .appendQueryParameter(Vk_version, "5.131")
                .appendQueryParameter(ACCESS_TOKEN, "9f46a7a69f46a7a69f46a7a6929c55902799f469f46a7a6fb5b3f172400d5940f2e3238")
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
