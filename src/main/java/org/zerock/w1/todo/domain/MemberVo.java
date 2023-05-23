package org.zerock.w1.todo.domain;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVo {
    private String id;
    private String pw;
    private String name;
    private String uuid;
}
