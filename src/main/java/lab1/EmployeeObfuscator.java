package lab1;

import org.jetbrains.annotations.NotNull;


public class EmployeeObfuscator {
    private static String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
    private static String target = "Q0WER5TY2UIO9PASDFG6HJKL1ZXCVBNMqwe7rtyu8iopas4dfghjklzxc3vbnm";

    public static String obfuscate(String s) {
        return getString(s, source, target);
    }

    public static String deobfuscate(String s) {
        return getString(s, target, source);
    }

    @NotNull
    private static String getString(String str, String source, String target) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int index = source.indexOf(c);
            stringBuilder.append(target.charAt(index));
        }
        return stringBuilder.toString();
    }
}
