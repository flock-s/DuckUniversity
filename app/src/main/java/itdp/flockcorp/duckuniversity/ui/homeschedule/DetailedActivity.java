package itdp.flockcorp.duckuniversity.ui.homeschedule;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import itdp.flockcorp.duckuniversity.R;

public class DetailedActivity extends AppCompatActivity {

    private ImageView mimg;
    private TextView mcourse_name;
    private TextView minstructor_name;
    private TextView mperiod;
    private TextView mpic_name;
    private TextView mroomnumber;
    private TextView mattendant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        mimg=(ImageView)findViewById(R.id.iv_img);
        mcourse_name=(TextView)findViewById(R.id.tvCoursename);
        minstructor_name=(TextView)findViewById(R.id.tvInstructorname);
        mperiod=(TextView)findViewById(R.id.tvPeriod);
        mroomnumber=(TextView)findViewById(R.id.tvRoomnumber);
        mattendant=(TextView)findViewById(R.id.tvNumberofAttendant);
        mpic_name = (TextView)findViewById(R.id.tvPIC);

        if(getIntent()!=null)
        {
            Intent intent=getIntent();

            String imageId=intent.getStringExtra(Constants.IMAGE_LABEL);
            String course_name=intent.getStringExtra(Constants.COURSE_LABEL);
            String instructor_name=intent.getStringExtra(Constants.INSTRUCTOR_LABEL);
            String period = intent.getStringExtra(Constants.PERIOD_LABEL);
            String roomnumber = intent.getStringExtra(Constants.ROOMNUMBER_LABEL);
            String attendant = intent.getStringExtra(Constants.ATTENDANT_LABEL);
            String pic_name = intent.getStringExtra(Constants.PIC_LABEL);
            System.out.println(pic_name);
            Glide.with(this).load(imageId).into(mimg);
            mcourse_name.setText(course_name);
            minstructor_name.setText(instructor_name);
            mperiod.setText(period);
            mroomnumber.setText(roomnumber);
            mattendant.setText(attendant);
            mpic_name.setText(pic_name);
        }
    }
}
