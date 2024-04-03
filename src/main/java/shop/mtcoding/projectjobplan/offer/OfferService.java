package shop.mtcoding.projectjobplan.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan._core.errors.exception.Exception404;
import shop.mtcoding.projectjobplan.board.Board;
import shop.mtcoding.projectjobplan.board.BoardJpaRepository;
import shop.mtcoding.projectjobplan.resume.Resume;
import shop.mtcoding.projectjobplan.resume.ResumeJpaRepository;
import shop.mtcoding.projectjobplan.user.SessionUser;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferJpaRepository offerJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;
    private final BoardJpaRepository boardJpaRepository;

    @Transactional(readOnly = true)
    public OfferResponse.OfferFormDTO getResumeAndBoard(int resumeId, SessionUser sessionUser) {
        Resume resume = resumeJpaRepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서 정보를 찾을 수 없습니다."));
        List<Board> boardList = boardJpaRepository.findByUserId(sessionUser.getId())
                .orElseThrow(() -> new Exception404("게시글 정보를 찾을 수 없습니다."));

        return new OfferResponse.OfferFormDTO(resume, boardList);
    }

    @Transactional
    public OfferResponse.OfferDTO createOffer(OfferRequest.OfferDTO requestDTO) {
        Board board = boardJpaRepository.findById(requestDTO.getBoardId())
                .orElseThrow(() -> new Exception404("게시글 정보를 찾을 수 없습니다."));
        Resume resume = resumeJpaRepository.findById(requestDTO.getResumeId())
                .orElseThrow(() -> new Exception404("이력서 정보를 찾을 수 없습니다."));
        Offer offer = new Offer(resume, board);

        offerJpaRepository.save(offer);
        return new OfferResponse.OfferDTO(offer);
    }

    @Transactional
    public OfferResponse.UpdateDTO updateOffer(OfferRequest.UpdateDTO requestDTO) {
        Offer offer = offerJpaRepository.findById(requestDTO.getId())
                .orElseThrow(() -> new Exception404("제안 이력이 없습니다."));
        offer.update(requestDTO);
        return new OfferResponse.UpdateDTO(offer);
    }


    @Transactional
    public void removeOffer(int id) {
        Offer offer = offerJpaRepository.findById(id)
                .orElseThrow(() -> new Exception404("취소할 제안이 없습니다."));
        offerJpaRepository.delete(offer);
    }
}
