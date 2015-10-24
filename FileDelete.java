package com.project1;

import java.io.File;
import java.util.Scanner;

public class FileDelete {
    public void Delete(File path) {
        File filelist[] = path.listFiles();
        int listlength = filelist.length;
        for (int i = 0; i < listlength; i++) {
            if (filelist[i].isDirectory()) {
                Delete(filelist[i]);
            } else {
                filelist[i].delete();
            }
        }
        path.delete();//删除当前目录
        System.out.println("success to delete!");
    }
    public static void main(String [] args){
        //System.out.println("hello world");
        new FileOperation();
        //删除
        System.out.println("input the file path that you want to delete:");
        Scanner s=new Scanner(System.in);
        File pathtodele=new File(s.next());
        if(pathtodele.exists()){
            FileDelete DeleteDirectory=new FileDelete();
            DeleteDirectory.Delete(pathtodele);
        }else
            System.out.println("Cannot find the directory:"+pathtodele);
    }
}
