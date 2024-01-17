/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tranC095
 */
abstract public class TableList implements Serializable {
	////////////////////////////////////////////////////////////////////////
	// CONSTANTS
	////////////////////////////////////////////////////////////////////////

	public static final List<String> VAList = getVaList();
	public static final List<String> VHAList = getVhaList();
	public static final String NEW_REG_SCHEMA = "";
	public static final String QRCODE_SCHEMA = "qrcode.";
	public static final String PERMIT_SCHEMA = "PERMIT.";
	public static final String ONLINE_SCHEMA = "ONLINESCHEMA.";
	public static final String ONLINE_USRMGMT = "online_usrmgmt.";
	public static final String PGI_SCHEMA = "vahanpgi.";
	public static final String CHALLAN_SCHEMA = "echallan.";
	public static final String TM_BANK = "TM_BANK";
	public static final String TM_EMP = "TM_EMP";
	public static final String TM_COUNTRY = "TM_COUNTRY";
	public static final String TM_OFFICE = "TM_OFFICE";
	public static final String TM_DESIGNATION = "TM_DESIGNATION";
	public static final String TM_DISTRICT = "TM_DISTRICT";
	public static final String VM_VH_CLASS = "VM_VH_CLASS";
	public static final String VM_VCH_CATG = "VM_VCH_CATG";
	public static final String VM_VHCLASS_CATG_MAP = "VM_VHCLASS_CATG_MAP";
	public static final String TM_STATE = "TM_STATE";
	public static final String TM_TALUK = "TM_TALUK";
	public static final String TM_VILLAGE = "TM_VILLAGE";
	public static final String VM_BD_TYPE = "VM_BD_TYPE";
	public static final String VM_OWCODE = "VM_OWCODE";
	public static final String VM_REGN_TYPE = "VM_REGN_TYPE";
	public static final String VM_REGN_SERIES = "vm_regn_series";
	public static final String VHM_REGN_SERIES = "vhm_regn_series";
	public static final String VM_REGN_AVAILABLE = "vm_regn_available";
	public static final String VM_FUEL = "vm_fuel";
	public static final String VM_OWCATG = "VM_OWCATG";
	public static final String VM_ICCODE = "VM_ICCODE";
	public static final String VM_INSTYP = "VM_INSTYP";
	public static final String VM_MAKER = "vm_maker_homologation";
	public static final String VM_MODELS = "vm_model_homologation";
	public static final String VM_SEAT_TYPE = "VM_SEAT_TYPE";
	public static final String VM_FIT_OFFICER = "VM_FIT_OFFICER";
	public static final String VSM_UP_MAST = "VSM_UP_MAST";
	public static final String VA_RANDOM_REGN_NO = "VA_RANDOM_REGN_NO";
	public static final String VA_FANCY_REGISTER = "VA_FANCY_REGISTER";
	public static final String VM_TAX_SLAB_FIELDS = "VM_TAX_SLAB_FIELDS";
	public static final String VM_HP_TYPE = "VM_HP_TYPE";
	public static final String VM_NORMS = "vm_norms";
	public static final String VM_TAX_MODE = "VM_TAX_MODE";
	public static final String VM_OTHER_CRITERIA = "VM_OTHER_CRITERIA";
	public static final String TM_CURRENTVALUES = "TM_CURRENTVALUES";
	public static final String TM_PURPOSE_MAST = "TM_PURPOSE_MAST";
	public static final String TM_INSTRUMENTS = "TM_INSTRUMENTS";
	// public static final String VA_STATUS = "va_status";
	// public static final String VA_DETAILS = "va_details";
	public static final String VHA_STATUS = "vha_status";
	public static final String view_purpose_action_flow = "vvv_purpose_action_flow";
	public static final String VIEW_OFF_USER_SEAT_ACTION = "vvv_off_user_seat_action";
	public static final String VA_CA = NEW_REG_SCHEMA + "VA_CA";
	public static final String VH_CA = NEW_REG_SCHEMA + "VH_CA";
	public static final String VHA_CA = NEW_REG_SCHEMA + "VHA_CA";
	public static final String VA_TO = NEW_REG_SCHEMA + "VA_TO";
	public static final String VH_TO = NEW_REG_SCHEMA + "VH_TO";
	public static final String VHA_TO = NEW_REG_SCHEMA + "VHA_TO";
	public static final String VA_HPA = NEW_REG_SCHEMA + "VA_HPA";
	public static final String VA_HPT = NEW_REG_SCHEMA + "VA_HPT";
	public static final String VHA_HPA = NEW_REG_SCHEMA + "VHA_HPA";
	public static final String VHA_HPT = NEW_REG_SCHEMA + "VHA_HPT";
	public static final String VH_HPT = NEW_REG_SCHEMA + "VH_HPT";
	public static final String VT_HYPTH = NEW_REG_SCHEMA + "VT_HYPTH";
	public static final String VH_HYPTH = NEW_REG_SCHEMA + "VH_HYPTH";
	public static final String VVA_HPT = NEW_REG_SCHEMA + "VVA_HPT";
	public static final String VA_NOC = "VA_NOC";
	public static final String VA_DUP = "VA_DUP";
	public static final String VH_DUP = "VH_DUP";
	public static final String VHA_DUP = "VHA_DUP";
	public static final String VT_FEE = "VT_FEE";
	public static final String VT_FEE_CANCEL = "vt_fee_cancel";
	public static final String VT_APPL_RCPT_MAPPING = "vt_appl_rcpt_mapping";
	public static final String VT_TAX_BREAKUP = "VT_TAX_BREAKUP";
	// Added By Nitin :25-feb-2015
	public static final String VM_COURT = CHALLAN_SCHEMA + "vm_courts";
	public static final String VM_ACCUSED = CHALLAN_SCHEMA + "vm_accused";
	public static final String VM_CHALLAN_BOOK = CHALLAN_SCHEMA + "vm_challan_book";
	public static final String VM_DA = CHALLAN_SCHEMA + "vm_da";
	public static final String VM_DA_PENALTY = CHALLAN_SCHEMA + "vm_da_penalty";
	public static final String VM_DOCUMENTS = CHALLAN_SCHEMA + "vm_documents";
	public static final String VM_EXCESS_SCHEDULE = CHALLAN_SCHEMA + "vm_excess_schedule";
	public static final String VM_OFFENCE_PENALTY = CHALLAN_SCHEMA + "vm_offence_penalty";
	public static final String VM_OFFENCES = CHALLAN_SCHEMA + "vm_offences";
	public static final String VM_OVERLOAD_SCHEDULE = CHALLAN_SCHEMA + "vm_overload_schedule";
	public static final String VM_SECTION = CHALLAN_SCHEMA + "vm_sections";
	public static final String VA_CHALLAN = CHALLAN_SCHEMA + "va_challan";
	public static final String VH_CHALLAN = CHALLAN_SCHEMA + "vh_challan";
	public static final String VT_CHALLAN = CHALLAN_SCHEMA + "vt_challan";
	public static final String VT_CHALLAN_ACCUSED = CHALLAN_SCHEMA + "vt_challan_accused";
	public static final String VT_CHALLAN_ADDL_INFO = CHALLAN_SCHEMA + "vt_challan_addl_info";
	public static final String VT_CHALLAN_AMT = CHALLAN_SCHEMA + "vt_challan_amt";
	public static final String VT_CHALLAN_OWNER = CHALLAN_SCHEMA + "vt_challan_owner";
	public static final String VT_CHALLAN_REFER_TO_COURT = CHALLAN_SCHEMA + "vt_challan_refer_to_court";
	public static final String VT_CHALLAN_SETTLEMANT = CHALLAN_SCHEMA + "vt_challan_settlement";
	public static final String VT_CHALLAN_TAX = CHALLAN_SCHEMA + "vt_challan_tax";
	public static final String VT_DOCS_IMPOUND = CHALLAN_SCHEMA + "vt_docs_impound";
	public static final String VT_VCH_DA = CHALLAN_SCHEMA + "vt_vch_da";
	public static final String VT_VCH_OFFENCES = CHALLAN_SCHEMA + "vt_vch_offences";
	public static final String VT_VEHICLE_IMPOUND = CHALLAN_SCHEMA + "vt_vehicle_impound";
	// Added By Nitin :25-feb-2015
	// Added By Nitin :12-may-2015
	public static final String VA_RC_SURRENDER = "va_rc_surrender";
	public static final String VT_RC_SURRENDER = "vt_rc_surrender";
	public static final String VH_RC_SURRENDER = "vh_rc_surrender";
	public static final String VHA_RC_SURRENDER = "vha_rc_surrender";
	public static final String VHA_RC_RELEASE = "vha_rc_release";
	public static final String VA_RC_RELEASE = "va_rc_release";
	public static final String VA_RC_CANCEL = "va_rc_cancel";
	public static final String VHA_RC_CANCEL = "vha_rc_cancel";
	public static final String VT_RC_CANCEL = "vt_rc_cancel";
	// Added By Nitin :12-may-2015
	// Added By Afzal : 03 Feb-2015
	public static final String TM_ACTION = "tm_action";
	// Added By Afzal : 02 March-2015 for black listed vehicle
	public static final String VT_BLACKLIST = "vt_blacklist";
	public static final String VT_BLACKLIST_CHASSIS = "VT_BLACKLIST_CHASSIS";
	public static final String VM_BLACKLIST = "VM_BLACKLIST";
	public static final String VH_BLACKLIST_CHASSIS = "VH_BLACKLIST_CHASSIS";
	public static final String VH_BLACKLIST = "VH_BLACKLIST";
	// NAMAN INSERT THE TABLE

