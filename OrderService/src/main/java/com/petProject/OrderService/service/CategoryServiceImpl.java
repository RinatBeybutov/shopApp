package com.petProject.OrderService.service;

import com.petProject.OrderService.dto.CategoryCreateDto;
import com.petProject.OrderService.dto.CategoryViewDto;
import com.petProject.OrderService.mapper.CategoryMapper;
import com.petProject.OrderService.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public List<CategoryViewDto> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public CategoryViewDto create(CategoryCreateDto dto) {
        var entity = categoryMapper.toEntity(dto);
        entity = categoryRepository.save(entity);
        return categoryMapper.toDto(entity);
    }
}
