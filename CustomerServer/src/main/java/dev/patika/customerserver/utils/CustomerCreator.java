package dev.patika.customerserver.utils;

import dev.patika.customerserver.entities.Customer;

import java.util.Random;

public class CustomerCreator {
    private static final Random random=new Random();
    private static final long tcMin=10000000000L;
    private static final long tcRange=99999999998L-tcMin;

    private static final long pnMin=5000000000L;
    private static final long pnRange=5999999999L-pnMin;

    private static final char[] alphabet="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static long getValidTcNumber(){
        float randomFloat = random.nextFloat();
        long tc= (long) (tcRange*randomFloat)+tcMin;
        if (tc%2==1)
            tc+=1;
        return tc;
    }

    public static long getValidPhoneNumber(){
        return (long) (pnRange*random.nextFloat())+pnMin;
    }

    public static String getValidNameOrSurname(){
        StringBuilder s=new StringBuilder();
        for (int i=0;i<random.nextInt(20)+5;i++){
            s.append(alphabet[random.nextInt(alphabet.length)]);
        }
        return s.toString();
    }

    public static double getSalary(){
        return random.nextFloat()/Float.MAX_VALUE*100000;
    }

    public static Customer createValidCustomer(){
        return new Customer(getValidTcNumber(),getValidNameOrSurname(),getValidNameOrSurname(),getSalary(),getValidPhoneNumber());
    }
}
