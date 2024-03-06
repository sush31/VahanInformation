package databaseconnection;
///////////////////////////////////////////////////////////////////////////////

// CLASS            : TableConstants
// PURPOSE          : Defines constants specific to database table structure.
// NOTES            : None
// LAST MODIFIED    :

//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
public interface TableConstants {

	final static String BLANK_STRING = "";
	final static String NA_STRING = "N/A";
	final static String BACKTOHOME = "back";
	final static String BACKTOLOGGEDINHOME = "backTOlogin";

	String YEARLY = "Yearly";
	String QUARTERLY = "Quarterly";
	String MONTHLY = "Monthly";
	String HALFYEARLY = "Half Yearly";
	String LIFETIME = "L";
	String ONETIME = "O";
	/**
	 * Fee code for issue of issue of NOC
	 */
	int VM_TRANSACTION_MAST_NOC = 9;
	int USER_ROLE_CASHIER = 9;
	int VM_TRANSACTION_MAST_NOC_CANCEL = 91;
	String VT_NOC_ISSUE_STATUS = "N";
	String VT_RC_SURRENDER_STATUS = "S";
	String VT_RC_RELEASE_STATUS = "Y";
	String VT_RC_CANCEL_STATUS = "C";
	int VM_TRANSACTION_MAST_MISC = 48;
	int VM_TRANSACTION_MAST_DIFF_FEE = 85;
	int VM_TRANSACTION_MAST_DIFF_TAX = 86;
	int VM_TRANSACTION_CONVERSION_PAPER_RC_TO_SMARTCARD=88;
	int VM_TRANSACTION_ADD_MODIFY_NOMINEE=109;
	int VM_TRANSACTION_NOT_TO_BE_TRANSACTED=178;
	int VM_TRANSACTION_OWNER_VEHICLE_DETAILS_EDIT=101;
	/**
	 * Fee code for issue of fitness certificate
	 */
	int VM_TRANSACTION_MAST_FIT_CERT = 2;
	int VM_TRANSACTION_MAST_NEW_VEHICLE_FITNESS=122;
	int VM_TRANSACTION_MAST_DUP_HSRP=126;
	int VM_VEHICLE_INSPECTION_FEE = 202;
	/**
	 * Fee code for New vehicle
	 */
	int VM_TRANSACTION_MAST_NEW_VEHICLE = 1;
	int VM_TRANSACTION_MAST_ADDL_TO_VEHICLE = 96;
	int VM_TRANSACTION_MAST_DEALER_NEW_VEHICLE = 123;
	int VM_TRANSACTION_MAST_DEALER_NEW_TEMP_VEHICLE=124;
	int TM_ROLE_DEALER_NEW_APPL = 12304;
	int TM_ROLE_DEALER_NEW_REGISTRATION_FEE = 12305;
	int TM_ROLE_DEALER = 12305;
	int TM_NEW_RC_VERIFICATION = 10005;
	int TM_NEW_RC_FITNESS_INSPECTION = 10001;
	int TM_NEW_RC_APPROVAL = 10006;
	String User_Dealer = "B";
	String REGN_TYPECODE = "N";
	/**
	 * Fee code for Renewal of registration
	 */
	int VM_TRANSACTION_MAST_REN_REG = 15;
	/**
	 * Fee code for Reassignment of registration number
	 */
	int VM_TRANSACTION_MAST_REASSIGN_REGN_NO = 17;
	/**
	 * Fee code for duplicate RC
	 */
	int VM_TRANSACTION_MAST_DUP_RC = 3;
	int VM_TRANSACTION_MAST_TO = 5;
	/**
	 * Fee code for addition of hypothecation
	 */
	int VM_TRANSACTION_MAST_ADD_HYPO = 6;
	/**
	 * Fee code for removal of hypothecation
	 */
	int VM_TRANSACTION_MAST_REM_HYPO = 7;
	/**
	 * Fee code for continution of hypothecation
	 */
	int VM_TRANSACTION_MAST_HPC = 8;
	/**
	 * Fee code for change of address
	 */
	int VM_TRANSACTION_MAST_CHG_ADD = 4;
	/**
	 * Fee code for Vehicle Conversion
	 */
	int VM_TRANSACTION_MAST_VEH_CONVERSION = 11;
	/**
	 * Fee code for vehicle alteration
	 */
	int VM_TRANSACTION_MAST_VEH_ALTER = 16;
	int VM_TRANSACTION_MAST_VEH_ALTER_ENTRY = 16004;
	int VM_TRANSACTION_MAST_VEH_ALTER_VERIFY = 16005;
	int VM_TRANSACTION_MAST_VEH_ALTER_APPROVE = 16006;
	/**
	 * Trade certificate module constants
	 */
	// added bY nitin
	int VM_TRANSACTION_MAST_RC_PARTICULARS_FOR_OFFICE_PURPOSE = 73;
	int VM_TRANSACTION_MAST_RC_PARTICULARS = 13;
	int VM_TRANSACTION_TRADE_CERT_NEW = 21;
	int TM_ROLE_TRADE_CERT_APPL_ENTRY = 21004;
	int TM_ROLE_TRADE_CERT_FEE = 21003;
	int TM_ROLE_TRADE_CERT_VERIFICATION = 21005;
	int TM_ROLE_TRADE_CERT_APPROVE = 21006;
	int TM_ROLE_TRADE_CERT_PRINT = 21007;
	/**
	 * temp registration
	 */
	int VM_TRANSACTION_MAST_TEMP_REG = 18;
	int VM_TRANSACTION_MAST_DUP_FC = 14;
	/**
	 * Fresh Rc In The Name Of Financer
	 */
	int VM_TRANSACTION_MAST_FRESH_RC = 12;
	/* purpose code for Choice no */
	int VM_TRANSACTION_MAST_CHOICE_NO = 95;
	int VM_TRANSACTION_MAST_USER_CHARGES = 99;