	public static final String VT_OWNER = NEW_REG_SCHEMA + "VT_OWNER";
	public static final String VIEW_VV_OWNER = NEW_REG_SCHEMA + "vv_owner";
	public static final String VIEW_VVA_OWNER = NEW_REG_SCHEMA + "vva_owner";
	public static final String VA_PERMIT_OWNER = PERMIT_SCHEMA + "VA_PERMIT_OWNER";
	public static final String VHA_PERMIT_OWNER = PERMIT_SCHEMA + "VHA_PERMIT_OWNER";
	public static final String VM_PERMIT_TYPE = PERMIT_SCHEMA + "VM_PERMIT_TYPE";
	public static final String VM_PERMIT_CATG = PERMIT_SCHEMA + "vm_permit_catg";
	public static final String VA_PERMIT = PERMIT_SCHEMA + "VA_PERMIT";
	public static final String VM_PERMIT_GRACE_PERIOD = PERMIT_SCHEMA + "VM_PERMIT_GRACE_PERIOD";
	public static final String VHA_PERMIT = PERMIT_SCHEMA + "VHA_PERMIT";
	public static final String VT_PERMIT = PERMIT_SCHEMA + "VT_PERMIT";
	public static final String VH_PERMIT = PERMIT_SCHEMA + "VH_PERMIT";
	public static final String VT_PERMIT_ROUTE = PERMIT_SCHEMA + "vt_permit_route";
	public static final String VA_PERMIT_ROUTE = PERMIT_SCHEMA + "va_permit_route";
	public static final String VM_ROUTE_MASTER = PERMIT_SCHEMA + "vm_route_master";
	public static final String VM_REGION = PERMIT_SCHEMA + "vm_region";
	public static final String VM_SERVICES_TYPE = PERMIT_SCHEMA + "vm_service_type";
	public static final String VHA_PERMIT_ROUTE = PERMIT_SCHEMA + "vha_permit_route";
	public static final String VA_TEMP_PERMIT = PERMIT_SCHEMA + "va_temp_permit";
	public static final String VA_TEMP_PERMIT_ROUTE = PERMIT_SCHEMA + "va_temp_permit_route";
	public static final String VHA_TEMP_PERMIT = PERMIT_SCHEMA + "vha_temp_permit";
	public static final String VHA_TEMP_PERMIT_ROUTE = PERMIT_SCHEMA + "vha_temp_permit_route";
	public static final String VT_TEMP_PERMIT = PERMIT_SCHEMA + "vt_temp_permit";
	public static final String VT_TEMP_PERMIT_ROUTE = PERMIT_SCHEMA + "vt_temp_permit_route";
	public static final String VA_PERMIT_HOME_AUTH = PERMIT_SCHEMA + "va_permit_home_auth";
	public static final String VHA_PERMIT_HOME_AUTH = PERMIT_SCHEMA + "vha_permit_home_auth";
	public static final String VT_PERMIT_HOME_AUTH = PERMIT_SCHEMA + "vt_permit_home_auth";
	public static final String VH_PERMIT_HOME_AUTH = PERMIT_SCHEMA + "vh_permit_home_auth";
	public static final String VA_PERMIT_TRANSACTION = PERMIT_SCHEMA + "va_permit_transaction";
	public static final String VHA_PERMIT_TRANSACTION = PERMIT_SCHEMA + "vha_permit_transaction";
	public static final String VT_PERMIT_TRANSACTION = PERMIT_SCHEMA + "vt_permit_transaction";
	public static final String VH_PERMIT_TRANSACTION = PERMIT_SCHEMA + "vh_permit_transaction";
	public static final String VM_PERMIT_DOC_STATE_MAP = PERMIT_SCHEMA + "vm_permit_doc_state_map";
	public static final String VM_PERMIT_DOCUMENTS = PERMIT_SCHEMA + "vm_permit_documents";
	public static final String VA_PERMIT_PRINT = PERMIT_SCHEMA + "va_permit_print";
	public static final String VH_PERMIT_PRINT = PERMIT_SCHEMA + "vh_permit_print";
	public static final String VM_PERMIT_FEE = PERMIT_SCHEMA + "vm_permit_fee";
	public static final String VM_OFF_ALLOTMENT = PERMIT_SCHEMA + "vm_off_allotment";
	public static final String VM_PERMIT_FEE_STATE_CONFIGURATION = PERMIT_SCHEMA + "vm_permit_fee_state_configuration";
	public static final String VM_PERMIT_STATE_CONFIGURATION = PERMIT_SCHEMA + "vm_permit_state_configuration";
	public static final String VM_PERMIT_RULE_HEADING = PERMIT_SCHEMA + "vm_permit_rule_heading";
	public static final String VH_TEMP_PERMIT = PERMIT_SCHEMA + "vh_temp_permit";
	public static final String VM_PERMIT_FEE_SLAB = PERMIT_SCHEMA + "vm_permit_fee_slab";
	public static final String VHA_SPL_ROUTE = PERMIT_SCHEMA + "vha_spl_route";
	public static final String VA_SPL_ROUTE = PERMIT_SCHEMA + "va_spl_route";
	public static final String VT_SPL_ROUTE = PERMIT_SCHEMA + "vt_spl_route";
	public static final String VA_EXEMPTION = "va_exemption";
	public static final String VHA_EXEMPTION = "vha_exemption";
	public static final String VT_EXEMPTION = "vt_exemption";
	public static final String VH_EXEMPTION = "vh_exemption";
	public static final String VM_PERMIT_RESERVATION = PERMIT_SCHEMA + "vm_permit_reservation";
	public static final String VA_PERMIT_OFFER_APPROVAL = PERMIT_SCHEMA + "va_permit_offer_approval";
	public static final String VH_PERMIT_OFFER_APPROVAL = PERMIT_SCHEMA + "vh_permit_offer_approval";
	public static final String VA_PERMIT_COUNTERSIGNATURE = PERMIT_SCHEMA + "va_permit_countersignature";
	public static final String VHA_PERMIT_COUNTERSIGNATURE = PERMIT_SCHEMA + "vha_permit_countersignature";
	public static final String VT_PERMIT_COUNTERSIGNATURE = PERMIT_SCHEMA + "vt_permit_countersignature";
	public static final String VH_PERMIT_COUNTERSIGNATURE = PERMIT_SCHEMA + "vh_permit_countersignature";
	public static final String VM_PERMIT_VALIDITY_MAST = PERMIT_SCHEMA + "vm_permit_validity_mast";
	public static final String VM_PERMIT_VALIDATE_MSG = PERMIT_SCHEMA + "vm_permit_validate_msg";
	public static final String VM_PERMIT_OBJECTION = PERMIT_SCHEMA + "vm_permit_objection";
	public static final String VA_DUP_DOCLIST = PERMIT_SCHEMA + "va_dup_docList";
	public static final String VH_DUP_DOCLIST = PERMIT_SCHEMA + "vh_dup_docList";
	public static final String VM_PERMIT_REPLACEMENT_VALIDITY = PERMIT_SCHEMA + "vm_permit_replacement_validity";
	public static final String VM_PERMIT_ROUTE_REGION_RESTRICTION = PERMIT_SCHEMA
			+ "vm_permit_route_region_restriction";
	public static final String VA_DOCUMENT_ESIGN = "vahan4.va_document_esign";
	public static final String VH_DOCUMENT_ESIGN = "vahan4.vh_document_esign";
	public static final String VA_DOCUMENT_DSIGN = "vahan4.va_document_digital_signed";
	public static final String VH_DOCUMENT_DSIGN = "vahan4.vh_document_digital_signed";
	public static final String TM_CONFIGURATION_DIGITAL_SIGNED = "tm_configuration_digital_signed";
	public static final String TM_CONFIGURATION_DMS = "tm_configuration_dms";

