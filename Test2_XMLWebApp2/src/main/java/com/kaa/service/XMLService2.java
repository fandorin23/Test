package com.kaa.service;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import com.kaa.XMLentities.Customers;

public class XMLService2 {
	
	private static Customers customers;
//	private static File file = new File("c:/Users/Fandorin/Documents/temp/test2.xml");
    
//    public static void main(String[] args) {   	   	
//    	
//    	customers = unmarshalCustomersXML(file);
//    	
//    	CustomersService cusService = new CustomersService(customers);
//    	cusService.totalCostAllCustomersOrders();
//    	cusService.cusWithMaxCostOfAllOrders();
//    	cusService.maxOrderCost();
//    	cusService.minOrderCost();
//    	cusService.numOfOrders();	
//    	cusService.everegeOrderPrice();
//
//	}

	public static Customers unmarshalCustomersXML(byte[] bytes) {
		
		Customers customers = null;

		try {

//			SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
//			Schema schema = factory.newSchema(new File("e:/java/workspace/Test2_XMLWebApp2/src/main/customers.xsd"));

			JAXBContext jaxbContext = JAXBContext.newInstance(Customers.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			//jaxbUnmarshaller.setSchema(schema);
			InputStream is = new ByteArrayInputStream(bytes);
			customers = (Customers) jaxbUnmarshaller.unmarshal(is);
			return customers;

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return customers;
	}

	public static Customers getCustomers() {
		return customers;
	}

	public static void setCustomers(Customers customers) {
		XMLService2.customers = customers;
	}

}
