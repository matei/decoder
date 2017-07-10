package com.optaros.tokendecoder;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.typesafe.config.ConfigFactory;
import com.typesafe.config.Config;

/**
 * AES-CBC token decoder
 * Created by matei.stefanescu on 06/07/2017.
 */
public class Decoder {

    public String decode(String encryptedToken, String encryptionKey) throws Exception
    {
        String[] parts = encryptedToken.split(":");
        String payloadString = parts[0];
        String ivString = parts[1];

        Base64 decoder = new Base64();
        byte[] payloadBytes = decoder.decode(payloadString);
        byte[] binaryEncryptedKey = decoder.decode(encryptionKey);
        byte[] ivBytes = decoder.decode(ivString);

        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        SecretKeySpec keySpec = new SecretKeySpec(binaryEncryptedKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

        byte[] decryptedPayload = cipher.doFinal(payloadBytes);
        return new String(decryptedPayload, "UTF-8");
    }


    public static void main(String[] args) throws Exception {

        if (args.length == 0)
        {
            System.out.println("Please specify encrypted token as first parameter");
            System.exit(1);
        }

        String encryptedToken = args[0];

        Config config = ConfigFactory.load();
        Decoder decoder = new Decoder();

        System.out.println("Decoded: " + decoder.decode(encryptedToken, config.getString("decoder.key")));
    }
}
