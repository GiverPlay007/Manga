package me.giverplay.manga.utils;

import java.util.function.BiConsumer;

public interface ExceptionBiConsumer<T, U> {
  void accept(T t, U u) throws Exception;
}
