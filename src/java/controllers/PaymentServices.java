package controllers;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author truon
 */
public class PaymentServices {

    private static final String CLIENT_ID = "ASIH8ksjEdxffC2Ci__kXHO3a-YPziSmX0Q0EKjCE86X68tvpgcMNo18wpDgr0nfmgsfNoqq5GJLzMXb";
    private static final String CLIENT_SECRET = "ENwiHgNMEhD0PBUWFhQBlB81gmv3Jm64nO_uh54hfwCDV_aMS0msqPi77Y4sfR-gkLIDT4VRrQ3a9s4Y";
    private static final String MODE = "sandbox";

    public String authorizePayment(String total)
            throws PayPalRESTException {

        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(total);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);

    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("Son")
                .setLastName("TranBa")
                .setEmail("tranbason241-buyer@gmail.com");

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8084/Assignment1_TranBaSon/cancel.jsp");
        redirectUrls.setReturnUrl("http://localhost:8084/Assignment1_TranBaSon/review_payment");

        return redirectUrls;
    }

    private List<Transaction> getTransactionInformation(String total) {
        Details details = new Details();
       // details.setShipping(orderDetail.getShipping());
        details.setSubtotal(total);
        //details.setTax(orderDetail.getTax());

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(total);
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
       // transaction.setDescription(orderDetail.getProductName());

//        ItemList itemList = new ItemList();
//        List<Item> items = new ArrayList<>();
//
//        Item item = new Item();
//        item.setCurrency("USD");
//        item.setName(orderDetail.getProductName());
//        item.setPrice(orderDetail.getSubtotal());
//        item.setTax(orderDetail.getTax());
//        item.setQuantity("1");

//        items.add(item);
//        itemList.setItems(items);
//        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        return payment.execute(apiContext, paymentExecution);
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

}
