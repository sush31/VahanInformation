/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.Serializable;

public class DropDownDobj implements Serializable{

    private String state_cd;
    private String rto_cd;
    private String rto_descr;
    private boolean tax;
    private boolean isto;
    private boolean ca;
    private boolean duprc;
    private boolean hpa;
    private boolean hpt;
    private boolean noc;
    private boolean fit;
    private boolean prmt;
    private boolean doc;
    private boolean dupFit;
    private boolean renReg;
    private boolean vehAlt;
    private boolean vehConv;
    private boolean vehReAssign;
    private boolean checkbox;
    private boolean vehCart;
    private boolean rcPerticuler;
    private boolean mobileReg;
    private boolean epermit;
    private boolean fpermit;
    private boolean spermit;
    private boolean tpermit;
    private boolean authpermit;
    //  private String Rto_name;

    /**
     * @return the state_cd
     */
    public String getState_cd() {
        return state_cd;
    }

    /**
     * @param state_cd the state_cd to set
     */
    public void setState_cd(String state_cd) {
        this.state_cd = state_cd;
    }

    /**
     * @return the rto_cd
     */
    public String getRto_cd() {
        return rto_cd;
    }

    /**
     * @param rto_cd the rto_cd to set
     */
    public void setRto_cd(String rto_cd) {
        this.rto_cd = rto_cd;
    }

    /**
     * @return the tax
     */
    public boolean isTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(boolean tax) {
        this.tax = tax;
    }

    /**
     * @return the isto
     */
    public boolean isIsto() {
        return isto;
    }

    /**
     * @param isto the isto to set
     */
    public void setIsto(boolean isto) {
        this.isto = isto;
    }

    /**
     * @return the ca
     */
    public boolean isCa() {
        return ca;
    }

    /**
     * @param ca the ca to set
     */
    public void setCa(boolean ca) {
        this.ca = ca;
    }

    /**
     * @return the duprc
     */
    public boolean isDuprc() {
        return duprc;
    }

    /**
     * @param duprc the duprc to set
     */
    public void setDuprc(boolean duprc) {
        this.duprc = duprc;
    }

    /**
     * @return the hpa
     */
    public boolean isHpa() {
        return hpa;
    }

    /**
     * @param hpa the hpa to set
     */
    public void setHpa(boolean hpa) {
        this.hpa = hpa;
    }

    /**
     * @return the hpt
     */
    public boolean isHpt() {
        return hpt;
    }

    /**
     * @param hpt the hpt to set
     */
    public void setHpt(boolean hpt) {
        this.hpt = hpt;
    }

    /**
     * @return the NOC
     */
    public boolean isNoc() {
        return noc;
    }

    /**
     * @param NOC the NOC to set
     */
    public void setNoc(boolean noc) {
        this.noc = noc;
    }

//    public String getRto_name() {
//        return Rto_name;
//    }
//
//    public void setRto_name(String Rto_name) {
//        this.Rto_name = Rto_name;
//    }
    public String getRto_descr() {
        return rto_descr;
    }

    public void setRto_descr(String rto_descr) {
        this.rto_descr = rto_descr;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public boolean isFit() {
        return fit;
    }

    public void setFit(boolean fit) {
        this.fit = fit;
    }

    public boolean isPrmt() {
        return prmt;
    }

    public void setPrmt(boolean prmt) {
        this.prmt = prmt;
    }

    public boolean isDoc() {
        return doc;
    }

    public void setDoc(boolean doc) {
        this.doc = doc;
    }

    public boolean isDupFit() {
        return dupFit;
    }

    public void setDupFit(boolean dupFit) {
        this.dupFit = dupFit;
    }

    public boolean isRenReg() {
        return renReg;
    }

    public void setRenReg(boolean renReg) {
        this.renReg = renReg;
    }

    public boolean isVehAlt() {
        return vehAlt;
    }

    public void setVehAlt(boolean vehAlt) {
        this.vehAlt = vehAlt;
    }

    public boolean isVehConv() {
        return vehConv;
    }

    public void setVehConv(boolean vehConv) {
        this.vehConv = vehConv;
    }

    public boolean isVehReAssign() {
        return vehReAssign;
    }

    public void setVehReAssign(boolean vehReAssign) {
        this.vehReAssign = vehReAssign;
    }

    public boolean isVehCart() {
        return vehCart;
    }

    public void setVehCart(boolean vehCart) {
        this.vehCart = vehCart;
    }

    public boolean isRcPerticuler() {
        return rcPerticuler;
    }

    public void setRcPerticuler(boolean rcPerticuler) {
        this.rcPerticuler = rcPerticuler;
    }

    public boolean isMobileReg() {
        return mobileReg;
    }

    public void setMobileReg(boolean mobileReg) {
        this.mobileReg = mobileReg;
    }

    public boolean isEpermit() {
        return epermit;
    }

    public void setEpermit(boolean epermit) {
        this.epermit = epermit;
    }

    public boolean isFpermit() {
        return fpermit;
    }

    public void setFpermit(boolean fpermit) {
        this.fpermit = fpermit;
    }

    public boolean isSpermit() {
        return spermit;
    }

    public void setSpermit(boolean spermit) {
        this.spermit = spermit;
    }

    public boolean isTpermit() {
        return tpermit;
    }

    public void setTpermit(boolean tpermit) {
        this.tpermit = tpermit;
    }

    public boolean isAuthpermit() {
        return authpermit;
    }

    public void setAuthpermit(boolean authpermit) {
        this.authpermit = authpermit;
    }
}
