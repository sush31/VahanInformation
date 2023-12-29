/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author tranC095
 */
public class MasterTables implements Serializable{
    /*
     * Master Table Read-Only Objects
     */
    //.........................................
    // CODE/DESCR based master tables
    //.........................................

    public MasterTableDO VM_AREA_MAST;
    public MasterTableDO TM_BANK;
    public MasterTableDO TM_USER_CATG;
    public MasterTableDO TM_DISTRICT;
    public MasterTableDO VM_VH_CLASS;
    public MasterTableDO VM_VCH_CATG;
    public MasterTableDO VM_VHCLASS_CATG_MAP;
    public MasterTableDO TM_STATE;
    public MasterTableDO TM_TALUK;
    public MasterTableDO TM_VILLAGE;
    public MasterTableDO VM_OWCODE;
    public MasterTableDO VM_REGN_TYPE;
    public MasterTableDO VM_FUEL;
    public MasterTableDO VM_OWCATG;
    public MasterTableDO VM_ICCODE;
    public MasterTableDO VM_INSTYP;
    public MasterTableDO VM_MAKER;
    public MasterTableDO VM_MODELS;
    public MasterTableDO VM_SEAT_TYPE;
    public MasterTableDO VM_FIT_OFFICER;
    public MasterTableDO VM_TAX_SLAB_FIELDS;
    public MasterTableDO VM_HP_TYPE;
    public MasterTableDO VM_NORMS;
    public MasterTableDO VM_TAX_MODE;
    public MasterTableDO VM_OTHER_CRITERIA;
    public MasterTableDO TM_OFFICE;
    public MasterTableDO TM_DESIGNATION;
    public MasterTableDO VM_DOMAIN;
    public MasterTableDO VM_PMT_CATEGORY;
    public MasterTableDO VM_ROUTE_MASTER;
    public MasterTableDO VM_PMT_MAST;
    public MasterTableDO TM_PURPOSE_MAST;
    public MasterTableDO vm_service_type;
    public MasterTableDO VM_PERMIT_TYPE;
    public MasterTableDO TM_ROLE;
    public MasterTableDO TM_EMP;
    public MasterTableDO TM_USER_INFO;
    public MasterTableDO VM_HSRP_FLAG;
    public MasterTableDO VM_REASON;
//Added By Nitin :25-feb-2015
    public MasterTableDO VM_COURT;
    public MasterTableDO VM_ACCUSED;
    public MasterTableDO VM_CHALLAN_BOOK;
    public MasterTableDO VM_DA;
    public MasterTableDO VM_DA_PENALTY;
    public MasterTableDO VM_DOCUMENTS;
    public MasterTableDO VM_EXCESS_SCHEDULE;
    public MasterTableDO VM_OFFENCE_PENALTY;
    public MasterTableDO VM_OFFENCES;
    public MasterTableDO VM_OVERLOAD_SCHEDULE;
    public MasterTableDO VM_SECTION;
    public MasterTableDO VM_PERMIT_DOCUMENTS;
    public MasterTableDO VM_PERMIT_OBJECTION;
    //Added By Nitin :25-feb-2015
    public MasterTableDO TM_INSTRUMENTS;//added by nitin
    // Added By Afzal : 03 Feb-2015
    //public MasterTableDO VM_PERMIT_TYPE;
    public MasterTableDO VM_REGION;
    public MasterTableDO TM_COUNTRY;
    public MasterTableDO VM_BLACKLIST;
    //added by vinay: 07March2016
    public MasterTableDO VM_DOC;
    //.............................................................
    // For internal use to store the individual references so that
    // common methods can be called in a loop
    //.............................................................
    private Vector masterTableDOs;
    private Vector masterTableDOsEditor;
    private Vector masterTableDOsEditor1;

