package org.springframework.examples.northwind.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.examples.northwind.dto.RepoListDto;
import org.springframework.examples.northwind.dto.RepositoryListDtoAdapter;
import org.springframework.examples.northwind.entity.GitHubItems;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

@Service
class GitHubRepoListService implements RepoListService {
    private static final String SEARCH_URL = "https://api.github.com/search/repositories?q={query}";

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @Override
    public ListenableFuture<RepoListDto> search(String query) {
        ListenableFuture<ResponseEntity<GitHubItems>> gitHubItems =
            asyncRestTemplate.getForEntity(SEARCH_URL, GitHubItems.class, query);
        return new RepositoryListDtoAdapter(query, gitHubItems);
    }
}

