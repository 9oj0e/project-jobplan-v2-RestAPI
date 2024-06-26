package shop.mtcoding.projectjobplan.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;
import shop.mtcoding.projectjobplan.user.SessionUser;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;

    @GetMapping("/") // 메인 페이지
    public ResponseEntity<?> index() {
        List<BoardResponse.IndexDTO> responseDTO = boardService.getAllBoardOnIndex();

        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }


    @PostMapping("/api/boards") // 공고 작성
    public ResponseEntity<?> post(@Valid @RequestBody BoardRequest.SaveDTO requestDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        System.out.println(sessionUser);
        BoardResponse.SaveDTO boardDTO = boardService.createBoard(requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil(boardDTO));
    }

    @GetMapping("/api/boards/{boardId}") // 공고 상세보기
    public ResponseEntity<?> detail(@PathVariable int boardId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser == null ? null : sessionUser.getId();
        BoardResponse.DetailDTO boardDetail = boardService.getBoardInDetail(boardId, sessionUserId);

        return ResponseEntity.ok(new ApiUtil(boardDetail));
    }

    @GetMapping("/api/boards")  // 공고 목록보기
    public ResponseEntity<?> listings(HttpServletRequest request,
                                      @PageableDefault(size = 10) Pageable pageable,
                                      @RequestParam(value = "skill", required = false) String skill,
                                      @RequestParam(value = "address", required = false) String address,
                                      @RequestParam(value = "keyword", required = false) String keyword) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        Integer sessionUserId = null;
        if (sessionUser != null) {
            sessionUserId = sessionUser.getId();
        }
        BoardResponse.ListingsDTO responseDTO = boardService.getAllBoard(pageable, sessionUserId, skill, address, keyword);

        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }

    @PutMapping("/api/boards/{boardId}") // 공고 수정
    public ResponseEntity<?> update(@PathVariable int boardId, @Valid @RequestBody BoardRequest.UpdateDTO requestDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        System.out.println(sessionUser);
        BoardResponse.UpdateDTO boardDTO = boardService.setBoard(boardId, requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil(boardDTO));
    }

    @DeleteMapping("/api/boards/{boardId}") // 공고 삭제
    public ResponseEntity<?> delete(@PathVariable int boardId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        boardService.removeBoard(boardId, sessionUser);

        return ResponseEntity.ok(new ApiUtil(null));
    }
}
