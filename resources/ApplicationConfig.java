/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.swing.filechooser.FileSystemView;

import nic.java.util.CommonUtils;
import nic.rto.vahan.common.VahanException;
import nic.vahan.common.jsf.utils.validators.POSValidator;
import nic.vahan.server.ServerUtil;

public class ApplicationConfig implements Serializable {

	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ApplicationConfig.class);

	public static final File OWNER_INFO_FILES = getDirectory();
	public static final String STAGING_PARIVAHAN_URL = "https://staging.parivahan.gov.in";
	public static final String PARIVAHAN = "https://vahan.parivahan.gov.in";
	public static final String DOCS_SERVER_PATH = "/opt/OnlineAppDocs";
	// public static String DOCS_SERVER_PATH = "D:/OnlineAppDocs";
	public static final String VAHAN_PGI_CONNTYPE = "VAHANPGI";
	public static final String VAHAN_PGI_CHECK_FAIL_CONNTYPE = "VAHANPGI_VER";
	public static int ownerDtls = 0;
	public static int taxDtls = 1;
	public static int paymentDtls = 2;
	// receipt URLS
	public static final String URI_FEETAX_RCPT = "/vahan/ui/onlineReceipt/formFeeTaxRecieptPrintReport.xhtml";
	public static final String URI_FEE_RCPT = "/vahan/ui/eapplication/formFeeRecieptPrintReport.xhtml";
	public static final String URI_LOGIN = "/vahanservice/vahan/ui/usermgmt/login.xhtml";
	public static final String URI_TAX_RCPT = "/vahan/ui/eapplication/formOldVehReceiptReport.xhtml";
	public static final String URI_SRVC_RCPT = "/mobileservice/androidTaxService/taxReceipt";
	public static final String URI_CART_TAX_RCPT = "/vahan/ui/eapplication/formCartRecieptPrintReport.xhtml";
	public static final String URI_TRADE_FEE_RCPT = "/vahan/ui/tradeCertificate/formTradeCertificateFeeReceipt.xhtml";
	public static final String URI_CART_TAX_RCPT_NEW = "/vahan/ui/cartServices/formCartRecieptPrintReportNew.xhtml";
	// public static final String URI_PERMIT_RCPT =
	// "/vahan/ui/receiptforms/formPermitReceiptPrintReport.xhtml";

	public static final String URI_PERMIT_RCPT = "/vahan/ui/onlinepermitfees/formPermitReciept.xhtml";

	public static final String URI_BOOK_APPT = "http://164.100.78.110/appointment/vahan/ui/userpanel/bookappoinment.xhtml";
	public static final String URI_BOOK_APPT_LIVE = ApplicationConfig.PARIVAHAN
			+ "/appointment/vahan/ui/userpanel/bookappoinment.xhtml";
	public static final String URI_REPRINT_APPT = "http://164.100.78.110/appointment/vahan/ui/userpanel/form_ReprintReceipt.xhtml";
	public static final String URI_REPRINT_APPT_LIVE = ApplicationConfig.PARIVAHAN
			+ "/appointment/vahan/ui/userpanel/form_ReprintReceipt.xhtml";
	public static final String URI_BALANCE_FEE_RCPT = "/vahan/ui/eapplication/formBalanceFeeRecieptPrintReport.xhtml";
	public static final String UMANG_URI = "https://stgweb.umang.gov.in/uw/api/deptt/payTaxUrl";
	public static final String WEB_SER_URI = "http://164.100.78.110";
	// http://164.100.78.110/vahanserviceTC/
	public static final String WEB_TRED_CRI = "http://164.100.78.110/vahanserviceTC/";
	public static final String URI_TC_FEE_RCPT = "/vahan/ui/tradeCertificate/form_tc_fee_receipt.xhtml";
	// public static final String WEB_URL_SARTHI_LOI_TESTING =
	// "http://164.100.74.73:8080/sarathiservice/rsServices/sarathi/service/getDlinfoArray/";//old
	public static final String WEB_URL_SARTHI_LOI_TESTING = "http://164.100.94.236:8080/SarathiReport/rsServices/sarathi/service/getDlinfoArray/";
	// public static final String UPDATED_WEB_URL_SARTHI_LOI_LIVE =
	// "https://sarathi.parivahan.gov.in/sarathiReport/rsServices/sarathi/service/getDlinfoArray/";
	// //url

	// Done by Mukul on Monday 3-June-2019 for DL service
	public static final String UPDATED_WEB_URL_SARTHI_LOI_LIVE = "https://sarathi.parivahan.gov.in/SarathiReport/rsServices/sarathi/service/getDlinfoArray/";

	// addedByVinit24July
	public static final String URI_RESCHDULEBOOK_APPT = "http://164.100.78.110/appointment/vahan/ui/userpanel/form_rescheduleAppointment.xhtml";
	public static final String URI_RESCHDULEBOOK_APPT_LIVE = ApplicationConfig.PARIVAHAN
			+ "/appointment/vahan/ui/userpanel/form_rescheduleAppointment.xhtml";

	// application status fields
	public static final String APPL_REQUEST = "R";
	public static final String APPL_SUCCESSFUL_TRANS = "S";
	public static final String APPL_FAILED_TRANS = "F";
	public static final String APPL_MOVED_IN_VAHAN = "M";
	public static String USER_ADMIN = "ADMIN";
	public static String USER_GENERAL = "GENERAL";
	public static String USER_DEVELOPER = "DEVELOPER";
	// property file path
	public static final String propertyFile = "Config.properties";
	public static final String propertyFileForSMS = "smsMessage.properties";
	public static Properties properties = new Properties();
	public static Properties propertiesForSMSMsg = new Properties();
	public static final String PROP_PAYMENT_MODE = "vahanservice.payment.mode";
	public static final String PROP_ICICI_SERVICE_MODE = "vahanservice.icici.service.mode";
	public static final String PROP_PAYMENT_SCHEDULAR = "vahanservice.payment.schedular";
	public static final String PROP_KEY_MODE_ISTESTING = "istesting";
	public static final String PROP_KEY_PAYMENT_MODE_APPOINTMENTURL = "appointment_url";
	public static final String PROP_KEY_PAYMENT_SCHEDULAR_ENABLED = "enabled";
	public static final String PROP_KEY_PAYMENT_SCHEDULAR_SLEEPDURATION = "sleepduration";
	public static final String PROP_SMS_PENDING = "vahanservice.SMS.schedular";
	public static final String PROP_KEY_SMS_SCHEDULAR_ENABLED = "enabled1";
	public static final String PROP_DMS_URL_NEW_TRADE_CERT_APPL = "dms.url_new_trade_cert_appl";
	public static final String PROP_DMS_URL_EXISTNG_TRADE_CERT_APPL = "dms.url_existng_trade_cert_appl";
	public String propPrefix;
	// property file ens
	// fixed purpose codes
	public static final String UserNameForVTTempApplTrn = "ONLINEPERMITFEE";
	public static final int AADHAR_SEEDING = 992;

	public static final int PUR_RD_TAX = 58;
	public static final int PUR_ARD_TAX = 59;
	public static final int PUR_DC = 3;
	public static final int PUR_COA = 4;
	public static final int PUR_TO = 5;
	public static final int PUR_HPA = 6;
	public static final int PUR_HPT = 7;
	public static final int PUR_HPC = 8;
	public static final int PUR_NOC = 9;
	public static final int PUR_FRC = 12;
	public static final int PUR_SWP = 10;
	public static final int PUR_RC_PARTICULARS = 13;
	public static final int PUR_SMRT_CRD = 80;
	public static final int PUR_USER_CHARGE = 99;
	public static final int PUR_POSTAL_FEE = 200;
	public static final int PUR_ADDTO = 96;
	public static final int PUR_FITNESS = 2;
	public static final int PUR_PERMIT_APPL = 25;
	public static final int PUR_PERMIT_FEE = 26;
	public static final int PUR_PMT_RENEWAL_PUR_CD = 27;
	public static final int PUR_PMT_TRANSFER_PUR_CD = 28;
	public static final int PUR_PMT_TRANSFER_DEATH_CASE_PUR_CD = 29;
	public static final int PUR_PMT_VARIATION_ENDORSEMENT_PUR_CD = 31;
	public static final int PUR_PMT_CA_PUR_CD = 32;
	public static final int PUR_PMT_RENEWAL_TEMP_PUR_CD = 53; /// ADDED BY AARTI
	public static final int PUR_PMT_CANCELATION_PUR_CD = 33;
	public static final int PUR_PMT_DUPLICATE_PUR_CD = 34;
	public static final int VM_PMT_COUNTER_SIGNATURE_PUR_CD = 38;
	public static final int PUR_TEMP_PERMIT = 35;
	public static final int PUR_SPECIAL_PERMIT = 36;
	public static final int PUR_PMT_HOME_AUTH_PERMIT_PUR_CD = 37;
	public static final int PUR_REN_OF_PERMIT_AUTH_PUR_CD = 39;
	public static final int PUR_PMT_SURRENDER = 41;
	public static final int PUR_RESTORE_PERMIT = 42;
	public static final int PUR_E_PERMIT = 981;
	public static final int PUR_VM_VEHICLE_INSPECTION_FEE = 202;
	public static final int PUR_ENVIROMENTAL_TAX = 346;
	public static final int PUR_MULTIPLE_FEES = 950;
	public static final int PUR_CHKFPTRANS = 951;
	public static final int PUR_OFFLINE = 952;
	public static final int PUR_CONF_CART = 953;
	public static final int PUR_CART_PAYMENT = 954;
	public static final int PUR_KNOW_YOUR_VEHICLE_STATUS = 955;
	public static final int PUR_REPORT = 956;
	public static final int PUR_TRANSACTION_STATUS = 957;
	public static final int PUR_TRANSACTION = 980;
	public static final int PUR_USER_REG = 970;
	public static final int PUR_CHANGE_PASS = 971;
	public static final int PUR_CONFIGURE_ROLE = 972;
	public static final int PUR_CREATE_ROLE = 973;
	public static final int PUR_UPDATE_USER = 974;
	public static final int PUR_LOGIN = 975;
	// addedByVinit15July16
	// --ForDuplicate_FC
	public static final int PUR_DUPLICATE_FC = 14;
	public static final int PUR_REN_REGISTRATION = 15;
	public static final int PUR_ALTERATION_OF_VEH = 16;
	public static final int PUR_VEH_CONVERSION = 11;
	public static final int PUR_REASSIGN_VEH = 17;
	public static String VEH_TRANSPORT = "Transport";
	public static String VEH_NON_TRANSPORT = "Non-Transport";
	public static final String DELIMITERS = "###";
	public static final int PUR_UPDATE_INSURANCE = 990;
	public static final int PUR_DIFFERENCE_OF_FEE = 85;
	public static final String URI_QRCodeLiveForOfflinePay = "http://164.100.78.32/qrCodeV/Receipt?val=";

	public static final String APPL_BEFORE_PAYMENT = "BFP";
	public static final String APPL_AFTER_PAYMENT = "AFP";
	// Raj Bahadur
	public static final int PUR_RC_CANCEL = 61;
	public static final int PUR_RC_SURRENDER = 65;
	public static final int PUR_RC_RELEASE = 66;
	// TradeCertificate mukul
	public static final int PUR_NEW_RENEW_TC = 21;
	public static final int PUR_DUPLICATE_TC = 22;
	public static final int PUR_TRANSACTION_FEE = 100;
	public static final int PUR_TRADE_CERT_ONLINE_PAYMENT = 51;

	/// addedByVinit09-OCT-17
	// forOnlinePermit
	public static final String URI_Verification_permit_TESTING = "http://164.100.78.110/onlinepermit/vahan/ui/permit/formVerifiApplication.xhtml";
	public static final String URI_PAYFEES_permit_TESTING = "http://164.100.78.110/onlinepermit/vahan/ui/onlinepermitfees/formPermitFees.xhtml";
	public static final String URI_Verification_permit_LOCAL = "http://localhost:8080/onlinepermit/vahan/ui/permit/formVerifiApplication.xhtml";
	public static final String URI_PAYFEES_permit_LOCAL = "http://localhost:8080/onlinepermit/vahan/ui/onlinepermitfees/formPermitFees.xhtml";
	// public static final String URI_QRCodeVerifyTesting =
	// "http://164.100.78.110/qrCodeV/Receipt?val=";
	// public static final String URI_QRCodeVerifylive =
	// "http://164.100.78.32/qrCodeV/Receipt?val=";
	public static final String URI_QRCodeVerifyTesting = "https://staging.parivahan.gov.in/qrCodeV/Receipt?val=";
	public static final String URI_QRCodeVerifylive = "https://qr.parivahan.gov.in/qrCodeV/Receipt?val=";
	public static final String URI_QRCodeTestingForOfflinePay = "http://164.100.78.110/qrCodeV/Receipt?val=";
	
	public static final String URI_QRCodeProduction = "https://qr.parivahan.gov.in/vq/qr?v=";
	public static final String URI_QRCodeStaging = "https://staging.parivahan.gov.in/vq/qr?v=";
	
	public static final String PROP_ONLINE_PERMIT_LIVE = "online_permit.live_mode";
	public static final String PROP_ONLINE_PERMIT_TEST = "online_permit.test_mode";
	public static final String PROP_ONLINE_PERMIT_COMMON = "online_permit.common_mode";
	public static final String PROP_DMS_TOKEN_URL = "dms_token_url";
	public static final String PROP_KEY_DMS_URL_UPLOAD = "dms_url";
	public static final String PROP_KEY_DMS_UPLOAD_PARAMETER = "dms_url_parameter";
	public static final String PROP_KEY_DMS_UPLOAD_KEY = "dms_url_jKeyUpload";
	public static final String PROP_KEY_DMS_UPLOAD_SECURITY_KEY = "dms_url_jSecurityKeyUpload";

	public static final String PROP_KEY_DMS_URL_MODIFY_VIEW = "dms_url_modify_view";
	public static final String PROP_KEY_DMS_MODIFY_PARAMETER = "dms_url_modify_view_parameter";
	public static final String PROP_KEY_DMS_MODIFY_KEY = "dms_url_modify_view_jKeyModify";
	public static final String PROP_KEY_DMS_MODIFY_SECURITY_KEY = "dms_url_modify_jSecurityKeyModify";

	public static final String PROP_KEY_DMS_URL_PENDING = "dms_url_pending";
	public static final String PROP_KEY_DMS_PENDING_PARAMETER = "dms_url_pending_parameter";
	public static final String PROP_KEY_DMS_PENDING_KEY = "dms_url_pending_jKeyModify";
	public static final String PROP_KEY_DMS_PENDING_SECURITY_KEY = "dms_url_pending_jSecurityKeyPending";

	public static final String RECEIPT_NOTE = "receipt_note";
	public static final String SURRENDER_NOTE = "surrender_note";
	public static final String APPLICATION_NOTE = "application_note";
	public static final String CITIZEN_SERVICE_URL = "vahanCitizenUrl";
	public static final String AADHAAR_AUTHENTICATION = "A";
	public static final String AADHAAR_BIOM_AUTHENTICATION = "AB";
	public static final String MOBILE_AUTHENTICATION = "M";
	public static final String BIOMETRIC_AUTHENTICATION = "BM";
	public static final String CHALLANAPI_STAGING_URL = "https://staging.parivahan.gov.in/echallan/api/get-pending-challan";
	public static final String CHALLANAPI_PROD_URL = "https://echallan.parivahan.gov.in/api/get-pending-challan";

	public ApplicationConfig(String propPrefix) {
		this.propPrefix = propPrefix;
	}

	static {
		InputStream inputStream = null;
		InputStream inputStreamSms = null;
		try {
			inputStream = ApplicationConfig.class.getResourceAsStream(propertyFile);
			if (!properties.isEmpty()) {
				properties.load(inputStream);
			}
			inputStreamSms = ApplicationConfig.class.getResourceAsStream(propertyFileForSMS);
			if (!propertiesForSMSMsg.isEmpty()) {
				propertiesForSMSMsg.load(inputStreamSms);
			}
		} catch (NullPointerException p) {
			// p.printStackTrace();
			LOGGER.error(p);
		} catch (IOException io) {
			LOGGER.error(io);
		} finally {
			if (inputStream != null) {
				ServerUtil.safeClose(inputStream);
			}
		}
	}

	/**
	 * Provides the property value based on the property prefix and property
	 * name.
	 *
	 * @param propName
	 *            Define the property name preceeding with property perfix i.e
	 *            already define in ApplicationConfig.java
	 * @return property vale based on the given property name.
	 * @throws VahanException
	 *             in case of property file not found.
	 * @since 10Feb2015
	 */
	public String getProperties(String propName) throws VahanException {

		if (properties == null || properties.isEmpty()) {
			InputStream inputStream = null;
			try {
				inputStream = ApplicationConfig.class.getResourceAsStream(propertyFile);
				properties.load(inputStream);
				// throw new VahanException("Property file 'Config.properties'
				// not found");
			} catch (IOException io) {
				LOGGER.error(io);
			} finally {
				if (inputStream != null) {
					ServerUtil.safeClose(inputStream);
				}
			}
		}
		// else {
		// return properties.getProperty(propPrefix + "." + propName);
		// }
		return properties.getProperty(propPrefix + "." + propName);
	}

	public String getTempletIdForSms(String msgKey, String state_cd) throws VahanException {
		String temp_id = "";
		InputStream inputStreamSms = null;
		try {
			if (propertiesForSMSMsg == null || propertiesForSMSMsg.isEmpty()) {

				inputStreamSms = ApplicationConfig.class.getResourceAsStream(propertyFileForSMS);
				propertiesForSMSMsg.load(inputStreamSms);
				// throw new VahanException("Property file 'Config.properties'
				// not found");
			}
			temp_id = propertiesForSMSMsg.getProperty(state_cd + "_" + msgKey);
			if (CommonUtils.isNullOrBlank(temp_id)) {
				temp_id = propertiesForSMSMsg.getProperty("ALL" + "_" + msgKey);
			}
		} catch (IOException io) {
			throw new VahanException("Unable to get SMS template ID...");
		} finally {
			if (inputStreamSms != null) {
				ServerUtil.safeClose(inputStreamSms);
			}
		}

		return (CommonUtils.isNullOrBlank(temp_id) ? "" : temp_id);
	}

	public static File getDirectory() {
		File targetDir = null;
		try {
			/**
			 * fetch default dir and create a dir and delete the dir
			 * automatically when jvm terminates
			 */
			FileSystemView fsw = FileSystemView.getFileSystemView();
			String dirFile = fsw.getDefaultDirectory().getPath();
			// //System.out.println("Dir file="+dirFile);
			if (POSValidator.validate(dirFile, "FILEPATH")) {
				targetDir = new File(dirFile + File.separator + "TAX_OWNER_INFO_FILES");
			}

			targetDir.mkdir();
			targetDir.setReadable(true);
			// //System.out.println("Path="+targetDir.getPath());

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return targetDir;
	}
}
