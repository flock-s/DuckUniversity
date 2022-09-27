package itdp.flockcorp.duckuniversity.ui.updateschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import itdp.flockcorp.duckuniversity.R;
import itdp.flockcorp.duckuniversity.ScheduleActivity;
import itdp.flockcorp.duckuniversity.network.Course.CourseApiConnect;
import itdp.flockcorp.duckuniversity.network.Course.CourseData;
import itdp.flockcorp.duckuniversity.network.Course.CourseRetrofitProvider;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleApiConnect;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleData;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleRetrofitProvider;
import itdp.flockcorp.duckuniversity.network.instructorForm.InstructorForm;
import itdp.flockcorp.duckuniversity.network.instructorForm.InstructorFormApiConnect;
import itdp.flockcorp.duckuniversity.network.instructorForm.InstructorFormRetrofitProvider;
import itdp.flockcorp.duckuniversity.ui.homeschedule.Constants;
import itdp.flockcorp.duckuniversity.ui.homeschedule.CustomAdapter;
import itdp.flockcorp.duckuniversity.ui.homeschedule.DataHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedScheduleUpdate extends AppCompatActivity implements View.OnClickListener {
    ScrollView scrollview;
    ProgressBar pbLoading;
    Button button;
    EditText etPeriodFrom;
    EditText etPeriodTo;
    EditText etRoomNumber;
    EditText etNumberofAttendant;
    Spinner spPIC;
    Spinner spCourse;
    Spinner spInstructor;
    SimpleDateFormat dateFormatter;
    DatePickerDialog etPeriodFromPickerDialog;
    DatePickerDialog etPeriodToPickerDialog;
    public static int id = -1;
    private ScheduleApiConnect api;
    private CourseApiConnect api2;
    private InstructorFormApiConnect api3;
    private List<String> listCourse = new ArrayList<>();
    private List<String> listCourseCode = new ArrayList<>();
    private List<String> listinstructor = new ArrayList<>();
    private List<String> listInstructorCode = new ArrayList<>();
    private List<String> listPIC = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_schedule_update);
        dateFormatter = new
                SimpleDateFormat("d/M/yyyy",
                Locale.US);
        Intent intent = getIntent();
        String roomnumber = intent.getStringExtra("roomNumber");
        String attendant = intent.getStringExtra("attendant");
        String period_from = intent.getStringExtra("period_from");
        String period_to = intent.getStringExtra("period_to");
        String pic = intent.getStringExtra("pic");
        String courseName = intent.getStringExtra("coursename");
        String instructorName = intent.getStringExtra("instructorname");
        final String idData = intent.getStringExtra("idData");

        final int index = Integer.parseInt(intent.getStringExtra("index"));
        scrollview = findViewById(R.id.detailedScheduleUpdateScrollView);
        pbLoading = findViewById(R.id.pbDetailedScheduleUpdate);
        etPeriodFrom = findViewById(R.id.etPeriodFromEdit);
        etPeriodFrom.setInputType(InputType.TYPE_NULL);
        etPeriodTo = findViewById(R.id.etPeriodToEdit);
        etPeriodTo.setInputType(InputType.TYPE_NULL);
        setDateTimeField();
        etPeriodFrom.setText(period_from);
        etPeriodTo.setText(period_to);

        etNumberofAttendant = findViewById(R.id.etAttendantEdit);
        etNumberofAttendant.setText(attendant);

        etRoomNumber = findViewById(R.id.etRoomNumberEdit);
        etRoomNumber.setText(roomnumber);

        spPIC = findViewById(R.id.PICSpinnerEdit);
        spCourse = findViewById(R.id.courseSpinnerEdit);
        spInstructor = findViewById(R.id.instructorSpinnerEdit);
        generatepic(pic);
        generateCourse(courseName);
        generateInstructor(instructorName);

        button = findViewById(R.id.buttonEdit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPeriodFrom.getText().toString().equals("From") || etPeriodFrom.getText().length() == 0) {
                    etPeriodFrom.setError("Date Must be Filled");
                } else if (etPeriodTo.getText().length() == 0 || etPeriodTo.getText().toString().equals("To")) {
                    etPeriodTo.setError("Date Must be Filled");
                } else if (spCourse.getSelectedItemPosition() == 0) {
                    Toast.makeText(getBaseContext(), "Select One", Toast.LENGTH_SHORT).show();
                } else if (etRoomNumber.getText().length() == 0) {
                    etRoomNumber.setError("Room Number Must be Filled");
                } else if (spInstructor.getSelectedItemPosition() == 0) {
                    Toast.makeText(getBaseContext(), "Select One", Toast.LENGTH_SHORT).show();
                } else if (spPIC.getSelectedItemPosition() == 0) {
                    Toast.makeText(getBaseContext(), "Select One", Toast.LENGTH_SHORT).show();
                } else if (etNumberofAttendant.getText().length() == 0) {
                    etNumberofAttendant.setError("Number of Attendant Must be Higher than 0");
                } else if (Integer.parseInt(etNumberofAttendant.getText().toString()) <= 0) {
                    etNumberofAttendant.setError("Number of Attendant Must be Filled");
                } else {
                    updateSchedule(idData);
                    Toast.makeText(getBaseContext(), "Update Schedule Success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailedScheduleUpdate.this, ScheduleActivity.class);
                    DetailedScheduleUpdate.this.startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setDateTimeField() {

        etPeriodFrom.setOnClickListener(this);
        etPeriodTo.setOnClickListener(this);
        //show calendar popup
        final Calendar newCalendar = Calendar.getInstance();
        //Edit text 1
        etPeriodFromPickerDialog = new DatePickerDialog(this,

                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        etPeriodFrom.setText(dateFormatter.format
                                (newDate.getTime()));
                    }
                    //get data month,year,day from user
                }, newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
        etPeriodToPickerDialog = new DatePickerDialog(this,

                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        etPeriodTo.setText(dateFormatter.format
                                (newDate.getTime()));
                    }
                    //get data month,year,day from user
                }, newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View v) {
        if (v == etPeriodFrom) {
            etPeriodFromPickerDialog.show();
        } else if (v == etPeriodTo) {
            etPeriodToPickerDialog.show();
        }
    }

    private void generatepic(final String pic) {
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getApplicationContext()
                , R.layout.support_simple_spinner_dropdown_item
                , listPIC);
        spPIC.setAdapter(dataAdapter);
        api = ScheduleRetrofitProvider.getClient().create(ScheduleApiConnect.class);
        final Call<ScheduleData> dataSet = api.getSchedule();
        dataSet.enqueue(new Callback<ScheduleData>() {
            @Override
            public void onResponse(Call<ScheduleData> call, Response<ScheduleData> response) {
                for (ScheduleData.Schedule s : response.body().getScheduleList()) {
                    listPIC.add(s.getPic());
                }
                dataAdapter.notifyDataSetChanged();
                Set<String> set = new HashSet<>(listPIC);
                listPIC.clear();
                listPIC.add("-- Select One --");
                listPIC.addAll(set);
                spPIC.setSelection(dataAdapter.getPosition(pic));
            }

            @Override
            public void onFailure(Call<ScheduleData> call, Throwable t) {
                System.out.println("MESSAGE = " + t.getMessage());
            }
        });
    }

    private void generateCourse(final String courseName) {
        listCourse.add("-- Select One --");
        listCourseCode.add("-");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getApplicationContext()
                , R.layout.support_simple_spinner_dropdown_item
                , listCourse);
        spCourse.setAdapter(dataAdapter);
        api2 = CourseRetrofitProvider.getClient().create(CourseApiConnect.class);
        Call<CourseData> dataSet = api2.getCourse();
        dataSet.enqueue(new Callback<CourseData>() {
            @Override
            public void onResponse(Call<CourseData> call, Response<CourseData> response) {
                for (CourseData.Course c : response.body().getCourseList()) {
                    listCourse.add(c.getCourse_name());
                    listCourseCode.add(c.getCourse_code());
                }
                dataAdapter.notifyDataSetChanged();
                spCourse.setSelection(dataAdapter.getPosition(courseName));
            }

            @Override
            public void onFailure(Call<CourseData> call, Throwable t) {
                System.out.println("MESSAGE = " + t.getMessage());
            }
        });
    }

    private void generateInstructor(final String instructorName) {
        listinstructor.add("-- Select One --");
        listInstructorCode.add("-");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getApplicationContext()
                , R.layout.support_simple_spinner_dropdown_item
                , listinstructor);
        spInstructor.setAdapter(dataAdapter);
        api3 = InstructorFormRetrofitProvider.getClient().create(InstructorFormApiConnect.class);
        final Call<InstructorForm> dataSet = api3.getInstructorList();
        dataSet.enqueue(new Callback<InstructorForm>() {
            @Override
            public void onResponse(Call<InstructorForm> call, Response<InstructorForm> response) {
                for (InstructorForm.InstructorFormData i : response.body().getInstructorFormDataList()) {
                    listinstructor.add(i.getNama());
                    listInstructorCode.add(i.getIns_code());
                }
                dataAdapter.notifyDataSetChanged();
                spInstructor.setSelection(dataAdapter.getPosition(instructorName));
                pbLoading.setVisibility(View.GONE);
                scrollview.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<InstructorForm> call, Throwable t) {

            }

        });
    }

    private void updateSchedule(String idData) {
        api = ScheduleRetrofitProvider.getClient().create(ScheduleApiConnect.class);
        final Call<Void> dataSet = api.updateSchedule(idData,
                etPeriodFrom.getText().toString(),
                etPeriodTo.getText().toString(),
                listCourseCode.get(spCourse.getSelectedItemPosition()).toString(),
                etRoomNumber.getText().toString(),
                listInstructorCode.get(spInstructor.getSelectedItemPosition()).toString(),
                spPIC.getSelectedItem().toString(),
                etNumberofAttendant.getText().toString(),
                "https:\\/\\/datasampleandroid.000webhostapp.com\\/nav_bg.jpg");
        dataSet.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("MESSAGE = " + t.getMessage());
            }
        });
    }

}
