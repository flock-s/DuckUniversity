package itdp.flockcorp.duckuniversity.network.Course;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CourseApiConnect {
    @GET("/scheduleAPI/select_course")
    Call<CourseData> getCourse();

}
