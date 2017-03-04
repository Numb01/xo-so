package inet.util;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author huyenntb
 */
public class DataEncrypter {
    public static final String ENCRYPT_KEY = "62cab5a7ed05a39cb38ce9e94c06a0ee";
    public static final String ENCRYPT_VECTOR = "ccdca97d71fe3247";
    
    public static final String DATA_ENCRYPT_KEY = "458c67d36ab7de26d9db5b0cf9d613e6";
    public static final String DATE_ENCRYPT_VECTOR = "ed5158c1ad980148";
    
    
    CryptLib _crypt = null;

    public DataEncrypter() {
        try {
            _crypt = new CryptLib();
        } catch (NoSuchAlgorithmException e) {
            
        }catch(NoSuchPaddingException e){
            
        }
    }

    public String encrypt(String data) throws Exception {
        CryptLib crypt = new CryptLib();
        String dataEncrypted = crypt.encrypt(data, ENCRYPT_KEY, ENCRYPT_VECTOR);
        return dataEncrypted;
    }
    
    public String dataEncrypt(String data) throws Exception {
        CryptLib crypt = new CryptLib();
        String dataEncrypted = crypt.encrypt(data, DATA_ENCRYPT_KEY, DATE_ENCRYPT_VECTOR);
        return dataEncrypted;
    }

    public String decrypt(String dataEncrypter) throws Exception {
        String data = _crypt.decrypt(dataEncrypter, ENCRYPT_KEY, ENCRYPT_VECTOR);
        return data;
    }

    public String dataDecrypt(String dataEncrypter) throws Exception {
        String data = _crypt.decrypt(dataEncrypter, DATA_ENCRYPT_KEY, DATE_ENCRYPT_VECTOR);
        return data;
    }
    
    public static String buildData(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static String buildDataEncrypt(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            sb.append(key).append("=").append(map.get(key)).append("|");
        }
        return sb.substring(0, sb.length() - 1);
    }

}