	// NAMAN INSERT THE TABLE
	public static final String VT_INSTRUMENTS = "VT_INSTRUMENTS";
	public static final String VA_OWNER = "va_owner";
	public static final String VHA_OWNER = "vha_owner";
	public static final String VH_OWNER = "vh_owner";
	public static final String VA_OWNER_IDENTIFICATION = "va_owner_identification";
	public static final String VT_OWNER_IDENTIFICATION = "vt_owner_identification";
	public static final String VHA_OWNER_IDENTIFICATION = "vha_owner_identification";
	public static final String VH_OWNER_IDENTIFICATION = "vh_owner_identification";
	public static final String TM_ROLE = "TM_ROLE";
	// Pawan
	public static final String TM_OFF_EMP_ACTION = "tm_off_emp_action";
	public static final String TM_USER_CATG = "tm_user_catg";
	public static final String VT_NOC = "VT_NOC";
	public static final String VHA_NOC = "VHA_NOC";
	public static final String VA_NOC_CANCEL = "VA_NOC_CANCEL";
	public static final String VHA_NOC_CANCEL = "VHA_NOC_CANCEL";
	public static final String VT_NOC_CANCEL = "VT_NOC_CANCEL";
	public static final String VA_RETROFITTING_DTLS = "va_retrofitting_dtls";
	public static final String VHA_RETROFITTING_DTLS = "vha_retrofitting_dtls";
	public static final String VH_RETROFITTING_DTLS = "vh_retrofitting_dtls";
	public static final String VT_RETROFITTING_DTLS = "vt_retrofitting_dtls";
	public static final String VA_OWNER_EX_ARMY = "va_owner_ex_army";
	public static final String VHA_OWNER_EX_ARMY = "vha_owner_ex_army";
	public static final String VH_OWNER_EX_ARMY = "vh_owner_ex_army";
	public static final String VT_OWNER_EX_ARMY = "vt_owner_ex_army";
	public static final String VA_AXLE = "va_axle";
	public static final String VHA_AXLE = "vha_axle";
	public static final String VH_AXLE = "vh_axle";
	public static final String VT_AXLE = "vt_axle";
	public static final String VA_IMPORT_VEH = "va_import_veh";
	public static final String VHA_IMPORT_VEH = "vha_import_veh";
	public static final String VH_IMPORT_VEH = "vh_import_veh";
	public static final String VT_IMPORT_VEH = "vt_import_veh";
	public static final String VA_ALT = "va_alt";
	public static final String VHA_ALT = "vha_alt";
	public static final String VH_ALT = "vh_alt";
	public static final String VA_CONVERSION = "va_conversion";
	public static final String VHA_CONVERSION = "vha_conversion";
	public static final String VH_CONVERSION = "vh_conversion";
	public static final String VA_INSURANCE = "va_insurance";
	public static final String VHA_INSURANCE = "vha_insurance";
	public static final String VH_INSURANCE = "vh_insurance";
	public static final String VT_INSURANCE = "vt_insurance";
	public static final String VH_RE_ASSIGN = "vh_re_assign";
	public static final String TM_USER_PERMISSIONS = "tm_user_permissions";
	public static final String VM_DEALER_MAST = "vm_dealer_mast";
	public static final String TM_RCPT_NO = "tm_rcpt_no";
	public static final String TM_USER_INFO = "tm_user_info";
	public static final String VA_RENEWAL = "va_renewal";
	public static final String VHA_RENEWAL = "vha_renewal";
	public static final String VH_RENEWAL = "vh_renewal";
	public static final String VM_REASON = "vm_reasons";
	// by komal
	public static final String VA_HSRP = "hsrp.va_hsrp";
	public static final String VHA_HSRP = "hsrp.vha_hsrp";
	public static final String VH_HSRP = "hsrp.vh_hsrp";
	public static final String VT_HSRP = "hsrp.vt_hsrp";
	public static final String VT_HSRP_FLATFILE = "hsrp.vt_hsrp_flatfile";
	public static final String VM_HSRP_FLAG = "hsrp.vm_hsrp_flag";
	public static final String VM_DEALER_HOMOLOGATION = "vm_dealer_homologation";
	public static final String VM_MAKER_HOMOLOGATION = "vm_maker_homologation";
	public static final String VT_OWNER_TEMP = "vt_owner_temp";
	public static final String VM_SMART_CARD_HSRP = "vm_smart_card_hsrp";
	public static final String VT_TAX = "vt_tax";
	public static final String VT_TAX_CANCEL = "vt_tax_cancel";
	public static final String SMART_CARD = "smartcard.smart_card";
	public static final String VA_SMART_CARD = "smartcard.va_smart_card";
	public static final String VH_SMART_CARD = "smartcard.vh_smart_card";
	public static final String VT_SC_FLATFILE = "smartcard.vt_sc_flatfile";
	public static final String VA_RC_PRINT = "va_rc_print";
	public static final String VHA_RC_PRINT = "vha_rc_print";
	public static final String RC_BE_TO_BO = "smartcard.rc_be_to_bo";
	public static final String RC_IA_TO_BE = "smartcard.rc_ia_to_be";
	public static final String VT_USER_LATEST_ACTIONS = "vt_user_latest_actions";
	public static final String VM_HOMOLOGATION_CHASSIS_INFO = "vm_homologation_chassis_info";
	public static final String VT_ADVANCE_REGN_NO = "vt_advance_regn_no";
	public static final String VA_TRADE_CERTIFICATE = "va_trade_certificate";
	public static final String VH_RC_PRINT = "vh_rc_print";
	public static final String VA_OTHER_STATE_VEH = "va_other_stat_veh";
	public static final String VT_OTHER_STATE_VEH = "vt_other_stat_veh";
	public static final String VHA_OTHER_STATE_VEH = "vha_other_stat_veh";
	public static final String VH_OTHER_STATE_VEH = "vh_other_stat_veh";
	public static final String VP_APPL_RCPT_MAPPING = "vp_appl_rcpt_mapping";
	public static final String VP_APPL_RCPT_MAPPING_CANCEL = "vp_appl_rcpt_mapping_cancel";
	public static final String VA_TMP_REGN_DTL = "va_tmp_regn_dtl";
	public static final String VHA_TMP_REGN_DTL = "vha_tmp_regn_dtl";
	public static final String VT_TMP_REGN_DTL = "vt_tmp_regn_dtl";
	public static final String VT_PUCC = "vt_pucc";
	public static final String VA_PUCC = "va_pucc";
	public static final String VHA_PUCC = "vha_pucc";
	public static final String VH_PUCC = "vh_pucc";
	public static final String VT_TAX_BASED_ON = "vt_tax_based_on";
	public static final String VT_TAX_BASED_ON_PERMIT = "vt_tax_based_on_permit_info";
	public static final String VH_TAX_BASED_ON_PERMIT = "vh_tax_based_on_permit_info";
	public static final String TM_CONFIGURATION = "tm_configuration";
	public static final String THM_OFFICE_CONFIGURATION = "thm_office_configuration";
	public static final String VA_FITNESS = "va_fitness";
	public static final String VT_FITNESS = "vt_fitness";
	public static final String VH_FITNESS = "vh_fitness";
	public static final String VHA_FITNESS = "vha_fitness";
	public static final String VM_FEE_MAST_PENALTY = "vm_feemast_penalty";
	public static final String VA_TRAILER = "va_trailer";
	public static final String VHA_TRAILER = "vha_trailer";
	public static final String VA_FC_PRINT = "va_fc_print";
	public static final String VH_FC_PRINT = "vh_fc_print";
	public static final String VM_FEEMAST_SERVICE = "vm_feemast_service";
	public static final String VT_TAX_CLEAR = "VT_TAX_CLEAR";
	public static final String VT_TAX_EXEM = "VT_TAX_EXEM";

