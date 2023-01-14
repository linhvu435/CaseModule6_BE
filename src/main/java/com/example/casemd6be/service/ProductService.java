package com.example.casemd6be.service;

import com.example.casemd6be.model.*;
import com.example.casemd6be.repository.manh.IProductRepoM;
import com.example.casemd6be.service.linh.CategoryService;
import com.example.casemd6be.service.linh.ImgProductService;
import com.example.casemd6be.service.linh.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final IProductRepoM iProductRepoM;
    private final CategoryService categoryService;
    private final ShopService shopService;
    private final ImgProductService imgProductService;

    public Product save(CreateProductRequest request) {
        Category category = categoryService.findById(request.getCategoryId());
        Shop shop = shopService.findById(request.getShopId());

        Product toSave = Product.builder()
                .name(request.getName())
                .detail(request.getDetail())
                .price(request.getPrice())
                .amount(request.getAmount())
                .category(category)
                .shop(shop)
                .img(request.getImgs().get(0))
                .build();

        Product saved = iProductRepoM.save(toSave);
        List<ImgProduct> imgProductList = new ArrayList<>();
        for (String img : request.getImgs()) {
            imgProductList.add(ImgProduct.builder().product(saved).name(img).build());
        }
        imgProductService.saveImgProduct(imgProductList);
        return saved;
    }

    public Iterable<Product> getAll() {
        return iProductRepoM.findAll();
    }

    public void delete(long id) {
        iProductRepoM.deleteById(id);
    }

    public Product finByID(long id) {
        return iProductRepoM.findById(id).get();
    }
    public List<Product> getProductByShopId(long id){
        return iProductRepoM.findProductByShopId(id);
    }

    public Long FindMaxIdProduct(){
      return  iProductRepoM.FindMaxIdProduct();
    }

    public Product findProductById(long id){
        return iProductRepoM.findById(id).get();
    }
}
