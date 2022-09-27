package itdp.flockcorp.duckuniversity.network.instructorForm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstructorFormRetrofitProvider {

    public static Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl("https://datasampleandroid.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
