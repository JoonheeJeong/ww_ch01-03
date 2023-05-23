package org.zerock.w1.todo.service;

import org.modelmapper.ModelMapper;
import org.zerock.w1.todo.dao.MemberDao;
import org.zerock.w1.todo.domain.MemberVo;
import org.zerock.w1.todo.dto.MemberDto;
import org.zerock.w1.todo.util.MapperUtil;

import java.sql.SQLException;

public enum MemberService {
    INSTANCE;

    private final MemberDao dao;
    private final ModelMapper mapper;

    MemberService() {
        dao = MemberDao.INSTANCE;
        mapper = MapperUtil.INSTANCE.get();
    }

    public MemberDto login(String id, String pw) throws SQLException {
        MemberVo vo = dao.selectOneWithPassword(id, pw);
        return mapper.map(vo, MemberDto.class);
    }

    public void updateUuid(String id, String uuid) throws SQLException {
        dao.updateUuid(id, uuid);
    }

    public MemberDto getByUuid(String uuid) throws SQLException {
        MemberVo vo = dao.selectOneByUuid(uuid);
        return mapper.map(vo, MemberDto.class);
    }
}
