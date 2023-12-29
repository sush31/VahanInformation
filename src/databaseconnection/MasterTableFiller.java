/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;
import javax.sql.RowSet;
//import nic.rto.vahan.common.VahanException;

import CommonUtils.POSValidator;


/**
 *
 * @author tranC095
 */
public class MasterTableFiller implements Serializable{
	// Load the master tables ONLY ONCE per SERVER SESSION

	//private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MasterTableFiller.class);
	public static MasterTables masterTables = loadMasterTables();
	public static MasterTables masterTablesEditor = loadMasterTablesEditor();
	public static Map<String, State> state = getStates();
	// public static List<SelectItem> state_list = new ArrayList<SelectItem>();
	// public static Map<String, List<SelectItem>> offMap = getStates2();

	private synchronized static MasterTables loadMasterTablesEditor() {

		// Create an empty MasterTables object
		MasterTables mtables = new MasterTables();

		// Load the tables and convert to the java objects
		try {
			//LOGGER.info("........... Creating MasterTables object ...");

			// Create the objects
			mtables.VM_OWCODE = makeMasterTableDO(TableList.VM_OWCODE, "ow_code", "DESCR", "OWNER TYPE MASTER");
			mtables.VM_REGN_TYPE = makeMasterTableDO(TableList.VM_REGN_TYPE, "regn_typecode", "DESCR", "REGISTRATION");
			mtables.VM_FUEL = makeMasterTableDO(TableList.VM_FUEL, "code", "DESCR", "FUEL MASTER");
			mtables.VM_OWCATG = makeMasterTableDO(TableList.VM_OWCATG, "owcatg_code", "DESCR", "OWNER CATEGORY");
			mtables.VM_ICCODE = makeMasterTableDO(TableList.VM_ICCODE, "ic_code", "DESCR", "IC CODE MASTER");
			mtables.VM_INSTYP = makeMasterTableDO(TableList.VM_INSTYP, "instyp_code", "DESCR", "INSURANCE TYPE MASTER");
			mtables.VM_MAKER = makeMasterTableDO(TableList.VM_MAKER, "code", "descr", "MAKER MASTER");
			mtables.VM_MODELS = makeMasterTableDO(TableList.VM_MODELS, "unique_model_ref_no", "model_name",
					"MODEL MASTER");
			mtables.VM_SEAT_TYPE = makeMasterTableDO(TableList.VM_SEAT_TYPE, "seat_cd", "seat_description",
					"SEAT DESCRIPTION");

			// ......................................................................
			// Once the master table DO are created, call this method
			// to fill in the masterTableDOs.
			// ......................................................................
			mtables.fillMasterTableVectorEditor();

			mtables.fillmastertablevectoreditor2();
			//LOGGER.info("...DONE!... Creating MasterTables object ...");
		} catch (SQLException e) {
			//LOGGER.error(e);
		} catch (Exception e) {
			//LOGGER.error(e);
		}

		return mtables;
	}

