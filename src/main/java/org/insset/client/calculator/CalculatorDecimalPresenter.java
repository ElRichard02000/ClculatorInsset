package org.insset.client.calculator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResetButton;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import org.insset.client.message.dialogbox.DialogBoxInssetPresenter;
import org.insset.client.service.RomanConverterService;
import org.insset.client.service.RomanConverterServiceAsync;
import org.insset.shared.FieldVerifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author talend
 */
public class CalculatorDecimalPresenter extends Composite {

    @UiField
    public ResetButton boutonClearR;
    @UiField
    public SubmitButton boutonConvertRToA;
    @UiField
    public ResetButton boutonClearA;
    @UiField
    public SubmitButton boutonConvertAToR;
    @UiField
    public ResetButton boutonClearD;
    @UiField
    public SubmitButton boutonConvertD;
    @UiField
    public TextBox valR;
    @UiField
    public TextBox valA;
    @UiField
    public TextBox valD;
    @UiField
    public Label errorLabelRToA;
    @UiField
    public Label errorLabelAToR;
    @UiField
    public Label errorLabelD;

    interface MainUiBinder extends UiBinder<HTMLPanel, CalculatorDecimalPresenter> {
    }

    private static MainUiBinder ourUiBinder = GWT.create(MainUiBinder.class);
    /**
     * Create a remote service proxy to talk to the server-side Greeting
     * service.
     */
    private final RomanConverterServiceAsync service = GWT.create(RomanConverterService.class);

    /**
     * Constructeur
     */
    public CalculatorDecimalPresenter() {
        initWidget(ourUiBinder.createAndBindUi(this));
        initHandler();
    }

    /**
     * Init des handler
     */
    private void initHandler() {
        boutonClearR.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                valR.setText("");
                errorLabelRToA.setText("");
            }
        });
        boutonConvertRToA.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                convertRomanToArabe();
            }

        });
        boutonClearA.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                valA.setText("");
                errorLabelAToR.setText("");
            }
        });
        boutonConvertAToR.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                convertArabeToRoman();
            }

        });
        boutonClearD.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                valD.setText("");
                errorLabelD.setText("");
            }
        });
        boutonConvertD.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                convertDate();
            }

        });
    }

    
    
   

    /**
     * call server
     */
    private void convertRomanToArabe() {
        if (!FieldVerifier.isValidRoman(valR.getText())) {
            
            
                  errorLabelRToA.addStyleName("serverResponseLabelError");
            errorLabelRToA.setText("Format incorect");
            return;
        }
        service.convertRomanToArabe(valR.getText(), new AsyncCallback<Integer>() {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
//                Window.alert(SERVER_ERROR);
            }

            public void onSuccess(Integer result) {
                new DialogBoxInssetPresenter("Convertion Roman to arabe", valR.getText(), String.valueOf(result));
            }
        });
    }

    /**
     * call server
     */
    
    
        // test fonction verif minimum value 
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
    private void convertArabeToRoman() {
         
        try{
        Integer.parseInt(valA.getText()); 
        if(!VerifValueMax(Integer.parseInt(valA.getText())) || !VerifValueMin(Integer.parseInt(valA.getText())))
        {
            errorLabelAToR.addStyleName("serverResponseLabelError");
            errorLabelAToR.setText("la valeur doit etre comprise entr 0 et 2000 ");
  
            return;
        }
         } catch(NumberFormatException e) { 
             errorLabelAToR.addStyleName("serverResponseLabelError");
            errorLabelAToR.setText("Format incorect");
  
        return ; 
    } catch(NullPointerException e) {
        errorLabelAToR.addStyleName("serverResponseLabelError");
            errorLabelAToR.setText("Format incorect");
  
        return ;
    
        }
      /*  if (!FieldVerifier.isValidDecimal(Integer.parseInt(valA.getText()))) {
            
            errorLabelAToR.addStyleName("serverResponseLabelError");
            errorLabelAToR.setText("Format incorect");
            return;
        }*/
        service.convertArabeToRoman(Integer.parseInt(valA.getText()), new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
//                Window.alert(SERVER_ERROR);
            }

            public void onSuccess(String result) {
                new DialogBoxInssetPresenter("Convertion Arabe to Roman", valA.getText(), result);
            }
        });
    }

    /**
     * call server
     */
    private void convertDate() {
        //Verif
        
        
           
         /* Check if date is 'null' */
    if (valD.getText().trim().equals(""))
    {
         errorLabelAToR.addStyleName("serverResponseLabelError");
            errorLabelAToR.setText("Veuillez renseigner une Date");
        return ;
    }
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
            javaDate = sdfrmt.parse(valD.getText()); 
          
        }
        /* Date format is invalid */
        catch (ParseException  e)
        {
             errorLabelAToR.addStyleName("serverResponseLabelError");
            errorLabelAToR.setText("Format incorect");
            return ;
        }
    }
        /* Return 'true' - since date is in valid format */
   
    
      /*  if (!FieldVerifier.isValidDate(valD.getText())) {
            errorLabelAToR.addStyleName("serverResponseLabelError");
            errorLabelAToR.setText("Format incorect");
            return;
        }*/
        //call server
        service.convertDateYears(valD.getText(), new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
//                Window.alert(SERVER_ERROR);
            }

            public void onSuccess(String result) {
                new DialogBoxInssetPresenter("Convertion Date", valD.getText(), result);
            }
        });
    }

}
