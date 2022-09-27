package itdp.flockcorp.duckuniversity.ui.homeschedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import itdp.flockcorp.duckuniversity.R;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleApiConnect;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleData;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleRetrofitProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView mrecyclerView;
    private ProgressBar pbSchedule;
    private ScheduleApiConnect api;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Constants.ClassPosition = "VIEW SCHEDULE";
        mrecyclerView=(RecyclerView)root.findViewById(R.id.rvSchedule);
        pbSchedule = root.findViewById(R.id.pbScheduleLoading);
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