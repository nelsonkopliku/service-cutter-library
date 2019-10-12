package ch.hsr.servicecutter.model.usersystem;

import com.google.common.base.Objects;

public class Nanoentity {

    private Long id;
    private String name;
    private String context;
    private UserSystem userSystem;

    public Nanoentity() {
    }

    public Nanoentity(final String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(final String context) {
        this.context = context;
    }

    public String getContextName() {
        return ((context != null && !"".equals(context.trim())) ? context + "." : "") + name;
    }

    public UserSystem getUserSystem() {
        return userSystem;
    }

    public void setUserSystem(final UserSystem userSystem) {
        this.userSystem = userSystem;
    }

    @Override
    public String toString() {
        return getContextName();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(context, name);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Nanoentity) {
            Nanoentity other = (Nanoentity) obj;
            return this == other || (Objects.equal(name, other.name) && Objects.equal(context, other.context));
        } else {
            return false;
        }
    }

}