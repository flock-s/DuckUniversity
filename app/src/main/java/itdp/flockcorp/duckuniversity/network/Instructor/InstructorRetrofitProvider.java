package itdp.flockcorp.duckuniversity.network.Instructor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstructorRetrofitProvider {
    public static Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
