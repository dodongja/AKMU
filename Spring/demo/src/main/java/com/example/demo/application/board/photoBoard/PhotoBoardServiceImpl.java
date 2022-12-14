//package com.example.demo.service.board.photoBoard;
//
//import com.example.demo.web.board.dto.BoardResponse;
//import com.example.demo.web.board.dto.BoardRequest;
//import com.example.demo.dto.request.LikeRequest;
//import com.example.demo.entity.board.photoBoard.PhotoBoard;
//import com.example.demo.entity.board.photoBoard.PhotoBoardLike;
//import com.example.demo.domain.photoBoard.repository.PhotoBoardLikeRepository;
//import com.example.demo.domain.photoBoard.repository.PhotoBoardRepository;
//
//import com.example.demo.service.board.BaseBoardService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@Service
//public class PhotoBoardServiceImpl extends BaseBoardService {
//
//    String path = "uploadImg";
//
//    @Autowired
//    private PhotoBoardRepository repository;
//
//    @Autowired
//    private PhotoBoardLikeRepository likeRepository;
//
//    @Override
//    public void register(BoardRequest board,
//                         MultipartFile files) throws Exception {
//
//        fileUpload(board, files, path);
//
//        PhotoBoard photoBoard = PhotoBoard.builder()
//                .title(board.getTitle())
//                .content(board.getContent())
//                .writer(board.getWriter())
//                .fileName(board.getFileName())
//                .build();
//
//        repository.save(photoBoard);
//    }
//
//
//    @Override
//    public  Object list() {
//                List<PhotoBoard> photo = repository.findAll(Sort.by(Sort.Direction.DESC, "boardNo"));
//                /*List<BoardResponse> response = new ArrayList<>();
//                 for(PhotoBoard board : photo){
//                     response.add(new BoardResponse(board.getTitle(), board.getContent(), board.getWriter(),
//                             board.getFileName(), board.getBoardNo(), board.getRegDate(), board.getReadCnt(),
//                             board.getLikeCnt(), board.getLikeCheck(), board.getCommentCnt()));
//                 }*/
//
//                 return photo;
//    }
//
//   @Override
//    public Object read(Integer boardNo) {
//        Optional<PhotoBoard> maybeReadBoard = repository.findById(Long.valueOf(boardNo));
//
//        if (maybeReadBoard.equals(Optional.empty())) {
//            log.info("Can't read board!!");
//            return null;
//        }
//
//        PhotoBoard readBoard = maybeReadBoard.get();
//        readBoard.readCnt();
//        return repository.save(readBoard);
//
//       /* BoardResponse response = new BoardResponse(readBoard.getTitle(), readBoard.getContent(), readBoard.getWriter(),
//                readBoard.getFileName(), readBoard.getBoardNo(), readBoard.getRegDate(), readBoard.getReadCnt(),
//                                        readBoard.getLikeCnt(), readBoard.getLikeCheck(), readBoard.getCommentCnt());
//
//        return response;*/
//    }
//
//    @Override
//    public void modify(Integer boardNo, BoardRequest board, MultipartFile files) throws Exception {
//
//        //?????? ??? ??? ????????? boardNo?????? ??????????????? likee?????? boardNo??? ???????????? ?????? ?????? writer??? boardNo?????? ???????????????????
//        //????????? ????????? PhotoBoard??? like??? boardNo??? ?????????????????? ????????????????????? ??? ???????????? ??????????????? ??? ??????????????? ????????????!
//        List<PhotoBoardLike> likes= likeRepository.findByBoardNo(Long.valueOf(boardNo));
//
//        PhotoBoard photoBoard = PhotoBoard.builder()
//                .boardNo(Long.valueOf(boardNo))
//                .title(board.getTitle())
//                .content(board.getContent())
//                .writer(board.getWriter())
//                .fileName(board.getFileName())
//                .regDate(board.getRegDate())
//                .likes(likes)
//                .build();
//        //????????? ?????? ???
//        if(files != null) {
//            String fileName = files.getOriginalFilename();
//            fileRemove(fileName, path);
//            fileUpload(board,files,path);
//            photoBoard.setFileName(board.getFileName());
//        }
//
//        repository.save(photoBoard);
//    }
//
//    @Override
//    public void remove(Integer boardNo) {
//        //????????????
//        Optional<PhotoBoard> findFileName = repository.findFileName(Long.valueOf(boardNo));
//
//        String fileName = String.valueOf(findFileName.get());
//
//        fileRemove(fileName, path);
//        //db??????
//        repository.deleteById(Long.valueOf(boardNo));
//    }
//
//    @Override
//    public void doLike(LikeRequest like) {
//        //like ???????????? ??????
//        Optional<PhotoBoard> maybeBoard = repository.findById(like.getBoardNo());
//        PhotoBoard board = maybeBoard.get();
//        //board.setLikeCheck(like.getLikeCheck());
//
//        log.info("*********" +  like.getLikeCheck());
//
//        PhotoBoardLike doLike = PhotoBoardLike.builder()
//                        .photoBoard(board)
//                        .writer(like.getWriter())
//                        .build();
//
//                 likeRepository.save(doLike);
//    }
//
//    @Override
//    public void unDoLike(LikeRequest like) {
//        //like ??????????????? ??????
//        Optional<PhotoBoardLike> id = likeRepository.findId(like.getBoardNo(), like.getWriter());
//            log.info("id " + id);
//            PhotoBoardLike likeRemove = id.get();
//
//          likeRepository.deleteById(likeRemove.getLikeNo());
//    }
//
//
//    @Override
//    public void likeCheck(String writer) {
//        //???????????? ????????? ????????? ?????????
//        //?????? ????????? ??????????????? ??? ??????...
//        //????????? ????????? ??????????????? ????????? ????????? ??????????????
//        //?????? ????????? ?????? ???????????? ??????????????? , ???????????? ????????? ????????? ????????????????????????
//
//        //????????? ???????????? ????????? ?????? boardNo ????????????
//        List<PhotoBoardLike> checkLike = likeRepository.findByWriter(writer);
//
//        //???????????? ?????? likeCnt 1??? ?????? ???????????? likeCnt??? 0?????? ????????? ????????? ?????????
//        List<PhotoBoard> photoBoard = repository.findAll();
//        for (PhotoBoard board : photoBoard) {
//            board.likeCheckZero();
//            repository.save(board);
//        }
//        //boardNo ?????? ????????? likeCheck 1(true) ???????????????
//        for (PhotoBoardLike likeCheck : checkLike){
//            Optional<PhotoBoard> findLike =
//                    repository.findById(Long.valueOf(likeCheck.getPhotoBoard().getBoardNo()));
//
//            PhotoBoard likeTrue = findLike.get();
//                if (likeTrue.getLikeCheck() == 0) {
//                    likeTrue.setLikeCheck(likeTrue.getLikeCheck() + 1);
//                }
//                //?????? ??????
//                repository.save(likeTrue);
//            }
//
//        }
//
//}
