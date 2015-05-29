package org.springframework.examples.northwind.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.examples.northwind.model.Product;
import org.springframework.examples.northwind.service.ProductBo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@RestController
@Log4j2
class ProductsAsyncController {

    @Autowired
    private ProductBo productBo;

    //Find product by Id
    @RequestMapping("/person/{id}")
    DeferredResult<ResponseEntity<?>> findProdAsync(@PathVariable("id") Integer id) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        ListenableFuture<Product> repositoryListDto = productBo.findByProductId(id);
        repositoryListDto.addCallback(
                new ListenableFutureCallback<Product>() {
                    @Override
                    public void onSuccess(Product result) {
                        ResponseEntity<Product> responseEntity =
                            new ResponseEntity<>(result, HttpStatus.OK);
                        deferredResult.setResult(responseEntity);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        log.error("Failed to fetch result from database", t);
                        ResponseEntity<Void> responseEntity =
                            new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
                        deferredResult.setResult(responseEntity);
                    }
                }
        );
        return deferredResult;
    }

    //Find product by Id
    @RequestMapping(value="/persons")
    DeferredResult<ResponseEntity<?>> findAllProdAsync() {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        ListenableFuture<List<Product>> repositoryListDto = productBo.findAllProducts();
        repositoryListDto.addCallback(
                new ListenableFutureCallback<List<Product>>() {
                    @Override
                    public void onSuccess(List<Product> result) {
                        ResponseEntity<List<Product>> responseEntity =
                                new ResponseEntity<>(result, HttpStatus.OK);
                        deferredResult.setResult(responseEntity);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        log.error("Failed to fetch result from database", t);
                        ResponseEntity<Void> responseEntity =
                                new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
                        deferredResult.setResult(responseEntity);
                    }
                }
        );
        return deferredResult;
    }

    //Delete product by Id
    @RequestMapping(value="/person/{id}",method= RequestMethod.DELETE)
    DeferredResult<ResponseEntity<?>> deleteProd(@PathVariable("id") Integer id) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        ListenableFuture<Boolean> repositoryListDto = productBo.delete(id);
        repositoryListDto.addCallback(
                new ListenableFutureCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        ResponseEntity<String> responseEntity =
                                new ResponseEntity<>("Operation successful", HttpStatus.OK);
                        deferredResult.setResult(responseEntity);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        log.error("Failed to fetch result from database", t);
                        ResponseEntity<Void> responseEntity =
                                new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
                        deferredResult.setResult(responseEntity);
                    }
                }
        );
        return deferredResult;
    }

    //Create product
    @RequestMapping(value="/person",method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    DeferredResult<ResponseEntity<?>> createProd(@RequestBody Product reqProd) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        ListenableFuture<Boolean> repositoryListDto = productBo.save(reqProd);
        repositoryListDto.addCallback(
                new ListenableFutureCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        ResponseEntity<String> responseEntity =
                                new ResponseEntity<>("Operation successful", HttpStatus.OK);
                        deferredResult.setResult(responseEntity);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        log.error("Failed to fetch result from database", t);
                        ResponseEntity<Void> responseEntity =
                                new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
                        deferredResult.setResult(responseEntity);
                    }
                }
        );
        return deferredResult;
    }

    //Update product
    @RequestMapping(value="/person",method= RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    DeferredResult<ResponseEntity<?>> updatePRod(@RequestBody Product reqProd) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        ListenableFuture<Boolean> repositoryListDto = productBo.update(reqProd);
        repositoryListDto.addCallback(
                new ListenableFutureCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        ResponseEntity<String> responseEntity =
                                new ResponseEntity<>("Update Operation successful", HttpStatus.OK);
                        deferredResult.setResult(responseEntity);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        log.error("Failed to fetch result from database", t);
                        ResponseEntity<Void> responseEntity =
                                new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
                        deferredResult.setResult(responseEntity);
                    }
                }
        );
        return deferredResult;
    }
}
