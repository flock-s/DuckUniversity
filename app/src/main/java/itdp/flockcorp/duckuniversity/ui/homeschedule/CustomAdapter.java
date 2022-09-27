package itdp.flockcorp.duckuniversity.ui.homeschedule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import itdp.flockcorp.duckuniversity.R;
import itdp.flockcorp.duckuniversity.ScheduleActivity;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleApiConnect;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleData;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleRetrofitProvider;
import itdp.flockcorp.duckuniversity.ui.updateschedule.DetailedScheduleUpdate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import android.support.v7.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private LayoutInflater inflater;
    private ScheduleData dataSet;
    private Context context;
    private ScheduleApiConnect api;
    FragmentManager fm;
    Fragment fg;

    public CustomAdapter(Context context, ScheduleData dataSet)
    {
        this.context=context;
        this.dataSet=dataSet;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.layout_single_row,parent,false);
        CustomViewHolder holder=new CustomViewHolder(context, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        ScheduleData.Schedule data= dataSet.getScheduleList().get(position);
        holder.mcourse_name.setText(data.getCourse_name());
        holder.minstructor_name.setText(data.getRuangan() + " Study Room");
        Glide.with(context).load(data.getImage()).into(holder.mimg);
        holder.indexId = position;
    }

    @Override
    public int getItemCount() {
        return dataSet.getScheduleList().size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        int indexId;
        ImageView mimg;
        TextView mcourse_name;
        TextView minstructor_name;
        Context context;

        public CustomViewHolder(final Context context, final View itemView) {
            super(itemView);

            mimg=(ImageView)itemView.findViewById(R.id.iv_img);
            mcourse_name=(TextView)itemView.findViewById(R.id.tvCoursename);
            minstructor_name=(TextView)itemView.findViewById(R.id.tvInstructorname);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScheduleData.Schedule dataset= dataSet.getScheduleList().get(indexId);
                    Intent intent = new Intent();
                    if(Constants.ClassPosition.equals("UPDATE SCHEDULE")){
                        intent = new Intent(context, DetailedScheduleUpdate.class);
                        intent.putExtra("index",String.valueOf(indexId));
                        intent.putExtra("period_from",dataset.getStart_date());
                        intent.putExtra("period_to",dataset.getEnd_date());
                        intent.putExtra("coursename",dataset.getCourse_name());
                        intent.putExtra("instructorname",dataset.getIns_name());
                        intent.putExtra("roomNumber",dataset.getRuangan());
                        intent.putExtra("pic",dataset.getPic());
                        intent.putExtra("attendant",dataset.getPeserta());
                        intent.putExtra("idData",dataset.getId());
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    }else if (Constants.ClassPosition.equals("VIEW SCHEDULE")){
                        intent = new Intent(context, DetailedActivity.class);
                        intent.putExtra(Constants.IMAGE_LABEL, dataset.getImage());
                        intent.putExtra(Constants.COURSE_LABEL, dataset.getCourse_name());
                        intent.putExtra(Constants.INSTRUCTOR_LABEL, dataset.getIns_name());
                        intent.putExtra(Constants.PERIOD_LABEL, dataset.getStart_date() + " - " + dataset.getEnd_date());
                        intent.putExtra(Constants.PIC_LABEL, dataset.getPic());
                        intent.putExtra(Constants.ATTENDANT_LABEL, dataset.getPeserta());
                        intent.putExtra(Constants.ROOMNUMBER_LABEL, dataset.getRuangan());
                        context.startActivity(intent);
                    }
                    else if (Constants.ClassPosition.equals("DELETE SCHEDULE")){
                        deletePost(dataset.getId());
                        intent = new Intent(itemView.getContext(),ScheduleActivity.class);
                        itemView.getContext().startActivity(intent);
                    }
                }
            });

        }
    }

    private void deletePost(String id) {
        api = ScheduleRetrofitProvider.getClient().create(ScheduleApiConnect.class);
        final Call<Void> call = api.deleteSchedule(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(context,"Delete Data Success!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
