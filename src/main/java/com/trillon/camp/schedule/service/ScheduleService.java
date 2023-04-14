package com.trillon.camp.schedule.service;

import java.util.List;

import com.trillon.camp.schedule.dto.Schedule;

public interface ScheduleService {

	public List<Schedule> selectTodo();
	
	void insertNewTodo(Schedule schedule);

	void deleteTodo(Schedule schedule);

	void insertNewTodoMadyByGroup(Schedule schedule);

	
}
