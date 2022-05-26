package com.example.fitnessshop.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import com.example.fitnessshop.util.Fields;


public class Validation {
	
	
	/**
	 * Check parameters validity in credit card form.
	 * 
	 * @param req HttpServletRequest
	 * @return Map of errors
	 */
	public static Map<String, String> validateCreditCardForm(HttpServletRequest req){
		
		// get form parameters and check weather they are valid
		String cardNumber = req.getParameter(Fields.CARDNUMBER.toString());
		String expiredMonth = req.getParameter(Fields.CARDEXPIRATIONMONTH.toString());
		String expiredYear = req.getParameter(Fields.CARDEXPIRATIONYEAR.toString());
		String cvCode = req.getParameter(Fields.CARDCVCODE.toString());
		Map<String, String> errors = new HashMap<String, String>();
		
		if(cardNumber==null || cardNumber == "") {
			errors.put(Fields.CARDNUMBER.toString(), "Please provide a valid card number");
		}
		if(expiredMonth == null || expiredYear == null || expiredMonth == "" || expiredYear == "") {
			errors.put(Fields.CARDEXPIRATIONMONTH.toString(), "Please provide valid expiration date");
		}
		if(cvCode == null || cvCode == "") {
			errors.put(Fields.CARDCVCODE.toString(), "Please provide a valid CV code");
		}
		return errors;
	}
	
	/**
	 * Check parameters validity when update product quantity in cart.
	 * 
	 * @param req HttpServletRequest
	 * @return Map of errors
	 */
	public static Map<String, String> validateUpdateCartProductForm(HttpServletRequest req){
		
		Map<String, String> errors = new HashMap<String, String>();
		
		if(req.getParameter(Fields.ID.toString()) == null ||req.getParameter(Fields.ID.toString()) == "" ) {
			errors.put(Fields.ID.toString(), "Please provide a valid product ID");
		}else {
			try {
				Integer.parseInt(req.getParameter(Fields.ID.toString()));
			}catch (NumberFormatException e) {
				errors.put(Fields.ID.toString(), "Please provide a valid product ID");
			}
		}
		
		if(req.getParameter(Fields.QUANTITY.toString()) == null ||req.getParameter(Fields.QUANTITY.toString()) == "" ) {
			errors.put(Fields.QUANTITY.toString(), "Please provide a valid product quantity");
		}else {
			try {
				int quantity = Integer.parseInt(req.getParameter(Fields.QUANTITY.toString()));
			}catch (NumberFormatException e) {
				errors.put(Fields.QUANTITY.toString(), "Please provide a valid product quantity");
			}
		}
		return errors;
		
	}
	
