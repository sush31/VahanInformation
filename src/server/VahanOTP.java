///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package nic.vahan.server;
//
//import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpSession;
//
///**
// * Provides OTP generation with time limit and OTP verification facility Also
// * facilitate re-send already generated OTP. OTP generated based on hash of
// * session and timestamp combination.
// *
// * @author vinay
// */
//public class VahanOTP {
//
//    HttpSession otpSession = null;
//    String secureOTP = null;
//
//    public String generateOTP() {
//        otpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        otpSession.
//        
//        return null;
//
//    }
//
//    public String reGenerateOTP() {
//        if (otpSession != null && !otpSession.isNew()) {
//            secureOTP = (String) otpSession.getAttribute("SecureOTP");
//            return secureOTP;
//        } else {
//            return null;
//        }
//    }
//    
//    protected String getHashOTP(HttpSession session){
//        
//        
//        return null;
//    }
//}
