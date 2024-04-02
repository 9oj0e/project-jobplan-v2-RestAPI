package shop.mtcoding.projectjobplan.offer;

import lombok.Data;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.resume.Resume;

import java.util.ArrayList;
import java.util.List;

public class OfferResponse {
    @Data
    public static class OfferFormDTO {
        // 이력서 정보
        private Integer resumeId;
        private String title;
        private String username;
        private String career;

        // 공고 리스트
        private List<BoardDTO> boardList = new ArrayList<>();

        // 제안 폼 DTO
        OfferFormDTO(Resume resume, List<Board> boardList) {
            this.resumeId = resume.getId();
            this.title = resume.getTitle();
            this.username = resume.getUser().getName();
            this.career = resume.getCareer();
            this.boardList = boardList.stream().map(board -> new BoardDTO(board)).toList();
        }

        // 공고 정보
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
}


