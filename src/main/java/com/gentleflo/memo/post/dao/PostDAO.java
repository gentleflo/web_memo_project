package com.gentleflo.memo.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gentleflo.memo.post.model.Post;

@Repository
public interface PostDAO {
	
	public int insertPost(
			@Param("userId") int userId
			, @Param("subject") String subject
			, @Param("content") String content
			, @Param("imagePath") String imagePath);
	
	public List<Post> selectMemoList(@Param("userId") int userId);
	
	public Post selectMemo(
			@Param("id") int id
			, @Param("userId") int userId);
	
	public int deleteMemo(@Param("id") int id
			, @Param("userId") int userId);
	
	public int updateMemo(@Param("id") int id
			, @Param("subject") String subject
			, @Param("content") String content
			, @Param("userId") int userId);
}
