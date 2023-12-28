package me.app.coinwallet.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxyHelper;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@MappedSuperclass
public abstract class AbstractEntity extends AbstractAggregateRoot<AbstractEntity> implements Serializable {
    public static final String TRUE_FALSE_TYPE = "true_false";

    public abstract Object getId();

    @Version
    @NotNull
    @Column(name = "VERSION", nullable = false, length = 10)
    private int version = 0;

    @Transient
    private boolean transientHashCodeLeaked = false;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + getId() + ")";
    }

    public boolean isPersisted() {
        return getId() != null;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        /*
         * The following is a solution that works for hibernate lazy loading proxies.
         */
        if (HibernateProxyHelper.getClassWithoutInitializingProxy(this) != HibernateProxyHelper.getClassWithoutInitializingProxy(obj)) {
            return false;
        }

        AbstractEntity other = (AbstractEntity) obj;
        if (isPersisted() && other.isPersisted()) { // both entities are not new
            return getId().equals(other.getId());
        }

        return false;
    }

    @Override
    public int hashCode() {
        if (!isPersisted()) { // is new or is in transient state.
            transientHashCodeLeaked = true;
            return -super.hashCode();
        }

        // because hashcode has just been asked for when the object is in transient state at that time super.hashCode() is returned.
        // Now for consistency, we return the same value.
        if (transientHashCodeLeaked) {
            return -super.hashCode();
        }

        // The above mechanism obey the rule: if 2 objects are equal, their hashcode must be same.
        return getId().hashCode();
    }
}
