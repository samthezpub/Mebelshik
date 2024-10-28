package com.example.mebelshik.Service.Impl;

import com.example.mebelshik.Entitiy.Filter;
import com.example.mebelshik.Exception.FilterNotFoundException;
import com.example.mebelshik.Repository.FilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl {

    private final FilterRepository filterRepository;

    // Получить все фильтры
    public List<Filter> getAllFilters() {
        return filterRepository.findAll();
    }

    public Filter getFilterById(Long id) throws FilterNotFoundException {
        return filterRepository.findById(id).orElseThrow(() -> new FilterNotFoundException("Такой фильтр не найден!"));
    }

    // Сохранить новый фильтр
    public Filter saveFilter(Filter filter) {
        return filterRepository.save(filter);
    }

    // Обновить существующий фильтр
    public Filter updateFilter(Long id, Filter filterDetails) {
        Filter filter = filterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Фильтр с ID " + id + " не найден"));

        filter.setFilterType(filterDetails.getFilterType());
        filter.setValues(filterDetails.getValues());

        return filterRepository.save(filter);
    }

    // Удалить фильтр
    public void deleteFilter(Long id) {
        filterRepository.deleteById(id);
    }
}
