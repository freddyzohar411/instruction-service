package com.avensys.rts.instructionservice.util;

public class FileUtil {
    public static String getFileName(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(0, dotIndex);
    }
}
