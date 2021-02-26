import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class AESUtils {
    private static String transform = "AES/CTR/NoPadding";
    private static int ivSize = 16;
    private static String algorithm = "AES";
    //private static String macAlgorithm = "HmacSHA256";
    private static String macAlgorithm = "V4aJLtkywr99UEinkYJh2T9bsWqGxqHb";
    private static String encryptKey = "23VA47fguGBbPPVZRyAiDBDD7FkteNZD";

    /**
     * description 解密
     * @param encryptData
     * @return java.lang.String
     * @Author huangxiaodong
     * @create_time 2019-09-17 11:51
     */
    public static String decode(String encryptData){
        String decodeData=null;
        try{
            if (StringUtils.isBlank(encryptData)){
                return encryptData;
            }

            byte[] encryptDataBytes= Base64Utils.decoderFromBasic(encryptData);

            byte[] originalText = Arrays.copyOfRange(encryptDataBytes, ivSize, encryptDataBytes.length);

            Cipher aesCipher = Cipher.getInstance(transform);
            aesCipher.init(2, new SecretKeySpec(encryptKey.getBytes(), algorithm), new IvParameterSpec(Arrays.copyOfRange(encryptDataBytes, 0, ivSize)));
            decodeData= new String(aesCipher.doFinal(originalText));
        }catch (Exception e) {
        }
        return decodeData;
    }

    /**
     * description 加密
     * @param data
     * @return java.lang.String
     * @Author huangxiaodong
     * @create_time 2019-09-17 11:51
     */
    public static String encrypt(String data){
        String encreptData=null;
        try {
            if (StringUtils.isBlank(data)){
                return data;
            }
            Cipher aesCipher = Cipher.getInstance(transform);
            byte[] iv = new byte[ivSize];
            (new SecureRandom()).nextBytes(iv);
            aesCipher.init(1, new SecretKeySpec(encryptKey.getBytes(), algorithm), new IvParameterSpec(iv));
            byte[] originalText = aesCipher.doFinal(data.getBytes());

            encreptData= Base64Utils.encodeToStringBasic(ArrayUtils.addAll(iv, originalText));
        } catch (Exception e) {

        }
        return encreptData;
    }

    public static void main(String[] args) {
        String[] str=new String[]{
                "5GI4F4LZDW5/8RoyEth8uJn+FPGVSgM8vFs="
        };
        decode(str);
    }

    private static void decode(String [] str){
        for (String s:str){
            System.out.println(s+"->"+AESUtils.decode(s));
        }
    }

}