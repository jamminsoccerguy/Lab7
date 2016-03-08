package lab;

//imports
import java.io.Serializable;

/**
 * Created by Mike Plucker
 * Date: 3/8/2016
 * Class: CSCI 1933-12
 */

/**
 * Lab 7: A Better Contact Class
 */
public class Contact implements Serializable {

    //instance variables
    private String name, address, comments;
    private long phone;


    //constructor without address or comments attribute
    public Contact(String name, long phone){
        this.name = name;
        this.phone = phone;
        this.address = "";
        this.comments = "";
    }


    //constructor without comments attribute
    public Contact(String name, long phone, String address){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.comments = "";
    }


    //constructor with all attributes
    public Contact(String name, long phone, String address, String comments){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.comments = comments;
    }


    //getters and setters for each attribute of the class
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPhone(long phone){
        this.phone = phone;
    }

    public long getPhone(){
        return phone;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public String getComments(){
        return comments;
    }


    //returns a string containing all the attributes of the class with the most significant attribute appearing first.
    @Override
    public String toString(){
        return "\nName: " + name + "\nPhone: " + phone + "\nAddress: " + address + "\nComments: " + comments;
    }


    //will check to see if each attribute is the same between two classes
    public boolean equals(Contact a){

        if(a.name.equalsIgnoreCase(name) && a.phone == phone && a.address.equalsIgnoreCase(address)){
            return true;
        }
        return false;
    }
}