	/**
	 * TEMP REG flag for VT_VEH_CATG
	 */
	String VT_VEH_CATG_TEMP_REG = "T";
	/**
	 * Vehicle Registration type New
	 */
	String VM_REGN_TYPE_NEW = "N";
	String VM_REGN_TYPE_TEMPORARY = "T";
	String VM_REGN_TYPE_EXARMY = "A";
	String VM_REGN_TYPE_OTHER_STATE_VEHICLE = "O";
	String VM_REGN_TYPE_IMPORTED_YES = "Y";
	int VM_FUEL_CNG_TYPE = 3;
	int VM_FUEL_TYPE_PETROL_CNG = 6;
	int VM_FUEL_TYPE_ELECTRIC = 4;
	int VM_FUEL_TYPE_DIESEL = 2;
	int VM_VEHTYPE_TRANSPORT = 1;

	String VM_VEHTYPE_TRANSPORT_DESCR = "Transport";
	String VM_VEHTYPE_NON_TRANSPORT_DESCR = "Non-Transport";
	int VM_VEHTYPE_NON_TRANSPORT = 2;
	int VM_VEHTYPE_ALL = 3;
	String VIEW_OWNER = "vv_owner";
	String TM_PAYMENT_MODE_CASH = "C";
	int OWNER_CODE_INDIVIDUAL = 1;
	int OWNER_CODE_FIRM = 2;
	int JOINT_STOCK_COMPANY = 3;
	int OWNER_CATG_OTHERS = 6;
	// String NATIONAL_PERMIT_TYPE = "106";
	String All_India_Tourist_Permit = "103";
	String GOODS_PERMIT = "105";
	String NATIONAL_PERMIT = "106";
	String AITP = "103";
	String CC_PERMIT = "102";
	int VM_FUEL_TYPE_LPG = 8;
	int VM_FUEL_TYPE_PETROL_LPG = 5;
	int VM_PARKING_FEE_PUR_CD = 90;
	int VM_PARK_EXEMPTION_PUR_CD = 353;
	int VM_SWAPPING_REGN_MARK = 10;
	/**
	 * * Roles
	 *
	 */
	// added By Nitin Kumar 12-05-2015
	int VM_MAST_RC_SURRENDER = 65;
	int VM_MAST_RC_RELEASE = 66;
	int VM_MAST_RC_CANCELLATION = 61;
	int VM_ROLE_RC_SURRENDER_ENTRY = 65004;
	int VM_ROLE_RC_SURRENDER_VERIFICATION = 65005;
	int VM_ROLE_RC_SURRENDER_APPROVAL = 65006;
	int VM_ROLE_RC_RELEASE_ENTRY = 66004;
	int VM_ROLE_RC_RELEASE_VERIFICATION = 66005;
	int VM_ROLE_RC_RELEASE_APPROVAL = 66006;
	int VM_ROLE_RC_CANCELLATION_ENTRY = 61004;
	int VM_ROLE_RC_CANCELLATION_VERIFICATION = 61005;
	int VM_ROLE_RC_CANCELLATION_APPROVAL = 61006;
	// added By Nitin Kumar 12-05-2015
	int VM_MAST_ENFORCEMENT = 111;
	int VM_MAST_ENFORCEMENT_DISPOSE = 112;
	int VM_MAST_ENFORCEMENT_RELEASE_DOC_VEHICLE = 113;
	int VM_MAST_ENFORCEMENT_FEE_TAX = 999;
	int VM_ROLE_ENFORCEMENT_ENTRY = 11104;
	int VM_ROLE_ENFORCEMENT_VERIFY = 11105;
	int VM_ROLE_ENFORCEMENT_APPROVE = 11106;
	int VM_ROLE_ENFORCEMENT_DISPOSE_ENTRY = 11204;
	int VM_ROLE_ENFORCEMENT_DISPOSE_VERIFY = 11205;
	int VM_ROLE_ENFORCEMENT_DISPOSE_APPROVE = 11206;
	int VM_ROLE_ENFORCEMENT_RELEASE_DOC_VEHICLE_ENTRY = 11304;
	int VM_ROLE_ENFORCEMENT_RELEASE_DOC_VEHICLE_VERIFY = 11305;
	int VM_ROLE_ENFORCEMENT_RELEASE_DOC_VEHICLE_APPROVE = 11306;
	int VM_ROLE_ENFORCEMENT_SETTLMENT_ENTRY = 11404;
	int VM_ROLE_ENFORCEMENT_SETTLMENT_VERIFY = 11405;
	int VM_ROLE_ENFORCEMENT_SETTLMENT_APPROVE = 11406;
	int VM_ROLE_ENFORCEMENT_FEE_TAX = 99996;
	int VM_ROLE_ENFORCEMENT_CHALLAN_REFER_TO_COURT = 11504;
	int TM_ROLE_INSPECTION = 01;
	int TM_ROLE_RANDOM_MARK_GENERATION = 02;
	int TM_ROLE_RANDOM_MARK_ACTION_CD = 10002;
	int TM_ROLE_PRINT_RC = 10007;
	int TM_ROLE_FANCY_NUMBER_FEE = 95001;
	int TM_ROLE_FANCY_NUMBER_ENTRY = 95004;
	int TM_ROLE_FANCY_NUMBER_VERIFY = 95005;
	int TM_ROLE_FANCY_NUMBER_APPROVE = 95006;
	int TM_ROLE_TAX_SLABS_ENTRY = 66661;
	int TM_ROLE_FEE_SLABS_ENTRY = 66662;
	int TM_ROLE_SCRUTINY_E_PAY = 03;
	int TM_ROLE_ENTRY = 04;
	int TM_ROLE_VERIFICATION = 05;
	int TM_ROLE_APPROVAL = 06;
	int TM_ROLE_PRINT = 07;
	int TM_ROLE_DISPATCH = 88;
	int TM_ROLE_NEW_APPL = 88881;
	int TM_ROLE_NEW_APPL_TEMP = 88882;
	int TM_ROLE_APPLICATION_INWARD = 88885;
	String TM_ROLE_NUMBER_ALLOCATION = "11";
	String STATUS_CANCEL = "R";
	String STATUS_COMPLETE = "C";
	String STATUS_NOT_STARTED = "N";
	String STATUS_REVERT = "M";
	String STATUS_DISPATCH_PENDING = "I";
	String STATUS_APPROVED = "A";
	String BACKWARD = "B";
	String FORWARD = "F";
	String DISPOSE = "D";
	int VEH_TYPE_GOVT_UNDERTAKING = 6;
	int VEH_TYPE_POLICE_DEPT = 15;
	int VEH_TYPE_STATE_TRANS_DEPT = 16;
	int VM_COURT_FEES = 74;
	/**
	 * Road tax code
	 *
	 */
	int TM_PASS_TAX = 54;
	int TM_ROAD_TAX = 58;
	int TM_ADDN_ROAD_TAX = 59;
	String TM_ROAD_TAX_DESC = "Road Tax";
	String TM_ADDN_ROAD_TAX_DESC = "Add Road tax";

