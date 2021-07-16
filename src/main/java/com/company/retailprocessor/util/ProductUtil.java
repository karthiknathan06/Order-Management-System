package com.company.retailprocessor.util;

import com.company.retailprocessor.enums.Category;
import org.springframework.stereotype.Service;

@Service
public class ProductUtil
{
    /***
     * To get category from user string
     * @param category
     * @return Category
     */
    public Category getProductCategory(String category)
    {
        Category type;
        switch(category.trim().toLowerCase())
        {
            case "furnitures":  type = Category.FURNITURES;
                                break;
            case "books":       type = Category.BOOKS;
                                break;
            case "machineries": type = Category.MACHINERIES;
                                break;
            default:            type = Category.OTHERS;
                                break;
        }
        return type;
    }
}
