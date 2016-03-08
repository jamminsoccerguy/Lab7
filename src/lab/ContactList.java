package lab;

//imports
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

import java.io.ObjectOutputStream; //output stream for Object
import java.io.FileOutputStream; //generic output stream
import java.io.ObjectInputStream; //input stream for Objects
import java.io.FileInputStream; //generic input stream
import java.io.*; //provide easy access to all IO Exceptions

/**
 * Created by Mike Plucker
 * Date: 3/8/2016
 * Class: CSCI 1933-12
 */

/**
 * Lab 7: Step 2: Try It
 */
public class ContactList {

    //instance variable
    private int ptr; //holds the index of the most recent contact referenced

    //contacts array
    private Contact[] contacts;

    //declare scanner/writer
    private Scanner scan = null;
    private PrintWriter p = null;


    //default constructor
    public ContactList(){
        contacts = new Contact[20]; //creates a ContactList that is of length 20
        ptr = -1; //ContactList is initially empty
    }


    //constructor
    public ContactList(int length){
        contacts = new Contact[length]; //creates a ContactList with whatever length is passed in by the parameter
        ptr = -1; //ContactList is initially empty
    }


    //add a new Contact at the end of ContactList and return true if successful
    public boolean add(Contact c){

        if(ptr == contacts.length - 1){ //if array is full
            System.out.println("\nError: Can't Add Contact, Contact List is Full!");
            return false;
        }
        else{ //if array isn't full yet
            ptr++; //move ptr ahead to next contact
            contacts[ptr] = c; //inserts the contact where the ptr counter is currently indexed
            return true;
        }
    }


    //returns the first contact found, relative to the current position in the ContactList...using the contains() method
    public Contact find(String name){

        //enhanced for loop to search for name among contacts
        for(Contact findName : contacts){ //iterates over the contacts array
            if(findName.getName().contains(name)){ //if a contact's name contains the name passed in thru the parameter
                return findName; //return that contact
            }
        }
        return null;
    }


    //removes the "current" contact pointed to and returns it
    public Contact remove(){

        if(ptr == -1){ //ContactList is empty
            System.out.println("\nError: Can't Remove Contact, No contacts found in Contact List.");
            return null;
        }
        else{
            Contact current = contacts[ptr]; //takes contact that is at the ptr index
            System.arraycopy(contacts, ptr + 1, contacts, ptr, contacts.length - 1 - ptr);

            //Contact current = ArrayUtils.removeElement(contacts, ptr); //need to add apache library to work!

            ptr--; //move ptr back to previous contact
            return current; //returns the contact that was removed
        }
    }


    //prints out the contacts in the ContactList to the console
    public void printToConsole(){

        //enhanced for loop to print out contacts
        for(Contact print : contacts){ //iterates over contacts array
            System.out.println(print); //prints the contacts to the console
        }
    }


    //writes the ContactList information to a text file named with the String provided
    public boolean write(String fileName){

        try{ //test for Exception in call to File constructor
            p = new PrintWriter(new File(fileName));
        }
        catch(Exception e){}

        //enhanced for loop to print contacts to file
        for(Contact fileWrite : contacts){ //iterates over contacts array
            p.println(fileWrite); //writes the contacts to the file
        }

        p.close(); //closes the file

        return true;
    }


    //writes the ContactList information to a text file using Serialization
    public boolean objectWrite(){

        ObjectOutputStream o;

        try{
            o = new ObjectOutputStream(new FileOutputStream("data2.txt"));

            for(Contact objectWrite : contacts){ //iterates over contacts array
                o.writeObject(objectWrite); //writes the contacts to the file
            }
        }
        catch(IOException e){
            System.out.println("\nFile write problem to fix.");
        }
        return true;
    }


    //reads the ContactList information from the text file named with the String provided
    public boolean read(String fileName){

        try{ //test for Exception in call to File constructor
            scan = new Scanner(new File(fileName)); //reads information from file
        } //end try block for File usage
        catch(Exception e){}

        System.out.println("\nContacts in Contact List: ");

        printToConsole(); //print all the contacts to the console

        return true;
    }


    //reads the ContactList information from a text file using Serialization
    public boolean objectRead(){

        ObjectInputStream i;

        try{
            i = new ObjectInputStream(new FileInputStream("data2.txt")); //data2.txt?
            //read1 = (Contact) i.readObject(); //where read1 is a Contact

            for(Contact objectRead : contacts){ //iterates over contacts array
                objectRead = (Contact) i.readObject(); //reads contact from file
                System.out.println(objectRead); //writes contact to console
            }

        }
        catch(EOFException e){
            System.out.println("\nEnd of File read.");
        }
        catch(IOException e){
            System.out.println("\nIO problem to fix.");
        }
        catch(ClassNotFoundException e){
            System.out.println("\nClass does not exist.");
        }
        return true;
    }