	String FitnessResultPass = "Y";
	String FitnessResultFail = "N";
	/*
	 * Flags For Fancy Numbers
	 */
	String FANCY_REJECTED = "R";
	String FANCY_ACCEPTED = "A";
	String FANCY_PAID = "P";
	int INITIAL_FLOW_WS_URL = 1;
	int NEXT_FLOW_WS_URL = 2;
	String RANDOM_NUMBER_STATUS_P = "P";
	String RANDOM_NUMBER_STATUS_A = "A";
	String RANDOM_NUMBER_STATUS_M = "M";
	String STATE_CD = "KL";
	boolean isNextStageWebService = false;
	// Permit related Constants
	// String VM_INSURANCE_CHECK =
	// ",25,26,41,27,28,29,31,32,33,35,34,36,37,39,53,";//counter
	int VM_PMT_APPLICATION_PUR_CD = 25;
	int VM_PMT_FRESH_PUR_CD = 26;
	int VM_PMT_RENEWAL_TEMP_PUR_CD = 53;
	int VM_PMT_WHOLE_SURRENDER_PUR_CD = 41;
	int VM_PMT_RENEWAL_PUR_CD = 27;
	int VM_PMT_TRANSFER_PUR_CD = 28;
	int VM_PMT_TRANSFER_DEATH_CASE_PUR_CD = 29;
	int VM_PMT_VARIATION_ENDORSEMENT_PUR_CD = 31;
	int VM_PMT_CA_PUR_CD = 32;
	int VM_PMT_CANCELATION_PUR_CD = 33;
	int VM_PMT_DUPLICATE_PUR_CD = 34;
	int VM_PMT_TEMP_PUR_CD = 35;
	int VM_PMT_SPECIAL_PUR_CD = 36;
	int VM_PMT_HOME_AUTH_PERMIT_PUR_CD = 37;
	int VM_FITNESS_REVOCATION=354;
	int VM_FITNESS_EXEMPTION=351;
	int VM_FITNESS_CANCELLATION=352;
	int VM_RETENTION_OF_REGN_NO=331;
	int VM_NONUSE_RESTORE_REMOVE=367;

