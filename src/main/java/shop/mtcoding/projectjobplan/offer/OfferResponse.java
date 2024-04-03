package shop.mtcoding.projectjobplan.offer;

import lombok.Data;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.util.ArrayList;
import java.util.List;

public class OfferResponse {
    @Data // 제안 폼
    public static class OfferFormDTO {
        // 이력서 정보
        private Integer resumeId;
        private String title;
        private String username;
        private String career;

        // 공고 목록
        private List<BoardDTO> boardList = new ArrayList<>();

        OfferFormDTO(Resume resume, List<Board> boardList) {
            this.resumeId = resume.getId();
            this.title = resume.getTitle();
            this.username = resume.getUser().getName();
            this.career = resume.getCareer();
            this.boardList = boardList.stream().map(board -> new BoardDTO(board)).toList();
        }

        @Data // 공고 정보
        public class BoardDTO {
            private Integer boardId;
            private String boardTitle;
            private String position;
            private String field;

            public BoardDTO(Board board) {
                this.boardId = board.getId();
                this.boardTitle = board.getTitle();
                this.position = board.getPosition();
                this.field = board.getField();
            }
        }

    }

    @Data // 제안 정보 returnDTO
    public static class OfferDTO {
        private Integer resumeId;
        private Integer boardId;
        private Boolean status;

        public OfferDTO(Offer offer) {
            this.resumeId = offer.getResume().getId();
            this.boardId = offer.getBoard().getId();
            this.status = offer.getStatus();
        }
    }

    @Data // 제안 수락 여부 returnDTO
    public static class UpdateDTO {
        private Integer offerId;
        private Boolean status;

        public UpdateDTO(Offer offer) {
            this.offerId = offer.getId();
            this.status = offer.getStatus();
        }
    }
}


