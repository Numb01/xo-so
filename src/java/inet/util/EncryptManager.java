
/*
 * Copyright(C) 2009 Viettel telecom
 *
 * EncryptManager.java, Feb 06, 2009, SonPN
 */
package inet.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.Base64;
import org.apache.log4j.Logger;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.DataLengthException;

import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.generators.DESedeKeyGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PEMWriter;
import org.bouncycastle.util.encoders.Hex;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author ManhPS
 */
public final class EncryptManager {
    //logger

    private static final char[] kDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
        'b', 'c', 'd', 'e', 'f'};

    public static String md5Encrypt(String password) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes(), 0, password.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (Exception ex) {
            log.error("md5Encrypt", ex);
        }
        return "";
    }

    public static String decryptRSA(String toDecrypt, String strPrivateKey) {

        RSAPrivateCrtKeyParameters rsaPrKey = getBCPrivateKeyFromString(strPrivateKey);
        log.info("running decryptRSA ....");
        if (rsaPrKey == null) {
            log.info("RSAPrivateKey == null");
            return null;
        }

        try {
            AsymmetricBlockCipher theEngine = new RSAEngine();
            theEngine = new PKCS1Encoding(theEngine);
            theEngine.init(false, rsaPrKey);
            return new String(theEngine.processBlock(Base64.decode(toDecrypt), 0, Base64.decode(toDecrypt).length));
        } catch (Exception ex) {
            log.error("decryptRSA", ex);
        }
        return null;
    }

    public static String encryptRSA(String toEncrypt, String strPublicKey) {
        RSAKeyParameters rsaPbKey = getBCPublicKeyFromString(strPublicKey);
        if (rsaPbKey == null) {
            log.info("RSAPublicKey == null");
            return null;
        }

        try {
            AsymmetricBlockCipher theEngine = new RSAEngine();
            theEngine = new PKCS1Encoding(theEngine);
            theEngine.init(true, rsaPbKey);
            return new String(Base64.encode(theEngine.processBlock(toEncrypt.getBytes(), 0, toEncrypt.getBytes().length)));
        } catch (InvalidCipherTextException ex) {
            log.error("encryptRSA", ex);
        }
        return null;
    }

    static public String decrypt3DES_iv(ParametersWithIV keyiv, String data) {
        BufferedBlockCipher cipher;
        cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()), new ZeroBytePadding());
        cipher.init(false, keyiv);
        byte[] outText = new byte[cipher.getOutputSize(data.getBytes().length)];
        int outputLen = cipher.processBytes(data.getBytes(), 0, data.getBytes().length, outText, 0);
        try {
            cipher.doFinal(outText, outputLen);
        } catch (DataLengthException ex) {
        } catch (IllegalStateException ex) {
        } catch (InvalidCipherTextException ex) {
        }
        return new String(outText);
    }

    static public String encrypt3DES_iv(ParametersWithIV keyiv, String data) {

        BufferedBlockCipher cipher;
        cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()), new ZeroBytePadding());
        cipher.init(true, keyiv);
        byte[] outText = new byte[cipher.getOutputSize(data.getBytes().length)];
        int outputLen = cipher.processBytes(data.getBytes(), 0, data.getBytes().length, outText, 0);
        try {
            cipher.doFinal(outText, outputLen);
        } catch (DataLengthException ex) {
        } catch (IllegalStateException ex) {
        } catch (InvalidCipherTextException ex) {
        }
        return new String(outText);
    }

    private static RSAPrivateCrtKeyParameters getBCPrivateKeyFromString(String strPrivateKey) {
        try {
            PrivateKey prvKey = getPrivateKeyFromString(strPrivateKey);
            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            RSAPrivateCrtKeySpec pkSpec = keyFac.getKeySpec(prvKey, RSAPrivateCrtKeySpec.class);
            RSAPrivateCrtKeyParameters priv = new RSAPrivateCrtKeyParameters(pkSpec.getModulus(), pkSpec.getPublicExponent(), pkSpec.getPrivateExponent(), pkSpec.getPrimeP(), pkSpec.getPrimeQ(), pkSpec.getPrimeExponentP(), pkSpec.getPrimeExponentQ(), pkSpec.getCrtCoefficient());
            return priv;
        } catch (Exception e) {
            return null;
        }

    }

    private static RSAKeyParameters getBCPublicKeyFromString(String strPublicKey) {
        try {
            PublicKey prvKey = getPublicKeyFromString(strPublicKey);

            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec pkSpec = keyFac.getKeySpec(prvKey, RSAPublicKeySpec.class);


            RSAKeyParameters pub = new RSAKeyParameters(false, pkSpec.getModulus(), pkSpec.getPublicExponent());
            return pub;
        } catch (Exception e) {
            return null;
        }

    }

    public static String publicEncryptString(String message, String strPublicKey) {

        try {
            PublicKey publicKey = getPublicKeyFromString(strPublicKey);

            // 1. encode message
            String encodeMsg = encodeString(message, PASS_PHRASE);
//            String encodeMsg = new String(Base64.encode(message.getBytes()));
            // 2. encrypt message
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            byte[] input = encodeMsg.getBytes();

            Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            log.info("block size is_________" + cipher.getBlockSize());
            return byteToHex(cipher.doFinal(input));

        } catch (Exception ex) {
        }
        return strPublicKey;
    }

    public static String privateDecryptString(String message, String strPrivateKey) {
        try {
            PrivateKey privateKey = getPrivateKeyFromString(strPrivateKey);

            // 1. decrypt message
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            byte[] input = hexToBytes(message);

            Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");

            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] plainText = cipher.doFinal(input);

            // 2. encode message
            return decodeString(new String(plainText), PASS_PHRASE.length());
//            return new String(Base64.decode(new String(plainText)));
        } catch (Exception ex) {
            log.error("privateDecryptString", ex);
        }
        return strPrivateKey;
    }

    /**
     *
     * @param data
     * @return
     */
    public static String byteToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;

            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }

        return buf.toString();
    }

    public static byte[] hexToBytes(char[] hex) {
        int length = hex.length / 2;
        byte[] raw = new byte[length];
        for (int i = 0; i < length; i++) {
            int high = Character.digit(hex[i * 2], 16);
            int low = Character.digit(hex[i * 2 + 1], 16);
            int value = (high << 4) | low;
            if (value > 127) {
                value -= 256;
            }
            raw[i] = (byte) value;
        }
        return raw;
    }

    public static byte[] hexToBytes(String hex) {
        return hexToBytes(hex.toCharArray());
    }

    /**
     * create a message signature by using private key
     * @param data
     * @param strPrivateKey
     * @return
     */
    public static String createMsgSignature(String data, String strPrivateKey) {
        String encryptData = "";
        try {
            PrivateKey privateKey = getPrivateKeyFromString(strPrivateKey);
            java.security.Signature s = java.security.Signature.getInstance("SHA1withRSA");
            s.initSign(privateKey);
            s.update(data.getBytes());
            byte[] signature = s.sign();
            // Encrypt data
            encryptData = new String(Base64.encode(signature));
        } catch (Exception e) {
            log.error("createMsgSignature", e);
        }
        return encryptData;
    }

    /**
     * decrypt a message signature by using private key
     *
     * @param data
     * @param strPrivateKey
     * @return
     */
    public static boolean verifyMsgSignature(String encodeText, String strPublicKey, String input) {

        try {
            PublicKey publicKey = getPublicKeyFromString(strPublicKey);
            // decode base64
            byte[] base64Bytes = Base64.decode(encodeText);
            java.security.Signature sig = java.security.Signature.getInstance("SHA1WithRSA");
            sig.initVerify(publicKey);
            sig.update(input.getBytes());

            return sig.verify(base64Bytes);
        } catch (Exception e) {
            log.error("verifyMsgSignature", e);
        }
        return false;
    }

    /**
     * create a private key from an encode string
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKeyFromString(String key) throws Exception {
        PrivateKey privateKey = null;
        try {
            PEMReader reader = new PEMReader(new StringReader(key), null, "SunRsaSign");
            KeyPair pemPair = (KeyPair) reader.readObject();

            reader.close();

            privateKey = (PrivateKey) pemPair.getPrivate();
        } catch (Exception e) {
        }
        return privateKey;

    }

    /**
     * create a public key from an encode string
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKeyFromString(String key) throws Exception {
        PublicKey publicKey = null;
        try {
            PEMReader reader = new PEMReader(new StringReader(key), null, "SunRsaSign");
            publicKey = (PublicKey) reader.readObject();

            reader.close();


        } catch (Exception e) {
            log.error("getPublicKeyFromString", e);
        }
        return publicKey;
    }

    public static RSAkeyObj genKeys() throws NoSuchAlgorithmException {

        // Java Cryptography Provider
        RSAkeyObj rsaobj = new RSAkeyObj();

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        // Initialize and Generate
        kpg.initialize(1024, new SecureRandom());
        KeyPair keys = kpg.generateKeyPair();

        // Retrieve keys
        RSAPrivateKey privateKey = (RSAPrivateKey) keys.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keys.getPublic();
//        log.info(publicKey.toString());
        String privateKeyStr = new String(Base64.encode(privateKey.getEncoded()));
        String publicKeyStr = new String(Base64.encode(publicKey.getEncoded()));
        rsaobj.setPrivateKey(privateKeyStr);
        rsaobj.setPublicKey(publicKeyStr);
        return rsaobj;
    }

    public static String encodeString(String data, String sample) {

        StringBuilder res = new StringBuilder();

        if (data.length() > sample.length()) {
            for (int i = 0; i < sample.length(); i++) {
                res.append(sample.charAt(i));
                res.append(data.charAt(i));
            }
            res.append(data.substring(sample.length()));
        } else {
            for (int i = 0; i < data.length(); i++) {
                res.append(sample.charAt(i));
                res.append(data.charAt(i));
            }
            res.append(sample.substring(data.length()));
        }

        return new String(Base64.encode(res.toString().getBytes()));
    }

    public static String decodeString(String encode, int padd) {

        String res = new String(Base64.decode(encode));
        char[] item = new char[res.length() - padd];
        int j = 0;
        if (padd * 2 < res.length()) {
            // sample < data
            for (int i = 0; i < padd * 2; i++) {
                if (i % 2 == 1) {
                    item[j++] = res.charAt(i);
                }
            }
            for (int i = padd * 2; i < res.length(); i++) {
                item[j++] = res.charAt(i);
            }
        } else {
            log.info("padd = " + padd + " & len = " + res.length());
            // sample > data
            for (int i = 0; i < (res.length() - padd) * 2; i++) {
                if (i % 2 == 1) {
                    item[j++] = res.charAt(i);
                }
            }
        }

        return new String(item);
    }

    public static String encrypt3DES_iv(String message, String key) {

        byte[] skey = new byte[24];
        System.arraycopy(Base64.decode(key), 0, skey, 0, skey.length);
        byte[] iv = new byte[8];
        System.arraycopy(Base64.decode(key), skey.length, iv, 0, iv.length);
        ParametersWithIV keyiv = new ParametersWithIV(new KeyParameter(skey), iv);

        byte[] data = null;
        try {
            data = message.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            log.debug("ex", ex);
        }

        BufferedBlockCipher cipherIV;
        cipherIV = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()), new ZeroBytePadding());
        cipherIV.init(true, keyiv);
        byte[] outText = new byte[cipherIV.getOutputSize(data.length)];
        int outputLen = cipherIV.processBytes(data, 0, data.length, outText, 0);
        try {
            cipherIV.doFinal(outText, outputLen);
        } catch (DataLengthException ex) {
            log.debug("ex", ex);
        } catch (IllegalStateException ex) {
            log.debug("ex", ex);
        } catch (InvalidCipherTextException ex) {
            log.debug("ex", ex);
        } catch (Exception ex) {
            log.debug("encrypt 3des error:" + ex);
        }
        return byteToHex(outText);
    }

    public static String decrypt3DES_iv(String data, String key) {

        byte[] skey = new byte[24];
        System.arraycopy(Base64.decode(key), 0, skey, 0, skey.length);
        byte[] iv = new byte[8];
        System.arraycopy(Base64.decode(key), skey.length, iv, 0, iv.length);

        ParametersWithIV keyiv = new ParametersWithIV(new KeyParameter(skey), iv);

        BufferedBlockCipher cipherIV;
        cipherIV = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()), new ZeroBytePadding());
        cipherIV.init(false, keyiv);
        byte[] outText = new byte[cipherIV.getOutputSize(hexToBytes(data).length)];
        int outputLen = cipherIV.processBytes(hexToBytes(data), 0, hexToBytes(data).length, outText, 0);
        try {
            cipherIV.doFinal(outText, outputLen);
        } catch (DataLengthException ex) {
            log.info("ex" + ex);
        } catch (IllegalStateException ex) {
            log.info("ex" + ex);
        } catch (InvalidCipherTextException ex) {
            log.info("ex" + ex);
        }
        String returnText = "";
        try {
            returnText = new String(outText, "UTF-8");
            returnText = returnText.trim();
        } catch (UnsupportedEncodingException ex) {
            log.debug("ex", ex);
        }
        return returnText;
    }

    public static String DESKeyGen() {

        DESedeKeyGenerator keyGen = new DESedeKeyGenerator();
        keyGen.init(new KeyGenerationParameters(new SecureRandom(), 192));
        byte[] kB = keyGen.generateKey();
        log.info("skey:" + new String(Hex.encode(kB)));
        byte[] iv = new byte[8];
        SecureRandom r = new SecureRandom();
        r.nextBytes(iv);
        log.info("iv:" + new String(Hex.encode(iv)));
        if (kB.length != 24) {
            log.info("DESede bit key wrong length.");
        }
        byte[] sk = new byte[32];
        System.arraycopy(kB, 0, sk, 0, kB.length);
        System.arraycopy(iv, 0, sk, kB.length, iv.length);
        log.info("sk:" + new String(Hex.encode(sk)));
        return byteToHex(sk);
    }

    /**
     * Helper function that dump an array of bytes in hex form
     *
     * @param buffer The bytes array to dump
     * @return A string representation of the array of bytes
     */
    public final String dumpBytes(byte[] buffer) {
        if (buffer == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < buffer.length; i++) {
            sb.append("0x").append((char) (HEX_CHAR[(buffer[i] & 0x00F0) >> 4])).append(
                    (char) (HEX_CHAR[buffer[i] & 0x000F])).append(" ");
        }

        return sb.toString();
    }

    public static String getKeyFile(String filePath) {
        File file = new File(filePath);
        StringBuilder contents = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                contents.append(text)
                        .append(System.getProperty(
                        "line.separator"));
            }
        } catch (IOException e) {
            log.error("getKeyFile", e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                log.error("getKeyFile", e);
            }
        }

        return contents.toString();
    }

    public static String AESKeyGen() throws NoSuchAlgorithmException {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128, new SecureRandom());
            SecretKey secretKey = keyGen.generateKey();
            return EncryptManager.byteToHex(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException noSuchAlgo) {
            log.error(noSuchAlgo.getMessage());
        }
        return null;
    }

    public static String encryptAES(String data, String key) throws Exception {
        String dataEncrypted = new String();
        try {
            Cipher aesCipher = Cipher.getInstance("AES");
            byte[] raw = hexToBytes(key);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] byteDataToEncrypt = data.getBytes();
            byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
            dataEncrypted = new BASE64Encoder().encode(byteCipherText);
            return dataEncrypted;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return dataEncrypted;
    }

    public static String decryptAES(String dataEncrypt, String key) throws Exception {
        String dataDecrypted = new String();
        try {
            Cipher aesCipher = Cipher.getInstance("AES");
            byte[] raw = hexToBytes(key);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(dataEncrypt);
            aesCipher.init(Cipher.DECRYPT_MODE, skeySpec, aesCipher.getParameters());
            byte[] byteDecryptedText = aesCipher.doFinal(decordedValue);
            dataDecrypted = new String(byteDecryptedText);
            return dataDecrypted;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return dataDecrypted;
    }

    public static void main(String a[]) throws Exception {
//        EncryptManager enc = new EncryptManager();
//        RSAkeyObj rsaKey = genKeys();
//        log.info("public key :"+rsaKey.getPublicKey());
//        log.info("***********************************************");
//        log.info("private key :"+rsaKey.getPrivateKey());


//        KeyPair pair = new KeyPair(getPublicKeyFromString(publickey), getPrivateKeyFromString(rsaKey.getPrivateKey()));


        //gen key RSA private, public key
//        RSAkeyObj rsaobj = new RSAkeyObj();

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        // Initialize and Generate
        kpg.initialize(2048, new SecureRandom());
        KeyPair pair = kpg.generateKeyPair();
        
        PEMWriter privWriter = new PEMWriter(new FileWriter("/home/vas-ncpt-manhps/privatekey_iweb_immc.pem"));
        privWriter.writeObject(pair.getPrivate());
        privWriter.close();

        privWriter = new PEMWriter(new FileWriter("/home/vas-ncpt-manhps/publickey_iweb_immc.pem"));
        privWriter.writeObject(pair.getPublic());
        privWriter.close();

//        String privKey = getKeyFile("/home/vas-ncpt-manhps/privatekey.pem");
//        String pubKey = getKeyFile("/home/vas-ncpt-manhps/publickey.pem");
//
//        String input = "src=9078&dest=84977173620&mtseq=111&msgtype=text&msgtitle=LoveStory&msgbody=Test_SMS&moseq=12345&procresult=1&mttotalseg=1&mtsegref=1&cpid=0&serviceid=201";
//        String value = "";
//        value = encrypt3DES_iv(input, TRIPLE_DES_KEY);
//        log.info("value: " + value);
//        log.info("data decrypt: " + decrypt3DES_iv(value, TRIPLE_DES_KEY));
//
//        String signature = URLEncoder.encode(createMsgSignature(value, privKey));
//        boolean kq = verifyMsgSignature(URLDecoder.decode(signature), pubKey, value);
//        log.info("signature: " + signature);
//        log.info("verify: " + kq);

//          DESKeyGen();
//        String key = AESKeyGen();
//        System.out.println("key AES: " + key);
//        

    }
    private static String PASS_PHRASE = "zaq1xsw2cde3vfr4";
//    private BufferedBlockCipher cipher;
    private static Logger log = Logger.getLogger(EncryptManager.class);
    private static String TRIPLE_DES_KEY = "pG6uWTV3DdIFgiWh3f0G6wqXRsZqbdpQAAAAAAAAAAA=";
    private static final byte[] HEX_CHAR = new byte[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
}
