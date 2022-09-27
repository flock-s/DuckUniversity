package itdp.flockcorp.duckuniversity.network.Schedule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScheduleRetrofitProvider {
    public static Retrofit getClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl("https://datasampleandroid.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
