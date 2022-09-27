package itdp.flockcorp.duckuniversity.instructor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import itdp.flockcorp.duckuniversity.R;
import itdp.flockcorp.duckuniversity.network.Instructor.InstructorApiConnect;
import itdp.flockcorp.duckuniversity.network.Instructor.InstructorData;
import itdp.flockcorp.duckuniversity.network.Instructor.InstructorRetrofitProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstructorActivity extends AppCompatActivity {
    private RecyclerView mrecyclerView;
    private ProgressBar pbInstructor;
    private InstructorApiConnect api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Instructor List");
        setContentView(R.layout.activity_instructor);
        mrecyclerView=(RecyclerView)findViewById(R.id.rvInstructor);
        pbInstructor = findViewById(R.id.pbInstructorLoading);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        generateInstructor();
    }

    private void generateInstructor(){
        api = InstructorRetrofitProvider.getClient().create(InstructorApiConnect.class);
        final Call<InstructorData> dataSet = api.getPerson("10");
        dataSet.enqueue(new Callback<InstructorData>() {
            @Override
            public void onResponse(Call<InstructorData> call, Response<InstructorData> response) {
                InstructorAdapter adapter = new InstructorAdapter(InstructorActivity.this,response.body());
                mrecyclerView.setAdapter(adapter);
                pbInstructor.setVisibility(View.GONE);
                mrecyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<InstructorData> call, Throwable t) {

            }
        });
    }

}
