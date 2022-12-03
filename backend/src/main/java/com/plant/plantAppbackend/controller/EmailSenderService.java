package com.plant.plantAppbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.plant.plantAppbackend.Model.AppUser;
import com.plant.plantAppbackend.Model.PlantModel;


@Service
public class EmailSenderService {

	// CODE GUIDE USED: //https://mkyong.com/spring-boot/spring-boot-how-to-send-email-via-smtp/ 
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendSimpleEmail(String toEmail, String body, String subject) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("plantifulgardens201@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
//		System.out.println("Mail Send...");
	}
	
	public void sendIntroEmail(AppUser user) {
		String emailAddress = user.getEmail();
		String subject ="hello from the creators of p l a n t!";
		String body ="welcome!" + "\n" + "we are so excited to welcome you"
				+ " to our application. here at p l a n t, we want to "
				+ "help you fulfill your gardening dreams. we're looking "
				+ "forward to having you on our website! "
				+ "we hope you have as much fun using it as we did creating it." 
				+ "\n" 
				+ "have a plantiful day!";
		
//	
		sendSimpleEmail(emailAddress, body, subject);
		System.out.println("Introduction email sent...");
//	
	}
	

	public void sendEmail(AppUser user) {
		
		List<PlantModel> plantList = user.needsWatering();
		System.out.println("these plants need watering : "+ plantList);
	
		if (plantList.size() == 0) {
			System.out.println(" YEEHAW ");
			return;
		}
		String plantsToWater = "";
		
		for (int i = 0; i < plantList.size(); i++) {
			PlantModel curr = plantList.get(i);
			plantsToWater += curr.getPlantName() + ", ";
		}
		
		
		
		String emailAddress = user.getEmail();
		String subject = "time to water your garden!";
		String body =  "hello from p l a n t!" + "\n" + "the time has come for you to nourish your garden"
				+ " - and yourself along the way." + "\n" + "please water the following plants today: " + 
				plantsToWater.substring(0, plantsToWater.length()-2) + "\n" + "be sure to indicate on the p l a n t application whether you've done so!" + "\n" +
				"remember, for a happy garden, you must water your plants on time and tell us that you did. " + "\n" 
				+ "have a plantiful day!";
		
		sendSimpleEmail(emailAddress, body, subject);
		System.out.println("Email reminder to water your plants sent...");

	}
	



}
