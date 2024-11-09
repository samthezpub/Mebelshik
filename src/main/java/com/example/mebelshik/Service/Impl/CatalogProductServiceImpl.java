package com.example.mebelshik.Service.Impl;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Entitiy.Category;
import com.example.mebelshik.Exception.CatalogProductNotFoundException;
import com.example.mebelshik.Exception.CategoryNotFoundException;
import com.example.mebelshik.Repository.CatalogRepository;
import com.example.mebelshik.Repository.CategoryRepository;
import com.example.mebelshik.Repository.ProductFilterRepository;
import com.example.mebelshik.Service.CatalogProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CatalogProductServiceImpl implements CatalogProductService {

    private final CatalogRepository catalogRepository;
    private final CategoryRepository categoryRepository;
    private final ProductFilterRepository productFilterRepository;

    @Override
    public void createCatalogProduct(CatalogProduct catalogProduct) {
        catalogRepository.save(catalogProduct);
    }

    @Override
    public List<CatalogProduct> findAll() {
        return catalogRepository.findAll();
    }


    @Override
    public CatalogProduct findCatalogProduct(Long id) throws CatalogProductNotFoundException {
        return catalogRepository.findById(id).orElseThrow(()-> new CatalogProductNotFoundException("Товар не найден"));
    }

    @Override
    public CatalogProduct findCatalogProductBySlug(String slug) throws CatalogProductNotFoundException {
        return catalogRepository.findCatalogProductBySlug(slug).orElseThrow(()->new CatalogProductNotFoundException("Продукт по slug не найден!"));
    }

    @Override
    public List<CatalogProduct> findCatalogProductsByCategorySlug(String slug) throws CatalogProductNotFoundException, CategoryNotFoundException {
        Category category = categoryRepository.findCategoryBySlug(slug).orElseThrow(() -> new CategoryNotFoundException("Категория не найдена!"));

        if (category.getIsMainCategory()){
            List<CatalogProduct> resultProducts = new ArrayList<>();
            resultProducts.addAll(catalogRepository.findCatalogProductsByCategorySlug(slug));
            for(Category subcategory: category.getSubCategories()){
                resultProducts.addAll(findCatalogProductsByCategorySlug(subcategory.getSlug()));
            }

            return resultProducts;
        }

        return catalogRepository.findCatalogProductsByCategorySlug(slug);


    }

    @Override
    public List<CatalogProduct> findCatalogProductsByCategoryId(Long category_id) {
        return catalogRepository.findAllByCategory_Id(category_id);
    }

    @Override
    public List<CatalogProduct> searchByName(String name) {
        return catalogRepository.findByNameContainingIgnoreCase(name);
    }

    public List<CatalogProduct> findCatalogProductsByFilters(List<Long> filterIds, List<String> values, Long filterCount) {
        return productFilterRepository.findByFilters(filterIds, values, filterCount);
    }

    @Override
    public void updateCatalogProduct(CatalogProduct catalogProduct) {
        catalogRepository.save(catalogProduct);
    }

    @Override
    public void deleteCatalogProduct(Long id) {
        catalogRepository.deleteById(id);
    }

    @Override
    public String generateKeywords(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("купить " + name + " б у мариуполь, ");
        stringBuilder.append("купить " + name + " в мариуполе, ");
        stringBuilder.append("купить " + name + " в мариуполе бу, ");
        stringBuilder.append("купить " + name + " мариуполь, ");
        stringBuilder.append("купить " + name + " мариуполь днр, ");
        stringBuilder.append("купить " + name + " на заказ в мариуполе, ");
        stringBuilder.append("озон " + name + " купить, ");
        stringBuilder.append(name + " цена, ");
        stringBuilder.append(name);
        stringBuilder.append("сколько стоит " + name + " в мариуполе, ");
        stringBuilder.append("сколько стоит " + name + " в мариуполе днр");


        return stringBuilder.toString();
    }

    // Метод транслитерации
    private String transliterate(String text) {
        Map<Character, String> translitMap = new HashMap<>();
        translitMap.put('а', "a"); translitMap.put('б', "b"); translitMap.put('в', "v");
        translitMap.put('г', "g"); translitMap.put('д', "d"); translitMap.put('е', "e");
        translitMap.put('ё', "e"); translitMap.put('ж', "zh"); translitMap.put('з', "z");
        translitMap.put('и', "i"); translitMap.put('й', "y"); translitMap.put('к', "k");
        translitMap.put('л', "l"); translitMap.put('м', "m"); translitMap.put('н', "n");
        translitMap.put('о', "o"); translitMap.put('п', "p"); translitMap.put('р', "r");
        translitMap.put('с', "s"); translitMap.put('т', "t"); translitMap.put('у', "u");
        translitMap.put('ф', "f"); translitMap.put('х', "kh"); translitMap.put('ц', "ts");
        translitMap.put('ч', "ch"); translitMap.put('ш', "sh"); translitMap.put('щ', "shch");
        translitMap.put('ъ', ""); translitMap.put('ы', "y"); translitMap.put('ь', "");
        translitMap.put('э', "e"); translitMap.put('ю', "yu"); translitMap.put('я', "ya");

        StringBuilder transliterated = new StringBuilder();
        for (char c : text.toLowerCase().toCharArray()) {
            transliterated.append(translitMap.getOrDefault(c, String.valueOf(c)));
        }
        return transliterated.toString();
    }

    // Метод для генерации slug
    public String generateSlug(String name) {
        // Транслитерация имени
        String transliterated = transliterate(name);

        // Преобразование в slug
        String slug = transliterated
                .toLowerCase()
                .replaceAll("\\s+", "-")        // Заменяем пробелы на дефисы
                .replaceAll("[^a-z0-9\\-]", "") // Удаляем нежелательные символы
                .replaceAll("-+", "-");         // Убираем повторяющиеся дефисы

        // Проверка на уникальность slug
        String uniqueSlug = slug;
        int count = 1;
        while (catalogRepository.existsBySlug(uniqueSlug)) {
            uniqueSlug = slug + "-" + count++;
        }
        return uniqueSlug;
    }
}