	// Loads the Master Tables in the respective MasterTableDO
	private synchronized static MasterTables loadMasterTables() {

		// Create an empty MasterTables object
		MasterTables mtables = new MasterTables();

		// Where I Am
		String whereiam = "MasterTableFiller.loadMasterTables()";

		// Load the tables and convert to the java objects
		try {
			//LOGGER.info("........... Creating MasterTables object ...");

			// Create the objects
			mtables.TM_BANK = makeMasterTableDO(TableList.TM_BANK, "bank_code", "descr", "BANK");
			// mtables.TM_EMP = makeMasterTableDO(TableList.TM_EMP, "emp_cd",
			// "emp_name", "EMPLOYEE DETAILS");
			mtables.TM_USER_INFO = makeMasterTableDO(TableList.TM_USER_INFO, "user_cd", "user_name",
					"EMPLOYEE DETAILS");
			mtables.TM_DISTRICT = makeMasterTableDO(TableList.TM_DISTRICT, "dist_cd", "descr", "DISTRICT");
			mtables.VM_VH_CLASS = makeMasterTableDO(TableList.VM_VH_CLASS, "vh_class", "descr", "VEHICLE CLASS");
			mtables.VM_VCH_CATG = makeMasterTableDO(TableList.VM_VCH_CATG, "catg", "catg_desc", "VEHICLE CATEGORY");
			mtables.VM_VHCLASS_CATG_MAP = makeMasterTableDO(TableList.VM_VHCLASS_CATG_MAP, "vh_class", "vch_catg",
					"VEHICLE CLASS CATEGORY");
			mtables.TM_STATE = makeMasterTableDO(TableList.TM_STATE, "state_code", "descr", "STATE MASTER");
			// mtables.TM_TALUK = makeMasterTableDO(TableList.TM_TALUK,
			// "taluk_cd", "descr", "TALUK MASTER");
			// mtables.TM_VILLAGE = makeMasterTableDO(TableList.TM_VILLAGE,
			// "village_cd", "village_name", "VILLAGE MASTER");
			mtables.VM_OWCODE = makeMasterTableDO(TableList.VM_OWCODE, "ow_code", "DESCR", "OWNER TYPE MASTER");
			mtables.VM_REGN_TYPE = makeMasterTableDO(TableList.VM_REGN_TYPE, "regn_typecode", "DESCR", "REGISTRATION");
			mtables.VM_FUEL = makeMasterTableDO(TableList.VM_FUEL, "code", "DESCR", "FUEL MASTER");
			mtables.VM_OWCATG = makeMasterTableDO(TableList.VM_OWCATG, "owcatg_code", "DESCR", "OWNER CATEGORY");
			mtables.VM_ICCODE = makeMasterTableDO(TableList.VM_ICCODE, "ic_code", "DESCR", "IC CODE MASTER");
			mtables.VM_INSTYP = makeMasterTableDO(TableList.VM_INSTYP, "instyp_code", "DESCR", "INSURANCE TYPE MASTER");
			mtables.VM_MAKER = makeMasterTableDO(TableList.VM_MAKER, "code", "descr", "MAKER MASTER");
			mtables.VM_MODELS = makeMasterTableDO(TableList.VM_MODELS, "unique_model_ref_no", "model_name",
					"MODEL MASTER");
			mtables.VM_SEAT_TYPE = makeMasterTableDO(TableList.VM_SEAT_TYPE, "seat_cd", "seat_description",
					"SEAT DESCRIPTION");
			mtables.VM_TAX_SLAB_FIELDS = makeMasterTableDO(TableList.VM_TAX_SLAB_FIELDS, "code", "descr",
					"TAX SLAB FIELDS");
			mtables.VM_HP_TYPE = makeMasterTableDO(TableList.VM_HP_TYPE, "hp_type_cd", "hp_type_descr",
					"HYPOTHECATION TYPE");
			mtables.VM_NORMS = makeMasterTableDO(TableList.VM_NORMS, "code", "descr", "NORMS");
			mtables.VM_TAX_MODE = makeMasterTableDO(TableList.VM_TAX_MODE, "tax_mode", "descr", "TAX MODE");
			mtables.VM_OTHER_CRITERIA = makeMasterTableDO(TableList.VM_OTHER_CRITERIA, "criteria_cd", "criteria_desc",
					"OTHER CRITERIA");
			mtables.VM_PMT_CATEGORY = makeMasterTableDO(TableList.VM_PERMIT_CATG, "code", "descr", "VM_PERMIT_CATG");
			mtables.TM_PURPOSE_MAST = makeMasterTableDO(TableList.TM_PURPOSE_MAST, "pur_cd", "descr",
					"DOMAIN DESCRIPTION");
			// Added By Prashant 30-10-2014
			mtables.VM_PERMIT_TYPE = makeMasterTableDO(TableList.VM_PERMIT_TYPE, "code", "descr", "PERMIT TYPE");
			mtables.vm_service_type = makeMasterTableDO(TableList.VM_SERVICES_TYPE, "code", "descr",
					"PERMIT SERVICES TYPE");
			mtables.TM_DESIGNATION = makeMasterTableDO(TableList.TM_DESIGNATION, "desig_cd", "desig_name",
					"TM_DESIGNATION");
			mtables.TM_ROLE = makeMasterTableDO(TableList.TM_ROLE, "role_cd", "role_descr", "TM_ROLE");
			mtables.TM_USER_CATG = makeMasterTableDO(TableList.TM_USER_CATG, "code", "descr", "TM_USER_CATG");
			mtables.TM_USER_INFO = makeMasterTableDO(TableList.TM_USER_INFO, "user_cd", "user_name",
					"EMPLOYEE DETAILS");
			mtables.VM_HSRP_FLAG = makeMasterTableDO(TableList.VM_HSRP_FLAG, "code", "descr", "HSRP FLAG");
			mtables.VM_REASON = makeMasterTableDO(TableList.VM_REASON, "code", "descr", "VM_REASON");
			// SURRENDER
			mtables.TM_INSTRUMENTS = makeMasterTableDO(TableList.TM_INSTRUMENTS, "code", "descr", "INSTRUMENT TYPE");
			mtables.TM_OFFICE = makeMasterTableDO(TableList.TM_OFFICE, "off_cd", "off_name", "TM_OFFICE");
			mtables.VM_REGION = makeMasterTableDO(TableList.VM_REGION, "region_cd", "region", "REGION DESCRIPTION");
			mtables.TM_COUNTRY = makeMasterTableDO(TableList.TM_COUNTRY, "code", "descr", "TM_COUNTRY");
			mtables.VM_BLACKLIST = makeMasterTableDO(TableList.VM_BLACKLIST, "code", "descr", "COMPLAIN TYPE");
			mtables.VM_COURT = makeMasterTableDO(TableList.VM_COURT, "court_cd", "court_name", "COURT DESCRIPTION");
			mtables.VM_ACCUSED = makeMasterTableDO(TableList.VM_ACCUSED, "descr", "code", "ACCUSED");
			mtables.VM_DA = makeMasterTableDO(TableList.VM_DA, "code", "descr", "DA TYPE");
			mtables.VM_DOCUMENTS = makeMasterTableDO(TableList.VM_DOCUMENTS, "code", "descr", "DOCUMENT DESCRIPTION");
			mtables.VM_OFFENCES = makeMasterTableDO(TableList.VM_OFFENCES, "offence_cd", "offence_desc",
					"OFFENCE DESCRIPTION");
			mtables.VM_SECTION = makeMasterTableDO(TableList.VM_SECTION, "section_cd", "section_name",
					"SECTION DESCRIPTION");
			mtables.VM_PERMIT_DOCUMENTS = makeMasterTableDO(TableList.VM_PERMIT_DOCUMENTS, "doc_id", "descr",
					"PERMIT DOCUMENT");
			// added by vinay: 07March2016
			mtables.VM_PERMIT_DOCUMENTS = makeMasterTableDO(TableList.VM_PERMIT_DOCUMENTS, "doc_id", "descr",
					"PERMIT DOCUMENT");
			// ......................................................................
			// Once the master table DO are created, call this method
			// to fill in the masterTableDOs.
			// ......................................................................
			mtables.fillMasterTableVector();

			//LOGGER.info("...DONE!... Creating MasterTables object ...");
		} catch (SQLException e) {
			//LOGGER.error(e);
		} catch (Exception e) {
			//LOGGER.error(e);
		}

		return mtables;
	}

