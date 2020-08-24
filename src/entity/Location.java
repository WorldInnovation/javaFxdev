package entity;

public class Location {
    Street StreetObject;
    private String city;
    private String state;
    private String country;
    private String postcode;
    Coordinates CoordinatesObject;
    Timezone TimezoneObject;


    // Getter Methods

    public Street getStreet() {
        return StreetObject;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }


    public String getPostcode() {
        return postcode;
    }

    public Coordinates getCoordinates() {
        return CoordinatesObject;
    }

    public Timezone getTimezone() {
        return TimezoneObject;
    }

    // Setter Methods

    public void setStreet( Street streetObject ) {
        this.StreetObject = streetObject;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public void setState( String state ) {
        this.state = state;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public void setPostcode( String postcode ) {
        this.postcode = postcode;
    }

    public void setCoordinates( Coordinates coordinatesObject ) {
        this.CoordinatesObject = coordinatesObject;
    }

    public void setTimezone( Timezone timezoneObject ) {
        this.TimezoneObject = timezoneObject;
    }
}
