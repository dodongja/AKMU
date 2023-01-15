//package com.example.demo.application.board.videoBoard;
//
//import com.example.demo.application.board.BaseCommentService;
//import com.example.demo.dto.request.CommentRequest;
//import com.example.demo.dto.request.ReplyRequest;
//import com.example.demo.dto.response.CommentResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
///*@Slf4j
//@Service
//public class VideoBoardCommentsServiceImpl extends BaseCommentService {
//
//    @Autowired
//    private VideoBoardCommentsRepository videoBoardCommentsRepository;
//
//    @Autowired
//    private VideoBoardRepository videoBoardRepository;
//
//    @Override
//    public void register(Integer boardNo, CommentRequest commentRequest) {
//        Optional<VideoBoard> maybeBoard = videoBoardRepository.findById(Long.valueOf(boardNo));
//
//        VideoBoard board = maybeBoard.get();
//
//        VideoBoardComments comment = VideoBoardComments.builder()
//                .comment(commentRequest.getComment())
//                .videoBoard(board)
//                .writer(commentRequest.getWriter())
//                .build();
//
//        videoBoardCommentsRepository.save(comment);
//    }
//
//    @Override
//    public Object list(Integer boardNo) {
//        List<VideoBoardComments> checkComments =
//                videoBoardCommentsRepository.findAllVideoBoardCommentsByBoardId(Long.valueOf(boardNo));
//        List<CommentResponse> response = new ArrayList<>();
//
//        //parCommentNo이 null일경우와 아닐경우 나누지 않으면 오류 발생
//        for(VideoBoardComments comment : checkComments){
//            if(comment.getReply() == null) {
//                response.add(new CommentResponse(comment.getWriter(), comment.getComment(),
//                        comment.getVideoBoard().getBoardNo(),
//                        comment.getRegDate(), comment.getCommentNo(),
//                        null));
//            }else{
//                response.add(new CommentResponse(comment.getWriter(), comment.getComment(),
//                        comment.getVideoBoard().getBoardNo(),
//                        comment.getRegDate(), comment.getCommentNo(),
//                        comment.getReply().getCommentNo()));
//            }
//        }
//
//        //db에서 최상위 부모인 애들끼리 묶고 거기서 레벨 나누거나 해보려 했는데 도무지 감이 안잡힘
//        //그래서 일단 댓글에 댓글정도만 구현 함
//        List<CommentResponse> response2 = new ArrayList<>();
//
//        for(int i = 0; i < response.size(); i++){
//            if(response.get(i).getParCommentNo() == null){
//                response2.add(response.get(i));
//                for(int y=0; y< response.size(); y++) {
//                    if (response.get(y).getParCommentNo() != null) {
//                        if (response.get(y).getParCommentNo() == response.get(i).getCommentNo()) {
//                            response2.add(response.get(y));
//                        }
//                    }
//                }
//            }
//        }
//        return response2;
//    }
//
//    @Override
//    public Object modify(Integer commentNo, CommentRequest commentRequest) {
//        Optional<VideoBoard> maybeBoard = videoBoardRepository.findById(Long.valueOf(commentRequest.getBoardNo()));
//        VideoBoard board = maybeBoard.get();
//
//        //처음엔 커멘츠 엔티티 자체를 보내서 이부분은 다시 vue에서 받아와서 필요 없었으나
//        //service를 통합 하는 과정에서 각각 다른 엔티티를 받아 올 수가 없었음
//        //그래서 commentNo으로 reply찾아옴
//        //하면서 느낀게 db단계에서 entityManger라는 엔티티를 하나더 만들어서 관리한다는것이 필요하지 않을까 생각이 자꾸 드는데
//        //정확히 뭔지 모르겠음
//        VideoBoardComments reply = videoBoardCommentsRepository.findByCommentNo(Long.valueOf(commentNo));
//
//        VideoBoardComments commentModify = VideoBoardComments.builder()
//                .commentNo(Long.valueOf(commentNo))
//                .videoBoard(board)
//                .reply(reply.getReply())
//                .comment(commentRequest.getComment())
//                .writer(commentRequest.getWriter())
//                .regDate(commentRequest.getRegDate())
//                .build();
//
//        return videoBoardCommentsRepository.save(commentModify);
//
//
//    }
//
//    @Override
//    public void remove(Integer commentNo) {
//        videoBoardCommentsRepository.deleteById(Long.valueOf(commentNo));
//    }
//
//    @Override
//    public void replyRegister(Integer boardNo, ReplyRequest commentRequest) {
//        Optional<VideoBoard> maybeBoard2 = videoBoardRepository.findById(Long.valueOf(boardNo));
//        VideoBoard board2 = maybeBoard2.get();
//
//        Optional<VideoBoardComments> maybeBoard = videoBoardCommentsRepository.findById(commentRequest.getCommentNo());
//        VideoBoardComments board = maybeBoard.get();
//
//
//        VideoBoardComments comment = VideoBoardComments.builder()
//                .reply(board)
//                .videoBoard(board2)
//                .comment(commentRequest.getReply())
//                .writer(commentRequest.getWriter())
//                .build();
//
//        videoBoardCommentsRepository.save(comment);
//    }
//}*/
