package com.project1;

import java.io.*;
import java.util.zip.*;
import java.util.Scanner;

public class FileCompress {
    public void MultiZipCompress(String filepath, String zippath) throws Exception {
        File file = new File(filepath);// 要被压缩的文件夹
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zippath));
        mulitfolder(zipOut, file, file.getName());
        zipOut.close();
    }

    private void mulitfolder(ZipOutputStream zipout, File file, String entry) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length == 0)
                zipout.putNextEntry(new ZipEntry(entry + File.separator));
            for (int i = 0; i < files.length; i++) {
                mulitfolder(zipout, files[i], entry + File.separator + files[i].getName());
            }
        } else {
            FileInputStream input = new FileInputStream(file);
            zipout.putNextEntry(new ZipEntry(entry));
            int tem = 0;
            while ((tem = input.read()) != -1) {
                zipout.write(tem);
            }
            input.close();
        }
    }

    public void ZipDecompress(String zippath,String depath) throws IOException{
        ZipInputStream zip=new ZipInputStream(new FileInputStream(zippath));
        BufferedInputStream zipin=new BufferedInputStream(zip);
        File out=null;
        ZipEntry entry;
        while((entry=zip.getNextEntry())!=null){
            out=new File(depath,entry.getName());
            if(!out.exists()){
                (new File(out.getParent())).mkdirs();
            }
            FileOutputStream fileout=new FileOutputStream(out);
            BufferedOutputStream fout=new BufferedOutputStream(fileout);
            int tem;
            while((tem=zipin.read())!=-1){
                fout.write(tem);
            }
            fout.close();
            fileout.close();
        }
    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String file = in.nextLine();
        String zip = in.nextLine();
        FileCompress test = new FileCompress();
        try {
            test.ZipDecompress(file,zip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}