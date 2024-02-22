package ch10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";

	// DB 연결을 가져오는 메서드, DBCP를 사용하는 것이 좋음
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 뉴스를 추가하는 메소드
	public void addNews(News n) throws Exception {
		Connection conn = open();

		String sql = "insert into news(title,img,date,content) values(?,?,CURRENT_TIMESTAMP(),?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try {
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3, n.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	// 뉴스 기사 목록 전체를 불러오는 메소드
	public List<News> getAll() throws Exception {
		Connection conn = open();
		List<News> newsList = new ArrayList<>();

		String sql = "select aid, title, PARSEDATETIME(date, 'yyyy-MM-dd HH:mm:ss.SSSSSS') as cdate from news";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		try {
			while(rs.next()) {
				News n = new News();
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setDate(rs.getString("cdate"));

				newsList.add(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsList;

	}

	// 특정 뉴스 기사의 세부 내용을 보여주기 위한 메서드.
	public News getNews(int aid) throws SQLException {
		Connection conn = open();

		News n = new News();

		String sql = "select aid, title, img, PARSEDATETIME(date, 'yyyy-MM-dd HH:mm:ss.SSSSSS') as cdate, content from news where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();

		rs.next();

		try {
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setImg(rs.getString("img"));
			n.setDate(rs.getString("cdate"));
			n.setContent(rs.getString("content"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n;
	}
	
	// 특정 뉴스를 삭제하는 메서드
	public void delNews(int aid) throws SQLException {
		Connection conn = open();
		
		String sql = "delete from news where aid = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try {
			pstmt.setInt(1, aid);
			
			// 쿼리문 실행 = 0 ----> executeUpdate()는 int 타입 반환 || 삭제된 뉴스가 없는 경우
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
