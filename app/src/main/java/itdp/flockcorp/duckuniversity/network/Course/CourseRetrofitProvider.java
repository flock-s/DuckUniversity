package itdp.flockcorp.duckuniversity.network.Course;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseRetrofitProvider {
    public static Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl("https://datasampleandroid.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
