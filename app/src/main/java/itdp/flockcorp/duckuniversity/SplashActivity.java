package itdp.flockcorp.duckuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import itdp.flockcorp.duckuniversity.ui.homeschedule.Constants;
import itdp.flockcorp.duckuniversity.ui.homeschedule.DataHolder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashActivity extends AppCompatActivity {
    private static String [] periodFrom;
    private static String [] periodTo;
    private static String [] courses;
    private static String [] roomnumber;
    private static String []instructorName;
    private static String [] picname;
    private static String [] numberofattendant;
    private int [] thumbs={R.drawable.duckthmb,R.drawable.duckthmb,R.drawable.duckthmb,R.drawable.duckthmb};

    private int [] images={R.drawable.duckimg,R.drawable.duckimg,R.drawable.duckimg,R.drawable.duckimg};
    private static int splashscreen = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createList();
        GenerateInstructor();
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },splashscreen);
    }

    protected List<DataHolder> createList()
    {
        Constants.dataSet.clear();
        courses=getResources().getStringArray(R.array.scheduled_course_name);
        instructorName=getResources().getStringArray(R.array.scheduled_instructor_name);
        periodFrom = getResources().getStringArray(R.array.scheduled_period_from);
        periodTo = getResources().getStringArray(R.array.scheduled_period_to);
        roomnumber = getResources().getStringArray(R.array.scheduled_room_number);
        picname = getResources().getStringArray(R.array.scheduled_pic_name);
        numberofattendant = getResources().getStringArray(R.array.scheduled_number_of_attendant);

        for(int i=0; i<courses.length && i<instructorName.length && i<images.length; i++)
        {
            DataHolder data=new DataHolder(images[i], thumbs[i], courses[i], instructorName[i],periodFrom[i]
                    ,periodTo[i],roomnumber[i],picname[i],numberofattendant[i]);
            Constants.dataSet.add(data);
        }
        return Constants.dataSet;
    }

    void GenerateInstructor()
    {
        Constants.instructorDataSet.clear();
        String requestUrl = "https://randomuser.me/api/?results=10";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(requestUrl)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("getProfileInfo", "FAIL");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                Log.i("getProfileInfo", jsonData);
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject rootObj = new JSONObject(jsonData);
                                JSONArray results = (JSONArray) rootObj.get("results");
                                for(int i = 0 ;i<results.length();i++){
                                    JSONObject resultObject = (JSONObject) results.get(i);
                                    JSONObject ge_name = (JSONObject) resultObject.get("name");
                                    String firstname = ge_name.getString("first");
                                    String lastname = ge_name.getString("last");
                                    JSONObject ge_location = (JSONObject) resultObject.get("location");
                                    JSONObject ge_street = (JSONObject)ge_location.get("street");
                                    String address = ge_street.get("number").toString()+" "+ge_street.get("name").toString()+", "+ge_location.get("city").toString()+", "+ge_location.get("state").toString();
                                    JSONObject ge_date = (JSONObject) resultObject.get("dob");
                                    String birthdate = ge_date.getString("date");
                                    JSONObject ge_picture = (JSONObject)resultObject.get("picture");
                                    String thumbnail = ge_picture.getString("thumbnail");
//                                    ScheduleData id = new ScheduleData(firstname,lastname,address,birthdate,thumbnail);
//                                    Constants.instructorDataSet.add(id);
//                                    Log.d("TEST : ", id.getInstructorFirstname());
                                }

                            } catch (JSONException e) {
                                Log.e("bad",e.getMessage());
                            }
                        }
                    });
                }
            }
        });

    }
}
