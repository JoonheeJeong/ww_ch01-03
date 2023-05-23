package org.zerock.w1.todo.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoVo {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
