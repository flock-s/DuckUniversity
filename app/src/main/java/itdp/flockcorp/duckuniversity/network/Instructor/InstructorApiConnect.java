package itdp.flockcorp.duckuniversity.network.Instructor;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InstructorApiConnect {
    @GET("api/")
    Call<InstructorData> getPerson(@Query("results") String results);
}