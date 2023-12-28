package me.app.coinwallet.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.util.ArrayUtils;
import me.app.coinwallet.exception.RTException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Filter;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Convenient utilities for working with JPA/Hibernate. The annotation @Component here is only used for autowiring the
 * {@link EntityManager} em in the static and then we can use it.
 */
@Component
public class JpaUtil {
    @PersistenceContext
    private EntityManager em;

    /**
     * Enables a Hibernate's fetch profile to customize data loading.
     *
     * @param profileName Name of the profile to be turned on.
     */
    public void enableFetchProfile(String profileName) {
        em.unwrap(Session.class).enableFetchProfile(profileName);
    }

    /**
     * Disable an enabled Hibernate's fetch profile.
     *
     * @param profileName Name of the profile to be turned off.
     */
    public void disableFetchProfile(String profileName) {
        em.unwrap(Session.class).disableFetchProfile(profileName);
    }

    /**
     * Turns on Hibernate filter whose name is specified by <code>filterName</code> with the input
     * <code>params</code>.
     *
     * @param filterName Name of the filter to be enabled.
     * @param params     Parameters to be used for the filter.
     */
    public void enableFilter(String filterName, Map<String, Object> params) {
        Filter filter = em.unwrap(Session.class).enableFilter(filterName);
        for (Map.Entry<String, Object> param : params.entrySet()) {
            filter.setParameter(param.getKey(), param.getValue());
        }
    }

    public void disableFilter(String filterName) {
        em.unwrap(Session.class).disableFilter(filterName);
    }

    /**
     * Initializes an Hibernate entity by touching its graph. Queries generated by this touch will
     * therefore fully respect what have been configured at entity mapping level.
     *
     * @param result       The entity itself.
     * @param associations List of associations to be initialized.
     * @return The entity (for convenient coding).
     */
    public static <T> T initialize(T result, Path<?>... associations) {
        if (result == null) {
            return null;
        }
        for (Path<?> association : associations) {
            // Small trick to remove all any(*) from the association string.
            String originalAssociation = association.toString();
            int countMatches = StringUtils.countMatches(originalAssociation, "any");
            for (int i = 0; i < countMatches; i++) {
                originalAssociation = originalAssociation.replaceAll("any\\((.*)\\)", "$1");
            }
            String[] paths = StringUtils.split(StringUtils.replace(originalAssociation, "_", "."), ".");
            initializeInternal(result, StringUtils.join(ArrayUtils.subarray(paths, 1, paths.length), "."));
        }
        return result;
    }

    public static <T> List<T> initializeList(List<T> result, Path<?>... associations) {
        if (result == null) {
            return Collections.emptyList();
        }
        result.forEach(r -> initialize(r, associations));
        return result;
    }

    private static void initializeInternal(Object result, String association) {
        try {
            String[] paths = StringUtils.split(association, ".");
            if (paths.length <= 0) {
                return;
            }

            BeanInfo bi = Introspector.getBeanInfo(result.getClass());
            PropertyDescriptor[] mds = bi.getPropertyDescriptors();
            for (PropertyDescriptor md : mds) {
                if (!paths[0].equals(md.getName())) {
                    continue;
                }

                Object newObj = md.getReadMethod().invoke(result);
                if (newObj == null) {
                    continue;
                }

                Hibernate.initialize(newObj);

                if (paths.length > 1) {
                    if (Collection.class.isAssignableFrom(newObj.getClass())) {
                        for (Object item : Collection.class.cast(newObj)) {
                            initializeInternal(item, StringUtils.join(paths, ".", 1, paths.length));
                        }
                    } else {
                        initializeInternal(newObj, StringUtils.join(paths, ".", 1, paths.length));
                    }
                }
            }
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RTException("Error occurred while initializing entity " + result, e);
        }
    }

    public void detach(Object object) {
        em.detach(object);
    }

    public void refresh(Object object){
        em.refresh(object);
    }

    public void flush() {
        em.flush();
    }

    public void flushAndClearSession() {
        em.flush();
        em.clear();
    }

    /**
     * handle 'in' condition with the list data has more than 1000 elements
     * @param path
     * @param list
     * @param <T>
     * @return
     */
    public static <T> BooleanBuilder in(SimpleExpression<T> path, Collection<T> list) {
        BooleanBuilder builder = new BooleanBuilder();
        if (!list.isEmpty()) {
            for (List<T> chunk : Lists.partition(Lists.newArrayList(list), 1000)) {
                builder.or(path.in(chunk));
            }
            return builder;
        } else {
            return builder.and(path.in(list));
        }
    }
}

