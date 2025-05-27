import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class OTPUtilities {

    public static String generateTOTP(byte[] secret, long timeStep, int digits) {
        long currentTime = System.currentTimeMillis() / 1000;
        long counter = currentTime / timeStep;

        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(counter);
        byte[] counterBytes = buffer.array();

        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec keySpec = new SecretKeySpec(secret, "HmacSHA1");
            mac.init(keySpec);
            byte[] hash = mac.doFinal(counterBytes);

            int offset = hash[hash.length - 1] & 0x0F;
            int binary = ((hash[offset] & 0x7f) << 24) |
                    ((hash[offset + 1] & 0xff) << 16) |
                    ((hash[offset + 2] & 0xff) << 8) |
                    (hash[offset + 3] & 0xff);

            int otp = binary % (int) Math.pow(10, digits);
            return String.format("%0" + digits + "d", otp);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }
}


// Cara Pake
// String secret = "SYDYDYYDYDY"
// byte[] secret = Base64.getDecoder().decode(secret);
// String otp = OTPUtilities.generateTOTP(secret, 30, 6);
// System.out.println("TOTP: " + otp);