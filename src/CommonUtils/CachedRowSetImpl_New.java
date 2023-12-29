/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonUtils;

import java.sql.SQLException;
import java.util.Hashtable;

/**
 *
 * @author nic5912
 */
public class CachedRowSetImpl_New extends com.sun.rowset.CachedRowSetImpl {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CachedRowSetImpl_New() throws SQLException {
        super();
    }

    public CachedRowSetImpl_New(Hashtable hshtbl) throws SQLException {
        super(hshtbl);
    }

    @Override
    public String getString(String colName) throws SQLException {
        String ret = super.getString(colName);
        if (ret == null) {
            ret = "";
        }
        return ret.trim();
    }

    @Override
    public String getString(int colNumber) throws SQLException {
        String ret = super.getString(colNumber);
        if (ret == null) {
            ret = "";
        }
        return ret.trim();
    }
}
