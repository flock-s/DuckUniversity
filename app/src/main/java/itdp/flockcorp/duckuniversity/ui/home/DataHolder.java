package itdp.flockcorp.duckuniversity.ui.home;

public class DataHolder {

    private int imageId;
    private String course_name;
    private String instructor_name;
    private int thumbId;
    private String periodFrom;
    private String periodTo;
    private String roomNumber;
    private String pic_name;
    private String numberofAttendant;

    public DataHolder(int imageId, int thumbId, String course_name, String instructor_name, String periodFrom,
                      String periodTo, String roomNumber, String pic_name, String numberofAttendant )
    {
        this.imageId=imageId;
        this.course_name=course_name;
        this.instructor_name=instructor_name;
        this.thumbId=thumbId;
        this.roomNumber = roomNumber;
        this.pic_name = pic_name;
        this.numberofAttendant = numberofAttendant;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
    }

    public int getImageId() {
        return imageId;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getInstructor_name() {
        return instructor_name;
    }

    public int getThumbId() {
        return thumbId;
    }

    public String getPeriodFrom(){ return periodFrom;}

    public String getPeriodTo(){ return periodTo;}

    public String getRoomNumber(){ return roomNumber;}

    public String getPic_name (){ return pic_name;}

    public String getNumberofAttendant (){ return numberofAttendant;}
}
