package lab1;

import org.jetbrains.annotations.NotNull;


public class EmployeeEncryptor {
    private static String source="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static String target="Q5A8ZWS0XEDC6RFVT9GBY4HNU3J2MI1KO7LP";
    private static boolean enabled;

    public static String obfuscate(String s) {
        return getString(s, source, target);
    }

    public static String deobfuscate(String s) {
        return getString(s, target, source);
    }

    @NotNull
    private static String getString(String str, String source, String target) {
        char[] result= new char[10];
        for (int i=0;i<str.length();i++) {
            char c=str.charAt(i);
            int index= source.indexOf(c);
            result[i]= target.charAt(index);
        }
        return new String(result);
    }
}
