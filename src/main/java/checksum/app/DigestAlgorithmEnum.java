package checksum.app;

public enum DigestAlgorithmEnum {
	SHA1("SHA-1"), 
	MD2("MD2"),
	MD5("MD5"),
	SHA512_256("SHA-512/256"),
	SHA3512("SHA3-512"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512_224("SHA-512/224"), 
	SHA512("SHA-512"), 
	SHA3256("SHA3-256"),
	SHA224("SHA-224"),
	SHA3384("SHA3-384"),
	SHA3224("SHA3-224");

	private String value;

	DigestAlgorithmEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
