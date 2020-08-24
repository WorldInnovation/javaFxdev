package entity;

import java.util.ArrayList;

public class User {

    private String gender;
    Name NameObject;
    Location LocationObject;
    private String email;
    Login LoginObject;
    Dob DobObject;
    Registered RegisteredObject;
    private String phone;
    private String cell;
    ArrayList<Object> id = new ArrayList<>();//Id IdObject;
    Picture PictureObject;
    private String nat;


    // Getter Methods

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return NameObject;
    }

    public Location getLocation() {
        return LocationObject;
    }

    public String getEmail() {
        return email;
    }

    public Login getLogin() {
        return LoginObject;
    }

    public Dob getDob() {
        return DobObject;
    }

    public Registered getRegistered() {
        return RegisteredObject;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public ArrayList<Object> getId() {
        return id;
    }

    public void setId(ArrayList<Object> id) {
        this.id = id;
    }

    public Picture getPicture() {
        return PictureObject;
    }

    public String getNat() {
        return nat;
    }

    // Setter Methods

    public void setGender( String gender ) {
        this.gender = gender;
    }

    public void setName( Name nameObject ) {
        this.NameObject = nameObject;
    }

    public void setLocation( Location locationObject ) {
        this.LocationObject = locationObject;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public void setLogin( Login loginObject ) {
        this.LoginObject = loginObject;
    }

    public void setDob( Dob dobObject ) {
        this.DobObject = dobObject;
    }

    public void setRegistered( Registered registeredObject ) {
        this.RegisteredObject = registeredObject;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }

    public void setCell( String cell ) {
        this.cell = cell;
    }


    public void setPicture( Picture pictureObject ) {
        this.PictureObject = pictureObject;
    }

    public void setNat( String nat ) {
        this.nat = nat;
    }
}



