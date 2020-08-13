package com.chozoi.productservice.domain.services;

import com.chozoi.productservice.app.dtos.*;
import com.chozoi.productservice.domain.ModelMapper;
import com.chozoi.productservice.domain.caches.CacheManager;
import com.chozoi.productservice.domain.entities.GenerateKey;
import com.chozoi.productservice.domain.entities.TokenInfo;
import com.chozoi.productservice.domain.entities.answer.Answer;
import com.chozoi.productservice.domain.entities.categories.Categories;
import com.chozoi.productservice.domain.entities.categories.CategoriesAttribute;
import com.chozoi.productservice.domain.entities.categories.CategoriesAttributeValue;
import com.chozoi.productservice.domain.entities.product.Product;
import com.chozoi.productservice.domain.entities.questions.Question;
import com.chozoi.productservice.domain.entities.rate.Rate;
import com.chozoi.productservice.domain.exception.NotFoundException;
import com.chozoi.productservice.domain.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BaseServices {

    @Autowired protected ProductRepository productRepository;

    @Autowired protected QuestionRepository questionRepository;

    @Autowired protected CategoriesRepository categoriesRepository;

    @Autowired protected AnswerRepository answerRepository;

    @Autowired protected RatesRepository ratesRepository;

    @Autowired protected AttributeValueRepository valueRepository;

    @Autowired protected AttributeRepository attributeRepository;

    @Autowired protected CacheManager cacheManager;

    @Autowired protected ModelMapper modelMapper;

    protected String genCategoryIdCacheKey(String accessToken){
        String cacheKey = GenerateKey.genCategoryKey(accessToken);
        return cacheKey;
    }

    protected Long hasCategoryToken(String accessToken) {
        String key = genCategoryIdCacheKey(accessToken);
        TokenInfo info = cacheManager.get(key);
        Long categoryId = Long.parseLong(info.getInfo());

        if (categoryId != null) return categoryId;
        else return null;
    }

    protected Long hasUserToken(String accessToken) {
        String key = GenerateKey.genUserKey(accessToken);
        TokenInfo info = cacheManager.get(key);

        if (Objects.isNull(info)){
            throw new NotFoundException("Times out! Sign in again to continue");
        }

        Long userId = Long.parseLong(info.getInfo());
        return userId;
    }

    protected Categories findCategoriesById(long categoryId) {
        return categoriesRepository.findById(categoryId).orElse(null);
    }

    protected List<ProductDTO> convertProductToDTOs(List<Product> models) {
        List<ProductDTO> responses = new ArrayList<>();

        List<QuestionDTO> questionDTOs;

        if (models.size() > 0) {

            for (Product product : models) {
                questionDTOs = convertToQuestionsDTOs(questionRepository.findAllByProduct(product));

                ProductDTO productDTO = modelMapper.productToResponse(product);
                productDTO.setQuestions(questionDTOs);

                responses.add(productDTO);
            }

            return responses;
        }else return null;
    }

    protected List<AttributeDTO> convertAttributeToDTOs(List<CategoriesAttribute> models) {
        List<AttributeDTO> responses = new ArrayList<>();

        if (models.size() > 0) {

            for (CategoriesAttribute attribute : models) {

                AttributeDTO dto = modelMapper.categoriesAttributeToResponse(attribute);

                responses.add(dto);
            }

            return responses;
        }else return null;
    }

    protected List<AttributeValueDTO> convertValueToDTOs(List<CategoriesAttributeValue> models) {
        List<AttributeValueDTO> responses = new ArrayList<>();

        if (models.size() > 0) {

            for (CategoriesAttributeValue value : models) {
                AttributeValueDTO dto = modelMapper.attributeValueToResponse(value);

                responses.add(dto);
            }

            return responses;
        }else return null;
    }

    protected List<CategoriesDTO> convertToCategoriesDTOs(List<Categories> models) {
        List<CategoriesDTO> responses = new ArrayList<>();

        if (models.size() > 0) {
            for (Categories categories : models) {
                if (categories.getLevel() == 1){
                    CategoriesDTO categoriesDTO = modelMapper.categoriesToResponse(categories);

                    responses.add(categoriesDTO);
                }
            }

            return responses;
        }else return null;
    }

    protected List<RateDTO> convertToRateDTOs(List<Rate> models) {
        List<RateDTO> responses = new ArrayList<>();

        if (models.size() > 0) {

            for (Rate rate : models) {

                RateDTO rateDTO = modelMapper.rateToResponse(rate);

                responses.add(rateDTO);
            }

            return responses;
        }else return null;
    }

    protected List<QuestionDTO> convertToQuestionsDTOs(List<Question> models) {
        List<QuestionDTO> responses = new ArrayList<>();

        if (models.size() > 0) {

            for (Question question : models) {
                QuestionDTO questionDTO = modelMapper.questionToResponse(question);

                responses.add(questionDTO);
            }

            return responses;
        }else return null;
    }

    protected List<AnswerDTO> convertToAnswersDTOs(List<Answer> models) {
        List<AnswerDTO> responses = new ArrayList<>();

        if (models.size() > 0) {

            for (Answer answer : models) {

                AnswerDTO answerDTO = modelMapper.answerToResponse(answer);

                responses.add(answerDTO);
            }

            return responses;
        }else return null;
    }

}
