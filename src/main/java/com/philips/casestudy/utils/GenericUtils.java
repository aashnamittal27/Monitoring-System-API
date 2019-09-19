package com.philips.casestudy.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericUtils {

  private GenericUtils() {
  }

  public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
    if (clazz == null || c == null) {
      throw new NullPointerException("[ERROR] Null argument(s)");
    }

    final List<T> r = new ArrayList<>(c.size());
    for(final Object o: c) {
      r.add(clazz.cast(o));
    }
    return r;
  }
}