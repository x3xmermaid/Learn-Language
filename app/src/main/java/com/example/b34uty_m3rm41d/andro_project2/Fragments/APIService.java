package com.example.b34uty_m3rm41d.andro_project2.Fragments;

import com.example.b34uty_m3rm41d.andro_project2.Notifications.MyResponse;
import com.example.b34uty_m3rm41d.andro_project2.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA5cJVaIw:APA91bHUFh4owQ5xDKMc5nsZljTJhySiFbq5N9cDeyyfCIIx-fH6V4Ck7nigILoOeLZduvlGihn_0Atcy_FbnnB2GSfBQR2v3tVqLTTlsvG2nQyVo9uiyyoLQ9lhWCzox1lG1Y3geyF6"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);


}
