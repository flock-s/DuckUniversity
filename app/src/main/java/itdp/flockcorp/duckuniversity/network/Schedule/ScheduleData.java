package itdp.flockcorp.duckuniversity.network.Schedule;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleData {

    @SerializedName("data")
    List<Schedule> scheduleList;

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public static class Schedule{
        public Schedule(String id, String start_date, String end_date, String course_name, String ruangan, String ins_name, String pic, String peserta, String image) {
            this.id = id;
            this.start_date = start_date;
            this.end_date = end_date;
            this.course_name = course_name;
            this.ruangan = ruangan;
            this.ins_name = ins_name;
            this.pic = pic;
            this.peserta = peserta;
            this.image = image;
        }

        @SerializedName("id")
        String id;

        @SerializedName("start_date")
        String start_date;

        @SerializedName("end_date")
        String end_date;

        @SerializedName("course_name")
        String course_name;

        @SerializedName("ruangan")
        String ruangan;

        @SerializedName("ins_name")
        String ins_name;

        @SerializedName("pic")
        String pic;

        @SerializedName("peserta")
        String peserta;

        @SerializedName("image")
        String image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getRuangan() {
            return ruangan;
        }

        public void setRuangan(String ruangan) {
            this.ruangan = ruangan;
        }

        public String getIns_name() {
            return ins_name;
        }

        public void setIns_name(String ins_name) {
            this.ins_name = ins_name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPeserta() {
            return peserta;
        }

        public void setPeserta(String peserta) {
            this.peserta = peserta;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }


}
