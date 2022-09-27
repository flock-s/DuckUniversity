package itdp.flockcorp.duckuniversity.course;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import itdp.flockcorp.duckuniversity.R;
import itdp.flockcorp.duckuniversity.network.Course.CourseData;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewAdapter> {
    private LayoutInflater inflater;
    private CourseData dataSet;
    private Context context;

    public CourseAdapter(CourseData dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CourseViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.layout_single_row,parent,false);
        CourseAdapter.CourseViewAdapter courseViewAdapter =  new CourseAdapter.CourseViewAdapter(context,view);
        return courseViewAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewAdapter holder, int position) {
        CourseData.Course course = dataSet.getCourseList().get(position);
        holder.indexId = position;
        holder.mcourse_name.setText(course.getCourse_name());
        holder.mcourse_code.setText(course.getCourse_code());
        holder.mimg.setImageResource(R.drawable.duckthmb);
    }

    @Override
    public int getItemCount() {
        return dataSet.getCourseList().size();
    }

    public class CourseViewAdapter extends RecyclerView.ViewHolder{
        int indexId;
        ImageView mimg;
        TextView mcourse_name;
        TextView mcourse_code;

        public CourseViewAdapter(final Context context, View itemView) {
            super(itemView);
            mimg=(ImageView)itemView.findViewById(R.id.iv_img);
            mcourse_name=(TextView)itemView.findViewById(R.id.tvCoursename);
            mcourse_code=(TextView)itemView.findViewById(R.id.tvInstructorname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CourseData.Course data = dataSet.getCourseList().get(indexId);
                    Intent intent = new Intent(context,CourseDetailedActivity.class);
                    intent.putExtra("course_name", data.getCourse_name());
                    intent.putExtra("course_code",data.getCourse_code());
                    context.startActivity(intent);
                }
            });
        }
    }
}
