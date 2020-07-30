package br.com.softblue.bluefood.util;

public enum FileType {

	PNG("image/png", "png"), 
	JPG("image/jpeg", "jpg");

	String mimeType;
	String extension;

	private FileType(String mimeType, String extension) {
		this.mimeType = mimeType;
		this.extension = extension;
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getExtension() {
		return extension;
	}

	private boolean sameOf(String mimiType) {
		return this.mimeType.equalsIgnoreCase(mimiType);
	}

	public static FileType of(String mimeType) {
		for (FileType fileType : values()) {
			if(fileType.sameOf(mimeType)) {
				return fileType;
			}
		}
		
		return null;
	}

}
