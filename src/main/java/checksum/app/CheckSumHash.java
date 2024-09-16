package checksum.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class CheckSumHash implements GenerateHash {

	public CheckSumHash() {
	}

	private MessageDigest createDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String generateHash(String text, String algorithm) {
		MessageDigest digest = createDigest(algorithm);
		if (digest == null) {
			return "";
		}
		digest.reset();
		digest.update(text.getBytes());
		byte[] digestBytes = digest.digest();
		return DatatypeConverter.printHexBinary(digestBytes).toLowerCase();
	}

	private String generateHash(byte[] bytes, String algorithm) {
		MessageDigest digest = createDigest(algorithm);
		if (digest == null) {
			return "";
		}
		digest.reset();
		digest.update(bytes);
		byte[] digestBytes = digest.digest();
		return DatatypeConverter.printHexBinary(digestBytes).toLowerCase();
	}

	@Override
	public String MD5(String text) {
		if (text == null) {
			return "";
		}
		return generateHash(text, DigestAlgorithmEnum.MD5.getValue());
	}

	@Override
	public String MD5(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		return generateHash(bytes, DigestAlgorithmEnum.MD5.getValue());
	}

	@Override
	public String SHA1(String text) {
		if (text == null) {
			return "";
		}
		return generateHash(text, DigestAlgorithmEnum.SHA1.getValue());
	}

	@Override
	public String SHA1(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		return generateHash(bytes, DigestAlgorithmEnum.SHA1.getValue());
	}

	@Override
	public String SHA224(String text) {
		if (text == null) {
			return "";
		}
		return generateHash(text, DigestAlgorithmEnum.SHA224.getValue());
	}

	@Override
	public String SHA224(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		return generateHash(bytes, DigestAlgorithmEnum.SHA224.getValue());
	}

	@Override
	public String SHA256(String text) {
		if (text == null) {
			return "";
		}
		return generateHash(text, DigestAlgorithmEnum.SHA256.getValue());
	}

	@Override
	public String SHA256(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		return generateHash(bytes, DigestAlgorithmEnum.SHA256.getValue());
	}

	@Override
	public String SHA384(String text) {
		if (text == null) {
			return "";
		}
		return generateHash(text, DigestAlgorithmEnum.SHA384.getValue());
	}

	@Override
	public String SHA384(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		return generateHash(bytes, DigestAlgorithmEnum.SHA384.getValue());
	}

	@Override
	public String SHA512(String text) {
		if (text == null) {
			return "";
		}
		return generateHash(text, DigestAlgorithmEnum.SHA512.getValue());
	}

	@Override
	public String SHA512(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		return generateHash(bytes, DigestAlgorithmEnum.SHA512.getValue());
	}

}
