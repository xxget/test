package wiqs;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 初始化密钥的方法1
 * @author xxg
 */
public class PasswdTest {
	public static void main(String[] args) {
		// 算法名称
		String algorithmName = "md5";
		// 散列对象
		String userPassword = "admin";
		// 散列使用的salt值
		String salt = "8516a292bfba7836908b56aa725e448e";
		// 散列次数
		int hashIterations = 2;
		//获得的密码
		String password = new SimpleHash(algorithmName, userPassword, ByteSource.Util.bytes(salt), hashIterations).toHex();
		System.out.println(password);
	}
}
