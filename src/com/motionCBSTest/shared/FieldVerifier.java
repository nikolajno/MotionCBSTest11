package com.motionCBSTest.shared;

public class FieldVerifier {

    public static boolean isValidFname (String Fname){
        // check if firstname is empty (not allowed)
        if (Fname.isEmpty()){
            return false;
        }

        // check if firstname field contains digits (not allowed)
        for (int n=0 ; n < Fname.length() ; n++){
            if (Character.isDigit(Fname.charAt(n)))
                return false;
        }
        // check if firstname contain at lest two characters
        return Fname.length() > 1;
    }

    public static boolean isValidLname (String Lname) {
        // check if lastname is empty (not allowed)
        if (Lname.isEmpty()) {
            return false;
        }

        // check if lastname field contains digits (not allowed)
        for (int n = 0; n < Lname.length(); n++) {
            if (Character.isDigit(Lname.charAt(n)))
                return false;
        }
        // check if lastname contain at lest two characters
        return Lname.length() > 1;
    }

    public static boolean isValidEmail (String Email) {
        // check if email is empty (not allowed)
        if (Email.isEmpty()) {
            return false;
        }
        // check if email contains '@' and '.'
        return Email.contains("@") && Email.contains(".");
    }

    public static boolean isValidAddress (String Address) {
        // check if address is empty (not allowed)
        if (Address.isEmpty()) {
            return false;
        }
        return Address.length() > 2;
    }

    public static boolean isValidMobileNo (String MobileNo) {
        // check if mobile no. is empty (not allowed)
        if (MobileNo.isEmpty()) {
            return false;
        }
        //check if mobile no. contains exactly 8 digits
        return MobileNo.length() == 8 || MobileNo.length() == 4;
    }

    public static boolean isValidEducation (String Education) {
        // check if education is empty (not allowed)
        return !Education.isEmpty();
    }

    public static boolean isValidExperience (String Experience) {
        // check if experience is empty (not allowed)
        if (Experience.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean isValidHoursPrWeek (Integer HoursPrWeek) {
        // check if experience is empty (not allowed)
        return !HoursPrWeek.toString().isEmpty();
    }

    public static boolean isValidPassword (String Password) {
        //check if password is at least 4 characters
        return Password.length() == 4;
    }
}
