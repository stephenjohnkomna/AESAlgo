import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
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

    public static void main(String[] args) throws IOException {
      /*  String[] str=new String[]{
                "qJTSurcikAKa6iQzEzrPteQ2m5fmrZFd/o4="
        };
        decode(str);*/

        String fileName = "/Users/mac/Documents/project/untitled folder/AESAlgo/src/test/encrypted-bank-account-numbers.csv";
        List<String> accountNoList = getListOfEncryptedAccountnumber(fileName);
        writeDecrptedAccountNumToCSVFile(accountNoList);
    }






    private static void writeDecrptedAccountNumToCSVFile(List<String> lstAccountNo) throws IOException {
        String csv = "/Users/mac/Documents/project/untitled folder/AESAlgo/src/test/Outputdata.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));

        //Create record
        String [] record = lstAccountNo.toArray(new String[0]);
        //Write the record to file
        writer.writeNext(record);

        //close the writer
        writer.close();
    }

    private static List<String> getListOfEncryptedAccountnumber(String fileName) throws IOException {
        List<AccountDetails> beans = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(AccountDetails.class)
                .build()
                .parse();

        return  beans.parallelStream()
                .map(data-> decode(data.getRecipient_bank_account_no_encrypted()))
                .collect(Collectors.toList());
    }

    private static String decode(String [] str){
       String result = AESUtils.decode(str);
       return result;
    }

}