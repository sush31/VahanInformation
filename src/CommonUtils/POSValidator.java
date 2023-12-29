package CommonUtils;

public class POSValidator {

	 public static boolean validate(String data, String type) throws Exception {
	        boolean validatFlag = false;
	        try {

	            if (type.equalsIgnoreCase("A")) {
	                validatFlag = JSFUtils.isAlphabet(data);
	            } else if (type.equalsIgnoreCase("N")) {
	                validatFlag = JSFUtils.isNumeric(data);
	            } else if (type.equalsIgnoreCase("F")) {
	                validatFlag = JSFUtils.isFloat(data);
	            } else if (type.equalsIgnoreCase("AN")) {
	                validatFlag = JSFUtils.isAlphaNumeric(data);
	            } else if (type.equalsIgnoreCase("AS")) {
	                validatFlag = JSFUtils.isAlphabetWithSpace(data);
	            } else if (type.equalsIgnoreCase("ANS")) {
	                validatFlag = JSFUtils.isAlphaNumericWithSpace(data);
	            } else if (type.equalsIgnoreCase("ADD")) {
	                validatFlag = JSFUtils.isAddressValid(data);
	            } else if (type.equalsIgnoreCase("DATE")) {
	                validatFlag = JSFUtils.isDateValid(data);
	            } else if (type.equalsIgnoreCase("DATETIME")) {
	                validatFlag = JSFUtils.isDateTimeValid(data);
	            } else if (type.equalsIgnoreCase("URL")) {
	                validatFlag = JSFUtils.isUrlValid(data);
	            } else if (type.equalsIgnoreCase("ENCDATA")) {
	                validatFlag = JSFUtils.isEncdataValid(data);
	            } else if (type.equalsIgnoreCase("IP")) {
	                validatFlag = JSFUtils.isIPValid(data);
	            } else if (type.equalsIgnoreCase("FNCR_NAME")) {
	                validatFlag = JSFUtils.isFncrNameValid(data);
	            } else if (type.equalsIgnoreCase("DBLENCDATA")) {
	                validatFlag = JSFUtils.isDblVarificationEncdataValid(data);
	            } else if (type.equalsIgnoreCase("USERNAME")) {
	                validatFlag = JSFUtils.isUserNameValid(data);
	            } else if (type.equalsIgnoreCase("ANWS")) {
	                validatFlag = JSFUtils.isAlphaNumWithSpecialChar(data);
	            } else if (type.equalsIgnoreCase("EMAIL")) {
	                validatFlag = JSFUtils.isEmailValid(data);
	            } else if (type.equalsIgnoreCase("MOBILE")) {
	                validatFlag = JSFUtils.checkMobileNumber(data);
	            } else if (type.equalsIgnoreCase("DESP")) {
	                validatFlag = JSFUtils.isDespStr(data);
	            } else if (type.equalsIgnoreCase("FILEPATH")) {
	                validatFlag = JSFUtils.isFilePathValid(data);
	            }
	            if (!validatFlag) {
	                throw new Exception("POSValidationException ; Column=:" + data + "could not be validated against " + type);
	            }

	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	        return validatFlag;
	    }

}
