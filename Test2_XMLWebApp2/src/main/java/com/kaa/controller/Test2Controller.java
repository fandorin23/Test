package com.kaa.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kaa.XMLentities.Customers;
import com.kaa.service.CustomersService;


@Controller
public class Test2Controller {
//    private static File XML_File;
//    
//	public static File getXML_File() {
//	    return XML_File;
//	}
//
//	private void setXML_File(File xML_File) {
//	    XML_File = xML_File;}
	
    

    
	@RequestMapping(value = { "/", "/login"})
	public String sayHello(Model model) {		
		return "index";
	}
	
	@RequestMapping(value = "/submit", method= RequestMethod.POST)
	public String handleFileUpload(Model model, String name, @RequestParam("XML_File") MultipartFile file){
	    	name="XML_File";
	        if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
				Customers customers = com.kaa.service.XMLService2.unmarshalCustomersXML(bytes);
				CustomersService cusService = new CustomersService(customers);
				model.addAttribute("totalCostAllCustomersOrders", cusService.totalCostAllCustomersOrders());
				model.addAttribute("cusWithMaxCostOfAllOrders", cusService.cusWithMaxCostOfAllOrders());
				model.addAttribute("maxOrderCost", cusService.maxOrderCost());
				model.addAttribute("minOrderCost", cusService.minOrderCost());
				model.addAttribute("numOfOrders", cusService.numOfOrders());
				model.addAttribute("everegeOrderPrice", cusService.everegeOrderPrice());

				return "result";

			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	        
	        
	    }
}