	// vinay:26-5-2015
	public static final String VT_TEMP_APPROVE = ONLINE_SCHEMA + "VT_TEMP_APPROVE";
	public static final String vh_temp_approve = ONLINE_SCHEMA + "vh_temp_approve";
	public static final String vp_sequence_no = ONLINE_SCHEMA + "vp_sequence_no";
	public static final String vp_details = ONLINE_SCHEMA + "vp_details";
	public static final String vp_rd_tax = ONLINE_SCHEMA + "vp_rd_tax";
	public static final String vp_rd_taxpaid = ONLINE_SCHEMA + "vp_rd_taxpaid";
	public static final String vp_tax_clear = ONLINE_SCHEMA + "vp_tax_clear";
	public static final String vp_onlinetaxpgi = ONLINE_SCHEMA + "vp_onlinetaxpgi";
	public static final String vp_pgi_details = PGI_SCHEMA + "vp_pgi_details";
	public static final String vt_ca_appl = ONLINE_SCHEMA + "vt_ca_appl";
	public static final String VP_ACCOUNT = ONLINE_SCHEMA + "VP_ACCOUNT";
	public static final String VT_TO_APPL = ONLINE_SCHEMA + "VT_TO_APPL";
	public static final String VT_HPT_APPL = ONLINE_SCHEMA + "vt_hpt_appl";
	public static final String VT_HYPTH_APPL = ONLINE_SCHEMA + "vt_hypth_appl";
	public static final String VT_DUP_APPL = ONLINE_SCHEMA + "vt_dup_appl";
	public static final String VT_TEMP_INSURANCE = ONLINE_SCHEMA + "VT_TEMP_INSURANCE";
	public static final String VP_ALLOWED_TRANSACTIONS = ONLINE_SCHEMA + "VP_ALLOWED_TRANSACTIONS";
	public static final String VP_ICICI_OBJECTFILE_DTLS = ONLINE_SCHEMA + "VP_ICICI_OBJECTFILE_DTLS";
	public static final String VA_STATUS = "VA_STATUS";
	public static final String VA_DETAILS = "VA_DETAILS";
	public static final String VA_STATUS_APPL = ONLINE_SCHEMA + "VA_STATUS_APPL";
	public static final String VA_DETAILS_APPL = ONLINE_SCHEMA + "VA_DETAILS_APPL";
	public static final String VT_TAX_BASED_ON_APPL = ONLINE_SCHEMA + "VT_TAX_BASED_ON_APPL";
	public static final String VT_TAX_BREAKUP_APPL = ONLINE_SCHEMA + "VT_TAX_BREAKUP_APPL";
	public static final String VT_TEMP_APPL_TRANSACTION = ONLINE_SCHEMA + "VT_TEMP_APPL_TRANSACTION";
	// vinay : 12-12-2015
	public static final String VT_OTP = ONLINE_SCHEMA + "VT_OTP";
	public static final String VH_OTP = ONLINE_SCHEMA + "VH_OTP";
	public static final String VT_TEMP_OWNER_IDENTIFICATION = ONLINE_SCHEMA + "VT_TEMP_OWNER_IDENTIFICATION";
	public static final String VT_ONLINE_APPOINTMENT_TRANS_DTLS = ONLINE_SCHEMA + "VT_ONLINE_APPOINTMENT_TRANS_DTLS";
	public static final String VT_ONLINE_APPOINTMENT_APPLICANT_DTLS = ONLINE_SCHEMA
			+ "VT_ONLINE_APPOINTMENT_APPLICANT_DTLS";
	public static final String VM_DOC = ONLINE_SCHEMA + "VM_DOC";
	public static final String VT_DOCUMENT = ONLINE_SCHEMA + "VT_DOCUMENT";
	public static final String VM_STATE_DOC_MAST = ONLINE_SCHEMA + "VM_STATE_DOC_MAST";
	public static final String VT_FEE_BREAKUP = "VT_FEE_BREAKUP";
	public static final String VH_NOFEES_APPLICATION = ONLINE_SCHEMA + "VH_NOFEES_APPLICATION";
	public static final String VT_NOFEES_APPLICATION = ONLINE_SCHEMA + "VT_NOFEES_APPLICATION";
	public static final String VM_VEHICLE_AGE_VALIDITY = "vm_veh_age_validity";
	public static final String VM_VALIDITY_MAST = "vm_validity_mast";

