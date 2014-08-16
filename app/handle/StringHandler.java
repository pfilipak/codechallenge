package handle;


public class StringHandler {

	private static final String QUOTE = "\"";
	private static final String EMPTY = "";
	private static final String NULL = "NULL";

	public String nullHandle(String content){
		return NULL.equals(content) ? EMPTY : content.replace(QUOTE, EMPTY);
	}
	
	public KeyValue concatAttribute(String content, String[] attributes, Integer index) {
		if (!"NULL".equals(content) && !content.endsWith("\"")) {
			index++;
			return concatAttribute((content + attributes[index]), attributes, index);
		}
		return new KeyValue((++index), nullHandle(content));
	}

}
