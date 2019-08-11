package com.brasilPrev.cadastro.clienteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;

import com.brasilPrev.cadastro.model.Categorias;
import com.brasilPrev.cadastro.model.Clientes;
import com.brasilPrev.cadastro.model.Pedidos;
import com.brasilPrev.cadastro.model.Produtos;

public class ProdutoService {

    private static final String GROUP   = "produto";
    private static final int    TIMEOUT = 60000;
    private final Produtos      produtoService;
    private final Pedidos       pedidosService;
    private final Clientes      clienteService;
    private final Categorias    categoriaService;

    @Autowired
    public ProdutoService(Produtos produtoService, Pedidos pedidosService,
                          Clientes clientesService, Categorias categoriaService) {
        this.produtoService = produtoService;
        this.pedidosService = pedidosService;
        this.clienteService = clientesService;
        this.categoriaService = categoriaService;
    }

    public <Map> Object getProductSummary(String productId) {
        <List> ArrayList callables = new ArrayList<>();
        callables.add(new BackendServiceCallable("details", getProductSummary(productId));
        callables.add(new BackendServiceCallable("pricing", getProductPricing1(productId)));
        return doBackendAsyncServiceCall(callables);
    }

    private Object getProductPricing1(String productId) {
        // TODO Auto-generated method stub
        return null;
    }

    public Map> getProduct(String productId) {
        List> callables = new ArrayList<>();
        callables.add(new BackendServiceCallable("details", getProductDetails(productId)));
        callables.add(new BackendServiceCallable("pricing", getProductPricing1(productId)));
        callables.add(new BackendServiceCallable("ratings", getProductRatings(productId)));
        callables.add(new BackendServiceCallable("reviews", getProductReviews(productId)));
        return doBackendAsyncServiceCall(callables);
    }

    private static Map> doBackendAsyncServiceCall(List> callables) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        try {
            List> futures = executorService.invokeAll(callables);
            executorService.shutdown();
            executorService.awaitTermination(TIMEOUT, TimeUnit.MILLISECONDS);
            Map> result = new HashMap<>();
            for (Future future : futures) {
                AsyncResponse response = future.get();
                result.put(response.serviceKey, response.response);
            }
            return result;
        } catch (InterruptedException|ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @Cacheable
    private HystrixCommand>

    getProductDetails(String productId) {
        return new HystrixCommand>(
                HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(GROUP))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("getProductDetails"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationThreadTimeoutInMilliseconds(TIMEOUT)
                        )
        ) {

    @Override
    protected Map run() throws Exception {
        return productDetailService.getDetails(productId);
    }

    @Override
    protected Map getFallback() {
        return new HashMap<>();
    }
    };}private HystrixCommand>

    getProductPricing(String productId) {
        // ... veja getProductDetails() ...
    }

    private HystrixCommand> getProductRatings(String productId) {
        // ... veja getProductDetails() ...
    }

    private HystrixCommand> getProductReviews(String productId) {
        // ... veja getProductDetails() ...
    }

    private static class AsyncResponse {

        private final String serviceKey;
        private final Map    response;

        AsyncResponse(String serviceKey, Map response) {
            this.serviceKey = serviceKey;
            this.response = response;
        }
    }

    private static class BackendServiceCallable implements Callable {

        private final String serviceKey;

        private final HystrixCommand> hystrixCommand;
        public BackendServiceCallable(String serviceKey, Object object, HystrixCommand> hystrixCommand) {
            this.serviceKey = serviceKey;
            this.hystrixCommand = hystrixCommand;
        }

        @Override
        public AsyncResponse call() throws Exception {
            return new AsyncResponse(serviceKey, hystrixCommand.execute());
        }
    }

}
