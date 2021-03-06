package com.clone.spring.crud.service;

import com.clone.spring.crud.dto.ProductRepository;
import com.clone.spring.crud.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public void save (Product product){

        productRepo.save(product);
    }

    public void  saveProductToDB(MultipartFile file, Product product)
    {
//        Product p = new Product();
//        product.setId("12");
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a valid file");
        }
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        //p.setDescription(description);
//
//        p.setName(name);
//        //p.setPrice(price);

        productRepo.save(product);
    }

    public List<Product> getAllProduct()

    {
        return productRepo.findAll();
    }

    public void deleteProductById(Long id)

    {
        productRepo.deleteById(id);
    }

    public void changeProductName(Long id ,String name)
    {
        Product p = new Product();
        p = productRepo.findById(id).get();
        p.setName(name);
        productRepo.save(p);
    }

    public void deleteById(long id) {
    }
}
