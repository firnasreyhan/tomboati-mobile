package com.android.tomboati.utils.notif;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAl2HBAKg:APA91bGvh5uD1QU5QflaQ9Ng5AiiSkfAVni-00gDk6hzlcu8qca1XnF2PfDu7tk2TOmzoLiW0EnWuiEFt4hY7bVRju-7zU3AfQ6F6LQ64nm59CDWCJMzcodRnSypy4pgZMbg5sDvcAcz" // Your server key refer to video for finding your server key
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body NotificationSender body);
}