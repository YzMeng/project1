package com.project1;

import javax.crypto.Cipher;
import java.util.Scanner;
import java.io.*;

/**
 * Created by myz on 15/10/18.
 */
public class FileManager {
    public FileOperation fileoperation = new FileOperation();
    public FileCompress filecompress = new FileCompress();
    public FileEncrypt fileencrypt = new FileEncrypt();
    public FileCopy filecopy = new FileCopy();
    private File currfile = null;
    private String currpath = null;

    FileManager() {
        currfile = new File(".");
        currpath = currfile.getAbsolutePath().substring(0, currfile.getAbsolutePath().lastIndexOf("/"));
        currfile = new File(currpath);
    }

    public void help() {
        System.out.println("文件列表:ls            e.g. ls");
        System.out.println("压缩文件:zip           e.g. zip dir filename");
        System.out.println("解压文件:dezip         e.g. dezip filename dir");
    }

    public String isCurrFile(String path) {
        if ("..".equals(path)) {
            return path;
        }
        if (path.startsWith("/")) {
            return path;
        } else {
            return currpath + File.separator + path;
        }
    }

    public void listmenu(String path) {
        File f1 = new File(path);
        if ((!f1.exists()) || (!f1.isDirectory())) {
            System.out.println("the folder is not exist!");
        } else {
            System.out.println("the folder contains the following content: ");
            String[] files = f1.list();
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i]);
            }
        }
    }

    public void listmenu() {
        String[] files = currfile.list();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }

    public void curr() {
        System.out.println(currpath);
    }

    public void cdmenu(String path) throws NullPointerException {
        String pre = "..";
        if (pre.equals(path)) {
            currpath = currfile.getAbsolutePath().substring(0, currfile.getAbsolutePath().lastIndexOf("/"));
            currfile = new File(currpath);
        } else {
            File tem = new File(path);
            if (tem.exists()) {
                currfile = new File(path);
                currpath = currfile.getAbsolutePath();
            } else {
                System.out.println("文件夹不存在");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("******************************************");
        System.out.println("*                                        *");
        System.out.println("* Welcome to use our File Manager System *");
        System.out.println("*                                        *");
        System.out.println("******************************************");
        System.out.println("If you need help about order,input 'help'");
        FileManager manager = new FileManager();
        while (true) {
            System.out.print("当前路径" + manager.currpath + ":");
            Scanner in = new Scanner(System.in);
            String order = in.nextLine();
            order.trim();
            String[] orders = order.split(" ");
            switch (orders[0]) {
                case "cd":
                    String cdf = manager.isCurrFile(orders[1]);
                    manager.cdmenu(cdf);
                    break;
                case "ls":
                    if (orders.length == 1) {
                        manager.listmenu();
                    } else {
                        manager.listmenu(orders[1]);
                    }
                    break;
                case "mkdir":
                    manager.fileoperation.CreateFile(manager.currpath + File.separator + orders[1]);
                    break;
                case "rm":
                    String tempath = manager.isCurrFile(orders[1]);
                    manager.fileoperation.DeleteFile(new File(tempath));
                    break;
                case "cp":
                    String cpsource = manager.isCurrFile(orders[1]);
                    String cptarget = manager.isCurrFile(orders[2]);
                    if (new File(cpsource).isDirectory()) {
                        manager.filecopy.copyDirectiory(cpsource, cptarget);
                    } else {
                        manager.filecopy.copyFile(new File(cpsource),new File(cptarget));
                    }
                    break;
                case "zip":
                    String zipsource = manager.isCurrFile(orders[1]);
                    String ziptarget = manager.isCurrFile(orders[2]);
                    if (orders.length == 2) {
                        manager.filecompress.MultiZipCompress(zipsource, zipsource + ".zip");
                    }
                    if (orders.length == 3) {
                        manager.filecompress.MultiZipCompress(zipsource, ziptarget);
                    }
                    break;
                case "dezip":
                    String sf = manager.isCurrFile(orders[1]);
                    System.out.println(sf);
                    //System.out.println(manager.currpath+File.separator+sf.substring(sf.lastIndexOf("/")+1,sf.lastIndexOf(".")));
                    if (orders.length == 2) {
                        System.out.println("2 ok");
                        manager.filecompress.ZipDecompress(sf, manager.currpath);
                    }
                    if (orders.length == 3) {
                        String df = manager.isCurrFile(orders[2]);
                        System.out.println(df);
                        manager.filecompress.ZipDecompress(sf, df);
                    }
                    break;
                case "encrypt":
                    String enf = manager.isCurrFile(orders[1]);
                    manager.fileencrypt.encoderOrdecoder(enf, Cipher.ENCRYPT_MODE);
                    break;
                case "decrypt":
                    String def = manager.isCurrFile(orders[1]);
                    manager.fileencrypt.encoderOrdecoder(def, Cipher.DECRYPT_MODE);
                    break;
                case "help":
                    manager.help();
                    break;
                case "exit":
                    return;
                case "curr":
                    manager.curr();
                    break;
                default:
                    System.out.println("Error Order");
                    manager.help();
                    break;
            }
        }
    }
}
