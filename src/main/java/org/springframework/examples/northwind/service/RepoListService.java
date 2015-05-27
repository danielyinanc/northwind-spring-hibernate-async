package org.springframework.examples.northwind.service;


import org.springframework.examples.northwind.dto.RepoListDto;
import org.springframework.util.concurrent.ListenableFuture;

public interface RepoListService {
    public ListenableFuture<RepoListDto> search(String query);
}
