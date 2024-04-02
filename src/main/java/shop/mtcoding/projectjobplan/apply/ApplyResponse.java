package shop.mtcoding.projectjobplan.apply;

import lombok.Data;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ApplyResponse {
    @Data
    public static class ApplyFormDTO {
        // 공고 정보
        private Integer boardId;
        private String boardTitle;
        private String businessName;
        private Timestamp closingDate;
        // 이력서 리스트
        private List<ResumeDTO> resumeList = new ArrayList<>();

        public ApplyFormDTO(Board board, List<Resume> resumeList) {
            this.boardId = board.getId();
            this.boardTitle = board.getTitle();
            this.businessName = board.getUser().getBusinessName();
            this.closingDate = board.getClosingDate();
            this.resumeList = resumeList.stream().map(resume -> new ResumeDTO(resume)).toList();
        }

        // 이력서 정보
        public class ResumeDTO {
            private Integer resumeId;
            private String resumeTitle;
            private Timestamp createdAt;

            public ResumeDTO(Resume resume) {
                this.resumeId = resume.getId();
                this.resumeTitle = resume.getTitle();
                this.createdAt = resume.getCreatedAt();
            }

            public String getCreatedAt() {
                return FormatUtil.timeFormatter(this.createdAt);
            }
        }

        private String getClosingDate() {
            return FormatUtil.timeFormatter(this.closingDate);
        }
    }
}