	// imroz
	public static final String VT_USER_VEHICLE_MAP = ONLINE_SCHEMA + "VT_USER_VEHICLE_MAP";
	public static final String VH_USER_VEHICLE_MAP = ONLINE_SCHEMA + "VH_USER_VEHICLE_MAP";
	public static final String vt_cart_detail = ONLINE_SCHEMA + "vt_cart_detail";
	// vivek
	public static final String USER_DTLS = ONLINE_USRMGMT + "USER_DTLS";
	public static final String VM_USER_ROLE_MASTER = ONLINE_USRMGMT + "VM_USER_ROLE_MASTER";
	public static final String VSM_SERVICES = ONLINE_USRMGMT + "VSM_SERVICES";
	public static final String VST_ROLES = ONLINE_USRMGMT + "VST_ROLES";
	public static final String VST_ROLES_SERVICES = ONLINE_USRMGMT + "VST_ROLES_SERVICES";

	public static final String VM_ONLINE_SERVICE = ONLINE_SCHEMA + "VM_ONLINE_SERVICE";
	public static final String VT_CART_PAYMENT = ONLINE_SCHEMA + "VT_CART_PAYMENT";
	public static final String VT_USER_PROFILE_VEHICLE = ONLINE_USRMGMT + "VT_USER_PROFILE_VEHICLE";
	public static final String VT_EMAIL_VERIFICATION = ONLINE_USRMGMT + "VT_EMAIL_VERIFICATION";
	// Aftab
	public static final String VA_FITNESS_APPL = ONLINE_SCHEMA + "VA_FITNESS_APPL";

	// public static final String VT_TEMP_APPROVE_PERMIT = ONLINE_SCHEMA +
	// "VT_TEMP_APPROVE_PERMIT";
	// public static final String VH_TEMP_APPROVE_PERMIT = ONLINE_SCHEMA +
	// "VH_TEMP_APPROVE_PERMIT";
	// public static final String VT_TEMP_APPL_TRANSACTION_PERMIT =
	// ONLINE_SCHEMA + "VT_TEMP_APPL_TRANSACTION_PERMIT";
	public static final String VT_TEMP_APPROVE_PERMIT = ONLINE_SCHEMA + "VT_TEMP_APPROVE";
	public static final String VH_TEMP_APPROVE_PERMIT = ONLINE_SCHEMA + "VH_TEMP_APPROVE";
	public static final String VT_TEMP_APPL_TRANSACTION_PERMIT = ONLINE_SCHEMA + "VT_TEMP_APPL_TRANSACTION";
	public static final String VA_TEMP_PERMIT_APPL = ONLINE_SCHEMA + "VA_TEMP_PERMIT_APPL";
	public static final String TM_PURPOSE_ACTION_FLOW = "tm_purpose_action_flow";
	public static final String VM_FEEMAST = "vm_feemast";
	public static final String VP_PERMIT_APPL_INWARDED_ONLINE = ONLINE_SCHEMA + "vp_permit_appl_inwarded_online";
	public static final String VA_PERMIT_HOME_AUTH_APPL = ONLINE_SCHEMA + "va_permit_home_auth_appl";
	public static final String VA_PERMIT_ROUTE_APPL = ONLINE_SCHEMA + "va_permit_route_appl";

