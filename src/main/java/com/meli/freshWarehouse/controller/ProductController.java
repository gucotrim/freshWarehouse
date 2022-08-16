package com.meli.freshWarehouse.controller;


import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.dto.ProductPurchaseDto;
import com.meli.freshWarehouse.dto.PurchaseOrderDto;
import com.meli.freshWarehouse.dto.WarehouseProductResponseDTO;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.exception.SectionIdNotFoundException;
import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.service.IProductService;
import com.meli.freshWarehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Saves a new product
     * @return Saves a new product, returns an exception if a product ID isn't found.
     * @throws NotFoundException When a product doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/product">Saves a product</a>
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDTO newProductDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(newProductDTO));
    }

    /**
     * Get all products
     * @return Returns a list with all saved products.
     * @see <a href="http://localhost:8080/api/v1/product">Get all products</a>
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    /**
     * Gets product by id
     *
     * @return Product with the designed iD.
     * @throws NotFoundException When a product doesn't exist with the informed ID.
     * @see <a href="http://localhost:8080/api/vi/product/{id}">Get the product by id</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }

    @GetMapping("{id}/list-batch")
    public ResponseEntity<WarehouseProductResponseDTO> getProductInAllBatches(@PathVariable Long id,
                                                                              @RequestParam(value = "idSection") Long idSection,
                                                                              @RequestParam(value = "filter", required = false) String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductInAllBatches(id,idSection,filter));
    }

    /**
     * Get a list of products by category.
     *
     * @param category - FS - Fresh; RS - Refrigerated; FF - Frozen
     * @return a products List
     * @throws SectionIdNotFoundException When a section doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/product/list?category=FS">Get Product list by category</a>
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProductPurchaseDto>> getProductsByCategory(
            @RequestParam String category) {
        return new ResponseEntity(productService.getProductByCategory(category), HttpStatus.OK);
    }

    /**
     * Updates a product by id
     *
     * @return Update the product with the informed id.
     * @throws NotFoundException When a product with the informed ID isn't found.
     * @see <a href="http://localhost:8080/api/vi/product">Update a product</a>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Valid ProductDTO productDto) {
        return ResponseEntity.ok(productService.update(id, productDto));
    }


    /**
     * Delete a product by id
     *
     * @return Delete the product with the informed id.
     * @throws NotFoundException When a product with the informed ID isn't found.
     * @see <a href="http://localhost:8080/api/vi/product">Delete a warehouse</a>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
