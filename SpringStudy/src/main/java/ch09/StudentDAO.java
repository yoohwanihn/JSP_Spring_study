package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	Connection conn = null;
	PreparedStatement pstmt;
	String sql = "";

	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";

	// 편의상 DB연결 메소드 만듬
	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 편의상 DB연결 해제 메소드 만듬
	public void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 학생을 등록하는 메소드
	public void insert(Student s) {
		open();
		sql = "INSERT INTO student(username, univ, birth, email) values(?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getUsername());
			pstmt.setString(2, s.getUniv());
			pstmt.setDate(3, s.getBirth());
			pstmt.setString(4, s.getEmail());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 학생 목록 메소드
	public List<Student> getAll() {
		open();
		List<Student> students = new ArrayList<>();
		sql = "select * from student";

		try {
			if (conn != null) {
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					Student s = new Student();
					s.setId(rs.getInt("id"));
					s.setUsername(rs.getString("username"));
					s.setUniv(rs.getString("univ"));
					s.setBirth(rs.getDate("birth"));
					s.setEmail(rs.getString("email"));

					students.add(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return students;
	}

}
