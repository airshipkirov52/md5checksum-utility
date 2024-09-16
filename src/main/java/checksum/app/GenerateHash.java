package checksum.app;

public interface GenerateHash {

	String MD5(String text);

	String MD5(byte[] bytes);
	
	String SHA1(String text);

	String SHA1(byte[] bytes);
	
	String SHA224(String text);

	String SHA224(byte[] bytes);

	String SHA256(String text);

	String SHA256(byte[] bytes);
	
	String SHA384(String text);

	String SHA384(byte[] bytes);
	
	String SHA512(String text);

	String SHA512(byte[] bytes);

}
