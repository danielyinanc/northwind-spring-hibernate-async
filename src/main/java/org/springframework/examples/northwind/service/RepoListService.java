package org.springframework.examples.northwind.service;


import org.springframework.util.concurrent.ListenableFuture;

public interface RepoListService {
    public ListenableFuture<RepoListDto> search(String query);
}
