package itdp.flockcorp.duckuniversity.network.Course;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourseData {

    @SerializedName("data")
    List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public class Course{
        public Course(String course_code, String course_name) {
            this.course_code = course_code;
            this.course_name = course_name;
        }

        @SerializedName("course_code")
        private String course_code;

        @SerializedName("course_name")
        private String course_name;

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getCourse_code() {
            return course_code;
        }

        public void setCourse_code(String course_code) {
            this.course_code = course_code;
        }
    }

}
