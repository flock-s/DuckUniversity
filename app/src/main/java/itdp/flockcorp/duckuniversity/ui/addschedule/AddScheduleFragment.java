package itdp.flockcorp.duckuniversity.ui.addschedule;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import itdp.flockcorp.duckuniversity.R;
import itdp.flockcorp.duckuniversity.ScheduleActivity;
import itdp.flockcorp.duckuniversity.course.CourseActivity;
import itdp.flockcorp.duckuniversity.course.CourseAdapter;
import itdp.flockcorp.duckuniversity.network.Course.CourseApiConnect;
import itdp.flockcorp.duckuniversity.network.Course.CourseData;
import itdp.flockcorp.duckuniversity.network.Course.CourseRetrofitProvider;
import itdp.flockcorp.duckuniversity.network.Instructor.InstructorApiConnect;
import itdp.flockcorp.duckuniversity.network.Instructor.InstructorData;
import itdp.flockcorp.duckuniversity.network.Instructor.InstructorRetrofitProvider;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleApiConnect;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleData;
import itdp.flockcorp.duckuniversity.network.Schedule.ScheduleRetrofitProvider;
import itdp.flockcorp.duckuniversity.network.instructorForm.InstructorForm;
import itdp.flockcorp.duckuniversity.network.instructorForm.InstructorFormApiConnect;
import itdp.flockcorp.duckuniversity.network.instructorForm.InstructorFormRetrofitProvider;
import itdp.flockcorp.duckuniversity.ui.homeschedule.Constants;
import itdp.flockcorp.duckuniversity.ui.homeschedule.CustomAdapter;
import itdp.flockcorp.duckuniversity.ui.homeschedule.DataHolder;
import itdp.flockcorp.duckuniversity.ui.homeschedule.HomeFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddScheduleFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    private CourseApiConnect api2;
    private AddScheduleViewModel galleryViewModel;
    ScrollView scrollview;
    Button button;
    EditText etPeriodFrom;
    EditText etPeriodTo;
    EditText etRoomNumber;
    EditText etNumberofAttendant;
    Spinner spPIC;
    Spinner spCourse;
    Spinner spInstructor;
    SimpleDateFormat dateFormatter;
    DatePickerDialog etTglLahirPickerDialog;
    ProgressBar pbLoading;
    private ScheduleApiConnect api;
    private InstructorFormApiConnect api3;
    private List<String> listCourse = new ArrayList<>();
    private List<String> listCourseCode = new ArrayList<>();
    private List<String> listinstructor = new ArrayList<>();
    private List<String> listInstructorCode = new ArrayList<>();
    private List<String> listPIC = new ArrayList<>();
    public static int id=-1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(AddScheduleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_schedule, container, false);
        pbLoading = root.findViewById(R.id.progressBar2);
        scrollview = root.findViewById(R.id.addScrollView);
        etPeriodFrom = root.findViewById(R.id.etPeriodFrom);
        etPeriodFrom.setInputType(InputType.TYPE_NULL);

        etPeriodTo = root.findViewById(R.id.etPeriodTo);
        etPeriodTo.setInputType(InputType.TYPE_NULL);
        setDateTimeField();

        etNumberofAttendant = root.findViewById(R.id.etAttendant);
        etRoomNumber = root.findViewById(R.id.etRoomNumber);

        spCourse = root.findViewById(R.id.courseSpinner);
        spInstructor = root.findViewById(R.id.instructorSpinner);
        spPIC = root.findViewById(R.id.PICSpinner);

        generatepic();
        generateCourse();
        generateInstructor();

        button = root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPeriodFrom.getText().toString().equals("From") || etPeriodFrom.getText().length()==0) {
                    etPeriodFrom.setError("Date Must be Filled");
                } else if (etPeriodTo.getText().length() == 0 || etPeriodTo.getText().toString().equals("To")) {
                    etPeriodTo.setError("Date Must be Filled");
                } else if (spCourse.getSelectedItemPosition() == 0) {
                    Toast.makeText(getActivity(), "Select One", Toast.LENGTH_SHORT).show();
                } else if (etRoomNumber.getText().length() == 0) {
                    etRoomNumber.setError("Room Number Must be Filled");
                } else if (spInstructor.getSelectedItemPosition() == 0) {
                    Toast.makeText(getActivity(), "Select One", Toast.LENGTH_SHORT).show();
                } else if (spPIC.getSelectedItemPosition() == 0) {
                    Toast.makeText(getActivity(), "Select One", Toast.LENGTH_SHORT).show();
                } else if (etNumberofAttendant.getText().length() == 0) {
                    etNumberofAttendant.setError("Number of Attendant Must be Higher than 0");
                } else if (Integer.parseInt(etNumberofAttendant.getText().toString()) <= 0) {
                    etNumberofAttendant.setError("Number of Attendant Must be Filled");
                } else {
                    Toast.makeText(getActivity(), "Add Schedule Success!", Toast.LENGTH_SHORT).show();
                    DataHolder data = new DataHolder(R.drawable.duckimg
                            , R.drawable.duckthmb
                            , spCourse.getSelectedItem().toString()
                            , spInstructor.getSelectedItem().toString()
                            , etPeriodFrom.getText().toString()
                            , etPeriodTo.getText().toString()
                            , etRoomNumber.getText().toString()
                            , spPIC.getSelectedItem().toString()
                            , etNumberofAttendant.getText().toString());
                    createSchedule();
                    Intent intent = new Intent(v.getContext(),ScheduleActivity.class);
                    v.getContext().startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        return root;
    }


    public void setDateTimeField() {
        etPeriodFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new AddScheduleFragment();
                id = etPeriodFrom.getId();
                newFragment.show(getFragmentManager(),"Date Picker From");
            }
        });

        etPeriodTo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new AddScheduleFragment();
                id = etPeriodTo.getId();
                newFragment.show(getFragmentManager(),"Date Picker To");
            }
        });
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this, yy,mm,dd);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String Date = ""+year+"-"+(month+1)+"-"+dayOfMonth;
        etPeriodFrom = getActivity().findViewById(id);
        etPeriodFrom.setText(Date);
    }

    private void createSchedule(){
        ScheduleData.Schedule schedule = new ScheduleData.Schedule("99"
                ,etPeriodFrom.getText().toString()
                ,etPeriodTo.getText().toString()
                ,spCourse.getSelectedItem().toString()
                ,etRoomNumber.getText().toString()
                ,spInstructor.getSelectedItem().toString()
                ,spPIC.getSelectedItem().toString()
                ,etNumberofAttendant.getText().toString()
                ,"https:\\/\\/datasampleandroid.000webhostapp.com\\/nav_bg.jpg");
        api = ScheduleRetrofitProvider.getClient().create(ScheduleApiConnect.class);
        final Call<ScheduleData.Schedule> dataSet = api.createPost(
                etPeriodFrom.getText().toString(),
                etPeriodTo.getText().toString(),
                listCourseCode.get(spCourse.getSelectedItemPosition()).toString(),
                etRoomNumber.getText().toString(),
                listInstructorCode.get(spInstructor.getSelectedItemPosition()).toString(),
                spPIC.getSelectedItem().toString(),
                etNumberofAttendant.getText().toString(),
                "https:\\/\\/datasampleandroid.000webhostapp.com\\/nav_bg.jpg");

        dataSet.enqueue(new Callback<ScheduleData.Schedule>() {
            @Override
            public void onResponse(Call<ScheduleData.Schedule> call, Response<ScheduleData.Schedule> response) {
                System.out.println("success" + response.body());
            }

            @Override
            public void onFailure(Call<ScheduleData.Schedule> call, Throwable t) {
                Log.d("ERROR: ",t.getMessage());
                Toast.makeText(getActivity(), "Masuk error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generatepic(){
        final ArrayAdapter<String>dataAdapter = new ArrayAdapter<String>(getContext()
                ,R.layout.support_simple_spinner_dropdown_item
                ,listPIC);
        spPIC.setAdapter(dataAdapter);
        api = ScheduleRetrofitProvider.getClient().create(ScheduleApiConnect.class);
        final Call<ScheduleData> dataSet = api.getSchedule();
        dataSet.enqueue(new Callback<ScheduleData>() {
            @Override
            public void onResponse(Call<ScheduleData> call, Response<ScheduleData> response) {
                for(ScheduleData.Schedule s :response.body().getScheduleList()){
                    listPIC.add(s.getPic());
                }
                dataAdapter.notifyDataSetChanged();
                Set<String> set = new HashSet<>(listPIC);
                listPIC.clear();
                listPIC.add("-- Select One --");
                listPIC.addAll(set);
            }

            @Override
            public void onFailure(Call<ScheduleData> call, Throwable t) {
                System.out.println("MESSAGE = " + t.getMessage());
            }
        });
    }

    private void generateCourse(){
        listCourse.add("-- Select One --");
        listCourseCode.add("-");
        final ArrayAdapter<String>dataAdapter = new ArrayAdapter<String>(getContext()
                ,R.layout.support_simple_spinner_dropdown_item
                ,listCourse);
        spCourse.setAdapter(dataAdapter);
        api2 = CourseRetrofitProvider.getClient().create(CourseApiConnect.class);
        Call<CourseData> dataSet = api2.getCourse();
        dataSet.enqueue(new Callback<CourseData>() {
            @Override
            public void onResponse(Call<CourseData> call, Response<CourseData> response) {
                for(CourseData.Course c : response.body().getCourseList()){
                    listCourse.add(c.getCourse_name());
                    listCourseCode.add(c.getCourse_code());
                }
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CourseData> call, Throwable t) {
                System.out.println("MESSAGE = " + t.getMessage());
            }
        });
    }

    private void generateInstructor(){
        listinstructor.add("-- Select One --");
        listInstructorCode.add("-");
        final ArrayAdapter<String>dataAdapter = new ArrayAdapter<String>(getContext()
                ,R.layout.support_simple_spinner_dropdown_item
                ,listinstructor);
        spInstructor.setAdapter(dataAdapter);
        api3 = InstructorFormRetrofitProvider.getClient().create(InstructorFormApiConnect.class);
        final Call<InstructorForm> dataSet = api3.getInstructorList();
        dataSet.enqueue(new Callback<InstructorForm>() {
            @Override
            public void onResponse(Call<InstructorForm> call, Response<InstructorForm> response) {
                for (InstructorForm.InstructorFormData i : response.body().getInstructorFormDataList()){
                    listinstructor.add(i.getNama());
                    listInstructorCode.add(i.getIns_code());
                }
                dataAdapter.notifyDataSetChanged();
                pbLoading.setVisibility(getView().GONE);
                scrollview.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<InstructorForm> call, Throwable t) {

            }

        });
    }
}