	int VM_PMT_COUNTER_SIGNATURE_AFTER_AUTH = 38;
	int VM_PMT_AUTH_COUNTER_SIGNATURE_PUR_CD = 412;
	int VM_PMT_RENEWAL_HOME_AUTH_PERMIT_PUR_CD = 39;
	int VM_PMT_SURRENDER_PUR_CD = 41;
	int VM_PMT_RESTORE_PUR_CD = 42;
	int VM_PMT_REPLACE_VEH_PUR_CD = 43;
	int VM_PMT_TRANSFER_REPLACE_VEH_PUR_CD = 45;
	int VM_PMT_PERMANENT_SURRENDER_PUR_CD = 46;
	int VM_PMT_EXEMPTION_PUR_CD = 47;
	int VM_PMT_RE_ASSIGMENT_PUR_CD = 49;
	int VM_PMT_SMART_CARD_FEE = 50;
	int VM_PMT_RENEW_TEMP_PUR_CD = 53;
	int VM_PMT_DUP_COUNTER_SIGNATURE = 407;
	int VM_PMT_SUR_COUNTER_SIGNATURE = 338;
	int VM_CANCEL_COUNTER_SIGNATURE_PERMIT = 338;
	int TM_ROLE_PMT_APPL = 88884;
	int TM_ROLE_PMT_APPL_FEE = 99995;
	int TM_ROLE_PMT_TEMP_FEE = 99998;
	int TM_ROLE_PMT_SPECIAL_FEE = 99979;
	int TM_ROLE_PMT_SCRUTINY = 26004;
	// int TM_ROLE_PMT_VERIFICATION = 26005;
	int TM_ROLE_PMT_FRESH_VERIFICATION = 26005;
	int TM_ROLE_PMT_APPROVE = 26006;
	int TM_ROLE_PMT_RENWAL_SCRUTINY = 27004;
	int TM_ROLE_PMT_RENWAL_VERIFICATION = 27005;
	int TM_ROLE_PMT_RENWAL_VERIFICATION_ONLINE = 44447;
	int TM_ROLE_PMT_RENWAL_APPROVE = 27006;
	int TM_ROLE_PMT_CANCEL_VERIFICATION = 33005;
	int TM_ROLE_PMT_CANCEL_APPROVAL = 33006;
	int TM_PMT_CANCEL_VEHICAL_PRINT = 33007;
	int TM_ROLE_PMT_HOME_AUTH_RENEW_ENTRY = 39004;
	int TM_ROLE_PMT_HOME_AUTH_RENEW_VERIFICATION = 39005;
	int TM_ROLE_PMT_HOME_AUTH_RENEW_APPROVAL = 39006;
	int TM_ROLE_PMT_HOME_AUTH_RENEW_PRINT = 39007;
	int TM_ROLE_PMT_SURRENDER_ENTRY = 41004;
	int TM_ROLE_PMT_SURRENDER_VERIFICATION = 41005;
	int TM_ROLE_PMT_SURRENDER_APPROVAL = 41006;
	int TM_ROLE_PMT_SURRENDER_PRINT = 41007;
	int TM_ROLE_PMT_RESTORE_ENTRY = 42004;
	int TM_ROLE_PMT_RESTORE_VERIFICATION = 42005;
	int TM_ROLE_PMT_RESTORE_APPROVAL = 42006;
	int TM_ROLE_PMT_REPLACE_VEH_ENTRY = 43004;
	int TM_ROLE_PMT_REPLACE_VEH_VERIFICATION = 43005;
	int TM_ROLE_PMT_REPLACE_VEH_APPROVAL = 43006;
	int TM_ROLE_REGISTRATION_FEE = 99992;
	int TM_ROLE_PMT_FEE = 99989;
	int TM_ROLE_NEW_REGISTRATION_FEE = 99991;
	int TM_ROLE_TEMP_REGISTRATION_FEE = 99993;
	int TM_ROLE_TAX_COLLECTION = 99994;
	int TM_PAYTMENT_CASH = 4;
	int TM_ROLE_PMT_TEMP_APPL = 88887;
	int TM_ROLE_PMT_SPECIAL_APPL = 88879;
	int TM_ROLE_PMT_TEMP_VERIFICATION = 35005;
	int TM_ROLE_PMT_TEMP_APPROVAL = 35006;
	int TM_ROLE_PMT_SPECIAL_VERIFICATION = 36005;
	int TM_ROLE_PMT_SPECIAL_APPROVAL = 36006;
	int TM_ROLE_PMT_APPLICATION_ENTRY = 25004;
	int TM_ROLE_PMT_VARIATION_ENDORSEMENT_APPL = 31004;
	int TM_ROLE_PMT_SURRENDER_TRANSFER_VERIFICATION = 28005;
	int TM_ROLE_PMT_SURRENDER_TRANSFER_IN_DEATH_VERIFICATION = 29005;
	int TM_ROLE_PMT_CA_VERIFICATION = 32005;
	int TM_ROLE_PMT_SURRENDER_TRANSFER_APPROVE = 28006;
	int TM_ROLE_PMT_SURRENDER_TRANSFER_PRINT = 28007;
	int TM_ROLE_PMT_SURRENDER_TRANSFER_IN_DEATH_APPROVE = 29006;
	int TM_ROLE_PMT_SURRENDER_TRANSFER_OF_DEATH_PRINT = 29007;
	int TM_ROLE_PMT_CA_APPROVAL = 32006;
	int TM_ROLE_PMT_CA_PRINT = 32007;
	int TM_ROLE_PMT_RE_ASSIGMENT_VERIFICATION = 49005;
	int TM_ROLE_PMT_RE_ASSIGMENT_APPROVAL = 49006;
	int TM_ROLE_PMT_TO_REPLACE_VERIFICATION = 45005;
	int TM_ROLE_PMT_TO_REPLACE_APPROVAL = 45006;
	int TM_ROLE_PMT_PERMANENT_SURRENDER_VERIFICATION = 46005;
	int TM_ROLE_PMT_PERMANENT_SURRENDER_APPROVAL = 46006;
	// int TM_ROLE_PMT_APPLICATION_VERIFICATION = 25005;

