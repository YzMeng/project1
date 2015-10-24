package com.project1;

import java.io.File;

public class FileOperation {
    public void CreateFile(String path) {
        try {
            File file = new File(path);
            file.mkdirs();
            System.out.println("success to create!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DeleteFile(File path) {
        if(path.isDirectory()){
        File filelist[] = path.listFiles();
        int listlength = filelist.length;
        for (int i = 0; i < listlength; i++) {
            if (filelist[i].isDirectory()) {
                DeleteFile(filelist[i]);
            } else {
                filelist[i].delete();
            }
        }
        path.delete();}else{
            path.delete();
        }
    }
    public static void main(String[] args){
//        FileOperation create=new FileOperation();
//        create.CreateFile("/Users/myz/Desktop/test/test1/test2/test3");
        File test=new File("/Users/myz/Desktop/yhf.zip");
        System.out.println(test.listFiles().length);
    }
}