    //returns the contact currently pointed to
    public Contact getCurrent(){
        return contacts[ptr]; //return current contact
    }


    //returns the contact at the location represented by i
    public Contact get(int i){

        //local variable
        int index = i - 1; //subtract 1 from input number to get the appropriate index

        if(ptr == -1){ //ContactList is empty
            System.out.println("\nError: Can't Get Contact, No contacts found in Contact List.");
            return null;
        }
        else if(index < 0 || index > contacts.length){ //if index is outside the bounds of the array
            return null;
        }
        else{
            ptr = index; //sets ptr to current contact
            return contacts[index]; //returns contact at that index
        }
    }


    //moves ptr ahead to the next contact and returns the contact
    public Contact next(){

        if(ptr == -1){ //ContactList is empty
            System.out.println("\nError: Can't Get Next Contact, No contacts found in Contact List.");
            return null;
        }
        else if(ptr == (contacts.length - 1)){ //ptr is at end of list
            ptr = 0; //set ptr to first contact
            return contacts[ptr]; //return first contact
        }
        else{
            ptr++; //move ptr ahead to next contact
            return contacts[ptr]; //return next contact
        }
    }


    //moves ptr back to the previous contact and returns the contact
    public Contact previous(){

        if(ptr == -1){ //ContactList is empty
            System.out.println("\nError: Can't Get Previous Contact, No contacts found in Contact List.");
            return null;
        }
        else if(ptr == 0){ //if ptr is at the start of the list
            ptr = (contacts.length - 1); //set ptr to the last contact
            return contacts[ptr]; //return last contact
        }
        else{
            ptr--; //move ptr back to previous contact
            return contacts[ptr]; //return previous contact
        }
    }


    //sorts the ContactList according to the name attribute (bubble sort)
    public void sort(){

        //local variables
        boolean finished = true; //determines when the sort is finished
        Contact temp;

        while(finished){
            finished = false;
            for(int i = 0; i < contacts.length - 1; i++){ //iterates over array
                if(contacts[i].getName().compareToIgnoreCase(contacts[i + 1].getName()) > 0){ //ascending sort
                    temp = contacts[i]; //places contact in temp variable

                    //swaps contacts
                    contacts[i] = contacts[i + 1]; //shifts element left
                    contacts[i + 1] = temp; //shifts element right
                    finished = true;
                }
            }
        }
    }


    //inserts a Contact into the contacts array in alphabetical order
    public boolean addInOrder(Contact c){

        //only adds contact if there is room in contacts array
        if(ptr < contacts.length){

            //local variables
            String compareContact, compareContact2;
            boolean finished = true; //determines when the sort is finished
            Contact temp;

            ptr++;
            contacts[ptr] = c; //adds contact to end of array

            while(finished){
                finished = false;
                for(int i = 0; i < ptr; i++){ //iterates over array (got nullpointerexception when using contacts.length - 1)
                    compareContact = contacts[i].toString(); //gets contacts name
                    compareContact2 = contacts[i + 1].toString(); //gets contacts name

                    if(compareContact.compareToIgnoreCase(compareContact2) > 0){ //ascending sort
                        temp = contacts[i]; //places contact in temp variable

                        //swaps contacts
                        contacts[i] = contacts[i + 1]; //shifts element left
                        contacts[i + 1] = temp; //shifts element right
                        finished = true;
                    }
                }
            }
            return true;
        }

        //array is full
        System.out.println("\nError: Can't Insert Contact, Contact List is Full!");
        return false; //doesn't add contact
    }



