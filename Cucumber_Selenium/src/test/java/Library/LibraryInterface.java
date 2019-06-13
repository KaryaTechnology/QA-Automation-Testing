package Library;

public interface LibraryInterface {

	static WebLibrary _webLibInterface = WebLibrary.getWebLibrary();
	static ReportLibrary _reportLibInterface = ReportLibrary.getReportLibrary();
	static FileLibrary _fileLibInstance = FileLibrary.getFileLibrary();
}