	public static final String VA_DATA_VERIFY_REQUEST = "va_data_verify_request";
	public static final String VHA_DATA_VERIFY_REQUEST = "vha_data_verify_request";
	// vivek
	public static final String SBI_ePAY_ONLINE = ONLINE_SCHEMA + "sbiepay_online";
	// addedBYVinit18JULY16
	// forVA_DUP_APPL
	public static final String vt_renewal_appl = ONLINE_SCHEMA + "vt_renewal_appl";
	public static final String VT_ALT_APPL = ONLINE_SCHEMA + "vt_alt_appl";
	public static final String VT_RE_ASSIGN_APPL = ONLINE_SCHEMA + "vt_re_assign_appl";
	public static final String VT_RETROFITTING_DTLS_APPL = ONLINE_SCHEMA + "vt_retrofitting_dtls_appl";
	public static final String VA_RE_ASSIGN = "va_re_assign";
	public static final String VT_CONVERSION_APPL = ONLINE_SCHEMA + "vt_conversion_appl";
	public static final String VT_AXLE_APPL = ONLINE_SCHEMA + "vt_axle_appl";
	// tarun
	public static final String VP_LOI_DTLS = ONLINE_SCHEMA + "vp_loi_dtls";

	// forBalanceFEE
	// 02MARCH17
	public static final String RETAIN_ACTUAL_TRANS_NO = ONLINE_SCHEMA + "retain_actual_transaction_no";
	// for NOC
	// addedBYVinit13FEB17
	public static final String VA_NOC_APPL = ONLINE_SCHEMA + "va_noc_appl";
	// Raj bahadur
	public static final String VA_RC_CANCEL_APPL = ONLINE_SCHEMA + "va_rc_cancel_appl";
	public static final String VA_RC_RELEASE_APPL = ONLINE_SCHEMA + "va_rc_release_appl";
	public static final String VA_RC_SURRENDER_APPL = ONLINE_SCHEMA + "va_rc_surrender_appl";
	// mukul
	public static final String VA_TRADE_CERTIFICATE_APPL = ONLINE_SCHEMA + "va_trade_certificate_appl";
	public static final String VM_APPLICANT_MAST_APPL = ONLINE_SCHEMA + "vm_applicant_mast_appl";
	public static final String VA_TRADE_CERTIFICATE_AUDIT_TRAIL = "va_trade_certificate_audit_trail";
	public static final String VAHAN_DOCS = "vahandocs.";
	public static final String VT_DOCUMENT_VAHAN_DOCS = VAHAN_DOCS + "vt_document";
	public static final String VM_MAKER_TRADE = "vahan4.vm_maker";
	public static final String VT_ENDORSEMENT_TAX = "vahan4.VT_ENDORSEMENT_TAX";
	public static final String va_permit_route = PERMIT_SCHEMA + "va_permit_route";
	public static final String vt_permit_route = PERMIT_SCHEMA + "vt_permit_route";
	// vinit20july17
	public static final String VT_APPOINTMENT_FLOW = ONLINE_SCHEMA + "vt_appointment_flow";
	public static final String VH_APPOINTMENT_FLOW = ONLINE_SCHEMA + "vh_appointment_flow";
	// by jyoti
	public static final String VT_MOBILEDETAILS = ONLINE_SCHEMA + "VT_MOBILEDETAILS";
	public static final String VM_ONLINE_CONFIGURATION = ONLINE_SCHEMA + "VM_ONLINE_CONFIGURATION";
	public static final String VHA_STATUS_APPL = ONLINE_SCHEMA + "vha_status_appl";

