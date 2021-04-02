package com.example.matrixsolver.controller;

import com.example.matrixsolver.grpc.GrpcClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yongchang Cai on 3/31/21
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class MatrixRestController {

    private static final int[][] EMPTY_ARRAY = new int[][];

    @NonNull
    private final GrpcClient grpcClient;


    @PostMapping(path = "/matrix/upload", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadMatrixFile(@RequestBody int[][] matrix) {
        ResponseEntity<String> result;
        if (validateSquareMatrix(matrix)) {
            result = ResponseEntity.status(HttpStatus.OK).body("Matrix update successfully");
        } else {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INCORRECT MATRIX: matrix shoudl be in square");
        }
        return result;
    }

    @PostMapping(path = "/matrix/multiple", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<int[][]> multipleMatrix(@RequestBody int[][] matrix) {
        ResponseEntity<int[][]> result;
        if (validateSquareMatrix(matrix)) {
            result = ResponseEntity.status(HttpStatus.OK).body(grpcClient.multipleMatrix(matrix));
        } else {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EMPTY_ARRAY);
        }
        return result;
    }

    @PostMapping(path = "/matrix/add", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<int[][]> addMatrix(@RequestBody int[][] matrix) {
        ResponseEntity<int[][]> result;
        if (validateSquareMatrix(matrix)) {
            result = ResponseEntity.status(HttpStatus.OK).body(grpcClient.addMatrix(matrix));
        } else {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EMPTY_ARRAY);
        }
        return result;
    }

    private boolean validateSquareMatrix(int[][] matrix) {
        boolean result = true;
        if (matrix == null || matrix.length == 0) {
            result = false;
        } else {
            int length = matrix.length;
            for (int i = 0; i < matrix.length; i++) {
                if (length != matrix[i].length) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}
