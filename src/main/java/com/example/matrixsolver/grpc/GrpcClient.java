package com.example.matrixsolver.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Yongchang Cai on 3/31/21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GrpcClient {

    public int[][] addMatrix(int[][] matrix) {
        StreamObserver<Matrix>
    }

    public int[][] multipleMatrix(int[][] matrix) {

    }
}
