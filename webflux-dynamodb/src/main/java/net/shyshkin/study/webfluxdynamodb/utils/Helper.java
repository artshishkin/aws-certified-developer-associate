package net.shyshkin.study.webfluxdynamodb.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Helper {

    public static String[] getNullPropertyNames(Object source) {
        return getPropertyNames(source, Objects::isNull);
    }

    public static String[] getNonNullPropertyNames(Object source) {
        return getPropertyNames(source, Objects::nonNull);
    }

    private static String[] getPropertyNames(Object source, Predicate<Object> predicate) {

        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> predicate.test(wrappedSource.getPropertyValue(propertyName)))
                .toArray(String[]::new);
    }
}
