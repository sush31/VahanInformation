package impl;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class TransferOfOwnershipImpl {
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	int purCd = Integer.parseInt((String) session.getAttribute("purcd"));
	String stateCd= (String) session.getAttribute("state");
	
}
