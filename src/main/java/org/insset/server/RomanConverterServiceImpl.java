/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insset.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.insset.client.service.RomanConverterService;

/**
 *
 * @author user
 */
@SuppressWarnings("serial")
public class RomanConverterServiceImpl extends RemoteServiceServlet implements
        RomanConverterService {

    
    
        public String unChiffreDecimal(int n, char i, char v, char x) {
        String s;
        switch (n) {
            case 0:
                s = "";
                break;
            case 1:
                s = "" + i;
                break;
            case 2:
                s = "" + i + i;
                break;
            case 3:
                s = "" + i + i + i;
                break;
            case 4:
                s = "" + i + v;
                break;
            case 5:
                s = "" + v;
                break;
            case 6:
                s = "" + v + i;
                break;
            case 7:
                s = "" + v + i + i;
                break;
            case 8:
                s = "" + v + i + i + i;
                break;
            case 9:
                s = "" + i + x;
                break;
            default:
                s = "???";
        }
        return s;
    }
    
    public String convertitEnRomains(int n) {
        int u = n % 10;
        int d = (n / 10) % 10;
        int c = (n / 100) % 10;
        int m = (n / 1000) % 1000;
        
        return (unChiffreDecimal(m, 'M', '?', '?')
                + unChiffreDecimal(c, 'C', 'D', 'M') 
                + unChiffreDecimal(d, 'X', 'L', 'C')
                + unChiffreDecimal(u, 'I', 'V', 'X')); 
    }
  
    
    @Override
    public String convertDateYears(String nbr) throws IllegalArgumentException {
        //Implement your code
        
         //String[] parts = nbr.split("(?/-)"); 
       
       String[] parts = nbr.split("/");
        

        String part1 = convertitEnRomains(Integer.parseInt(parts[0])); // 004-
        String part2 = convertitEnRomains(Integer.parseInt(parts[1])); // 034556
        String part3 = convertitEnRomains(Integer.parseInt(parts[2]));


        return part1 + "/" + part2 + "/" + part3 ;
        
 
    }

    @Override
    public Integer convertRomanToArabe(String nbr) throws IllegalArgumentException {
        //Implement your code
        return 3;
    }

    @Override
    public String convertArabeToRoman(Integer nbr) throws IllegalArgumentException {
        //Implement your code
        String value = convertitEnRomains(nbr);
        return value;
    }

}
