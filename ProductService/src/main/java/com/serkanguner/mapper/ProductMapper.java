package com.serkanguner.mapper;

import com.serkanguner.dto.request.TokenReturnDto;
import com.serkanguner.dto.response.MenuItemResponseDto;
import com.serkanguner.entity.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

/*    @Mapping(target = "componentId", source = "componentId")
    @Mapping(target = "optionId", source = "optionId")
    MenuItemResponseDto menuItemToDto(MenuItem menuItem);*/


}
