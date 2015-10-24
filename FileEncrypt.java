package com.project1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by myz on 15/10/18.
 */

public class FileEncrypt {
    private static final String PASSKEY = "A6548EW4";
    private static final String DESKEY = "WE65R466";
    /**
     * mode 加密模式  加密：Cipher.ENCRYPT_MODE 解密：Cipher.DECRYPT_MODE
     */
    public void encoderOrdecoder(String FilePath, int mode) {

        InputStream is = null;
        OutputStream out = null;
        CipherInputStream cis = null;
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(DESKEY.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            IvParameterSpec iv = new IvParameterSpec(PASSKEY.getBytes());
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(mode, securekey, iv, sr);
            int pos=FilePath.lastIndexOf("/");
            String filePath=FilePath.substring(0,pos);
            String fileName=FilePath.substring(pos+1);
            if(mode==Cipher.ENCRYPT_MODE) {
                is = new FileInputStream(filePath + File.separator + fileName);
                out = new FileOutputStream(filePath + File.separator + "Locked-" + fileName);
            }else{
                is = new FileInputStream(filePath + File.separator +fileName);
                out = new FileOutputStream(filePath + File.separator + fileName.substring(7));
            }
            cis = new CipherInputStream(is, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (cis != null) {
                    cis.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e1){
            }
        }
    }

    public static void main(String[] args) {
        FileEncrypt des=new FileEncrypt();
        Scanner in=new Scanner(System.in);
        String filePath=in.nextLine();
        des.encoderOrdecoder(filePath,Cipher.DECRYPT_MODE);
    }
}