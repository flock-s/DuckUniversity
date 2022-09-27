package itdp.flockcorp.duckuniversity.network.instructorForm;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InstructorForm {

    @SerializedName("data")
    List<InstructorFormData> instructorFormDataList;

    public List<InstructorFormData> getInstructorFormDataList() {
        return instructorFormDataList;
    }

    public void setInstructorFormDataList(List<InstructorFormData> instructorFormDataList) {
        this.instructorFormDataList = instructorFormDataList;
    }

    public class InstructorFormData{
       @SerializedName("nama")
        String nama;

       @SerializedName("ins_code")
        String ins_code;

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getIns_code() {
            return ins_code;
        }

        public void setIns_code(String ins_code) {
            this.ins_code = ins_code;
        }
    }
}
