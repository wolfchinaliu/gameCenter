package weixin.integrate.util;

import java.util.regex.Pattern;

public class IntegrateUtils {
    private static Pattern phonePattern = 
            Pattern.compile("^((13[0-9]{1})|(14[0-9]{1})|(15[^4,\\D]{1})|(17[0-9]{1})|(18[0-9]{1}))\\d{8}$");

    public static String toFlowTypeName(String flowType) {
        switch (flowType) {
        case "national":
            return "1";
        case "provincial":
            return "2";
        case "1":
            return "1";
        case "2":
            return "2";
        default:
            return null;
        }
    }

    public static boolean validatePhone(String phoneNumber) {
        return phonePattern.matcher(phoneNumber).matches();
    }
}