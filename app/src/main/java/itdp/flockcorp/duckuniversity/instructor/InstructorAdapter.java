package itdp.flockcorp.duckuniversity.instructor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import itdp.flockcorp.duckuniversity.R;
import itdp.flockcorp.duckuniversity.network.Instructor.InstructorData;

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.InstructorViewAdapter>  {

    private LayoutInflater inflater;
    private InstructorData dataSet;
    private Context context;

    public InstructorAdapter (Context context, InstructorData dataSet){
        this.context = context;
        this.dataSet = dataSet;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public InstructorViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.layout_single_row,parent,false);
        InstructorViewAdapter instructorview =  new InstructorViewAdapter(context,view);
        return instructorview;
    }

    @Override
    public void onBindViewHolder(InstructorViewAdapter holder, int position) {
        InstructorData.Instructor data = dataSet.getResults().get(position);
        holder.indexId = position;
        holder.minstructor_name.setText(data.getName().getFirst()+" "+data.getName().getLast());
        holder.minstructor_address.setText(data.getLocation().getStreet().getNumber()
                +" "+data.getLocation().getStreet().getNumber()
                +", "+ data.getLocation().getCity());
        Glide.with(context).load(data.getPicture().getThumbnail()).into(holder.mimg);

//        holder.indexId = position;
//        holder.minstructor_name.setText(data.getInstructorFirstname()+" "+data.getInstructorLastname());
//        holder.minstructor_address.setText(data.getInstructorAddress());
//        holder.mimg.setImageResource(R.drawable.duckthmb);
//        Picasso.with(context).load(data.getInstructorImage()).into(holder.mimg);
//        holder.indexId = position;
    }

    @Override
    public int getItemCount() {
        return dataSet.getResults().size();
    }

    public class InstructorViewAdapter extends RecyclerView.ViewHolder {
        int indexId;
        ImageView mimg;
        TextView minstructor_name;
        TextView minstructor_address;

        public InstructorViewAdapter(final Context context, View itemView) {
            super(itemView);
            mimg=(ImageView)itemView.findViewById(R.id.iv_img);
            minstructor_name=(TextView)itemView.findViewById(R.id.tvCoursename);
            minstructor_address=(TextView)itemView.findViewById(R.id.tvInstructorname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InstructorData.Instructor data = dataSet.getResults().get(indexId);
                    Intent intent = new Intent(context,InstructorDetailedActivity.class);
                    intent.putExtra("name",data.getName().getFirst() + " " +data.getName().getLast());
                    intent.putExtra("dob", data.getDob().getDate());
                    intent.putExtra("gender",data.getGender());
                    intent.putExtra("address", data.getLocation().getStreet().getNumber()
                            +" "+data.getLocation().getStreet().getNumber()
                            +", "+ data.getLocation().getCity()+", "+ data.getLocation().getState());
                    intent.putExtra("picture",data.getPicture().getLarge());
                    context.startActivity(intent);
                }
            });
        }
    }
}
