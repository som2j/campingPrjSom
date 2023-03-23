package com.trillon.camp.comewithme.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.trillon.camp.comewithme.common.Paging;
import com.trillon.camp.comewithme.dto.Answer;
import com.trillon.camp.comewithme.dto.ComeWithMeBoard;

@Repository
public interface ComeWithMeRepository {
	
	@Insert("insert into comewithme_board(title, content, num_of_person, place, camping_way, age_average) "
			+ "values(#{title}, #{content}, #{num_of_person}, #{place}, #{camping_way}, #{age_average})")
	@Options(useGeneratedKeys = true, keyProperty = "bdIdx")
	public void insertBoard(ComeWithMeBoard board);

	@Select("select count(*) from comewithme_board where is_del = 0")
	public int countAllBoard();
	
	@Select("select * from comewithme_board where is_del = 0 order by ${sortColumn} ${sortDirection} limit #{start}, #{cntPerPage}")
	List<ComeWithMeBoard> selectBoardList(Paging paging);


	@Select("select * from comewithme_board where place = #{place} and camping_way = #{campingWay} and age_average = #{ageAverage} ")
	List<ComeWithMeBoard> selectMatchList(Answer answer);
	
	@Select("select * from comewithme_board where is_del = 0 and bd_idx = #{bdIdx}")
	ComeWithMeBoard selectBoardByBdIdx(int bdIdx);


}


