	// MukulGaurav
	public static final String TM_CONFIGURATION_SPL_PMT = PERMIT_SCHEMA + "tm_configuration_spl_pmt";
	public static final String TM_CONFIGURATION_TMP_PMT = PERMIT_SCHEMA + "tm_configuration_temp_pmt";
	public static final String VT_SPL_PASSENGER = PERMIT_SCHEMA + "vt_spl_passenger";// by
																						// aarti
	public static final String VT_SIDE_TRAILER = "vt_side_trailer";
	public static final String VA_NON_USE_TAX_EXEM = "va_non_use_tax_exem";
	public static final String VT_NON_USE_TAX_EXEM = "vt_non_use_tax_exem";
	public static final String ONLINE_PERMIT_SCHEMA = "onlinepermit.";
	public static final String VH_AUDIT_TRAIL = ONLINE_PERMIT_SCHEMA + "vh_audit_trail_permit";
	public static final String VA_PERMIT_ROUTE_ONLINE = ONLINE_PERMIT_SCHEMA + "va_permit_route";
	public static final String VHA_DETAILS = "vha_details";
	public static final String VA_PERMIT_TRANSACTION_ONLINE = ONLINE_PERMIT_SCHEMA + "va_permit_transaction";
	public static final String VHA_PERMIT_TRANSACTION_ONLINE = ONLINE_PERMIT_SCHEMA + "vha_permit_transaction";
	public static final String VA_PERMIT_Online = ONLINE_PERMIT_SCHEMA + "VA_PERMIT";
	public static final String VA_PERMIT_OWNER_ONLINE = ONLINE_PERMIT_SCHEMA + "VA_PERMIT_OWNER";
	public static final String VA_PERMIT_HOME_AUTH_ONLINE = ONLINE_PERMIT_SCHEMA + "va_permit_home_auth";
	public static final String VHA_PERMIT_ONLINE = ONLINE_PERMIT_SCHEMA + "VHA_PERMIT";
	public static final String VHA_PERMIT_OWNER_ONLINE = ONLINE_PERMIT_SCHEMA + "VHA_PERMIT_OWNER";
	public static final String VHA_PERMIT_ROUTE_ONLINE = ONLINE_PERMIT_SCHEMA + "vha_permit_route";
	public static final String VA_TEMP_PERMIT_ONLINE = ONLINE_PERMIT_SCHEMA + "va_temp_permit";
	public static final String VA_TEMP_PERMIT_ROUTE_ONLINE = ONLINE_PERMIT_SCHEMA + "va_temp_permit_route";
	public static final String VHA_TEMP_PERMIT_ONLINE = ONLINE_PERMIT_SCHEMA + "vha_temp_permit";
	public static final String VHA_TEMP_PERMIT_ROUTE_ONLINE = ONLINE_PERMIT_SCHEMA + "vha_temp_permit_route";
	public static final String VHA_PERMIT_HOME_AUTH_ONLINE = ONLINE_PERMIT_SCHEMA + "vha_permit_home_auth";
	public static final String VA_SPL_PASSWENGER_ONLINE = ONLINE_PERMIT_SCHEMA + "va_spl_passenger_appl";
	public static final String VA_TEMP_INSURANCE = ONLINE_PERMIT_SCHEMA + "va_temp_insurance";
	public static final String VhA_SPL_PASSWENGER_ONLINE = ONLINE_PERMIT_SCHEMA + "vha_spl_passenger_appl";
	public static final String VA_SPL_ROUTE_ONLINE = ONLINE_PERMIT_SCHEMA + "va_spl_route_appl";
	public static final String VhA_SPL_ROUTE_ONLINE = ONLINE_PERMIT_SCHEMA + "vha_spl_route_appl";
	public static final String PERMIT_PURPOSE_ACTION_FLOW = ONLINE_PERMIT_SCHEMA + "va_permit_purpose_action_flow";
	public static final String ONLINE_PERMIT_STATE_CONFIGURE = ONLINE_PERMIT_SCHEMA + "tm_online_state_configuration";
	public static final String ONLINE_PERMIT_OFF_CONFIGURE = ONLINE_PERMIT_SCHEMA + "tm_off_configuration";
	public static final String ONLINE_PERMIT_ALLOW_RESTRICTION = ONLINE_PERMIT_SCHEMA + "tm_permit_type_allowed";
	public static final String ONLINE_VEH_PERMIT_RESTRICTION = ONLINE_PERMIT_SCHEMA + "tm_veh_permit_permitted";
	public static final String ONLINE_VA_PERMIT_OFFER_APPROVAL = ONLINE_PERMIT_SCHEMA + "va_permit_offer_approval";
	public static final String VM_NATURE_OF_GOODS = ONLINE_PERMIT_SCHEMA + "vm_nature_of_goods ";
	public static final String ONLINE_TM_CONFIGURATION_SPL_PMT = ONLINE_PERMIT_SCHEMA + "tm_configuration_spl_pmt";
	public static final String ONLINE_TM_CONFIGURATION_FRESH_PMT = ONLINE_PERMIT_SCHEMA
			+ "tm_configuration_fresh_pmt_withregn";
	public static final String ONLINE_TM_CONFIGURATION_TMP_PMT = ONLINE_PERMIT_SCHEMA + "tm_configuration_temp_pmt";
	public static final String ONLINE_TM_CONFIGURATION_SURR_PMT = ONLINE_PERMIT_SCHEMA + "tm_configuration_surr_pmt";
	public static final String ONLINE_TM_CONFIGURATION_REST_PMT = ONLINE_PERMIT_SCHEMA + "tm_configuration_rest_pmt";
	public static final String ONLINE_VM_REASON_TYPE_ = ONLINE_PERMIT_SCHEMA + "vm_reason_type";
	public static final String ONLINE_TM_CONFIGURATION_COUNTERSIGN_AUTHORIZATION = ONLINE_PERMIT_SCHEMA
			+ "tm_configuration_countsignauth";

	public static final String ONLINE_VA_PMT_COUNT_AUTH = ONLINE_PERMIT_SCHEMA
			+ "va_permit_countersignature_authorization";
	public static final String ONLINE_VA_PMT_COUNT_SGN = ONLINE_PERMIT_SCHEMA + "va_permit_countersignature";
	public static final String ONLINE_VHA_PMT_COUNT_SGN = ONLINE_PERMIT_SCHEMA + "vha_permit_countersignature";
	public static final String VT_PMT_RUNNING_COUNT = PERMIT_SCHEMA + "vt_countsign_auth_running_reservation";
	public static final String ONLINE_VM_PMT_RESERVATION = PERMIT_SCHEMA + "vm_counter_sign_auth_reservation";
	// public static final String ONLINE_VM_PMT_COUNT_SIG_RESERVATION =
	// PERMIT_SCHEMA + "vm_reciprocal_counter_sign_reservation";
	public static final String ONLINE_VM_PMT_COUNT_SIG_RESERVATION = ONLINE_PERMIT_SCHEMA
			+ "vm_reciprocal_counter_sign_reservation";
	public static final String ONLINE_VT_PMT_COUNT_AUTH = PERMIT_SCHEMA + " vt_permit_countersignature_authorization ";
	public static final String ONLINE_VHA_PMT_COUNT_AUTH = ONLINE_PERMIT_SCHEMA
			+ "vha_permit_countersignature_authorization";
	public static final String VA_PMT_COUNT_SGN_AUTH = PERMIT_SCHEMA + "va_permit_countersignature_authorization";
	public static final String VA_DUPL_DOCLIST = ONLINE_PERMIT_SCHEMA + "va_dup_docList";
	public static final String ON_VA_TEMPSPL_TAX_BASED_ON_ROUTE_SERVICE = ONLINE_PERMIT_SCHEMA
			+ "va_tempspl_tax_based_on_route_service";
	public static final String OnlinePermitVmRegionRouteConfig = ONLINE_PERMIT_SCHEMA + "vm_region_route_config";
	public static final String ON_VHA_TEMPSPL_TAX_BASED_ON_ROUTE_SERVICE = ONLINE_PERMIT_SCHEMA
			+ "vha_tempspl_tax_based_on_route_service";
	public static final String VA_TEMPSPL_TAX_BASED_ON_ROUTE_SERVICE = PERMIT_SCHEMA + "va_tempspl_tax_based_on_permit";
	public static final String VT_TEMPSPL_TAX_BASED_ON_ROUTE_SERVICE = PERMIT_SCHEMA + "vt_tempspl_tax_based_on_permit";
	public static final String ONLINE_FLOW_CONFIG_SURR_RESTRORE_PMT = ONLINE_PERMIT_SCHEMA
			+ "tm_flow_config_surr_restore_pmt";
	public static final String ONLINE_PMT_RENEW_CONFIG = ONLINE_PERMIT_SCHEMA + "tm_configuration_pmt_renewal";
	public static final String TM_DOCUMENT_NO_AVAILABLE = "tm_document_no_available";
	public static final String TM_DOCUMENT_NO_ALLOTED = "tm_document_no_alloted";
	public static final String ONLINE_VA_PERMIT_ROUTE_SCHEDULE = ONLINE_PERMIT_SCHEMA + "va_permit_route_time_chart";
	public static final String ONLINE_VHA_PERMIT_ROUTE_SCHEDULE = ONLINE_PERMIT_SCHEMA + "vha_permit_route_time_chart";
	public static final String PERMIT_VA_PERMIT_ROUTE_SCHEDULE = PERMIT_SCHEMA + "va_permit_route_time_table";
	public static final String PERMIT_VHA_PERMIT_ROUTE_SCHEDULE = PERMIT_SCHEMA + "vha_permit_route_time_table";
	public static final String PERMIT_VT_PERMIT_ROUTE_SCHEDULE = PERMIT_SCHEMA + "vt_permit_route_time_table";
	public static final String PERMIT_VH_PERMIT_ROUTE_SCHEDULE = PERMIT_SCHEMA + "vh_permit_route_time_table";
	public static final String VA_PERMIT_ROUTE_SCHEDULE_ONLINE = ONLINE_PERMIT_SCHEMA + "va_permit_route_time_chart";
	public static final String PERMIT_VH_PERMIT_ROUTE = PERMIT_SCHEMA + "vh_permit_route";
	public static final String VM_PERMIT_PURPOSE_CASE_FLAG = PERMIT_SCHEMA + "vm_permit_purpose_case_flag";
	public static final String EMERGENCY_RELATED_EXCEMPTED_RECORD = ONLINE_PERMIT_SCHEMA
			+ "emergency_related_excempted_record";
	public static final String VT_SERVER_ROUTE_LOG = ONLINE_PERMIT_SCHEMA + "VT_SERVER_ROUTE_LOG";
	public static final String VH_SERVER_ROUTE_LOG = ONLINE_PERMIT_SCHEMA + "VH_SERVER_ROUTE_LOG";

