package com.project1;
import java.io.*;

public class FileCopy {

    static String url1 = "/Users/myz/Desktop/yhf";

    static String url2 = "/Users/myz/Desktop/jj";
    public static void main(String args[]) throws IOException {
        FileCopy test=new FileCopy();
                (new File(url2)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(url1)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {

                test.copyFile(file[i],new File(url2+file[i].getName()));
            }
            if (file[i].isDirectory()) {

                String sourceDir=url1+File.separator+file[i].getName();
                String targetDir=url2+File.separator+file[i].getName();
                test.copyDirectiory(sourceDir, targetDir);
            }
        }
    }

    public void copyFile(File sourceFile,File targetFile)
            throws IOException{

        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff=new BufferedInputStream(input);

        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff=new BufferedOutputStream(output);

        byte[] b = new byte[1024 * 5];
        int len;
        while ((len =inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        outBuff.flush();

        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    public void copyDirectiory(String sourceDir, String targetDir)
            throws IOException {
        targetDir=targetDir+File.separator+sourceDir.substring(sourceDir.lastIndexOf("/")+1);
                (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile=file[i];
                // 目标文件
                File targetFile=new
                        File(new File(targetDir).getAbsolutePath()
                        +File.separator+file[i].getName());
                copyFile(sourceFile,targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1=sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2=targetDir + "/"+ file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }
}