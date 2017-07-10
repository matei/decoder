package com.optaros.tokendecoder;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AWS-CBC token decoder
 * Created by matei.stefanescu on 06/07/2017.
 */
public class Decoder {

    public static void main(String[] args) throws Exception {
        String encryptedToken = "rT7UpXjSMfFqK2s9S2ZsZA3hqGp3+gXiVWd48biLXMGP74VbMruHhBKncE+SbYHIHk398M2778EgkN+eJBfFtGdGfwfhBqlfFFU2WSonQmo0mAQ+bzB7Nviu0kpMmGNaDvy6ggUArnhlcn9atrFbGOHz5c02C4UNgY2+puohUg3BvCkqtInqvr71wQ6MUBwthZ0nsO25x5Qoi8vo8W9ilg==:JH+MhcKL14RnT+X9lkNfQA==";
        String encryptionKey = "b3B0YXJvczEyMzQ1Njc4OQ==";

        String[] parts = encryptedToken.split(":");
        String payloadString = parts[0];
        String ivString = parts[1];

        Base64 decoder = new Base64();
        byte[] payloadBytes = decoder.decode(payloadString);
        byte[] binaryEncryptedKey = decoder.decode(encryptionKey);
        byte[] ivBytes = decoder.decode(ivString);

        System.out.println(ivBytes.length);
        System.out.println(payloadBytes.length);

        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        SecretKeySpec keySpec = new SecretKeySpec(binaryEncryptedKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

        byte[] decryptedPayload = cipher.doFinal(payloadBytes);
        String decryptedString = new String(decryptedPayload, "UTF-8");

        System.out.println("Decoded: " + decryptedString);
    }
}
