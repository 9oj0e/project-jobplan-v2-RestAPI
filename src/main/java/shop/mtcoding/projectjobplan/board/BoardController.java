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
import shop.mtcoding.projectjobplan.user.UserResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;

    @GetMapping("/")
    public ResponseEntity<?> index() {
        final int limit = 8;
        List<BoardResponse.IndexDTO> responseDTO = boardService.getAllBoardOnIndex(limit);

        return ResponseEntity.ok(new ApiUtil(responseDTO));
    }


    @PostMapping("/api/boards/post")
    public ResponseEntity<?> post(@Valid BoardRequest.SaveDTO requestDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardDTO boardDTO = boardService.createBoard(requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil(boardDTO));
    }

    @GetMapping("/api/boards/{boardId}")
    public ResponseEntity<?> detail(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser == null ? null : sessionUser.getId();
        BoardResponse.DetailDTO boardDetail = boardService.getBoardInDetail(boardId, sessionUserId);

        return ResponseEntity.ok(new ApiUtil(boardDetail));
    }

    @GetMapping("/api/boards")
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
    public ResponseEntity<?> update(@PathVariable int boardId, @Valid BoardRequest.UpdateDTO requestDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
       Board board = boardService.setBoard(boardId, requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil(requestDTO));
    }

    @DeleteMapping("/api/boards/{boardId}/delete")
    public ResponseEntity<?> delete(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.removeBoard(boardId, sessionUser);

        return ResponseEntity.ok(new ApiUtil(null));
    }
}
