package com.study.springboot.board.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.springboot.board.model.Board;
import com.study.springboot.board.model.ReplyCommand;

@Repository
public class BoardDao {
//	private int bno;
//	private int empno;
//	private String title;
//	private String content;
//	private Date date;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Board insert(Board board) {

		String sql = "insert into board(mno,title,content,date) values(?,?,?,now())";
		int suc = jdbcTemplate.update(sql, board.getMno(), board.getTitle(), board.getContent());
		if (suc > 0) {
			Integer lastId = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
			return new Board(lastId, board.getMno(), board.getTitle(), board.getContent(), board.getDate());
		}
		return null;

	}

	public List<Board> select(int startRow, int size) throws SQLException {
		String sql = "select * from board order by bno desc limit ?, ?";
		List<Board> list = jdbcTemplate.query(sql, (rs, r) -> {
			Board board = new Board(rs.getInt("bno"), rs.getInt("mno"), rs.getString("title"),
					rs.getString("content"), rs.getString("date"));
			return board;
		}, startRow, size);

		return list;
	}
	public List<Board> mySelect(int startRow, int size,Long mno) throws SQLException {
		String sql = "select * from board where mno = ? order by bno desc limit ?, ?";
		List<Board> list = jdbcTemplate.query(sql, (rs, r) -> {
			Board board = new Board(rs.getInt("bno"), rs.getInt("mno"), rs.getString("title"),
					rs.getString("content"), rs.getString("date"));
			return board;
		},mno ,startRow, size);

		return list;
	}

	public void replyUpdate(ReplyCommand reply) {
		String sql = "update board set content = ? where bno = ?";
		jdbcTemplate.update(sql, reply.getContent(),reply.getBno());
		
	}
	public int selectCount() throws SQLException {

		String sql = "select count(*) from board";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public int mySelectCount(Long mno) throws SQLException {

		String sql = "select count(*) from board where mno = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class,mno);
	}

	public Board selectBoard(int bno) {
		String sql = "select * from board where bno = ?";

		List<Board> list = jdbcTemplate.query(sql, (rs, r) -> {
			Board board = new Board(rs.getInt("bno"), rs.getInt("mno"), rs.getString("title"),
					rs.getString("content"), rs.getString("date"));
			return board;
		}, bno);
		return list.isEmpty() ? null : list.get(0);
	}

}
