package lab;

/**
 * Created by Mike Plucker
 * Date: 3/8/2016
 * Class: CSCI 1933-12
 */
public class ContactListTest {

    public static void main(String[] args) {

        //create ContactList objects
        ContactList list = new ContactList(7); //list has a length of 7


        //create Contact objects
        Contact mike = new Contact("Mike", 8309991234L, "1234 Pleasent Street", "Cool dude");
        Contact mikey = new Contact("Mike", 8309991234L, "1234 Pleasent Street", "Cool"); //for testing equals method
        Contact greg = new Contact("Greg", 9521231234L, "1324 Main Street"); //no comments
        Contact allen = new Contact("Allen", 6123258594L); //no address or comments
        Contact bill = new Contact("Bill", 7639874343L, "14 Central Way", "Classmate");

        NewContact sarah = new NewContact("Sarah", 9528553242L, "125 Apache St", "Girlfriend", "sarahp@gmail.com", "UofM", "Peaches");
        NewContact catherine = new NewContact("Catherine", 6129871534L, "8342 Studio Ln", "", "cathyp@hotmail.com", "UofM", "Cathy"); //no comments
        NewContact ashley = new NewContact("Ashley", 7638984635L, "ahsleyp@gmail.com");
        NewContact ashleeey = new NewContact("Ashley", 7638984635L, "unicornsareawesome@aol.com"); //for testing equals method


        System.out.println("Now adding Contacts.\n");

        //add contacts to Contact List
        list.add(mike);
        list.addInOrder(catherine);
        list.addInOrder(greg);
        list.addInOrder(bill);
        list.addInOrder(allen);
        list.addInOrder(sarah);
        list.addInOrder(ashley);

        System.out.println("\nContacts in Contact List: ");
        list.printToConsole();  //print out all contacts in list



		//test equals method
		System.out.println("\nTesting Equals:");

		if(mike.equals(sarah)){
			System.out.println("These are the same contact.");
		}
		else
			System.out.println("Not the same contact!");


		if(mike.equals(mikey)){
			System.out.println("These are the same contact.");
		}
		else
			System.out.println("Not the same contact!");


		if(ashley.equals(ashleeey)){
			System.out.println("These are the same contact.");
		}
		else
			System.out.println("Not the same contact!");
    }
}
