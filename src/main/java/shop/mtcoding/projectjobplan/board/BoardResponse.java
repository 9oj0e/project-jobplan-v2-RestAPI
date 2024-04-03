package shop.mtcoding.projectjobplan.board;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.mtcoding.projectjobplan._core.utils.FormatUtil;
import shop.mtcoding.projectjobplan._core.utils.PagingUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BoardResponse {
    @Data
    public static class UpdateFormDTO {
        private Integer id; // boardId
        private String title;
        private String field;
        private String position;
        private String salary;
        private String content;
        private String openingDate;
        private String closingDate;

        public UpdateFormDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.field = board.getField();
            this.position = board.getPosition();
            this.salary = board.getSalary();
            this.content = board.getContent();
            this.openingDate = FormatUtil.timeFormatter(board.getOpeningDate());
            this.closingDate = FormatUtil.timeFormatter(board.getClosingDate());
        }
    }

    @Data
    public static class DetailDTO {
        // 공고 정보
        private Integer id;
        private String title; // 제목
        private String content; // 내용
        private String field; // 채용 분야
        private String position; // 포지션
        private String salary; // 연봉
        private Timestamp openingDate; // 게시일
        private Timestamp closingDate; // 마감일 == null -> "상시채용"
        private List<SkillDTO> skillList; // 우대 스킬
        // 회원 정보
        private String address;
        private String phoneNumber;
        private String email;
        private String businessName;
        // 기타 정보
        private Double rating; // 평점
        private boolean isBoardOwner; // 공고 주인 여부
        private boolean hasSubscribed; // 구독 여부
        private boolean hasRated; // 평가 여부

        public DetailDTO(Board board, Double rating, boolean isBoardOwner, boolean hasSubscribed, boolean hasRated) {
            this.address = board.getUser().getAddress();
            this.phoneNumber = board.getUser().getPhoneNumber();
            this.email = board.getUser().getEmail();
            this.businessName = board.getUser().getBusinessName();
            this.isBoardOwner = isBoardOwner;
            this.hasSubscribed = hasSubscribed;
            this.hasRated = hasRated;
            this.rating = rating;
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.field = board.getField();
            this.position = board.getPosition();
            this.salary = board.getSalary();
            this.skillList = board.getSkillList().stream().map(skill -> new SkillDTO(skill.getName())).toList();
            this.openingDate = board.getOpeningDate();
            this.closingDate = board.getClosingDate();
        }

        @Data
        public class SkillDTO {
            private String skillName;

            public SkillDTO(String skillName) {
                this.skillName = skillName;
            }
        }

        public String getOpeningDate() {
            return FormatUtil.timeFormatter(this.openingDate);
        }

        public String getClosingDate() {
            return FormatUtil.timeFormatter(this.closingDate);
        }

        public Double getRating() {
            return FormatUtil.numberFormatter(this.rating);
        }

    }

    @Data
    public static class ListingsDTO {
        Page<BoardDTO> boardList;
        List<Integer> pageList;
        List<RecommendationDTO> recommendationList = new ArrayList<>();
        String skill;
        String address;
        String keyword;

        public ListingsDTO(Pageable pageable, List<Board> boards, List<Object[]> recommendations, String skill, String address, String keyword) {
            List<BoardDTO> boardList = boards.stream().map(board -> new BoardDTO(board)).toList();
            this.boardList = PagingUtil.pageConverter(pageable, boardList);
            this.pageList = PagingUtil.getPageList(this.boardList);
            this.skill = skill;
            this.address = address;
            this.keyword = keyword;
            for (Object[] result : recommendations) {
                RecommendationDTO dto
                        = new RecommendationDTO(
                        (Integer) result[0], (String) result[1], (String) result[2], (String) result[3]
                );
                this.recommendationList.add(dto);
            }
        }

        public List<RecommendationDTO> getRecommendationList() {
            if (!recommendationList.isEmpty()) { // 추천 공고가 있으면 그대로 반환
                return this.recommendationList;
            } else { // sessionUser가 없거나 기술이 없는 회원은, 최근 공고 3개 띄우기
                return boardList.stream()
                        .map(boardDTO -> new RecommendationDTO(boardDTO.id, boardDTO.title, boardDTO.field, boardDTO.businessName))
                        .limit(3)
                        .toList();
            }
        }

        @Data
        public class BoardDTO {
            // board_tb
            private Integer id;
            private String title;
            private String field;
            private String salary;
            private Timestamp closingDate;

            // user_tb
            private String address;
            private String businessName;

            public BoardDTO(Board board) {
                this.id = board.getId();
                this.title = board.getTitle();
                this.field = board.getField();
                this.salary = board.getSalary();
                this.closingDate = board.getClosingDate();
                this.address = board.getUser().getAddress();
                this.businessName = board.getUser().getBusinessName();
            }

            public String getClosingDate() {
                return FormatUtil.timeFormatter(closingDate);
            }
        }


        @NoArgsConstructor
        @Data
        public static class RecommendationDTO {
            private Integer boardId;
            private String title;
            private String field;
            private String businessName;

            public RecommendationDTO(Integer boardId, String title, String field, String businessName) {
                this.boardId = boardId;
                this.title = title;
                this.field = field;
                this.businessName = businessName;
            }
        }
    }

    @Data
    public static class IndexDTO {
        // 공고 정보
        private Integer id;
        private String title;
        private String field;
        private String position;

        // 게시자 정보 (기업)
        private Integer userId;
        private String businessName;

        public IndexDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.field = board.getField();
            this.position = board.getPosition();
            this.userId = board.getUser().getId();
            this.businessName = board.getUser().getBusinessName();
        }
    }

    //board 엔티티를 리턴하기 위해 만든 DTO
    @Data
    public static class SaveDTO {

        // 공고 정보
        private Integer id;
        private String title; // 제목
        private String content; // 내용
        private String field; // 채용 분야
        private String position; // 포지션
        private String salary; // 연봉
        private Timestamp openingDate; // 게시일
        private Timestamp closingDate; // 마감일 == null -> "상시채용"
//        private List<SkillDTO> skillList = new ArrayList<>(); // 우대 스킬
        // 회원 정보

        public SaveDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.field = board.getField();
            this.position = board.getPosition();
            this.salary = board.getSalary();
//            this.skillList = board.getSkillList().stream().map(skill -> new BoardDTO.SkillDTO(skill.getName())).toList();
            this.openingDate = board.getOpeningDate();
            this.closingDate = board.getClosingDate();
        }

        @Data
        public class SkillDTO {
            private String skillName;

            public SkillDTO(String skillName) {
                this.skillName = skillName;
            }
        }

    }

    @Data
    public static class UpdateDTO {

        // 공고 정보
        private Integer id;
        private String title; // 제목
        private String content; // 내용
        private String field; // 채용 분야
        private String position; // 포지션
        private String salary; // 연봉
        private Timestamp openingDate; // 게시일
        private Timestamp closingDate; // 마감일 == null -> "상시채용"
//        private List<SkillDTO> skillList = new ArrayList<>(); // 우대 스킬
        // 회원 정보

        public UpdateDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.field = board.getField();
            this.position = board.getPosition();
            this.salary = board.getSalary();
//            this.skillList = board.getSkillList().stream().map(skill -> new BoardDTO.SkillDTO(skill.getName())).toList();
            this.openingDate = board.getOpeningDate();
            this.closingDate = board.getClosingDate();
        }

        @Data
        public class SkillDTO {
            private String skillName;

            public SkillDTO(String skillName) {
                this.skillName = skillName;
            }
        }

    }
}
