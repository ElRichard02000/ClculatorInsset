package org.insset.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * <p>
 * FieldVerifier validates that the name the user enters is valid.
 * </p>
 * <p>
 * This class is in the <code>shared</code> packing because we use it in both
 * the client code and on the server. On the client, we verify that the name is
 * valid before sending an RPC request so the user doesn't have to wait for a
 * network round trip to get feedback. On the server, we verify that the name is
 * correct to ensure that the input is correct regardless of where the RPC
 * originates.
 * </p>
 * <p>
 * When creating a class that is used on both the client and the server, be sure
 * that all code is translatable and does not use native JavaScript. Code that
 * is note translatable (such as code that interacts with a database or the file
 * system) cannot be compiled into client side JavaScript. Code that uses native
 * JavaScript (such as Widgets) cannot be run on the server.
 * </p>
 */
public class FieldVerifier {

     private static Pattern regex = Pattern.compile("M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})");
    /**
     * Verifies that the specified name is valid for our service.
     *
     * In this example, we only require that the name is at least four
     * characters. In your application, you can use more complex checks to
     * ensure that usernames, passwords, email addresses, URLs, and other fields
     * have the proper syntax.
     *
     * @param name the name to validate
     * @return true if valid, false if invalid
     */
    public static boolean isValidName(String name) {
        if ((name == null) || (name.isEmpty())) {
            return false;
        }
        return true;
    }

    /**
     * Verifies that the specified value is valide.
     *
     * In this example, we only require that the name is at least four
     * characters. In your application, you can use more complex checks to
     * ensure that usernames, passwords, email addresses, URLs, and other fields
     * have the proper syntax.
     *
     * @param name the name to validate
     * @return true if valid, false if invalid
     */
    
    public static boolean VerifValueMin(int val)
    {
        boolean res = true;
        if(val < 0)
            res = false;
            
            return res;
            
    }
    
    

    public static boolean VerifValueMax( int val )
    {
         boolean res = true;
        if(val > 2000)
            res = false;
            
            return res;
    }
    public static boolean isValidDecimal(Integer nbr) {
        //Implement your code
        
         try { 
         Integer.parseInt(nbr.toString()); 
         if(!VerifValueMax(nbr) || !VerifValueMin(nbr))
            return false;        
         else
        return true;
        
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }

       
    }

    /**
	 * Test whether the input string is a valid roman numeral (between 1 and 4999).
	 */
	public static boolean isRoman(String s) {
		return s!=null && !"".equals(s) && regex.matcher(s.toUpperCase()).matches();
	}

    public  static boolean isValidRoman(String nbr) {
        //Implement your code
        boolean breturn =false;
       
               if (isRoman(nbr)) {
			breturn = true;
		}
		else
               {
	         throw new RuntimeException("\""+nbr+"\" is not a valid roman numeral.");
                 
                 
               }

               return breturn;
       
    }

    public static boolean isValidDate(String date) {
        //Implement your code
        
         /* Check if date is 'null' */
    if (date.trim().equals(""))
    {
        return true;
    }
    /* Date is not 'null' */
    else
    {
        /*
         * Set preferred date format,
         * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
        SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
        sdfrmt.setLenient(false);
        /* Create Date object */
        Date javaDate = null;
        /* parse the string into date form */
        try
        {
            javaDate = sdfrmt.parse(date); 
            System.out.println("Date after validation: " + javaDate);
        }
        /* Date format is invalid */
        catch (ParseException  e)
        {
            System.out.println("The date you provided is in an " +"invalid date format.");
            return false;
        }
        /* Return 'true' - since date is in valid format */
        return true;
    }
       
    }
}
