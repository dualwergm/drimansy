package com.dg.drimansy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dg.drimansy.model.DailyOut;

public interface IDailyOutRepository extends JpaRepository<DailyOut, Long>{
	@Query("Select d from DailyOut d order by d.date desc")
	public List<DailyOut> findAllOrdered();
	
	@Query("Select d from DailyOut d where d.date = :date and d.car.id = :carId")
	public List<DailyOut> findByCarAndDate(@Param("carId") Long carId, @Param("date") Date date);
}
