package org.zerock.w1.todo.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.zerock.w1.todo.dao.TodoDao;
import org.zerock.w1.todo.domain.TodoVo;
import org.zerock.w1.todo.dto.TodoDto;
import org.zerock.w1.todo.util.MapperUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public enum TodoService {
    INSTANCE;

    private TodoDao dao;
    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDao();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDto todoDto) throws SQLException {
        log.info("service - register: " + todoDto);
        TodoVo vo = modelMapper.map(todoDto, TodoVo.class);
        log.info("todoVo: " + vo);
        dao.insert(vo);
    }

    public List<TodoDto> getList() throws SQLException {
        log.info("service - getList");
        List<TodoVo> voList = dao.selectAll();
        List<TodoDto> dtoList = new ArrayList<>(voList.size());
        voList.forEach(vo -> {
            TodoDto dto = modelMapper.map(vo, TodoDto.class);
            dtoList.add(dto);
        });
        return dtoList;
    }

    public List<TodoDto> getTestList() {
        log.info("service - getTestList");
        return IntStream.range(0, 10)
                .mapToObj(i ->
                        TodoDto.builder()
                                .tno((long)i)
                                .title("Todo.." + i)
                                .dueDate(LocalDate.now())
                                .build())
                .collect(Collectors.toList());
    }

    public TodoDto get(Long tno) throws SQLException {
        log.info("service - get: " + tno);
        TodoVo vo = dao.selectOne(tno);
        TodoDto dto = modelMapper.map(vo, TodoDto.class);
        return dto;
    }

    public void remove(Long tno) throws SQLException {
        log.info("service - remove: " + tno);
        dao.deleteOne(tno);
    }

    public void modify(TodoDto dto) throws SQLException {
        log.info("service - modify: " + dto);
        TodoVo vo = modelMapper.map(dto, TodoVo.class);
        dao.updateOne(vo);
    }
}