	/**
	 * Check parameters validity when add new product in cart.
	 * 
	 * @param req HttpServletRequest
	 * @return Map of errors
	 */
	public static Map<String, String> validateNewCartProductForm(HttpServletRequest req){
		
		Map<String, String> errors = new HashMap<String, String>();
		if(req.getParameter(Fields.NAME.toString()) == null ||req.getParameter(Fields.NAME.toString()) == "" ) {
			errors.put(Fields.NAME.toString(), "Please provide a valid product name");
		}
		if(req.getParameter(Fields.DESCRIPTION.toString()) == null ||req.getParameter(Fields.DESCRIPTION.toString()) == "" ) {
			errors.put(Fields.DESCRIPTION.toString(), "Please provide a valid product description");
		}
		if(req.getParameter(Fields.IMGPATH.toString()) == null ||req.getParameter(Fields.IMGPATH.toString()) == "" ) {
			errors.put(Fields.IMGPATH.toString(), "Please provide a valid product path image");
		}
		
		if(req.getParameter(Fields.ID.toString()) == null ||req.getParameter(Fields.ID.toString()) == "" ) {
			errors.put(Fields.ID.toString(), "Please provide a valid product ID");
		}else {
			try {
				Integer.parseInt(req.getParameter(Fields.ID.toString()));
			}catch (NumberFormatException e) {
				errors.put(Fields.ID.toString(), "Please provide a valid product ID");
			}
		}
		
		String quantity = req.getParameter(Fields.QUANTITY.toString());
		if(quantity == null || quantity == "" ) {
			errors.put(Fields.QUANTITY.toString(), "Please provide a valid product quantity");
		}else {
			try {
				Integer.parseInt(req.getParameter(Fields.QUANTITY.toString()));
			}catch (NumberFormatException e) {
				errors.put(Fields.QUANTITY.toString(), "Please provide a valid product quantity");
			}
		}
		
		if(req.getParameter(Fields.PRICE.toString()) == null ||req.getParameter(Fields.PRICE.toString()) == "" ) {
			errors.put(Fields.PRICE.toString(), "Please provide a valid product price");
		}else {
			try {
				Float.parseFloat(req.getParameter(Fields.PRICE.toString()));
			}catch (NumberFormatException e) {
				errors.put(Fields.PRICE.toString(), "Please provide a valid product price");
			}
			
		}
		
		return errors;
		
	}
	

	
	/**
	 * Check parameters validity in Sign-In form.
	 * 
	 * @param req HttpServletRequest
	 * @return Map of errors
	 */
	public static Map<String, String> validateSignInForm(HttpServletRequest req){
		
		// get form parameters and check weather they are valid
		String username = req.getParameter(Fields.USERNAME.toString());
		String password = req.getParameter(Fields.PASSWORD.toString());
		Map<String, String> errors = new HashMap<String, String>();
		
		if(username==null || username == "") {
			errors.put(Fields.USERNAME.toString(), "Please provide a valid username");
		}
		if(password == null || password == "") {
			errors.put(Fields.PASSWORD.toString(), "Please provide a valid password");
		}
		return errors;
	}
	
	
	/**
	 * Check parameters validity in checkout address form.
	 * 
	 * @param req HttpServletRequest
	 * @return Map of errors
	 */
	public static Map<String, String> validateAddressCheckoutForm(HttpServletRequest req){
		
		// get form parameters and check weather they are valid
		String firstName = req.getParameter(Fields.FIRSTNAME.toString());
		String lastName = req.getParameter(Fields.LASTNAME.toString());
		String country = req.getParameter(Fields.COUNTRY.toString());
		String street = req.getParameter(Fields.STREET.toString());
		String zipCode = req.getParameter(Fields.ZIPCODE.toString());
		String city = req.getParameter(Fields.CITY.toString());
		
		Map<String, String> errors = new HashMap<String, String>();
		
		if(firstName == null || firstName == "" || firstName.length()>30) {
			errors.put(Fields.FIRSTNAME.toString(), "Please provide a valid first name");
		}
		if(lastName == null || lastName == "" || lastName.length()>30) {
			errors.put(Fields.LASTNAME.toString(), "Please provide a valid last Name");
		}
		if(country == null || country == "" || country.length()>30) {
			errors.put(Fields.COUNTRY.toString(), "Please provide a valid country");
		}
		if(street == null || street == "" || street.length()>30) {
			errors.put(Fields.STREET.toString(), "Please provide a valid street address");
		}
		if(zipCode == null || zipCode == "" || zipCode.length()>10 || !zipCode.matches("^[0-9]*$")) {
			errors.put(Fields.ZIPCODE.toString(), "Please provide a valid zip code");
		}
		if(city == null || city == "" || city.length()>30 || !city.matches(".*[a-zA-Z]+.*")) {
			errors.put(Fields.CITY.toString(), "Please provide a valid city");
		}
		return errors;
	}
	

	
	
	
	/**
	 * Check parameters validity in Sign-Up form.
	 * 
	 * @param req HttpServletRequest
	 * @return Map of errors
	 */
	public static Map<String, String> validateSignUpForm(HttpServletRequest req){
		
		// get form parameters and check weather they are valid
		String username = req.getParameter(Fields.USERNAME.toString());
		String password = req.getParameter(Fields.PASSWORD.toString());
		String email = req.getParameter(Fields.EMAIL.toString());
		String phone = req.getParameter(Fields.PHONE.toString());
		String birthdate = req.getParameter(Fields.BIRTHDATE.toString());
		System.out.println(phone);

		Map<String, String> errors = validateAddressCheckoutForm(req);
		
		if(username==null || username == "" || !username.matches(".*[a-zA-Z]+.*") || username.length()<5 || username.length()>10) {
			errors.put(Fields.USERNAME.toString(), "Please provide a valid username");
		}
		if(password == null || password == "" || password.length()<5 || password.length()>10) {
			errors.put(Fields.PASSWORD.toString(), "Please provide a valid password");
		}
		
		if(email == null || email == "" || email.length()>30 || !isValidEmail(email)) {
			errors.put(Fields.EMAIL.toString(), "Please provide a valid email");
		}
		if(phone == null) {
			System.out.println(phone);
			errors.put(Fields.PHONE.toString(), "Пожалуйста, укажите действующий телефон");
		}
		if(!isValidDate(birthdate)) {
			errors.put(Fields.BIRTHDATE.toString(), "Please provide a valid birthdate");
		}
		System.out.println(phone);
		return errors;
	}
	
	/**
	 * Check weather an email is valid.
	 * 
	 * @param email to check
	 * @return boolean
	 */
	 private static boolean isValidEmail(String email) {
		   boolean isValid = true;
		   try {
		      InternetAddress emailAddress = new InternetAddress(email);
		      emailAddress.validate();
		   } catch (AddressException ex) {
		      isValid = false;
		   }
		   return isValid;
		}
	 
	 /**
	  * Check if a string date is valid.
	  * 
	  * @param str string date
	  * @return boolean
	  */
	 private static boolean isValidDate(String str) {
		 boolean isValid = true;
		 try {
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
				Date date =  format.parse(str);
				if(!date.before(new Date(System.currentTimeMillis()))) {
					isValid = false;
				}
				
			} catch (ParseException e) {
				System.out.println("invalid Date");
				isValid = false;
			}
		 return isValid;
	 }
}