    //tests ContactList class
    public static void main(String[] args) {

        //create ContactList objects
        ContactList list = new ContactList(5); //list has a length of 5
        ContactList list2 = new ContactList(1);

        list.scan = new Scanner(System.in); //create scanner

        //create Contact objects
        Contact mike = new Contact("Mike", 8309991234L, "1234 Pleasent Street", "Cool dude");
        Contact greg = new Contact("Greg", 9521231234L, "1324 Main Street"); //no comments
        Contact al = new Contact("Al", 6123258594L); //no address or comments
        Contact bill = new Contact("Bill", 7639874343L, "14 Central Way", "Classmate"); //won't get added initially, because list will be full

        NewContact sarah = new NewContact("Sarah", 9528553242L, "125 Apache St", "Girlfriend", "sarahp@gmail.com", "UofM", "Peaches");
        NewContact catherine = new NewContact("Catherine", 6129871534L, "8342 Studio Ln", "", "cathyp@hotmail.com", "UofM", "Cathy"); //no comments
        NewContact ashley = new NewContact("Ashely", 7638984635L, "ahsleyp@gmail.com");


        System.out.println("The length of the Contact List is: " + list.contacts.length + "\n");

        list.remove(); //tests remove method when there are no contacts (error message)


        System.out.println("\nNow adding Contacts.\n");

        //add contacts to Contact List
        list.add(mike);
        list.add(greg);
        list.add(al);

        list.add(sarah);
        list.add(catherine);

        list.add(bill); //doesn't add because list is full (prints error message)


        System.out.println("\nContacts in Contact List: ");
        list.printToConsole();  //print out all contacts in list


        list.sort(); //sort contact list alphabetically
        System.out.println("\nContacts in List are now sorted alphabetically!");
        list.printToConsole(); //print out all contacts in list


        System.out.println(); //spacing


        //check whether Mike is found in the contact list or not (works!)
        String name1 = "Mike";
        Contact check1 = list.find(name1); //test find method

        if(check1 != null){
            System.out.println("Search Found: " + check1);
        }
        else{
            System.out.println(name1 + " is not found in the Contact List.");
        }


        System.out.println(); //spacing


        //check whether Susan is found in the contact list or not (doesn't work!)
        String name2 = "Susan";
        Contact check2 = list.find(name2); //test find method

        if(check2 != null){
            System.out.println("Search Found: " + check2);
        }
        else{
            System.out.println(name2 + " is not found in the Contact List.");
        }


        System.out.println(); //spacing


        //check whether Al is found in the contact list or not (works!)
        String name3 = "Al";

        Contact check3 = list.find(name3); //test find method

        if(check3 != null){
            System.out.println("Search Found: " + check3);
        }
        else{
            System.out.println(name3 + " is not found in the Contact List.");
        }


        System.out.println(); //spacing


        //check whether Sarah is found in the contact list or not (works!)
        String name4 = "Sarah";

        Contact check4 = list.find(name4); //test find method

        if(check4 != null){
            System.out.println("Search Found: " + check4);
        }
        else{
            System.out.println(name4 + " is not found in the Contact List.");
        }


        System.out.println(); //spacing


        //check remove method
        Contact removeContact = list.remove(); //removes last contact (Sarah) from ContactList
        System.out.println("Removed Contact: \n " + removeContact);


        System.out.println(); //spacing


        //re-add a contact
        //list.add(bill); //now Bill is 3rd contact instead of Mike
        list.add(ashley); //now Ashley is 3rd contact instead of Sarah


        //test writing to a file (writes Al, Catherine, Greg, Mike and Ashley)
        String fileName;
        System.out.print("What file do you want to write to? ");
        fileName = list.scan.next();
        list.write(fileName);


        //test reading from a file (reads Al, Catherine, Greg, Mike and Ashley)
        System.out.print("What file do you want to read from? ");
        fileName = list.scan.next();
        list.read(fileName);


        System.out.println("\nThe Un-Sorted Contacts!");

        list.objectWrite();
        list.objectRead();


        list.sort(); //sort the contact list alphabetically
        System.out.println("\nContacts in List are now sorted alphabetically!");
        list.printToConsole(); //print out all contacts in list



        System.out.println("\nThe Sorted Contacts!");

        list.objectWrite(); //writes the contacts to a file (data2.txt)
        list.objectRead(); //reads contact from file and then prints to console


        //test getCurrent() method
        System.out.println("\nThe Current contact is: " + list.getCurrent()); //Mike

        //test get() method
        System.out.println("\nThe First Contact is: " + list.get(1)); //Al

        //test next() method
        System.out.println("\nThe next Contact is: " + list.next()); //Ashley
        System.out.println("\nThe next Contact is: " + list.next()); //Catherine
        System.out.println("\nThe next Contact is: " + list.next()); //Greg

        //test previous() method
        System.out.println("\nThe previous Contact is: " + list.previous()); //Catherine
        System.out.println("\nThe previous Contact is: " + list.previous()); //Ashley
        System.out.println("\nThe previous Contact is: " + list.previous()); //Al


        //create new ContactList
        System.out.println("\n\nNew Contact List!");

        //test previous() method with only one contact in ContactList
        list2.add(mike); //add contact to list
        list2.printToConsole(); //print out contacts in list
        System.out.println("\nThe previous Contact is: " + list2.previous()); //Mike
    }
}
