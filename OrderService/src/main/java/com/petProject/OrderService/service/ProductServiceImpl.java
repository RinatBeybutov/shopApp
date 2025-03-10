package com.petProject.OrderService.service;

import com.petProject.OrderService.dto.ProductCreateDto;
import com.petProject.OrderService.dto.ProductFilterDto;
import com.petProject.OrderService.dto.ProductViewDto;
import com.petProject.OrderService.entity.CategoryEntity_;
import com.petProject.OrderService.entity.ProductEntity;
import com.petProject.OrderService.entity.ProductEntity_;
import com.petProject.OrderService.mapper.ProductMapper;
import com.petProject.OrderService.repository.CategoryRepository;
import com.petProject.OrderService.repository.ProductRepository;
import com.petProject.OrderService.repository.ProductToOrderRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductToOrderRepository productToOrderRepository;

    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductViewDto> getProducts(ProductFilterDto filter) {
        if (filter == null) {
            filter = new ProductFilterDto();
        }
        return productRepository.findAll(specificationFor(filter))
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ProductViewDto create(ProductCreateDto dto) {
        var categoryEntity = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Not found category with id = " + dto.getCategoryId()));

        var entity = productMapper.toEntity(dto);
        entity.setCategory(categoryEntity);

        entity = productRepository.save(entity);

        return productMapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductViewDto> getAllByCategoryId(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    private Specification<ProductEntity> specificationFor(ProductFilterDto filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            var categoryPredicate = getCategoryPredicate(filter, root, criteriaBuilder);
            predicates.add(categoryPredicate);

            var namePredicate = getNamePredicate(filter, root, criteriaBuilder);
            predicates.add(namePredicate);

            return criteriaBuilder.and(
                    getNotNullPredicates(predicates)
            );
        };
    }

    private Predicate[] getNotNullPredicates(List<Predicate> predicates) {
        return predicates.stream()
                .filter(Objects::nonNull)
                .toList()
                .toArray(Predicate[]::new);
    }

    private Predicate getCategoryPredicate(ProductFilterDto filter, Root<ProductEntity> root, CriteriaBuilder criteriaBuilder) {
        var categoryJoin = root.join(ProductEntity_.category, JoinType.LEFT);
        if (filter.getCategoryUuid() != null) {
            return criteriaBuilder.equal(categoryJoin.get(CategoryEntity_.UUID), filter.getCategoryUuid());
        }
        return null;
    }

    private Predicate getNamePredicate(ProductFilterDto filter, Root<ProductEntity> root, CriteriaBuilder criteriaBuilder) {
        if (filter.getName() != null) {
            return criteriaBuilder.equal(root.get(ProductEntity_.NAME), filter.getName());
        }
        return null;
    }
}