	int TM_ROLE_PMT_APPLICATION_APPROVAL = 25006;
	int TM_ROLE_PMT_DUPL_APPLICATION = 88878;
	int TM_ROLE_PMT_DUPL_VERIFICATION = 44445;
	int TM_ROLE_PMT_DUPL_APPROVAL = 34006;
	int TM_PMT_APPL_PRINT = 25007;
	int TM_PMT_FRESH_PRINT = 26007;
	int TM_PMT_RENEWAL_PRINT = 27007;
	int TM_PMT_DUPLICATE_PRINT = 34007;
	int TM_PMT_TEMP_PRINT = 35007;
	int TM_PMT_SPECIAL_PRINT = 36007;
	int TM_PMT_RENEWAL_AUTH_PRINT = 36007;

	int TM_PMT_REPLACE_VEHICAL_PRINT = 43007;
	String TM_FEE_REG_TYPE = "RF";
	String TM_FEE_TAX_TYPE = "TX";
	String TM_FEE_PERMIT_TYPE = "PF";
	String TM_FEE_SERVICE_CHARGE_TYPE = "SF";
	String PAYMENT_INWARD_CONSTANT = "IB";
	String PAYMENT_MODE = "I";
	String HSRP_DUP_BOTH_SIDE = "DB";

	String HSRP_DUP_FRONT = "DF";
	String HSRP_DUP_REAR = "DR";
	String HSRP_NEW_BOTH_SIDE = "NB";
	String HSRP_OLD_BOTH_SIDE = "OB";
	String USER_CATG_PORTAL_ADMIN = "P";
	String USER_CATG_STATE_ADMIN = "S";
	String USER_CATG_OFFICE_ADMIN = "A";
	String USER_CATG_OFF_STAFF = "L";
	String USER_CATG_DEALER = "B";
	String USER_CATG_SMARTCARD = "X";
	String USER_CATG_HSRP = "Y";
	int TM_ROLE_CREATE_USER = -5;
	int TM_ROLE_ASSIGN_ROLE = -6;
	int TM_ROLE_CONV_VERIFICATION = 11005;
	int TM_ROLE_CONV_APPROVAL = 11006;
	int PRINT_REPORT = -2;
	String PRINT_FORM20_21_CURRENT_RECEIPT = "/ui/dealer/form_printDealerReports.xhtml?faces-redirect=true";
	String VAHAN_PGI_KEY = "VAHANPGI";
	String VAHAN_PGI_CONNTYPE = "VAHANPGI";
	String VAHAN_PGI_CHECK_FAIL_CONNTYPE = "VAHANPGI_VER";
	String NO_GEN_TYPE_SEQ = "S";
	String NO_GEN_TYPE_RAND = "R";
	String NO_GEN_TYPE_MIX = "M";
	String VAHAN_PGI_SUCCESS = "S";
	String VAHAN_PGI_FAIL = "F";
	String VAHAN_PGI_PENDING = "P";
	// User Accessibility Role Constants
	String PORTAL_ADMIN_P = "P";
	String STATE_ADMIN_S = "S";
	String OFFICE_ADMIN_A = "A";
	String OFFICE_STAFF_L = "L";
	String DEALER_STAFF_B = "B";
	String HSRP_VENDOR_Y = "Y";
	String SMART_VENDOR_X = "X";
	String INS_TYPE_NA = "4";
	int VEH_TYPE_GOVT = 4;
	int VEH_TYPE_STATE_GOVT = 5;
	int RCPT_UPPER_BOUND = 999999999;
	int RCPT_LOWER_BOUND = 1;
	String VAHAN_PGI_MERCHANT_CODE = "VAHAN4";
	String SERVICE_CHARGE_PER_RCPT = "R";
	String SERVICE_CHARGE_PER_TRANS = "T";
	String VM_PMT_TYPE_IN_TAX_CODE = "<25>";
	String VM_PMT_CATG_IN_TAX_CODE = "<28>";
	// addedbyVinit03-MAY-17
	int TM_GREEN_TAX = 69;
	// addedbyVinit05-june-17 //for audit
	public static final String MAIL_SERVER = "mail.nic.in";
	// public static final String USERNAME = "username@nic.in";
	public static final String USERNAME = "nic-odi";
	// public static final String PASSWORD = "password";
	public static final String PASSWORD = "*Odi13idO";
	public static final String USERNAME_CTAX = "username@nic.in";
	public static final String PASSWORD_CTAX = "password";
	int VM_TRANSACTION_TRADE_CERT_ONLINE_PAYMENT = 51;
	int TM_ROLE_TRADE_CERT_ONLINE_APPL_ENTRY = 51004;
	String MOVED_FROM_ONLINE_APPL_STATUS_YES = "Y";
	String SomthingWentWrong = "Something Went Wrong, Please Contact to the System Administrator.";
	int VM_DUPLICATE_TO_TAX_CARD = 72;

