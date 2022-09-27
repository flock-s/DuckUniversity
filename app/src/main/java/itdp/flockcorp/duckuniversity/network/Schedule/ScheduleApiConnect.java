package itdp.flockcorp.duckuniversity.network.Schedule;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ScheduleApiConnect {
    @GET("/scheduleAPI/select")
    Call<ScheduleData> getSchedule();

    @FormUrlEncoded
    @POST("/scheduleAPI/add")
    Call<ScheduleData.Schedule> createPost(
        @Field("start_date") String start_date,
        @Field("end_date") String end_date,
        @Field("course_code") String course_code,
        @Field("ruangan") String ruangan,
        @Field("ins_code") String ins_code,
        @Field("pic") String pic,
        @Field("peserta") String peserta,
        @Field("pict") String pict
    );

    @FormUrlEncoded
    @POST("/scheduleAPI/delete")
    Call<Void> deleteSchedule(@Field("id") String id);

    @FormUrlEncoded
    @POST("/scheduleAPI/update")
    Call<Void> updateSchedule(
            @Field("id") String id,
            @Field("start_date") String start_date,
            @Field("end_date") String end_date,
            @Field("course_code") String course_code,
            @Field("ruangan") String ruangan,
            @Field("ins_code") String ins_code,
            @Field("pic") String pic,
            @Field("peserta") String peserta,
            @Field("pict") String pict
    );

}