package com.jiepi.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtiles {

    public static boolean delete(String filePath) {
        File file = new File(filePath);
        if (file.isFile()) {
            file.delete();
            return true;
        }
        return false;
    }

    public static String save(MultipartFile file, String savePath) throws IllegalStateException, IOException {

        if (file != null && file.getSize() > 0) {
            File fileFolder = new File(savePath);
            if (!fileFolder.exists()) {
                fileFolder.mkdirs();
            }
            File saveFile = getFile(savePath, file.getOriginalFilename());
            file.transferTo(saveFile);

            return saveFile.getName();
        }

        return null;
    }

    private static File getFile(String savePath, String originalFilename) {
        String fileName = System.currentTimeMillis() + "_" + originalFilename;
        File file = new File(savePath + fileName);
        if (file.exists()) {
            return getFile(savePath, originalFilename);
        }
        return file;
    }


}