	String GCW_VEH_CLASS = ",63,90,84,";
	int VM_PMT_SURCHARG_FEE = 92;

	String permitOfferLetterActive = "A";
	// mukul
	int TM_ROLE_PMT_VARIATION_ENDORSEMENT_VERIFICATION = 31005;
	String freshPurposeWithActionCd = TableConstants.VM_PMT_FRESH_PUR_CD + "~"
			+ TableConstants.TM_ROLE_PMT_APPLICATION_ENTRY;
	String applPurposeWithActionCd = TableConstants.VM_PMT_APPLICATION_PUR_CD + "~"
			+ TableConstants.TM_ROLE_PMT_APPLICATION_ENTRY;
	String renewalPurposeWithActionCd = TableConstants.VM_PMT_RENEWAL_PUR_CD + "~"
			+ TableConstants.TM_ROLE_PMT_RENWAL_SCRUTINY;
	int TM_ROLE_PMT_APPLICATION_VERIFICATION = 33335;
	int TM_ROLE_PMT_VERIFICATION = 44445;
	String MOVED_FROM_ONLINE_APPL_STATUS_NO = "N";
	String NOT_VERIFIED_BY_USER = "NV";
	String VERIFIED_BY_USER = "V";
	String PAID_BY_USER = "A";
	int VM_PMT_DIFFERENTIAL_SPECIAL_TAX = 102;
	int VM_TRANSACTION_MAST_GREEN_TAX = 69;
	int TM_ROLE_PMT_COUNTER_SIGNATURE_ENTRY = 38004;
	int TM_ROLE_PMT_COUNTER_SIGNATURE_VERIFICATION = 44445;
	int TM_ROLE_PMT_COUNTER_SIGNATURE_APPROVAL = 99989;
	int TM_ROLE_PMT_COUNTER_SIGNATURE_APPROVAL_PRT_RTO = 41207;
	int TM_ROLE_PMT_DUP_COUNTER_SIGNATURE_VERIFICATION = 44445;
	int TM_ROLE_PMT_DUP_COUNTER_SIGNATURE_ENTRY = 40704;
	int TM_ROLE_PMT_SUR_COUNTER_SIGNATURE_ENTRY = 33804;
	int TM_ROLE_PMT_SUR_COUNTER_SIGNATURE_VERIFICATION = 44445;
	int VM_TRANSACTION_MAST_ENVIRONMENT_TAX = 346;
	/**
	 * Fee code for Fees with All transactions
	 */
	int VM_TRANSACTION_MAST_ALL = 100;
	int VM_POSTAL_FEES = 200;
	int flow_slno_at_entryPoint = 1;
	int flow_slno_at_verifyPoint = 2;
	int STAGE_CARRIAGE_PERMIT = 101;
	int CONTRACT_CARRIAGE_PERMIT = 102;
	int ALL_INDIA_TOURIST_PERMIT = 103;
	int PRIVATE_SERVICE_VEHICLE_PERMIT = 104;
	int GOODS_CARRIAGE_PERMIT = 105;
	int NATIONAL_PERMIT_TYPE = 106;
	String RESCTRICTED_STATES_FOR_VEHICLE_PERMISSION = ",UP,MH,UK,KA,";
	String PERMIT_PRINT_ACTIONS = "," + TM_PMT_FRESH_PRINT + "," + TM_PMT_RENEWAL_PRINT + "," + TM_PMT_SPECIAL_PRINT
			+ "," + TM_PMT_TEMP_PRINT + "," + TM_PMT_DUPLICATE_PRINT + "," + TM_ROLE_PMT_HOME_AUTH_RENEW_PRINT + ","
			+ TM_ROLE_PMT_SURRENDER_TRANSFER_OF_DEATH_PRINT + "," + TM_ROLE_PMT_SURRENDER_PRINT + ","
			+ TM_ROLE_PMT_SURRENDER_TRANSFER_PRINT + "," + TM_ROLE_PMT_CA_PRINT + "," + TM_PMT_CANCEL_VEHICAL_PRINT
			+ ",";
	String AUTO_APPROVAL_ACTIONS = "," + TM_ROLE_PMT_CANCEL_APPROVAL + "," + TM_ROLE_PMT_PERMANENT_SURRENDER_APPROVAL
			+ ",";
	public static final String IDCHECKPENDING = "777";
	String GOODS_TO_CARRY_DELIMETER = "::";
	int GOODS_TO_CARRY_MAXLENGTH = 80;
	int PRINT_FEE = 167;
	String TRANSPORT_CATEGORY_GOODS = "G";
	int PaperDocumentCharge = 454;
	int PERMIT_CATG_AUTO = 40;
	// int TM_ROLE_PMT_HOME_AUTH_RENEW_PRINT = 39007;
	int STA_OFFICE = 999;
	String PERMIT_DOC_TEMP_PMT = "6";
	String PERMIT_DOC_SPL_PMT = "7";
	String PERMIT_DOC_PART_A = "1";
	String PERMIT_DOC_PART_B = "2";
	String PERMIT_HOME_AUTH_DOC = "5";
	int NCR_AUTO = 28;
	int THREE_WHEELER_PASSENGER = 57;
	String NCR_CD = "20";
	int CITY_PERMIT = 1;
	int VIA_LENGTH_MP = 1200;
	int VIA_LENGTH = 250;
	int EDUCATIONAL_PERMIT = 14;
	int EDUCATIONAL_BUS_CITY_PERMIT = 15;
	String PROJECT_NAME = "PRMTR";
}
