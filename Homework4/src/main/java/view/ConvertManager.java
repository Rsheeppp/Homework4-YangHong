/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import controller.CurrencyConvert;
import model.*;

/**
 *
 * @author Rsheep
 */
@Named("conMan")
@ConversationScoped
public class ConvertManager implements Serializable {
    @EJB
    private CurrencyConvert currencyConvert;
    private convertDTO currentConvert;
    private String fromCurrency;
    private String toCurrency;
    private float inputAmount;
    private float outputAmount;
    private String tp;
    private float rate;
    private Integer searchedAcct;
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }


    public boolean getSuccess() {
        return transactionFailure == null;
    }

    public Exception getException() {
        return transactionFailure;
    }


    public void findRate() {
        try {
            startConversation();
            transactionFailure = null;
            tp = fromCurrency + "-" + toCurrency;
            currentConvert = currencyConvert.findConvert(tp);
            rate = currentConvert.getRate();
            
        } catch (Exception e) {
            handleException(e);
        }
    }

  
    public void convert() {
        try {
            transactionFailure = null;
            findRate();
            outputAmount = inputAmount * rate;
        } catch (Exception e) {
            handleException(e);
        }
    }

    public float getOutputAmount(){
        return outputAmount;
    }

  
    public void setInputAmount(float inputAmount) {
        this.inputAmount = inputAmount;
    }

   
    public float getInputAmount() {
        return inputAmount;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

 
    public String getToCurrency() {
        return null;
    }


    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

   
    public String getfromCurrency() {
        return null;
    }

    public convertDTO getCurrentAcct() {
        return currentConvert;
    }
}
