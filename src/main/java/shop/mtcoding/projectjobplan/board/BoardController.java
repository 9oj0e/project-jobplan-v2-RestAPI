package shop.mtcoding.projectjobplan.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        final int limit = 8;
        List<BoardResponse.IndexDTO> responseDTO = boardService.getAllBoardOnIndex(limit);
        request.setAttribute("boardList", responseDTO);

        return "index";
    }

    @GetMapping("/boards/main")
    public String main() {
        return "board/main";
    }

    @GetMapping("/boards/post-form")
    public String postForm() {

        return "board/post-form";
    }

    @PostMapping("/boards/post")
    public String post(@Valid BoardRequest.SaveDTO requestDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.createBoard(requestDTO, sessionUser);

        return "redirect:/boards/" + board.getId();
    }

    @GetMapping("/boards/{boardId}")
    public String detail(@PathVariable int boardId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser == null ? null : sessionUser.getId();
        BoardResponse.DetailDTO boardDetail = boardService.getBoardInDetail(boardId, sessionUserId);
        request.setAttribute("boardDetail", boardDetail);

        return "board/detail";
    }

    @GetMapping("/boards")
    public String listings(HttpServletRequest request,
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
        request.setAttribute("page", responseDTO);

        return "board/listings";
    }

    /* modal로 대체
    @GetMapping("/boards/{boardId}/update-form") // 공고수정폼
    public String updateForm(@PathVariable int boardId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.UpdateFormDTO responseDTO = boardService.getBoard(boardId, sessionUser);
        request.setAttribute("board", responseDTO);

        return "board/update-form";
    }
    */
    @PostMapping("/boards/{boardId}/update") // 공고수정
    public String update(@PathVariable int boardId, @Valid BoardRequest.UpdateDTO requestDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.setBoard(boardId, requestDTO, sessionUser);

        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/boards/{boardId}/delete")
    public String delete(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.removeBoard(boardId, sessionUser);

        return "redirect:/users/" + sessionUser.getId();
    }
}
