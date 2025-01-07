package ma.entraide.ramadan.controller;


	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class ResponseData {

	    public String getFileName() {
			return fileName;
		}
		public ResponseData() {
			super();
		}
		public ResponseData(String fileName, String downloadURL, String fileType, long fileSize) {
			super();
			this.fileName = fileName;
			this.downloadURL = downloadURL;
			this.fileType = fileType;
			this.fileSize = fileSize;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getDownloadURL() {
			return downloadURL;
		}
		public void setDownloadURL(String downloadURL) {
			this.downloadURL = downloadURL;
		}
		public String getFileType() {
			return fileType;
		}
		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
		public long getFileSize() {
			return fileSize;
		}
		public void setFileSize(long fileSize) {
			this.fileSize = fileSize;
		}
		private String fileName;
	    private String downloadURL;
	    private String fileType;
	    private long fileSize;
	}

