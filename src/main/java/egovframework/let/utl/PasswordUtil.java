package egovframework.let.utl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 유틸리티 클래스는 보통 모든 메서드가 인스턴스를 만들지 않고도 사용할 수 있도록 static으로 선언
// 인스턴스화 없이 클래스 이름으로 직접 호출하는 방식(@Autowired 사용 안함)
public class PasswordUtil {

	public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
