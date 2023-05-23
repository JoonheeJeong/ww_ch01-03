package org.zerock.w1.todo.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
