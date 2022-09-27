package itdp.flockcorp.duckuniversity.network.instructorForm;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InstructorFormApiConnect {
    @GET("/scheduleAPI/select_inst")
    Call<InstructorForm> getInstructorList();
}
