package library;

public interface LibraryInterface {

	static WebLibrary _webLibInterface = WebLibrary.getWebLibrary();
	static ReportLibrary _reportLibInterface = ReportLibrary.getReportLibrary();

}