	// private synchronized static Map<String, List<SelectItem>> getStates2() {
	// TransactionManager tmgr = null;
	// PreparedStatement ps;
	// RowSet rs;
	// Map<String, List<SelectItem>> stateData = new HashMap<>();
	//
	// // "select state.code as state_cd, state.descr as state_name,\n"
	// // + "offi.off_cd, offi.off_name from vm_state_cd state\n"
	// // + "inner join tm_office offi on state.code = offi.state_cd\n"
	// // + " order by state.code";
	// String stateSql = "select state.state_code as state_cd, state.descr as
	// state_name,\n"
	// + "offi.off_cd, offi.off_name from tm_state state\n"
	// + "inner join tm_office offi on state.state_code = offi.state_cd\n"
	// + "order by state.state_code";
	// try {
	// tmgr = new TransactionManager("getStates");
	// ps = tmgr.prepareStatement(stateSql);
	// rs = tmgr.fetchDetachedRowSet_No_release();
	// String state_cd = null;
	//
	// while (rs.next()) {
	// ////System.out.println("in side while the row no is" + rs.getRow());
	// state_cd = rs.getString("state_cd").trim();
	// ArrayList<SelectItem> al = new ArrayList<>();
	// do {
	//
	// ////System.out.println(rs.getString("state_cd").trim());
	// if (state_cd.equals(rs.getString("state_cd").trim())) {
	// al.add(new SelectItem(rs.getString("off_cd"), rs.getString("off_name")));
	// } else {
	//// //System.out.println("break on row" + rs.getRow());
	// break;
	// }
	// } while (rs.next());
	//// //System.out.println("after do wile the row no is" + rs.getRow());
	// rs.absolute(rs.getRow() - 1);
	// if (!al.isEmpty())
	// stateData.put(rs.getString("state_cd"), al);
	// state_list.add(new SelectItem(rs.getString("state_cd"),
	// rs.getString("state_name")));
	// }
	// } catch (SQLException e) {
	// LOGGER.error(e);
	// } catch (Exception e) {
	// LOGGER.error(e);
	// } finally {
	// if (tmgr != null) {
	// try {
	// tmgr.release();
	// } catch (Exception e) {
	// LOGGER.error(e);
	// }
	//
	// }
	// }
	// return stateData;
	// }

