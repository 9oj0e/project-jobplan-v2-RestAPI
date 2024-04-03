package shop.mtcoding.projectjobplan.apply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.SessionUser;
import shop.mtcoding.projectjobplan.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyJpaRepository applyJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;
    private final BoardJpaRepository boardJpaRepository;

    @Transactional(readOnly = true)
    public ApplyResponse.ApplyFormDTO getBoardAndResume(int boardId, SessionUser sessionUser) {
        Board board = boardJpaRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        List<Resume> resumeList = resumeJpaRepository.findByUserId(sessionUser.getId())
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));

        return new ApplyResponse.ApplyFormDTO(board, resumeList);
    }

    @Transactional
    public ApplyResponse.ApplyDTO createApply(ApplyRequest.ApplyDTO requestDTO) {
        Resume resume = resumeJpaRepository.findById(requestDTO.getResumeId())
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));
        Board board = boardJpaRepository.findById(requestDTO.getBoardId())
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        Apply apply = new Apply(resume, board);

        applyJpaRepository.save(apply);
        return new ApplyResponse.ApplyDTO(apply);
    }

    @Transactional
    public ApplyResponse.UpdateDTO updateApply(ApplyRequest.UpdateDTO requestDTO) {
        Apply apply = applyJpaRepository.findById(requestDTO.getId())
                .orElseThrow(() -> new Exception404("지원 이력이 없습니다."));

        apply.update(requestDTO);
        return new ApplyResponse.UpdateDTO(apply);
    }
}
