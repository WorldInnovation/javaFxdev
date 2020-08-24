package entity;

public class Picture {
    private String large;
    private String medium;
    private String thumbnail;


    // Getter Methods

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    // Setter Methods

    public void setLarge( String large ) {
        this.large = large;
    }

    public void setMedium( String medium ) {
        this.medium = medium;
    }

    public void setThumbnail( String thumbnail ) {
        this.thumbnail = thumbnail;
    }
}
