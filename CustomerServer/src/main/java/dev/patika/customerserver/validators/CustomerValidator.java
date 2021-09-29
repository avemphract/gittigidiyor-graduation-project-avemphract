package dev.patika.customerserver.validators;

import dev.patika.customerserver.api.exceptions.NotValidCustomerNameException;
import dev.patika.customerserver.api.exceptions.NotValidPhoneNumberException;
import dev.patika.customerserver.api.exceptions.NotValidTcNumberException;

public class CustomerValidator {
    public static String nameSurnameValidator(String name, Type type) {
        if (name.isEmpty())
            throw new NotValidCustomerNameException(type.toString + " can not be empty");
        boolean allCharsAreSpace=true;
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                throw new NotValidCustomerNameException(type.toString + " has invalid character. Invalid character is '" + c + "'");
            }
            if (Character.isLetter(c))
                allCharsAreSpace=false;
        }
        if(allCharsAreSpace){
            throw new NotValidCustomerNameException(type.toString + " only contains space");
        }
        String[] words = name.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].subSequence(1, words[i].length()).toString().toLowerCase();
        }
        return String.join(" ", words);
    }

    public static void tcNumberValidator(long tcNumber) {
        if (getNumberLength(tcNumber) != 11) {
            throw new NotValidTcNumberException("Tc number must be 11 length. Actual: " + getNumberLength(tcNumber));
        }
        if (getDigit(tcNumber, 1) % 2 == 1) {
            throw new NotValidTcNumberException("Tc number must be even number. Actual last digit: " + getDigit(tcNumber, 1));
        }
    }

    public static void phoneNumberValidator(long phoneNumber) {
        //507 906 4664
        if (getNumberLength(phoneNumber) != 10) {
            throw new NotValidPhoneNumberException("Phone length must be 10. Actual length: " + getNumberLength(phoneNumber));
        }
        if (getDigit(phoneNumber, 10) != 5) {
            throw new NotValidPhoneNumberException("Phone must be begin with 5. Actual: " + getDigit(phoneNumber, 10));
        }
    }

    private static int getDigit(long number, int digit) {
        return (int) ((number % Math.pow(10, digit)) / Math.pow(10, digit - 1));
    }

    private static int getNumberLength(long number) {
        int i = 0;
        while (number > 0) {
            number /= 10;
            i++;
        }
        return i;
    }

    public enum Type {
        NAME("Name"), SURNAME("Surname");
        private final String toString;

        Type(String toString) {
            this.toString = toString;
        }
    }
}
