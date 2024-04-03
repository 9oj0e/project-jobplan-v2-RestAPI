package shop.mtcoding.projectjobplan.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;

    @GetMapping("/")
    public ResponseEntity<?> index() {
        List<BoardResponse.IndexDTO> responseDTO = boardService.getAllBoardOnIndex();

        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }


    @PostMapping("/api/boards") // 게시글 작성
    public ResponseEntity<?> post(@Valid @RequestBody BoardRequest.SaveDTO requestDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DTO boardDTO = boardService.createBoard(requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil(boardDTO));
    }

    @GetMapping("/api/boards/{boardId}")
    public ResponseEntity<?> detail(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser == null ? null : sessionUser.getId();
        BoardResponse.DetailDTO boardDetail = boardService.getBoardInDetail(boardId, sessionUserId);

        return ResponseEntity.ok(new ApiUtil(boardDetail));
    }

    @GetMapping("/api/boards")  // 개인 채용공고 리스트
    public ResponseEntity<?> listings(HttpServletRequest request,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(value = "skill", required = false) String skill,
                           @RequestParam(value = "address", required = false) String address,
                           @RequestParam(value = "keyword", required = false) String keyword) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = null;
        if (sessionUser != null) {
            sessionUserId = sessionUser.getId();
        }
        BoardResponse.ListingsDTO responseDTO = boardService.getAllBoard(pageable, sessionUserId, skill, address, keyword);

        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }

    @PutMapping("/api/boards/{boardId}") // 공고수정
    public ResponseEntity<?> update(@PathVariable int boardId, @Valid @RequestBody BoardRequest.UpdateDTO requestDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
       BoardResponse.DTO boardDTO = boardService.setBoard(boardId, requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil(boardDTO));
    }

    @DeleteMapping("/api/boards/{boardId}")
    public ResponseEntity<?> delete(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.removeBoard(boardId, sessionUser);

        return ResponseEntity.ok(new ApiUtil(null));
    }
}
