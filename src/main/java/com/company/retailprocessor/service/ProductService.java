package com.company.retailprocessor.service;

import com.company.retailprocessor.dto.AddProductDto;
import com.company.retailprocessor.enums.Category;
import com.company.retailprocessor.exception.NoSuchElementFoundException;
import com.company.retailprocessor.model.Product;
import com.company.retailprocessor.repository.ProductRepository;
import com.company.retailprocessor.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.AttributeNotFoundException;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private ProductUtil productUtil;

    @Autowired
    private ProductRepository productRepository;

    /***
     * To add new product
     * @param addProductDto
     */
    public boolean addProduct(AddProductDto addProductDto)
    {
        Product product = new Product(addProductDto.getName().trim().toLowerCase(), addProductDto.getDescription().trim().toLowerCase(),
                addProductDto.getCost(), productUtil.getProductCategory(addProductDto.getCategory()));
        productRepository.save(product);
        return true;
    }

    /***
     * To fetch all the products
     * @return List<Product>
     */
    public List<Product> fetchAll()
    {
        return productRepository.findAll();
    }

    /***
     * To delete product by name
     * @param name
     */
    public void deleteProduct(String name)
    {
        productRepository.deleteByName(name);
    }

    /***
     * To fetch product by product name
     * @param name
     * @return return list of products matches name
     */
    public Product fetchByName(String name)
    {
        return productRepository.findByName(name);
    }

    /***
     * Returns a product provided productid
     * @param id
     * @return Product object
     */
    public Product fetchById(String id)
    {
        return productRepository.findByProductId(id);
    }

    public boolean isProductPresent(String productId)
    {
        return productRepository.findByProductId(productId) != null;
    }
}
