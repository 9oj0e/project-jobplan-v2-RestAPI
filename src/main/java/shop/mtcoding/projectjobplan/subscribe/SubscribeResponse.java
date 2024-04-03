package shop.mtcoding.projectjobplan.subscribe;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan._core.utils.PagingUtil;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SubscribeResponse {
    @Data
    public static class DTO {
        private Page<?> page;
        private List<Integer> pageList;

        public DTO(User user, List<Subscribe> subscription, Pageable pageable) {
            if (user.getIsEmployer()) {
                List<ResumeDTO> resumeList = subscription.stream().map(subscribe -> new ResumeDTO(subscribe.getResume())).toList();
                this.page = PagingUtil.pageConverter(pageable, resumeList);
            } else {
                List<BoardDTO> boardList = subscription.stream().map(subscribe -> new BoardDTO(subscribe.getBoard())).toList();
                this.page = PagingUtil.pageConverter(pageable, boardList);
            }
            this.pageList = PagingUtil.getPageList(this.page);
        }

        // (개인) 공고 구독 목록
        @Data
        public class BoardDTO {
            private String address;
            private String businessName;
            private Integer boardId;
            private String field;
            private String title;
            private String salary;
            private Timestamp closingDate;

            public String getClosingDate() {
                return FormatUtil.timeFormatter(this.closingDate);
            }

            public BoardDTO(Board board) {
                this.address = board.getUser().getAddress();
                this.businessName = board.getUser().getBusinessName();
                this.boardId = board.getId();
                this.field = board.getField();
                this.title = board.getTitle();
                this.salary = board.getSalary();
                this.closingDate = board.getClosingDate();
            }
        }

        // (기업) 이력서 구독 목록
        @Data
        public class ResumeDTO {
            private Integer resumeId;
            private String resumeUserName;
            private String title;
            private String email;
            private String birthdate;

            public ResumeDTO(Resume resume) {
                this.resumeId = resume.getId();
                this.resumeUserName = resume.getUser().getName();
                this.title = resume.getTitle();
                this.birthdate = resume.getUser().getBirthdate();
                this.email = resume.getUser().getEmail();
            }
        }
    }

    @Data
    public static class BoardDTO {
        private String address;
        private String businessName;
        private Integer boardId;
        private String field;
        private String title;
        private String salary;
        private Timestamp closingDate;

        public String getClosingDate() {
            return FormatUtil.timeFormatter(this.closingDate);
        }

        public BoardDTO(Board board) {
            this.address = board.getUser().getAddress();
            this.businessName = board.getUser().getBusinessName();
            this.boardId = board.getId();
            this.field = board.getField();
            this.title = board.getTitle();
            this.salary = board.getSalary();
            this.closingDate = board.getClosingDate();
        }
    }

    @Data
    public static class ResumeDTO {
        private Integer resumeId;
        private String resumeUserName;
        private String title;
        private String email;
        private String birthdate;

        public ResumeDTO(Resume resume) {
            this.resumeId = resume.getId();
            this.resumeUserName = resume.getUser().getName();
            this.title = resume.getTitle();
            this.birthdate = resume.getUser().getBirthdate();
            this.email = resume.getUser().getEmail();
        }
    }
}
