package itdp.flockcorp.duckuniversity.course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import itdp.flockcorp.duckuniversity.R;
import itdp.flockcorp.duckuniversity.network.Course.CourseApiConnect;
import itdp.flockcorp.duckuniversity.network.Course.CourseData;
import itdp.flockcorp.duckuniversity.network.Course.CourseRetrofitProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {
    private RecyclerView mrecyclerView;
    private ProgressBar pbInstructor;
    private CourseApiConnect api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Course List");
        setContentView(R.layout.activity_course);
        mrecyclerView=(RecyclerView)findViewById(R.id.rvCourse);
        pbInstructor = findViewById(R.id.pbCourseLoading);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        generateCourse();


    }
    private void generateCourse(){
        api = CourseRetrofitProvider.getClient().create(CourseApiConnect.class);
        final Call<CourseData> dataSet = api.getCourse();
        dataSet.enqueue(new Callback<CourseData>() {
            @Override
            public void onResponse(Call<CourseData> call, Response<CourseData> response) {
                CourseAdapter adapter = new CourseAdapter(response.body(),CourseActivity.this);
                mrecyclerView.setAdapter(adapter);
                pbInstructor.setVisibility(View.GONE);
                mrecyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<CourseData> call, Throwable t) {
                System.out.println("MESSAGE = " + t.getMessage());
            }
        });
    }
}
