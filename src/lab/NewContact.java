package lab;

//imports
import java.io.Serializable;

/**
 * Created by Mike Plucker
 * Date: 3/8/2016
 * Class: CSCI 1933-12
 */

/**
 * Lab 7: Step 1: Build a Class called NewContact that extends Contact
 */
public class NewContact extends Contact implements Serializable{

    //instance variables
    private String email, group, quickRef;


    //constructor with all attributes
    public NewContact(String name, long phone, String address, String comments, String email, String group, String quickRef){

        super(name, phone, address, comments); //passes to Contact constructor
        this.email = email;
        this.group = group;
        this.quickRef = quickRef;
    }

    //constructor with just name, phone, address, email
    public NewContact(String name, long phone, String address, String email){

        super(name, phone, address); //passes to Contact constructor
        this.email = email;

        this.group = "";
        this.quickRef = "";
    }

    //constructor with just name, phone, email
    public NewContact(String name, long phone, String email){

        super(name, phone); //passes to Contact constructor
        this.email = email;

        this.group = "";
        this.quickRef = "";
    }


    //getters and setters for each attribute of the class
    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setGroup(String group){
        this.group = group;
    }

    public String getGroup(){
        return group;
    }

    public void setQuickRef(String quickRef){
        this.quickRef = quickRef;
    }

    public String getQuickRef(){
        return quickRef;
    }


    //returns a string containing all the attributes of the class with the most significant attribute appearing first.
    @Override
    public String toString(){
        return super.toString() + "\nEmail: " + email + "\nGroup: " + group + "\nQuick Reference: " + quickRef;
    }


    //will check to see if each attribute is the same between two classes
    public boolean equals(Contact a){

        if(super.equals(a)){
            return true;
        }
        return false;
    }
}
