package itdp.flockcorp.duckuniversity.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import itdp.flockcorp.duckuniversity.R;

public class CourseDetailedActivity extends AppCompatActivity {

    private ImageView mimg;
    private TextView mcourse_name;
    private TextView mcourse_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra("course_name"));
        setContentView(R.layout.activity_course_detailed);

        mimg=(ImageView)findViewById(R.id.iv_imgCourseDetail);
        mcourse_name=(TextView)findViewById(R.id.tvCourseNameDetailContent);
        mcourse_code=(TextView)findViewById(R.id.tvCourseCodeDetailContent);

        if(getIntent()!=null){
            Intent intent = getIntent();
            String course_name=intent.getStringExtra("course_name");
            String course_code=intent.getStringExtra("course_code");
            String picture = intent.getStringExtra("picture");

            mcourse_name.setText(course_name);
            mcourse_code.setText(course_code);
            mimg.setImageResource(R.drawable.duckimg);
        }
    }
}
