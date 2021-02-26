import java.io.UnsupportedEncodingException;
import java.util.Base64;

public final class Base64Utils {
    private Base64Utils() {
    }

    public static String encodeToStringBasic(String str) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
    }

    public static String encodeToStringBasic(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decoderFromBasic(String str) {
        return Base64.getDecoder().decode(str);
    }

    public static String decoderStringFromBasic(String str) throws UnsupportedEncodingException {
        byte[] decode = Base64.getDecoder().decode(str);
        return new String(decode, "utf-8");
    }

    public static String encodeToStringUrl(String str) throws UnsupportedEncodingException {
        return Base64.getUrlEncoder().encodeToString(str.getBytes("utf-8"));
    }

    public static String encodeToStringUrl(byte[] bytes) {
        return Base64.getUrlEncoder().encodeToString(bytes);
    }

    public static byte[] decoderFromUrl(String str) {
        return Base64.getUrlDecoder().decode(str);
    }

    public static String decoderStringFromUrl(String str) throws UnsupportedEncodingException {
        byte[] decode = Base64.getUrlDecoder().decode(str);
        return new String(decode, "utf-8");
    }

    public static String encodeToStringMime(String str) throws UnsupportedEncodingException {
        return Base64.getMimeEncoder().encodeToString(str.getBytes("utf-8"));
    }

    public static String encodeToStringMime(byte[] bytes) {
        return Base64.getMimeEncoder().encodeToString(bytes);
    }

    public static byte[] decoderFromMime(String str) {
        return Base64.getMimeDecoder().decode(str);
    }

    public static String decoderStringFromMime(String str) throws UnsupportedEncodingException {
        byte[] decode = Base64.getMimeDecoder().decode(str);
        return new String(decode, "utf-8");
    }
}