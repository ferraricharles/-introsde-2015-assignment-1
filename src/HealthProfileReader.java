import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;
import java.io.IOException;

import java.io.DataInputStream;  


import java.io.IOException;



public class HealthProfileReader{ 
	public static PeopleStore people = new PeopleStore();
	
	Document doc;
	XPath xpath;

	public void loadXML() throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		doc = builder.parse("people.xml");

		//creating xpath object
		getXPathObj();
	}

	public XPath getXPathObj() {

        	XPathFactory factory = XPathFactory.newInstance();
        	xpath = factory.newXPath();
        	return xpath;

	}

	public static void printPersonDetails(Person p){
		HealthProfile hp = p.getHProfile();
		
		System.out.println("\n\nPerson: " + p.getFirstname() + "\nBorn: "
		      + p.getBirthdate()+ "\nWeight: "+hp.getWeight() +"\nHeight: "+hp.getHeight());
	}

	public static void showEverything()  throws Exception{

		Scanner sc = new Scanner(System.in);
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
		System.out.println();
		System.out.println("Output from our XML File: ");
		Unmarshaller um = jc.createUnmarshaller();
		PeopleStore people = (PeopleStore) um.unmarshal(new FileReader("people.xml"));
		List<Person> list = people.getData();
		
		int i = 0;
		for (Person person : list) {
		  i++;
		  printPersonDetails(person);
		  if(i==10) {
			System.out.println("Press Enter to continue...");
			sc.nextLine();
		  }
		  
		}

	} 

	public static void printPersonById(int id) throws Exception  {
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);		
		Unmarshaller um = jc.createUnmarshaller();
		PeopleStore people = (PeopleStore) um.unmarshal(new FileReader("people.xml"));
		List<Person> list = people.getData();
		for (Person person : list) {
			if (person.getPersonId() == id){
				printPersonDetails(person);
				return;
			}
		}

	}

	
	public void askPersonById() throws Exception {
		System.out.println("Inform the request ID");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		printPersonById(n);

	}

	public void getPeopleByWeight(Integer weight, String operator) throws XPathExpressionException {
		
		
		////person[firstname="Paul"][lastname="Pogba"]/healthprofile/weight/text()
		
		XPathExpression expr = xpath.compile("//person[healthprofile/weight"+operator+weight+"]");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        		
		for (int i=0; i<nodes.getLength(); i++){

			System.out.println(nodes.item(i).getTextContent());
		}
				
		//return node.getTextContent();
		
		
	}

	public void takeOperatorAndWeight()  throws XPathExpressionException{
		System.out.println("Please inform an operator for your search ('>' for grater, '<' for smaller and '=' for equal)");
		Scanner sc = new Scanner(System.in);
		char operator;
		operator = sc.next().charAt(0);
		if(operator!='>' && operator!='<' && operator!='='){
			System.out.println("Invalid operator");
		}
		System.out.println("Please now inform the weight you want to compare (use only integer values)");
		Integer weight = sc.nextInt();
		
		getPeopleByWeight(weight,""+operator);
		
	}

	public void getWeight() throws XPathExpressionException {
		System.out.println("Inform the ID from the person you want to know the weight");
		Scanner sc = new Scanner(System.in);
		int code = sc.nextInt();
        	XPathExpression expr = xpath.compile("//person[@id="+code+"]/healthprofile/weight/text()");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        	String weight = node.getTextContent();
       		System.out.println("The person's weight is "+weight);
    	}

	public void getHeight() throws XPathExpressionException {
		System.out.println("Inform the ID from the person you want to know the height");
		Scanner sc = new Scanner(System.in);
		int code = sc.nextInt();
        	XPathExpression expr = xpath.compile("//person[@id="+code+"]/healthprofile/height/text()");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        	String height = node.getTextContent();
       		System.out.println("The person's weight is "+height + "cm");
    	}

	public static void printMenuOptions(){
		System.out.println("Select one option \n "+
				   "0- Exit \n"+
				   "1- show all registers \n"+
				   "2- Print a register of a certain ID \n"+
				   "3- Get weight from a person\n"+
				   "4- Get height from a person\n"+
				   "5- Get register from a wieght comparison\n");
	}
	public static void main (String[] args) throws Exception{
		printMenuOptions();
		HealthProfileReader hpr = new HealthProfileReader();
		hpr.loadXML();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while(n!=0){
			switch(n){
				case 1: 
					showEverything();
					break;
				case 2: 
					hpr.askPersonById();
					break;	
				case 3: 
					hpr.getWeight();
					break;	
				case 4: 
					hpr.getHeight();
					break;	
				case 5: 
					hpr.takeOperatorAndWeight();
					break;	
			}

			printMenuOptions();
			n = sc.nextInt();
		}

	}
	
}
