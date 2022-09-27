package itdp.flockcorp.duckuniversity.instructor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import itdp.flockcorp.duckuniversity.R;
import itdp.flockcorp.duckuniversity.ui.homeschedule.Constants;

public class InstructorDetailedActivity extends AppCompatActivity {

    private ImageView mimg;
    private TextView minstructor_name;
    private TextView minstructor_dob;
    private TextView mgender;
    private TextView maddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra("name"));
        setContentView(R.layout.activity_instructor_detailed);

        mimg=(ImageView)findViewById(R.id.iv_imgInstructorDetail);
        minstructor_name=(TextView)findViewById(R.id.tvInstructorNameDetailContent);
        minstructor_dob=(TextView)findViewById(R.id.tvInstructorDobDetailContent);
        mgender=(TextView)findViewById(R.id.tvInstructorGenderDetailContent);
        maddress=(TextView)findViewById(R.id.tvInstructorAddressDetailContent);

        if(getIntent()!=null){
            Intent intent = getIntent();
            String name=intent.getStringExtra("name");
            String dob=intent.getStringExtra("dob");
            String gender = intent.getStringExtra("gender");
            String address = intent.getStringExtra("address");
            String picture = intent.getStringExtra("picture");

            minstructor_name.setText(name);
            minstructor_dob.setText(dob.substring(0,10));
            mgender.setText(gender.replace(gender.charAt(0), Character.toString(gender.charAt(0)).toUpperCase().charAt(0)));
            maddress.setText(address);
            Glide.with(this).load(picture).into(mimg);
        }



    }
}
