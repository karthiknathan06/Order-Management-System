package com.company.retailprocessor.controller;

import com.company.retailprocessor.dto.AddProductDto;
import com.company.retailprocessor.model.Product;
import com.company.retailprocessor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("product")
@RestController
public class ProductController
{
    @Autowired
    private ProductService productService;

    /***
     * To add new product
     * @param addProductDto
     * @return ResponseEntity with message
     */
    @PostMapping("/")
    public ResponseEntity addProduct(@RequestBody @NotNull AddProductDto addProductDto)
    {
        if(productService.addProduct(addProductDto)) {
            return new ResponseEntity<>("Product Added", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Exception while adding product", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /***
     * To delete product
     * @param name
     * @return Response Entity with message
     */
    @DeleteMapping("/delete")
    public ResponseEntity deleteProduct(String name)
    {
        if(productService.fetchByName(name) != null)
        {
            productService.deleteProduct(name);
            return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("No Product Found", HttpStatus.NOT_FOUND);
    }

    /***
     * To fetch All Product
     * @return get List of product
     */
    @GetMapping("/")
    public List<Product> fetchProducts()
    {
        return productService.fetchAll();
    }

    /***
     * To get Product by name
     * @param name
     * @return
     */
    @GetMapping("/{name}")
    public Product fetchProductByName(@PathVariable String name)
    {

        return productService.fetchByName(name);
    }

}
