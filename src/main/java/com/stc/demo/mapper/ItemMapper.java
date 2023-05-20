package com.stc.demo.mapper;

import com.stc.demo.dto.ItemDto;
import com.stc.demo.entities.Item;
import org.mapstruct.Mapper;

@Mapper
public interface ItemMapper {

    ItemDto fromItemToItemDto(Item item);
}
