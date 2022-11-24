package ru.tpu.labs.controller;

import ru.tpu.labs.model.Product;
import ru.tpu.labs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> GetAllProduct(){
        return productService.findAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product product){
        return productService.saveOrUpdateProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/byname")
    public List<Product> getProductByName(@RequestParam(name= "name")String name){
        return productService.findAllByName(name);
    }

    @GetMapping("/byprice")
    public List<Product> getProductByPrice(@RequestParam(name = "price")Double price){
        return productService.findProductByPrice(price);
    }
}
