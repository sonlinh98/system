package jsoft;

public interface ShareControl {

	// Điều khiển chia sẻ CP giữa các Basic
	public ConnectionPool getCP();

	// Yêu cầu đối tượng trả lại kết nối
	public void releaseConnection();

}