	private synchronized static Map<String, State> getStates() {
		TransactionManager tmgr = null;
		PreparedStatement ps;
		RowSet rs;
		Map<String, State> stateData = new HashMap<>();
		Map<String, VehicleClass> vehicleData = new HashMap<>();
		String stateSql = "Select * from " + TableList.TM_STATE + " order by descr";
		try {
			tmgr = new TransactionManager("getStates");
			ps = tmgr.prepareStatement(stateSql);
			rs = tmgr.fetchDetachedRowSet_No_release();
			while (rs.next()) {
				State obj = new State();
				obj.setStateCode(rs.getString("state_code"));
				obj.setStateDescr(rs.getString("descr"));
				obj.setEgovCode(rs.getInt("egov_code"));
				obj.setDisrict(
						getMasterData(rs.getString("state_code"), tmgr, TableList.TM_DISTRICT, "dist_cd", "descr"));
				obj.setOffice(
						getMasterData(rs.getString("state_code"), tmgr, TableList.TM_OFFICE, "off_cd", "off_name"));
				obj.setOtherCriteria(getMasterData(rs.getString("state_code"), tmgr, TableList.VM_OTHER_CRITERIA,
						"criteria_cd", "criteria_desc"));
				obj.setCourt(
						getMasterData(rs.getString("state_code"), tmgr, TableList.VM_COURT, "court_cd", "court_name"));
				obj.setMagistrate(getMasterDataString(rs.getString("state_code"), tmgr, TableList.VM_COURT,
						"magistrate_cd", "magistrate_name"));
				obj.setOffence(getMasterData(rs.getString("state_code"), tmgr, TableList.VM_OFFENCES, "offence_cd",
						"offence_desc"));
				obj.setSection(getMasterData(rs.getString("state_code"), tmgr, TableList.VM_SECTION, "section_cd",
						"section_name"));
				obj.setDa(getMasterData(rs.getString("state_code"), tmgr, TableList.VM_DA, "code", "descr"));
				stateData.put(rs.getString("state_code"), obj);
			}
		} catch (SQLException e) {
			//LOGGER.error(e.getMessage());
		} catch (Exception e) {
			//LOGGER.error(e.getMessage());
		} finally {
			if (tmgr != null) {
				try {
					tmgr.release();
				} catch (Exception e) {
					//LOGGER.error(e.getMessage());
				}

			}
		}
		return stateData;
	}

