package me.app.coinwallet.repository.impl;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import me.app.coinwallet.entity.AbstractEntity;
import me.app.coinwallet.util.JpaUtil;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class AbstractRepositoryImpl<T extends AbstractEntity> implements InitializingBean {
    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    protected JpaUtil jpaUtil;

    protected EntityPath<T> path;
    protected PathBuilder<T> builder;
    protected Querydsl querydsl;
    protected JpaEntityInformation<T, String> entityInformation;

    @Override
    public void afterPropertiesSet() {
        if (getDomainClass() != null) {
            entityInformation = new JpaRepositoryFactory(em).getEntityInformation(getDomainClass());
            this.path = DEFAULT_ENTITY_PATH_RESOLVER.createPath(entityInformation.getJavaType());
            this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
            this.querydsl = new Querydsl(em, builder);
        }
    }

    protected <K> K queryWithFetchProfile(String profileName, Supplier<K> supplier) {
        try {
            jpaUtil.enableFetchProfile(profileName);
            return supplier.get();
        } finally {
            jpaUtil.disableFetchProfile(profileName);
        }
    }

    protected <K extends AbstractEntity> JPAQuery<K> selectFrom(EntityPath<K> path) {
        return new JPAQuery<K>(em).from(path);
    }

    public Optional<T> findOne(JPAQuery<T> path) {
        return Optional.ofNullable(path.fetchOne());
    }

    protected Class<T> getDomainClass() {
        return (Class<T>) TypeUtils.getTypeArguments(getClass(), AbstractRepositoryImpl.class)
                .values()
                .iterator().next();
    }

    protected Query createNativeQuery(String queryString) {
        return this.em.createNativeQuery(queryString);
    }
}