    /**
     * Once the master table DO are created, call this method to fill the
     * masterTableDOs.
     *
     */
    public void fillMasterTableVector() {
        masterTableDOs = new Vector();
        //..........................................................
        // Add the master table references in the masterTableDOs
        //..........................................................
        masterTableDOs.add(TM_BANK);
        masterTableDOs.add(TM_DISTRICT);
        masterTableDOs.add(TM_STATE);
        masterTableDOs.add(TM_TALUK);
        masterTableDOs.add(TM_VILLAGE);
        masterTableDOs.add(VM_OWCODE);
        masterTableDOs.add(VM_REGN_TYPE);
        masterTableDOs.add(VM_FUEL);
        masterTableDOs.add(VM_OWCATG);
        masterTableDOs.add(VM_ICCODE);
        masterTableDOs.add(VM_INSTYP);
        masterTableDOs.add(VM_MAKER);
        masterTableDOs.add(VM_MODELS);
        masterTableDOs.add(VM_SEAT_TYPE);
        masterTableDOs.add(VM_FIT_OFFICER);
        masterTableDOs.add(VM_TAX_SLAB_FIELDS);
        masterTableDOs.add(VM_HP_TYPE);
        masterTableDOs.add(VM_NORMS);
        masterTableDOs.add(VM_TAX_MODE);
        masterTableDOs.add(VM_OTHER_CRITERIA);
        // Added by Prashant : 31-10-2014
        masterTableDOs.add(TM_PURPOSE_MAST);
        masterTableDOs.add(VM_PMT_MAST);
        masterTableDOs.add(VM_DOMAIN);
        masterTableDOs.add(VM_PMT_CATEGORY);
        masterTableDOs.add(TM_INSTRUMENTS);
        masterTableDOs.add(TM_OFFICE);
        masterTableDOs.add(TM_DESIGNATION);
        masterTableDOs.add(VM_PERMIT_TYPE);
        masterTableDOs.add(VM_REGION);
        masterTableDOs.add(TM_ROLE);
        masterTableDOs.add(TM_EMP);
        masterTableDOs.add(TM_USER_CATG);
        masterTableDOs.add(VM_HSRP_FLAG);
        masterTableDOs.add(VM_REASON);
    }

    public void fillMasterTableVectorEditor() {
        masterTableDOsEditor = new Vector();
        //..........................................................
        // Add the master table references in the masterTableDOs
        //..........................................................

        masterTableDOsEditor.add(VM_OWCODE);
        masterTableDOsEditor.add(VM_REGN_TYPE);
        masterTableDOsEditor.add(VM_FUEL);
        masterTableDOsEditor.add(VM_OWCATG);
        masterTableDOsEditor.add(VM_ICCODE);
        masterTableDOsEditor.add(VM_INSTYP);
        masterTableDOsEditor.add(VM_MAKER);
        masterTableDOsEditor.add(VM_MODELS);
        masterTableDOsEditor.add(VM_SEAT_TYPE);

    }

    public void fillmastertablevectoreditor2() {
        masterTableDOsEditor1 = new Vector();
        masterTableDOsEditor1.add(VM_CHALLAN_BOOK);
        masterTableDOsEditor1.add(VM_COURT);
        masterTableDOsEditor1.add(VM_DA);
        masterTableDOsEditor1.add(VM_DA_PENALTY);
        masterTableDOsEditor1.add(VM_DOCUMENTS);
        masterTableDOsEditor1.add(VM_EXCESS_SCHEDULE);
        masterTableDOsEditor1.add(VM_OFFENCES);
        masterTableDOsEditor1.add(VM_OFFENCE_PENALTY);
        masterTableDOsEditor1.add(VM_SECTION);
        masterTableDOsEditor1.add(VM_OVERLOAD_SCHEDULE);

    }

    /**
     * Returns the master table list
     *
     * @return Vector containing master table DOs.
     */
    public Vector getMasterTables() {
        return masterTableDOs;
    }

    public Vector getMasterTablesEditor() {
        return masterTableDOsEditor;
    }

    public Vector getMasterTableDOsEditor1() {
        return masterTableDOsEditor1;
    }

    /**
     * Dump. Prints the contents of the Master Table Objects.
     */
    
}
