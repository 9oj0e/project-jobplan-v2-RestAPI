package shop.mtcoding.projectjobplan.apply;

import lombok.Data;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.offer.Offer;
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

    @Data
    public static class ApplyDTO {
        private Integer resumeId;
        private Integer boardId;
        private Boolean status;

        public ApplyDTO(Apply apply) {
            this.resumeId = apply.getResume().getId();
            this.boardId = apply.getBoard().getId();
            this.status = apply.getStatus();
        }
    }

    @Data
    public static class UpdateDTO {
        private Integer applyId;
        private Boolean status;

        public UpdateDTO(Apply apply) {
            this.applyId = apply.getId();
            this.status = apply.getStatus();
        }
    }
}


