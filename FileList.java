package com.project1;
import java.io.*;
import java.util.*;

public class FileList {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        System.out.println("input the folder name that you want to list:");
        String path_list=s.next();

        listmenu(path_list);
    }
    static void listmenu(String path)
    {
        File f1 = new File(path);
        if((!f1.exists())||(!f1.isDirectory()))
        {
            System.out.println("the folder is not exist!");
        }
        else
        {
            System.out.println("the folder contains the following content: ");
            String[] files = f1.list();
            for(int i=0;i<files.length;i++)
            {
                System.out.println(files[i]);
            }
        }
    }

}
