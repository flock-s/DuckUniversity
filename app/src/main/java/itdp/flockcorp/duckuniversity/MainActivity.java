package itdp.flockcorp.duckuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import itdp.flockcorp.duckuniversity.course.CourseActivity;
import itdp.flockcorp.duckuniversity.instructor.InstructorActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    public void goNavigate(View view){
        switch(view.getId()){
            case R.id.formcardId:
                Intent intent = new Intent(this,ScheduleActivity.class);
                this.startActivity(intent);
                break;
            case R.id.instructorcardId:
                Intent intent2 = new Intent(this, InstructorActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.coursecardId:
                Intent intent3 = new Intent(this, CourseActivity.class);
                this.startActivity(intent3);
                break;
        }
    }
}
