import java.io.File;
import java.io.FileReader;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
//import javax.xml.bind.JSONMarshaller;
import javax.xml.bind.Unmarshaller;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;


//The people list was taken in http://blogilates.tumblr.com/post/5444106156/female-celebrity-heights-and-weights
public class HealthProfileWriter {  	
	public static PeopleStore people = new PeopleStore();

	public static void initializeDB() {
		
		HealthProfile hp1 = new HealthProfile(50,177.80);
		Person p1 = new Person(new Long(1), "Adriana", "Lima", "1985-02-01", hp1);


		HealthProfile hp2 = new HealthProfile(52, 167.64);
		Person p2 = new Person(new Long(2), "Alicia", "Keys", "1990-03-03", hp2);

		HealthProfile hp3 = new HealthProfile(53, 172.72);
		Person p3 = new Person(new Long(3), "Angelina", "Jolie", "1991-04-20", hp3);


		HealthProfile hp4 = new HealthProfile(52, 172.72);
		Person p4 = new Person(new Long(4), "Anna", "Kournikova", "1969-05-25", hp4);


		HealthProfile hp5 = new HealthProfile(54, 170.18);
		Person p5 = new Person(new Long(5), "Ashley", "Judd", "1999-06-12", hp5);


		HealthProfile hp6 = new HealthProfile(50, 157.48);
		Person p6 = new Person(new Long(6), "Avril", "Lavigne", "1980-07-27", hp6);


		HealthProfile hp7 = new HealthProfile(56, 174);
		Person p7 = new Person(new Long(7), "Bar", "Rafaeli", "1979-08-30", hp7);


		HealthProfile hp8 = new HealthProfile(57, 167.64 );
		Person p8 = new Person(new Long(8), "Beyonce", "Knowles", "1977-09-10", hp8);


		HealthProfile hp9 = new HealthProfile(54, 172.72);
		Person p9 = new Person(new Long(9), "Blake", "Lively", "1987-10-21", hp9);


		HealthProfile hp10 = new HealthProfile(47, 162.56 );
		Person p10 = new Person(new Long(10), "Britney", "Spears", "1990-11-22", hp10);

		HealthProfile hp11 = new HealthProfile(42, 167.64);
		Person p11 = new Person(new Long(11), "Calista", "Flockhart", "1991-12-05", hp11);


		HealthProfile hp12 = new HealthProfile(49, 173.99);
		Person p12 = new Person(new Long(12), "Cameron", "Diaz", "1995-12-20", hp12);

		HealthProfile hp13 = new HealthProfile(49, 160.02);
		Person p13 = new Person(new Long(13), "Carmen", "Electra", "1994-01-05", hp13);


		HealthProfile hp14 = new HealthProfile(52, 160.02);
		Person p14 = new Person(new Long(14), "Carrie", "Underwood", "1959-05-08", hp14);


		HealthProfile hp15 = new HealthProfile(52, 173.99);
		Person p15 = new Person(new Long(15), "Cate", "Blanchett", "2000-03-10", hp15);


		HealthProfile hp16 = new HealthProfile(54, 172.72);
		Person p16 = new Person(new Long(16), "Catherine", "Zeta Jones", "2001-02-20", hp16);


		HealthProfile hp17 = new HealthProfile(56, 176.53);
		Person p17 = new Person(new Long(17), "Charlize", "Theron", "1996-09-23", hp17);


		HealthProfile hp18 = new HealthProfile(57, 156.21);
		Person p18 = new Person(new Long(18), "Christina", "Aguilera", "1985-03-19", hp18);


		HealthProfile hp19 = new HealthProfile(45, 172.72);
		Person p19 = new Person(new Long(19), "Ciara9", "Ciara", "2000-03-13", hp19);


		HealthProfile hp20 = new HealthProfile(58, 177.80);
		Person p20 = new Person(new Long(20), "Cindy", "Crawford", "1985-03-11", hp20);

		 
		people.getData().add(p1);
		people.getData().add(p2);
		people.getData().add(p3);
		people.getData().add(p4);
		people.getData().add(p5);
		people.getData().add(p6);
		people.getData().add(p7);
		people.getData().add(p8);
		people.getData().add(p9);
		people.getData().add(p10);
		people.getData().add(p11);
		people.getData().add(p12);
		people.getData().add(p13);
		people.getData().add(p14);
		people.getData().add(p15);
		people.getData().add(p16);
		people.getData().add(p17);
		people.getData().add(p18);
		people.getData().add(p19);
		people.getData().add(p20);
	}	

	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        m.marshal(people,new File("people.xml")); // marshalling into a file
        m.marshal(people, System.out);			  // marshalling into the system default output



	// Marshalling into JSon

	/*JSONMarshaller marshaller = JSONJAXBContext.getJSONMarshaller( m );
	marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
	marshaller.marshallToJSON( people,new File("people.json"));*/

    }
}
