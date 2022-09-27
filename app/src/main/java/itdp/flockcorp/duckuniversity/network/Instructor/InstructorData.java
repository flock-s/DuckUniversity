package itdp.flockcorp.duckuniversity.network.Instructor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InstructorData {
//

    @SerializedName("results")
    private List<Instructor> results;

    public List<Instructor> getResults() {
        return results;
    }

    public void setResults(List<Instructor> results) {
        this.results = results;
    }

    public class Instructor{

        @SerializedName("gender")
        private String gender;

        @SerializedName("name")
        private Name name;

        @SerializedName("location")
        private Location location;

        @SerializedName("dob")
        private Dob dob;

        @SerializedName("picture")
        private Picture picture;

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Dob getDob() {
            return dob;
        }

        public void setDob(Dob dob) {
            this.dob = dob;
        }

        public Picture getPicture() {
            return picture;
        }

        public void setPicture(Picture picture) {
            this.picture = picture;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }
    }

    public class Name{
        @SerializedName("first")
        private String first;

        @SerializedName("last")
        private String last;

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }

    public class Location{
        @SerializedName("city")
        private String city;

        @SerializedName("street")
        private Street street;

        @SerializedName("state")
        private String state;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Street getStreet() {
            return street;
        }

        public void setStreet(Street street) {
            this.street = street;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    public class Street{
        @SerializedName("number")
        private String number;

        @SerializedName("name")
        private String name;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Dob{
        @SerializedName("date")
        private String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public class Picture{
        @SerializedName("large")
        private String large;

        @SerializedName("thumbnail")
        private String thumbnail;

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}
