package org.springframework.examples.northwind.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.examples.northwind.model.Product;
import org.springframework.examples.northwind.service.ProductBo;
import org.springframework.examples.northwind.service.RepoListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
class AsyncController {
    private static final Logger log = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private RepoListService repoListService;

    @Autowired
    private ProductBo productBo;

    @RequestMapping("/async")
    DeferredResult<ResponseEntity<?>> async(@RequestParam("q") String query) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        ListenableFuture<Product> repositoryListDto = productBo.findByProductId(1);
        repositoryListDto.addCallback(
                new ListenableFutureCallback<Product>() {
                    @Override
                    public void onSuccess(Product result) {

                        //Product product = productBo.findByProductId(1);
                        //System.out.println(product);

                        System.out.println(result);

                        ResponseEntity<Product> responseEntity =
                            new ResponseEntity<>(result, HttpStatus.OK);
                        deferredResult.setResult(responseEntity);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        log.error("Failed to fetch result from remote service", t);
                        ResponseEntity<Void> responseEntity =
                            new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
                        deferredResult.setResult(responseEntity);
                    }
                }
        );
        return deferredResult;
    }
}