	/**
	 * Make DO for a given master table.
	 *
	 * @param tableName
	 *            The master table name
	 *
	 * @return The MasterTableDO object
	 *
	 * @throws SQLException
	 * @throws VahanException
	 */
	public static MasterTableDO makeMasterTableDO(String tableName, String codeName, String descName, String tableLable)
			throws SQLException, Exception {

		String[][] metadata = null;
		String[][] data = null;
		Map dataMap = new LinkedHashMap();
		TransactionManager tmg = null;

		String query = "SELECT  * FROM " + tableName + " ORDER BY " + descName;
		//LOGGER.info("[Master Query] " + query);
		String whereiam = "MasterTableFiller.makeTableDO()";
		try {
			tmg = new TransactionManager("rowCount");
			String sqlRowCount = "SELECT COUNT(*) FROM " + tableName;
			PreparedStatement ps = tmg.prepareStatement(sqlRowCount);
			javax.sql.RowSet rsC = tmg.fetchDetachedRowSet();
			int rowsCount = 0;
			if (rsC.next()) {
				rowsCount = rsC.getInt(1);
			}

			int nCols = 0;

			// ................................................................
			// Get the whole table and store the data into this object.
			// NOTE:Though the master table can contain columns more than 2,
			// but we will store only the first two columns
			// ................................................................
			tmg = new TransactionManager(whereiam);
			ps = tmg.prepareStatement(query);
			rsC = tmg.fetchDetachedRowSet();

			// Get the number of columns
			java.sql.ResultSetMetaData rsmd = rsC.getMetaData();
			nCols = rsmd.getColumnCount();

			// Get the metadata
			metadata = new String[4][nCols];
			for (int i = 1; i <= nCols; i++) {
				// Get the designated column's name
				metadata[0][i - 1] = rsmd.getColumnName(i);
				// Retrieves the designated column's database-specific type name
				metadata[1][i - 1] = rsmd.getColumnTypeName(i);
				// Retrieves the designated column's SQL type
				metadata[2][i - 1] = "" + rsmd.getColumnType(i);
				// Retrieves the designated column's normal maximum width in
				// characters
				metadata[3][i - 1] = "" + rsmd.getColumnDisplaySize(i);
			}

			// Get the table data
			if (rowsCount > 0) {

				data = new String[rowsCount][nCols];
				int row = 0;
				while (rsC.next()) {
					row++;
					// Note: Column number starts with 1
					for (int col = 1; col <= nCols; col++) {
						data[row - 1][col - 1] = rsC.getString(col);
					}

					dataMap.put(rsC.getString(codeName), rsC.getString(descName));
				}
			}

			if (nCols < 2) {
				throw new Exception("Master table \"" + tableName + "\" contains less than 2 columns!");
			}
		} catch (Exception e) {
			//LOGGER.error(e);
		} finally {
			try {
				if (tmg != null) {
					tmg.release();
				}
			} catch (Exception e) {
				//LOGGER.error(e);
			}

		}
		String[][] data_AO = null;
		data_AO = data;
		return new MasterTableDO(tableName, metadata, data, data_AO, dataMap, codeName, descName, tableLable);
	}

