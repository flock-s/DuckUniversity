package itdp.flockcorp.duckuniversity.ui.home;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import itdp.flockcorp.duckuniversity.R;

//import android.support.v7.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private LayoutInflater inflater;
    private List<DataHolder> dataSet= Collections.emptyList();
    private Context context;
    private int indexClicked;
    public CustomAdapter(Context context, List<DataHolder> dataSet)
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
        DataHolder data=dataSet.get(position);
        Log.d("DDDDDDD",String.valueOf(position));
        holder.mcourse_name.setText(data.getCourse_name());
        holder.minstructor_name.setText(data.getInstructor_name());
        holder.mimg.setImageResource(data.getThumbId());
        holder.imageId=data.getImageId();
        holder.indexId = position;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        int indexId;
        ImageView mimg;
        TextView mcourse_name;
        TextView minstructor_name;
        int imageId;
        Context context;

        public CustomViewHolder(final Context context, final View itemView) {
            super(itemView);

            mimg=(ImageView)itemView.findViewById(R.id.iv_img);
            mcourse_name=(TextView)itemView.findViewById(R.id.tvCoursename);
            minstructor_name=(TextView)itemView.findViewById(R.id.tvInstructorname);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("OSSSSSS","id : "+indexId);
                    Intent intent=new Intent(context,DetailedActivity.class);
                    DataHolder dataset = Constants.dataSet.get(indexId);
                    intent.putExtra(Constants.IMAGE_LABEL,dataset.getImageId());
                    intent.putExtra(Constants.COURSE_LABEL,dataset.getCourse_name());
                    intent.putExtra(Constants.INSTRUCTOR_LABEL,dataset.getInstructor_name());
                    intent.putExtra(Constants.PERIOD_LABEL,dataset.getPeriodFrom() + " - " + dataset.getPeriodTo() );
                    intent.putExtra(Constants.PIC_LABEL,dataset.getPic_name());
                    intent.putExtra(Constants.ATTENDANT_LABEL,dataset.getNumberofAttendant());
                    intent.putExtra(Constants.ROOMNUMBER_LABEL,dataset.getRoomNumber());

                    context.startActivity(intent);
                }
            });

        }
    }
}
