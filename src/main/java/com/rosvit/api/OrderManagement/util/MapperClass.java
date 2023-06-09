package com.rosvit.api.OrderManagement.util;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MapperClass {

    private MapperClass() {
    }

    public <T> T toObject(Object item, Class<T> clazz) {
        if (item == null) return null;
        ModelMapper mapper = new ModelMapper();
        return mapper.map(item, clazz);
    }

    public static <D, T> D converter(final T entity, Class<D> outClass) {
        if (ObjectUtils.isEmpty(entity))
            return null;

        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));

        try {
            return mapper.map(entity, outClass);
        } catch (Exception e) {
            Logger.getLogger(outClass.getName()).log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static <D, T> List<D> converter(final List<T> entity, Class<D> outClass) {
        if (ObjectUtils.isEmpty(entity))
            return Collections.emptyList();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setPropertyCondition(context -> (!(context.getSource() instanceof PersistentCollection)));

        List<D> l = new ArrayList<>(entity.size());
        entity.forEach(en -> {
            try {
                l.add(mapper.map(en, outClass));
            } catch (Exception e) {
                Logger.getLogger(outClass.getName()).log(Level.SEVERE, e.getMessage());
            }
        });

        return l;
    }

    public static <D, T> Set<D> converter(final Set<T> entity, Class<D> outClass) {
        if (ObjectUtils.isEmpty(entity))
            return Collections.emptySet();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));

        Set<D> l = new HashSet<D>(entity.size());
        entity.forEach(en -> {
            try {
                l.add(mapper.map(en, outClass));
            } catch (Exception e) {
                Logger.getLogger(outClass.getName()).log(Level.SEVERE, e.getMessage());
            }
        });
        return l;
    }

    public static <D, T> Page<D> converter(final Page<T> entity, Class<D> outClass) {
        if (ObjectUtils.isEmpty(entity))
            return null;

        return new PageImpl<>(MapperClass.converter(entity.getContent(), outClass), entity.getPageable(), entity.getTotalElements());
    }
}
