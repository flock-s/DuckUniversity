package itdp.flockcorp.duckuniversity.ui.deleteschedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import itdp.flockcorp.duckuniversity.R;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleApiConnect;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleData;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleRetrofitProvider;
import itdp.flockcorp.duckuniversity.ui.homeschedule.Constants;
import itdp.flockcorp.duckuniversity.ui.homeschedule.CustomAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteScheduleFragment extends Fragment {
    private DeleteScheduleViewModel toolsViewModel;
    private RecyclerView mrecyclerView;
    private ProgressBar pbSchedule;
    private ScheduleApiConnect api;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(DeleteScheduleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_delete_schedule, container, false);
        Constants.ClassPosition = "DELETE SCHEDULE";
        mrecyclerView=(RecyclerView)root.findViewById(R.id.rvScheduleDelete);
        pbSchedule = root.findViewById(R.id.progressBarDelete);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        generateCourse();
        return root;
    }

    private void generateCourse(){
        api = ScheduleRetrofitProvider.getClient().create(ScheduleApiConnect.class);
        final Call<ScheduleData> dataSet = api.getSchedule();
        dataSet.enqueue(new Callback<ScheduleData>() {
            @Override
            public void onResponse(Call<ScheduleData> call, Response<ScheduleData> response) {
                CustomAdapter adapter = new CustomAdapter(getContext(),response.body());
                mrecyclerView.setAdapter(adapter);
                pbSchedule.setVisibility(View.GONE);
                mrecyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ScheduleData> call, Throwable t) {
                System.out.println("MESSAGE = " + t.getMessage());
            }
        });
    }

}