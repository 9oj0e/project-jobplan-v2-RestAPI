package shop.mtcoding.projectjobplan.board;

import jakarta.persistence.*;
import lombok.Builder;
import shop.mtcoding.projectjobplan.user.User;

public class BoardDTO {

    private Integer id;

    private User user;
    private String title; // 제목
    private String content; // 내용
    private String field; // 채용 분야
    private String position; // 포지션
    private String salary; // 연봉

    @Builder
    public BoardDTO(Integer id, User user, String title, String content, String field, String position, String salary) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.field = field;
        this.position = position;
        this.salary = salary;
    }

    public BoardDTO(Board board) {
        this.id = board.getId();
        this.user = board.getUser();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.field = board.getField();
        this.position = board.getPosition();
        this.salary = board.getSalary();
    }
}