	/**
	 * Modify the given data by copying the first column and inserting it as
	 * second column.
	 *
	 * @param tn
	 *            - Tablename
	 * @param md
	 *            - Metadata
	 * @param d
	 *            - data (full)
	 * @param d_AO
	 *            - data in available order
	 *
	 * @return The modified data
	 */
	private static Object[] modifyData(String tn, String[][] md, String[][] d, String[][] d_AO) {

		// Check input. Return null if any of the data given is null.
		if (md == null || d == null || d_AO == null) {
			return null;
		}

		// Increase column size by one
		String[][] m_md = new String[md.length][md[0].length + 1];
		String[][] m_d = new String[d.length][d[0].length + 1];
		String[][] m_d_AO = new String[d_AO.length][d_AO[0].length + 1];

		// Insert the column
		int ncol = 0;
		for (int i = 0; i < md.length; i++) {
			ncol = 0;
			for (int j = 0; j < md[0].length; j++) {
				m_md[i][ncol] = md[i][j];
				if (j == 0) {
					ncol++;
					m_md[i][ncol] = md[i][j];
				}
				ncol++;
			}
		}
		// Rename the name of the column just inserted
		// 04-April-2014

		for (int i = 0; i < d.length; i++) {
			ncol = 0;
			for (int j = 0; j < d[0].length; j++) {
				m_d[i][ncol] = d[i][j];
				if (j == 0) {
					ncol++;
					m_d[i][ncol] = d[i][j];
				}
				ncol++;
			}
		}

		for (int i = 0; i < d_AO.length; i++) {
			ncol = 0;
			for (int j = 0; j < d_AO[0].length; j++) {
				m_d_AO[i][ncol] = d_AO[i][j];
				if (j == 0) {
					ncol++;
					m_d_AO[i][ncol] = d_AO[i][j];
				}
				ncol++;
			}
		}

		// Return modified data
		return new Object[] { m_md, m_d, m_d_AO };
	}

	public synchronized static void ReloadMasterTables() {
		masterTables = null;
		masterTables = loadMasterTables();
		masterTablesEditor = loadMasterTablesEditor();

	}

	// public static List getOfficeList(String stateCode) {
	// if (state.get(stateCode) == null) {
	// return new ArrayList();
	// }
	// State st = (State) state.get(stateCode);
	// ArrayList list = new ArrayList(st.getOffice());
	//
	// return list;
	// }

	private static List<SelectItem> getMasterData(String stateCode, TransactionManager tmgr, String tableName,
			String colName, String descr) {
		List<SelectItem> dataList = new ArrayList<>();
		String Sql = "Select * from " + tableName + " where state_cd = ? order by " + descr + "";
		PreparedStatement ps;
		RowSet rs;
		try {
			ps = tmgr.prepareStatement(Sql);
			if (POSValidator.validate(stateCode, "A")) {
				ps.setString(1, stateCode);
			}
			rs = tmgr.fetchDetachedRowSet_No_release();
			while (rs.next()) {
				dataList.add(new SelectItem(rs.getInt(colName), rs.getString(descr)));
			}
		} catch (SQLException e) {
			//LOGGER.error(e);
		} catch (Exception e) {
			//LOGGER.error(e);
		}
		return dataList;
	}

	private static List<SelectItem> getMasterDataString(String stateCode, TransactionManager tmgr, String tableName,
			String colName, String descr) {
		List<SelectItem> dataList = new ArrayList<>();
		String Sql = "Select * from " + tableName + " where state_cd = ? order by " + descr + "";
		PreparedStatement ps;
		RowSet rs;
		try {
			ps = tmgr.prepareStatement(Sql);
			if (POSValidator.validate(stateCode, "A")) {
				ps.setString(1, stateCode);
			}
			rs = tmgr.fetchDetachedRowSet_No_release();
			while (rs.next()) {
				dataList.add(new SelectItem(rs.getString(colName), rs.getString(colName) + "-" + rs.getString(descr)));
			}
		} catch (SQLException e) {
			//LOGGER.error(e);

		} catch (Exception e) {
			//LOGGER.error(e);
		}
		return dataList;
	}

	/**
	 * Standalone testing.
	 */
	// public static void main(String[] args) {
	// MasterTables mtables = loadMasterTables();
	// String[][] data = mtables.VM_VH_CLASS.getData();
	// String[][] metData = mtables.VM_VH_CLASS.getMetadata();
	// }

	public static List getDistrictList(String stateCode) {
		if (state.get(stateCode) == null) {
			return new ArrayList();
		}
		State st = (State) state.get(stateCode);
		ArrayList list = new ArrayList(st.getDisrict());

		return list;
	}

	/**
	 *
	 * @param stateCode
	 * @return Office List usable for SelectOneMenu
	 */
	public static List getOfficeList(String stateCode) {
		if (state.get(stateCode) == null) {
			return new ArrayList();
		}
		State st = (State) state.get(stateCode);
		ArrayList list = new ArrayList(st.getOffice());

		return list;
	}

}