	public static final String ONLINE_VA_DUP_DOCLIST = ONLINE_PERMIT_SCHEMA + "va_dup_docList";
	public static final String ONLINE_VH_DUP_DOCLIST = ONLINE_PERMIT_SCHEMA + "vh_dup_docList";
	public static final String VM_DOCUMENT_TYPE = "vahan4.vm_document_type";
	public static final String AADHAAR_TM_AUTH_AADHAAR = ONLINE_SCHEMA + "aadhaar_tm_auth_aadhaar";
	public static final String AADHAAR_VT_AADHAAR_AUDIT_TRAIL = ONLINE_SCHEMA + "aadhaar_vt_aadhaar_audit_trail";
	public static final String AADHAAR_CONFIGURATION = ONLINE_PERMIT_SCHEMA + "tm_aadhar_configuration";
	public static final String VT_PERMIT_QR = QRCODE_SCHEMA + "vt_permit_qr";
	public static final String VH_PERMIT_QR = QRCODE_SCHEMA + "vh_permit_qr";
	public static final String VT_PERMIT_SURRENDER_QR = QRCODE_SCHEMA + "vt_permit_surrender_qr";
	public static final String VH_PERMIT_SURRENDER_QR = QRCODE_SCHEMA + "vh_permit_surrender_qr";
	public static final String VH_PERMIT_AUTH_QR = QRCODE_SCHEMA + "vh_permit_auth_qr";
	public static final String VT_PERMIT_AUTH_QR = QRCODE_SCHEMA + "vt_permit_auth_qr";
	public static final String VT_TEMPSPL_PERMIT_QR = QRCODE_SCHEMA + "vt_tempspl_permit_qr";
	public static final String VH_TEMPSPL_PERMIT_QR = QRCODE_SCHEMA + "vh_tempspl_permit_qr";
	public static final String VT_RECEIPT_QR = QRCODE_SCHEMA + "vt_receipt_qr";
	public static final String VH_RECEIPT_QR = QRCODE_SCHEMA + "vh_receipt_qr";

	public static final String VM_SANCTION_ORDER_PERMISSIBLE = ONLINE_PERMIT_SCHEMA + "vm_sanction_order_permissible";
	public static final String VHM_SANCTION_ORDER_PERMISSIBLE = ONLINE_PERMIT_SCHEMA + "vhm_sanction_order_permissible";

	public static final String VT_AUTOAPPROVED_TRANSACTIONS = ONLINE_SCHEMA + "vt_autoapproved_transactions";
	public static final String VM_ONLINE_SERVICECHECKS = ONLINE_SCHEMA + "vm_online_servicechecks";
	public static final String VA_PERMIT_HOME_AUTH_PERIOD = ONLINE_PERMIT_SCHEMA
			+ "onlinepermit.va_permit_home_auth_period";
	public static final String VM_VEHICLE_AGE_VALIDITY_PMT = PERMIT_SCHEMA + "vm_veh_age_validity_pmt";

	public static final String VT_BLACKLIST_STATE_ADMIN = "vahan4.VT_BLACKLIST_STATE_ADMIN";
	public static final String VM_BLACKLIST_NOT_TRANSACTED = "vahan4.VM_BLACKLIST_NOTTRANSACTED";
	public static final String VM_BLACKLIST_NOT_TRANSACTED_OTHRES = "vahan4.VM_BLACKLIST_NOTTRANSACTED_OTHERS";
	public static final String VT_BLACKLIST_CHASSIS_STATE_ADMIN = "vahan4.VT_BLACKLIST_CHASSIS_STATE_ADMIN";

	
	public static List getVaList() {
		List list = new ArrayList();
		list.add(TableList.VA_OWNER);
		list.add(TableList.VA_OWNER_IDENTIFICATION);
		list.add(TableList.VA_HPA);
		list.add(TableList.VA_INSURANCE);
		// list.add(TableList.VA_FITNESS);
		// list.add(TableList.VA_TRAILER);
		list.add(TableList.VA_RETROFITTING_DTLS);
		list.add(TableList.VA_IMPORT_VEH);
		list.add(TableList.VA_OWNER_EX_ARMY);
		list.add(TableList.VA_AXLE);
		list.add(TableList.VA_PUCC);
		list.add(TableList.VA_OTHER_STATE_VEH);
		return list;
	}

	public static List getVhaList() {
		List list = new ArrayList();
		list.add(TableList.VHA_OWNER);
		list.add(TableList.VHA_OWNER_IDENTIFICATION);
		list.add(TableList.VHA_HPA);
		list.add(TableList.VHA_INSURANCE);
		// list.add(TableList.VHA_FITNESS);
		// list.add(TableList.VHA_TRAILER);
		list.add(TableList.VHA_RETROFITTING_DTLS);
		list.add(TableList.VHA_IMPORT_VEH);
		list.add(TableList.VHA_OWNER_EX_ARMY);
		list.add(TableList.VHA_AXLE);
		list.add(TableList.VHA_PUCC);
		list.add(TableList.VHA_OTHER_STATE_VEH);
		return list;
	}
}
