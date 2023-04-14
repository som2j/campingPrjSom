package com.trillon.camp.suggest.repository;

<<<<<<< Updated upstream
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.trillon.camp.suggest.dto.APIParsing;
import com.trillon.camp.suggest.dto.Answer;
import com.trillon.camp.suggest.dto.Campsite;

@Repository
public interface SuggestRepository {

	
	@Insert("insert into CampingData (addr1, doNm,sigunguNm , facltDivNm, facltNm,featureNm,intro,lctCl,lineIntro,mapX,mapY,resveUrl,tel,firstImageUrl,homepage,induty,tooltip) "
			+ "values(#{addr1}, #{doNm},#{sigunguNm}, #{facltDivNm},#{facltNm},#{featureNm},#{intro} , #{lctCl},#{lineIntro},#{mapX}, #{mapY}, #{resveUrl},#{tel},#{firstImageUrl}, #{homepage}, #{induty},#{tooltip})")
	void insertNewCampingData(APIParsing testData);

	
	
	@Select("select * from campingData where (doNm=#{doNm} and sigunguNm=#{sigunguNm}) and (lctCl like #{lctCl}) and (induty like #{induty})")
	List<Campsite> findCampingByAnswer(Answer answer);


	@Select("select * from campingData where (doNm=#{doNm} and sigunguNm=#{sigunguNm}) and (induty like #{induty})")
	List<Campsite> findCampingByAnswerWithoutInduty(Answer answer);

=======
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestRepository {

>>>>>>> Stashed changes
}
