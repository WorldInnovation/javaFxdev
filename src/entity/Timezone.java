package entity;

public class Timezone {
    private String offset;
    private String description;


    // Getter Methods

    public String getOffset() {
        return offset;
    }

    public String getDescription() {
        return description;
    }

    // Setter Methods

    public void setOffset( String offset ) {
        this.offset = offset;
    }

    public void setDescription( String description ) {
        this.description = description;
    }
}
